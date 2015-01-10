package edu.odu.cs.cs350.yellow1.ui.cli;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.BuildException;

import edu.odu.cs.cs350.yellow1.ant.AntRunner;
import edu.odu.cs.cs350.yellow1.ant.BuildFileNotFoundException;
import edu.odu.cs.cs350.yellow1.ant.TargetNotFoundException;
import edu.odu.cs.cs350.yellow1.file.FileMover;
import edu.odu.cs.cs350.yellow1.file.FileMovingException;
import edu.odu.cs.cs350.yellow1.jar.ExecutionResults;
import edu.odu.cs.cs350.yellow1.jar.ExecutionState;
import edu.odu.cs.cs350.yellow1.jar.JarExecutor;
import edu.odu.cs.cs350.yellow1.jar.JarExecutorBuilder;
import edu.odu.cs.cs350.yellow1.jar.JarUtil;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile.EOL;
/**
 * Main running class for package jar
 * 
 * @author jberlin
 * @author jord
 */
public class Main {
	
	private static JavaFile a;
	private static FileMover fMover;
	private static JarUtil jar;
	private static AntRunner ant;
	private static JarExecutor jarExecutor;
	private static byte[] goldOrgContent;
	private static Path goldPath;
	private static Logger logger;
	private static File mutLog;
	private static File aliveLog;
	private static File logFolder;
	
