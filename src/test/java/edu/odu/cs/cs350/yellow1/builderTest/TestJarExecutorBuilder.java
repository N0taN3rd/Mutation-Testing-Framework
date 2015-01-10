package edu.odu.cs.cs350.yellow1.builderTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.yellow1.jar.ExecutionState;
import edu.odu.cs.cs350.yellow1.jar.JarExecutor;
import edu.odu.cs.cs350.yellow1.jar.JarExecutorBuilder;

/**
 * Test the validity of the JarExecutorBuilder class
 * @author jberlin
 *
 */
public class TestJarExecutorBuilder {
	
	@Test
	public void testJarExecutorBuilder() {
		Object jeb = JarExecutorBuilder
				.pathToJarDirectory("testMutateBuildRun/TestMutations/mutantDir");
		assertNotNull(jeb);
		assertTrue(jeb instanceof JarExecutorBuilder);
		
		jeb = JarExecutorBuilder.pathToCompareScript(" ");
		assertNotNull(jeb);
		
		jeb = JarExecutorBuilder
				.pathToLogDir("testMutateBuildRun/TestMutations/mutantDir/logs");
		assertNotNull(jeb);
		
		jeb = JarExecutorBuilder
				.pathToGold("testMutateBuildRun/TestMutations/gold2.txt");
		assertNotNull(jeb);
		
		jeb = JarExecutorBuilder.withExecutionState(ExecutionState.multiFile);
		assertNotNull(jeb);
		
		jeb = JarExecutorBuilder
				.withTestSuitePath("testMutateBuildRun/TestMutations/tests");
		assertNotNull(jeb);
		
	   JarExecutorBuilder jExBuild = (JarExecutorBuilder) jeb;
	   
	   Object jex = jExBuild.create();
	   
	   assertNotNull(jex);
	   assertTrue(jex instanceof JarExecutor);
	   
	   assertNull(jExBuild.create());
	}
}
