package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCase;
import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCaseVector;

public class TestMutantCaseVector {

	/**
	 * Test the MutantCaseVectorClass
	 */
	@Test
	public void binarySearch() {
		try {
			MutantCaseVector mvc = new MutantCaseVector();
			assertTrue(mvc.contains(88, 89, "-") < 0); // not found yet
			mvc.add(88, 89, "-");
			assertFalse(mvc.contains(88, 89, "-") > 0); // First element is not
														// bigger than zero
			assertTrue(mvc.contains(88, 89, "-") >= 0); // Must have found it
														// may be the first
														// element

			assertTrue(mvc.contains(95, 96, "-") < 0); // not found yet
			mvc.add(95, 96, "-");
			mvc.add(new MutantCase(95, 96, "-"));
			assertTrue(mvc.contains(95, 96, "-") >= 0); // Must have found it

			assertTrue(mvc.contains(119, 120, "-") < 0); // not found yet
			mvc.add(119, 120, "-");
			assertTrue(mvc.contains(119, 120, "-") >= 0); // Must have found it
			assertTrue(!mvc.equals(new MutantCaseVector()));
			assertEquals(88, mvc.get(0).getStartIndex());
			assertEquals(89, mvc.get(0).getStopIndex());
			assertTrue(mvc.get(0).getReplacment().equals("-"));
			assertEquals(3, mvc.getSize());
			mvc.add(new MutantCase(125, 126, "-"));
			assertEquals(4, mvc.getSize());
			assertEquals(125, mvc.get(3).getStartIndex());
			assertEquals(126, mvc.get(3).getStopIndex());
			assertTrue(mvc.get(3).getReplacment().equals("-"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests If ConditionChange
	 */
	@Ignore
	public void JavaTestIfCond1() {

		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in");
			jf.ifConditionTrue();
			for (int i = 0; i < jf.getMutantCaseVector().getSize(); i++) {
				if (jf.getMutantCase(i).getStartIndex() == 2397
						&& jf.getMutantCase(i).getStopIndex() == 2455)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 2552
						&& jf.getMutantCase(i).getStopIndex() == 2598)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 2986
						&& jf.getMutantCase(i).getStopIndex() == 3007)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 3102
						&& jf.getMutantCase(i).getStopIndex() == 3124)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 3295
						&& jf.getMutantCase(i).getStopIndex() == 3353)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 3886
						&& jf.getMutantCase(i).getStopIndex() == 3899)
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 4094
						&& jf.getMutantCase(i).getStopIndex() == 4147)
					assertTrue(true);
				else
					fail();

			}

		} catch (Exception e) {
			fail();
		}
	}
}
