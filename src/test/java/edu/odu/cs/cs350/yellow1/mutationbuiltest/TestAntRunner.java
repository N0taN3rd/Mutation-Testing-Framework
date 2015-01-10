package edu.odu.cs.cs350.yellow1.mutationbuiltest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.tools.ant.BuildException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.ant.AntBuilder;
import edu.odu.cs.cs350.yellow1.ant.AntRunner;
import edu.odu.cs.cs350.yellow1.ant.BuildFileNotFoundException;
import edu.odu.cs.cs350.yellow1.ant.TargetNotFoundException;

public class TestAntRunner {
	private static AntRunner aRunner;
	private static File checkJarConditions;
	private static File checkDotClassConditions;
	private static FilenameFilter jarFilter = FileFilterUtils.suffixFileFilter(".jar");
	private static FilenameFilter classFilter = FileFilterUtils.suffixFileFilter(".class");
	
	@SuppressWarnings("static-access")
	@BeforeClass
	public static void setUp() throws FileNotFoundException,
			BuildFileNotFoundException {
		
		aRunner = AntBuilder
				.projectDirLocation("testMutateBuildRun/TestMutations")
				.displayOutPut(true)
				.requiresInit(true)
				.create();
		checkJarConditions = new File("testMutateBuildRun/TestMutations/bin");
		checkDotClassConditions = new File(
				"testMutateBuildRun/TestMutations/bin/testingmutation");
		aRunner.setUp();
	}
	
	@Test
	public void testAntRun(){
		try {
			assertTrue(aRunner.run());
			aRunner.clean();
		} catch (BuildException | TargetNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAntCompile() {
		try {
			assertTrue(aRunner.compile());
			assertEquals(checkDotClassConditions.list(classFilter).length,2);
			aRunner.clean();
		} catch (BuildException | TargetNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAntBuild(){
		try {
			assertTrue(aRunner.build());
			assertEquals(checkJarConditions.list(jarFilter).length,1);
			aRunner.clean();
		} catch (BuildException | TargetNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAntClean(){
		try {
			if(checkDotClassConditions.list(classFilter).length > 0){
				assertTrue(aRunner.clean());
				assertEquals(checkDotClassConditions.list(classFilter).length,0);
				assertTrue(aRunner.build());
				assertEquals(checkJarConditions.list(jarFilter).length,1);
				assertTrue(aRunner.clean());
				assertEquals(checkDotClassConditions.list(classFilter).length,0);
				assertEquals(checkJarConditions.list(jarFilter).length,0);
			}else{
				assertTrue(aRunner.compile());
				assertTrue(aRunner.clean());
				assertEquals(checkDotClassConditions.list(classFilter).length,0);
				assertTrue(aRunner.build());
				assertEquals(checkJarConditions.list(jarFilter).length,1);
				assertTrue(aRunner.clean());
				assertEquals(checkDotClassConditions.list(classFilter).length,0);
				assertEquals(checkJarConditions.list(jarFilter).length,0);
			}
			aRunner.clean();
		} catch (BuildException | TargetNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
