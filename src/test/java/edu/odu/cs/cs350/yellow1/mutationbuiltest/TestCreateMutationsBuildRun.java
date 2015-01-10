package edu.odu.cs.cs350.yellow1.mutationbuiltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.ant.AntRunner;
import edu.odu.cs.cs350.yellow1.ant.BuildFileNotFoundException;
import edu.odu.cs.cs350.yellow1.ant.TargetNotFoundException;
import edu.odu.cs.cs350.yellow1.file.FileMover;
import edu.odu.cs.cs350.yellow1.file.FileMovingException;
import edu.odu.cs.cs350.yellow1.jar.ExecutionState;
import edu.odu.cs.cs350.yellow1.jar.JarExecutor;
import edu.odu.cs.cs350.yellow1.jar.JarExecutorBuilder;
import edu.odu.cs.cs350.yellow1.jar.JarUtil;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile.EOL;

/**
 * Test the use of mutation creating class JavaFile and Ant project class and file jar running classes
 * @author jberlin
 *
 */
public class TestCreateMutationsBuildRun {
	
	private static JavaFile a;
	private static FileMover fMover;
	private static JarUtil jar;
	private static AntRunner ant;
	private static JarExecutor jarExecutor;
	private static byte[] additionOrgContent;
	private static Path additionPath;
	private static Logger logger;
	private static File mutLog;
	private static File logFolder;
	
	@SuppressWarnings("static-access")
	@BeforeClass
	public static void setUp() throws BuildFileNotFoundException, IOException {
		logger = LogManager.getLogger(TestCreateMutationsBuildRun.class);
		mutLog = new File(
				"testMutateBuildRun/TestMutations/mutantDir/logs/mutationsApplied.txt");
		logFolder = new File("testMutateBuildRun/TestMutations/mutantDir/logs");
		// ok because of the output on jenkins sanitize the logs directory
		if (logFolder.exists())
			if (logFolder.isDirectory())
				for (File f : logFolder.listFiles())
					f.delete();

		//give ant runer the project location
		ant  = new AntRunner("testMutateBuildRun/TestMutations");
		ant.requiresInit(true);
		//call setup
		ant.setUp();
		a = new JavaFile();
		
		//give the jarUtil the  directory where to expect the jar   the directory where to put the jar
		jar = new JarUtil("testMutateBuildRun/TestMutations/bin", "testMutateBuildRun/TestMutations/mutantDir");
		
		//get a file object to the original file
		File additionFile = new File("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		additionPath = additionFile.toPath();
		//get the bytes from it for checking if applying mutation and restore works
		additionOrgContent = Files.readAllBytes(additionPath);
		
		
		File script = new File("testMutateBuildRun/TestMutations/compare.sh");
		//build the JarExecutor using the JarBuilder
		jarExecutor = JarExecutorBuilder
				.pathToJarDirectory(
						"testMutateBuildRun/TestMutations/mutantDir")
				.pathToCompareScript(script.getAbsolutePath())
				.pathToLogDir("testMutateBuildRun/TestMutations/mutantDir/logs")
				.pathToGold("testMutateBuildRun/TestMutations/gold2.txt")
				.withExecutionState(ExecutionState.multiFile)
				.withTestSuitePath("testMutateBuildRun/TestMutations/tests").create();
		File tDir = new File("testMutateBuildRun/TestMutations/mutantDir");	 
		 if(!tDir.exists())
			 tDir.mkdir();
		//Create a fileMover object give it the directory where mutations will be placed   the directory of the original file location
		fMover = new FileMover("testMutateBuildRun/TestMutations/mutantDir",               "testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		try {
			
			 fMover.setUp();
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage());
			
		}
		
		 
	}
	
	@Test
	public void test() {
		 
		 
		try {
			a.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		} catch (IOException e) {
			logger.error(e.getMessage());
			fail();
		}
		
		
		a.executeAll();
		int mutantsCreated = a.getMutantCaseVector().getSize();
		logger.info("Created "+mutantsCreated +"Mutatnts");
		for (int i = 0; i < a.getMutantCaseVector().getSize(); i++) {
			a.getMutantCaseOperations(i).writeMutation("testMutateBuildRun/TestMutations/mutantDir/Addition"+Integer.toString(i+1)+".java");
		}
		
		//get the files into the file mover object
		fMover.pullFiles();
		
		//check to see if the filemover got all the files
		assertEquals(fMover.getFileToBeMovedCount(),mutantsCreated);
		
		int moved = 0;
		int failed = 0;
		//move through each file moving them one by one
		while(fMover.hasMoreFiles()){
			try {
				//move next file
				assertTrue(fMover.moveNextFile());
				
				//build the new executable
				assertTrue(ant.build());
				//move the created jar with correct number corresponding to the mutation created 
				assertTrue(jar.moveJarToDestNumbered());
				//clean the project
				assertTrue(ant.clean());
				//check to see if the mutation was applied
				assertThat(additionOrgContent, IsNot.not(IsEqual.equalTo(Files.readAllBytes(additionPath))));
				
				
				
			} catch (FileMovingException | BuildException | TargetNotFoundException | IOException e) {
				
				//build failed
				if(e instanceof BuildException){
					logger.error("Build exception "+e.getMessage());
					
					//restore the file back since compilation was not successful 
					fMover.restorTarget();
				
					try {
						//check to see if the file was restored
						assertArrayEquals(additionOrgContent, Files.readAllBytes(additionPath));
						//clean the project
						assertTrue(ant.clean());
					} catch (Exception ee) {
						logger.error(ee.getClass().getSimpleName()+": "+ee.getMessage());
						
					} 
					//indicate compile failure
					++failed;
				}
				
			}
			
			//restore the file back to its original state
			assertTrue(fMover.restorTarget());
			//check to see if the file was restored
			try {
				assertArrayEquals(additionOrgContent, Files.readAllBytes(additionPath));
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			
			//increment move count
			++moved;
			//see if the file mover has the correct amount of mutatants still to be moved
			assertEquals(fMover.getFileToBeMovedCount(), mutantsCreated - moved);
		}
		
		//set up for execution
		jarExecutor.setUp();
		//start execution of jars
		jarExecutor.start();
		
		//get the number of successful and failed runs
		int succesful = jarExecutor.getNumberOfMutantsKilled();
		int failurs = jarExecutor.getNumberOfMutantsNotKilled();
		
		
		//moved - failed = number of jars actually created
		moved = moved - failed;
		//see if the total number of executions equals the total amount of jars created
		assertEquals(succesful+failurs,moved);
		logger.debug("Compilation failurs= "+failed+" total files moved= "+moved);
		logger.debug("Execution succesful=" +succesful+" Execution failurs= "+failurs);
		
		EOL eol = System.getProperty("os.name")
				.toLowerCase().contains("windows") ? EOL.DOS : EOL.NIX;
		try {
			a.writeMutationsLog("testMutateBuildRun/TestMutations/mutantDir/logs/mutationsApplied.txt", eol);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		assertTrue(mutLog.exists());
		mutLog.delete();
		
	}

}
