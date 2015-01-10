package edu.odu.cs.cs350.yellow1.builderTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.yellow1.ant.AntBuilder;
import edu.odu.cs.cs350.yellow1.ant.AntRunner;

/**
 * Test the validity of the AntBuilder class
 * @author jberlin
 *
 */
public class TestAntBuilder {
	
	@Test
	public void testAntBuilder(){
		Object aBuild = AntBuilder.projectDirLocation("testMutateBuildRun/TestMutations");
		assertTrue(aBuild instanceof AntBuilder);
		assertNotNull(aBuild);
		aBuild = AntBuilder.displayOutPut(true);
		assertNotNull(aBuild);
		aBuild = AntBuilder.requiresInit(true);
		assertNotNull(aBuild);
		aBuild = AntBuilder.requiresInit(true);
		AntBuilder ab = (AntBuilder) aBuild;
		Object ar = ab.create();
		assertTrue(ar instanceof AntRunner);
		AntRunner aRun = (AntRunner) ar;
		assertNotNull(aRun);
		assertNotNull(aRun.getProjectPath());
		assertTrue(aRun.isRequiringInIt());
		assertTrue(aRun.isDisplayingOutPut());
		assertNull(ab.create());
	}
}
