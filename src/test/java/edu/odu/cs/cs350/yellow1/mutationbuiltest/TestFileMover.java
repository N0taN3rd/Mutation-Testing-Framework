package edu.odu.cs.cs350.yellow1.mutationbuiltest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.odu.cs.cs350.yellow1.file.FileMover;
import edu.odu.cs.cs350.yellow1.file.FileMovingException;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;

public class TestFileMover {
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	private static JavaFile a;
	private static FileMover fMover;
	private static byte[] additionOrgContent;
	private static Path additionPath;
	private static File tDir;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a = new JavaFile();
		 tDir = new File("testMutateBuildRun/TestMutations/mutantDir");	 
		 if(!tDir.exists())
			 tDir.mkdir();
		fMover = new FileMover("testMutateBuildRun/TestMutations/mutantDir", "testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		File additionFile = new File("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		additionPath = additionFile.toPath();
		additionOrgContent = Files.readAllBytes(additionPath);
		try {
			
			 fMover.setUp();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			fail();
			
		}
		
	
	}

	
	
	@Before
	public void before(){
		
		try {
			a.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		a.executeAll();
		int mutantsCreated = a.getMutantCaseVector().getSize();
		for (int i = 0; i < a.getMutantCaseVector().getSize(); i++) {
			a.getMutantCaseOperations(i).writeMutation("testMutateBuildRun/TestMutations/mutantDir/Addition"+Integer.toString(i)+".java");
		}
		fMover.pullFiles();
	}
	
	
	@Test
	public void testFileMover() {
		int totalMutantsCreated = a.getMutantCaseVector().getSize();
		int moved = 0;
		assertEquals(fMover.getFileToBeMovedCount(),totalMutantsCreated);
		assertTrue(fMover.hasMoreFiles());
		while(fMover.hasMoreFiles()){
			try {
				assertTrue(fMover.moveNextFile());
				assertThat(additionOrgContent, IsNot.not(IsEqual.equalTo(Files.readAllBytes(additionPath))));
			} catch (FileMovingException | IOException e) {
				assertTrue(fMover.restorTarget());
			
			}
			assertTrue(fMover.restorTarget());
			try {
				assertArrayEquals(additionOrgContent, Files.readAllBytes(additionPath));
			} catch (IOException e) {
				assertTrue(fMover.restorTarget());
				
			}
			++moved;
			
			assertEquals(fMover.getFileToBeMovedCount(), totalMutantsCreated-moved);
			
		}
		
		assertTrue(fMover.restorTarget());
	}

	
	

}
