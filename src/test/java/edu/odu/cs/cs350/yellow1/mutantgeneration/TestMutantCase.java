package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCase;
import static edu.odu.cs.cs350.yellow1.mutantgeneration.TestUtils.*;

/**
 * 
 * @author msoliman This class tests the
 *         edu.odu.cs.cs350.yellow1.mutantgeneration.MutantCase
 *
 */
public class TestMutantCase {

    @Test
    public void test() {

	MutantCase mc = new MutantCase(1, 2, "op");
	assertTrue(!mc.equals(new MutantCase(1, 3, "op")));
	assertTrue(mc.getReplacment().equalsIgnoreCase("op"));
	assertEquals(1, mc.getStartIndex());
	assertEquals(2, mc.getStopIndex());
	assertTrue(mc.applyAgainst("hello world").getOriginalToken()
		.equals("e"));

	mc.applyAgainst("hello world").writeMutation(
		"testfile/TestMutantCase.out");
	try {
	    assertTrue(readFile("testfile/TestMutantCase.out").equals(
	    	"hopllo world"));
	} catch (Exception e) {
	   fail();
	}

    }

}
