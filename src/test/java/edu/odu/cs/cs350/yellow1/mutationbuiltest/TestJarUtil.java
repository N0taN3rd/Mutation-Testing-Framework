package edu.odu.cs.cs350.yellow1.mutationbuiltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.filefilter.FileFilterUtils;
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
import edu.odu.cs.cs350.yellow1.jar.JarUtil;
import edu.odu.cs.cs350.yellow1.jar.TargetHandleingState;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;

public class TestJarUtil {
	private static JavaFile a;
	private static FileMover fMover;
	private static JarUtil jarToDest;
	private static JarUtil jarToDestNewName;
	private static JarUtil jarToDestNumbered;
	private static JarUtil jarToDestNumberedNewName;
	
	private static File normal;
	private static File newName;
	private static File numbered;
	private static File numberedNewName;

	private static AntRunner ant;

	@BeforeClass
	public static void setUp() throws BuildFileNotFoundException, IOException {
		File log = new File("testMutateBuildRun/TestMutations/mutantDir/logs");
		// ok because of the output on jenkins sanitize the logs directory
		if (log.exists())
			if (log.isDirectory()) 
				for (File f : log.listFiles())
					f.delete();
		
		normal = new File("testMutateBuildRun/TestMutations/mutantDir/normal");
		
		newName = new File("testMutateBuildRun/TestMutations/mutantDir/newName");
		
		numbered = new File("testMutateBuildRun/TestMutations/mutantDir/numbered");
		
		numberedNewName = new File("testMutateBuildRun/TestMutations/mutantDir/numberedNewName");
		
		//create jarUtil objects and put them in the create target directory state
		jarToDest =  new JarUtil("testMutateBuildRun/TestMutations/bin", "testMutateBuildRun/TestMutations/mutantDir/normal",TargetHandleingState.createTarget);
		jarToDestNewName  =  new JarUtil("testMutateBuildRun/TestMutations/bin", "testMutateBuildRun/TestMutations/mutantDir/newName",TargetHandleingState.createTarget);
		jarToDestNumbered  =  new JarUtil("testMutateBuildRun/TestMutations/bin", "testMutateBuildRun/TestMutations/mutantDir/numbered",TargetHandleingState.createTarget);
		jarToDestNumberedNewName  =  new JarUtil("testMutateBuildRun/TestMutations/bin", "testMutateBuildRun/TestMutations/mutantDir/numberedNewName",TargetHandleingState.createTarget);
	
		// give ant runer the project location
		ant = new AntRunner("testMutateBuildRun/TestMutations");
		ant.requiresInit(true);
		// call setup
		ant.setUp();
		a = new JavaFile();
		
		File tDir = new File("testMutateBuildRun/TestMutations/mutantDir");
		if (!tDir.exists())
			tDir.mkdir();
		// Create a fileMover object give it the directory where mutations will
		// be placed the directory of the original file location
		fMover = new FileMover("testMutateBuildRun/TestMutations/mutantDir",
				"testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		try {

			fMover.setUp();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();

		}

		

		try {
			a.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		a.executeAll();
		int mutantsCreated = a.getMutantCaseVector().getSize();
		for (int i = 0; i < a.getMutantCaseVector().getSize(); i++) {
			a.getMutantCaseOperations(i).writeMutation("testMutateBuildRun/TestMutations/mutantDir/Addition"+Integer.toString(i+1)+".java");
		}
		
		//get the files into the file mover object
		fMover.pullFiles();

	}
	
	@Test
	public void testJarUtil(){
		// move through each file moving them one by one
		int testNum = 1;
		int moved = 0;
		int failed = 0;
		//call set up on JarUtil Objects
		try {
			assertTrue(jarToDest.setUp());
			assertTrue(jarToDestNewName.setUp());
			assertTrue(jarToDestNumbered.setUp());
			assertTrue(jarToDestNumberedNewName.setUp());
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//test to see if they created the correct directories
		assertTrue(normal.exists());
		assertTrue(newName.exists());
		assertTrue(numbered.exists());
		assertTrue(numberedNewName.exists());
		
		
		
		
		while (fMover.hasMoreFiles()) {
			try {
				// move next file
				assertTrue(fMover.moveNextFile());

				// build the new executable
				assertTrue(ant.build());
				
				assertTrue(jarToDest.moveJarToDest()); 
				assertTrue(jarToDestNewName.moveJarToDest("destNewName")); 
				assertTrue(jarToDestNumbered.moveJarToDestNumbered());
				assertTrue(jarToDestNumberedNewName.moveJarToDestNumbered("destNumbered"));
			
				// clean the project
				assertTrue(ant.clean());
				
				
				
				
			} catch (FileMovingException | BuildException
					| TargetNotFoundException | IOException e) {

				// build failed
				if (e instanceof BuildException) {
					System.out.println("In test: " + e.getMessage());
					// restore the file back since compilation was not
					// successful
					fMover.restorTarget();
				
					try {
						assertTrue(ant.clean());
					} catch (BuildException e1) {

					} catch (TargetNotFoundException e1) {

					}
					
				}
				++failed;
				
			}
			assertTrue(fMover.restorTarget());
			++testNum;
			++moved;

			
		}
		
		FilenameFilter jarFilter = FileFilterUtils.suffixFileFilter(".jar");
		
		moved = moved - failed;
		
		
		//test to see if they moved the correct number of jars and then cleaned out all jars 
		int count = normal.list(jarFilter).length;
		assertEquals(count, 1);
		
		jarToDest.cleanJarsInTargetDir();
		count = normal.list(jarFilter).length;
		assertEquals(count, 0);
		
		count = newName.list(jarFilter).length;
		assertEquals(count, moved);
		
		jarToDestNewName.cleanJarsInTargetDir();
		count = newName.list(jarFilter).length;
		assertEquals(count, 0);
		
		count = numbered.list(jarFilter).length;
		assertEquals(count, moved);
		
		jarToDestNumbered.cleanJarsInTargetDir();
		count = numbered.list(jarFilter).length;
		assertEquals(count, 0);
		
		
		count = numberedNewName.list(jarFilter).length;
		assertEquals(count, moved);
		jarToDestNumberedNewName.cleanJarsInTargetDir();
		count = numberedNewName.list(jarFilter).length;
		assertEquals(count, 0);
		
		//delete the created directories for future use
		normal.delete();
		newName.delete();
		numbered.delete();
		numberedNewName.delete();
		
	}

}