	/**
	 * Parses CLI to recievedArgs and feeds user input params to mutate function
	 * @param args From user input
	 */
	public static void main(String[] args) {
		ClI commandLine = new ClI(args);
		try {
			commandLine.parse();
		} catch (RequireArgumentsNotRecieved e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		Map<String,String> recievedArgs = commandLine.getRecievedArguments();
		
		String srcFolder = recievedArgs.get("src");
		String fileToBeMutated = recievedArgs.get("mutF");
		String testSuitePath = recievedArgs.get("testSte");
		String goldOutput = recievedArgs.get("goldOut");
		
		try {
			mutate(srcFolder, fileToBeMutated, testSuitePath, goldOutput);
		} catch (BuildFileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
		
	}
	/**
	 * Main program function. Creates and runs mutants as well as logging and output files
	 * 
	 * @param srcFolder Source folder for project to be mutated
	 * @param fileToBeMutated Source file for project to be mutated
	 * @param testSuitePath Path for test suite
	 * @param goldOutput Original version output file
	 * @throws BuildFileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public static void mutate (String srcFolder, String fileToBeMutated, String testSuitePath, String goldOutput)throws BuildFileNotFoundException, IOException{
		
		//Step 1: Set up file directory paths for antRunner, jarExecutor, and fileMover
		
		logger = LogManager.getLogger(Main.class);
		mutLog = new File(
				srcFolder.toString() + File.separator + "mutantDir" + File.separator + "logs" + File.separator + "mutationsApplied.txt");
		aliveLog = new File (
				srcFolder.toString() + File.separator + "mutantDir" + File.separator + "logs" + File.separator + "mutationsAlive.txt");
		logFolder = new File(srcFolder.toString() + File.separator + "mutantDir" + File.separator + "logs");
		// ok because of the output on jenkins sanitize the logs directory
		if (logFolder.exists())
			if (logFolder.isDirectory())
				for (File f : logFolder.listFiles())
					f.delete();
		//give ant runer the project location
		ant  = new AntRunner(srcFolder);
		ant.requiresInit(true);
		//call setup
		ant.setUp();
		a = new JavaFile();
		
		//give the jarUtil the  directory where to expect the jar   the directory where to put the jar
		jar = new JarUtil((srcFolder + File.separator + "bin"), (srcFolder + File.separator + "mutantDir"));
		
		//get a file object to the original file
		File goldFile = new File(fileToBeMutated);
		goldPath = goldFile.toPath();
		//get the bytes from it for checking if applying mutation and restore works
		goldOrgContent = Files.readAllBytes(goldPath);
		
		
		File script = new File(srcFolder + File.separator + "compare.sh");
		//build the JarExecutor using the JarExecutor
		jarExecutor = JarExecutorBuilder
				.pathToJarDirectory(
						srcFolder + File.separator + "mutantDir")
				.pathToCompareScript(script.getAbsolutePath())
				.pathToLogDir(srcFolder + File.separator + "mutantDir" + File.separator + "logs")
				.pathToGold(goldOutput.toString())
				.withExecutionState(ExecutionState.multiFile)
				.withTestSuitePath(testSuitePath).create();
		File tDir = new File(srcFolder + File.separator + "mutantDir");	 
		 if(!tDir.exists())
			 tDir.mkdir();
		//Create a fileMover object give it the directory where mutations will be placed   the directory of the original file location
		fMover = new FileMover(srcFolder + File.separator + "mutantDir",                   fileToBeMutated);
		fMover.setNoDelete(true);
		try {
			
			 fMover.setUp();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			
		}
		
		//Step2: Create and run mutants
		
		
		try {
			a.readFile(fileToBeMutated);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		a.executeAll();
		int mutantsCreated = a.getMutantCaseVector().getSize();
		logger.info("Created "+mutantsCreated +" Mutants");
		for (int i = 0; i < a.getMutantCaseVector().getSize(); i++) {
			a.getMutantCaseOperations(i).writeMutation(srcFolder + File.separator + "mutantDir" + File.separator + "Mutation" +Integer.toString(i+1)+ ".java");
		}
		
		//get the files into the file mover object
		fMover.pullFiles();
		
		//check to see if the filemover got all the files
		//assertEquals(fMover.getFileToBeMovedCount(),mutantsCreated);
		
		int moved = 0;
		int failed = 0;
		//move through each file moving them one by one
		while(fMover.hasMoreFiles()){
			try {
				//move next file
				fMover.moveNextFile();
				
				//build the new executable
				ant.build();
				//move the created jar with correct number corresponding to the mutation created 
				jar.moveJarToDestNumbered();
				//clean the project
				ant.clean();
				//check to see if the mutation was applied
				//assertThat(additionOrgContent, IsNot.not(IsEqual.equalTo(Files.readAllBytes(additionPath))));
				
				
				
			} catch (FileMovingException | BuildException | TargetNotFoundException | IOException e) {
				
				//build failed
				if(e instanceof BuildException){
					logger.error("Build exception "+e.getMessage());
					
					//restore the file back since compilation was not successful 
					fMover.restorTarget();
					//try {
					//	//check to see if the file was restored
					//	assertArrayEquals(goldOrgContent, Files.readAllBytes(goldPath));
					//} catch (IOException e1) {
					//	
					//}
					//clean the project
					try {
						ant.clean();
					} catch (BuildException e1) {
						
						
					} catch (TargetNotFoundException e1) {
						
					}
					//indicate compile failure
					++failed;
				}
				//fail();
			}
			
			//restore the file back to its original state
			fMover.restorTarget();
			//check to see if the file was restored
			//try {
			//	assertArrayEquals(goldOrgContent, Files.readAllBytes(goldPath));
			//} catch (IOException e) {
			//	
			//}
			
			//increment move count
			++moved;
			//see if the file mover has the correct amount of mutatants still to be moved
			//assertEquals(fMover.getFileToBeMovedCount(), mutantsCreated - moved);
		}
		
		//set up for execution
		jarExecutor.setUp();
		//start execution of jars
		jarExecutor.start();
		
		//get the number of successful and failed runs
		int succesful = jarExecutor.getNumberOfMutantsKilled();
		int failurs = jarExecutor.getNumberOfMutantsNotKilled();
		int numTests = jarExecutor.getNumberOfTests();
		int total = succesful + failurs;
		String aliveFile = null;
		String newLine = System.lineSeparator();
		
		//Find any test jars that remain alive and write them to the log file
		List<ExecutionResults> testResults = jarExecutor.getMutationTestingResults();
		for (ExecutionResults result  : testResults ){
			if (!result.isKilled()){
				aliveFile = result.getJarName();
				FileUtils.writeStringToFile(aliveLog, aliveFile + newLine, true);
			}
		}
		
		//moved - failed = number of jars actually created
		moved = moved - failed;
		//see if the total number of executions equals the total amount of jars created
		//assertEquals(succesful+failurs,moved);
		logger.debug("Compilation failurs= "+failed+" total files moved= "+moved);
		logger.debug("Execution succesful=" +succesful+" Execution failurs= "+failurs);
		
		EOL eol = System.getProperty("os.name")
				.toLowerCase().contains("windows") ? EOL.DOS : EOL.NIX;
		try {
			a.writeMutationsLog(srcFolder.toString() + File.separator + "mutantDir" + File.separator + "logs" + File.separator + "mutationsApplied.txt", eol);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String finalOutput = "Number of tests: " + numTests + " " + "Number of mutants: " + total + " " + "Mutants killed: " + succesful;
		FileUtils.writeStringToFile(mutLog, newLine + finalOutput, true);
		
		
		System.out.println(finalOutput + "\n");
	}
}
