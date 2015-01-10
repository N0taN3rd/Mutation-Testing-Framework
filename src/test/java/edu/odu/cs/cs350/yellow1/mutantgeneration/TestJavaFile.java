package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile.EOL;
import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCaseVector;

/**
 * Author Michael Soliman msoliman@cs.odu.edu You are welcomed to make changes
 * to this file as long as you do not break the build or any test cases.
 */
public class TestJavaFile {
	private static final Logger log = LogManager.getLogger(JavaFile.class);
	@Test
	/**
	 * Checks if JavaFile can read a file
	 * Also checks the getFileName() returns the true file name
	 */
	public void readFile() {
		try {
			if (true) {
				JavaFile jf = new JavaFile();
				jf.readFile("testfile/addSub.in");
				byte[] encoded = Files.readAllBytes(Paths
						.get("testfile/addSub.in"));
				String fileContents = new String(encoded);
				assertEquals(fileContents, jf.toString());
				assertTrue(jf.getFileName().equals("addSub.in"));
			}

			if (true) {
				JavaFile jf = new JavaFile();
				try {
					jf.readFile("testfile/adsfasdf.in");
					/** if there is no exception then fail */
					fail();

				} catch (Exception e) {
					assertTrue(true);
				}

			}
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	/**
	 * Checks if JavaFile throws exception on bad file path
	 */
	public void readFile1() {
		try {
			JavaFile jf = new JavaFile();
			try {
				jf.readFile("testfile/adsfasdf.in");
				/** if there is no exception then fail */
				fail();

			} catch (Exception e) {
				assertTrue(true);
			}
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Checks the replacement of the Add token by Sub Token
	 */
	@Test
	public void addSub1() {

		try {

			JavaFile jf = new JavaFile();
			jf.readFile("testfile/addSub.in");
			jf.addSub();
			assertEquals(7, jf.getMutantCaseVector().getSize());
			jf.getMutantCaseVector().print();

			for (int i = 0; i < jf.getMutantCaseVector().getSize(); i++)
				if (jf.getMutantCase(i).getStartIndex() == 88
						&& jf.getMutantCase(i).getStopIndex() == 89
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 95
						&& jf.getMutantCase(i).getStopIndex() == 96
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 119
						&& jf.getMutantCase(i).getStopIndex() == 120
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);

				else if (jf.getMutantCase(i).getStartIndex() == 144
						&& jf.getMutantCase(i).getStopIndex() == 145
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 164
						&& jf.getMutantCase(i).getStopIndex() == 165
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 225
						&& jf.getMutantCase(i).getStopIndex() == 226
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else if (jf.getMutantCase(i).getStartIndex() == 235
						&& jf.getMutantCase(i).getStopIndex() == 236
						&& jf.getMutantCaseOperations(i).getOriginalToken()
								.equals("+"))
					assertTrue(true);
				else
					fail();

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Checks the replacement of the Add token by Sub Token A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#addSub()}
	 */
	@Test
	public void addSub() {

		try {

			JavaFile jf = new JavaFile();
			jf.readFile("testfile/addSub.in");
			jf.addSub();
			assertEquals(7, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("-"));
			assertEquals(88, jf.getMutantCase(0).getStartIndex());
			assertEquals(89, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("-"));
			assertEquals(95, jf.getMutantCase(1).getStartIndex());
			assertEquals(96, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("-"));
			assertEquals(119, jf.getMutantCase(2).getStartIndex());
			assertEquals(120, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("-"));
			assertEquals(144, jf.getMutantCase(3).getStartIndex());
			assertEquals(145, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("-"));
			assertEquals(164, jf.getMutantCase(4).getStartIndex());
			assertEquals(165, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("-"));
			assertEquals(225, jf.getMutantCase(5).getStartIndex());
			assertEquals(226, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("-"));
			assertEquals(235, jf.getMutantCase(6).getStartIndex());
			assertEquals(236, jf.getMutantCase(6).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#addMul()}
	 */
	@Test
	public void addMul() {

		try {

			JavaFile jf = new JavaFile();
			jf.readFile("testfile/addSub.in");
			jf.addMul();
			assertEquals(7, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("*"));
			assertEquals(88, jf.getMutantCase(0).getStartIndex());
			assertEquals(89, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("*"));
			assertEquals(95, jf.getMutantCase(1).getStartIndex());
			assertEquals(96, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("*"));
			assertEquals(119, jf.getMutantCase(2).getStartIndex());
			assertEquals(120, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("*"));
			assertEquals(144, jf.getMutantCase(3).getStartIndex());
			assertEquals(145, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("*"));
			assertEquals(164, jf.getMutantCase(4).getStartIndex());
			assertEquals(165, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("*"));
			assertEquals(225, jf.getMutantCase(5).getStartIndex());
			assertEquals(226, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("*"));
			assertEquals(235, jf.getMutantCase(6).getStartIndex());
			assertEquals(236, jf.getMutantCase(6).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#addDiv()}
	 */
	@Test
	public void addDiv() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/addSub.in");
			jf.addDiv();
			assertEquals(7, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("/"));
			assertEquals(88, jf.getMutantCase(0).getStartIndex());
			assertEquals(89, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("/"));
			assertEquals(95, jf.getMutantCase(1).getStartIndex());
			assertEquals(96, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("/"));
			assertEquals(119, jf.getMutantCase(2).getStartIndex());
			assertEquals(120, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("/"));
			assertEquals(144, jf.getMutantCase(3).getStartIndex());
			assertEquals(145, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("/"));
			assertEquals(164, jf.getMutantCase(4).getStartIndex());
			assertEquals(165, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("/"));
			assertEquals(225, jf.getMutantCase(5).getStartIndex());
			assertEquals(226, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("+"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("/"));
			assertEquals(235, jf.getMutantCase(6).getStartIndex());
			assertEquals(236, jf.getMutantCase(6).getStopIndex());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#subAdd()}
	 */
	@Test
	public void subAdd() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.subAdd();
			assertEquals(5, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("+"));
			assertEquals(4112, jf.getMutantCase(0).getStartIndex());
			assertEquals(4113, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("+"));
			assertEquals(4195, jf.getMutantCase(1).getStartIndex());
			assertEquals(4196, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("+"));
			assertEquals(4199, jf.getMutantCase(2).getStartIndex());
			assertEquals(4200, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("+"));
			assertEquals(4262, jf.getMutantCase(3).getStartIndex());
			assertEquals(4263, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("+"));
			assertEquals(4295, jf.getMutantCase(4).getStartIndex());
			assertEquals(4296, jf.getMutantCase(4).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#subMul()}
	 */
	@Test
	public void subMul() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.subMul();
			assertEquals(5, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("*"));
			assertEquals(4112, jf.getMutantCase(0).getStartIndex());
			assertEquals(4113, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("*"));
			assertEquals(4195, jf.getMutantCase(1).getStartIndex());
			assertEquals(4196, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("*"));
			assertEquals(4199, jf.getMutantCase(2).getStartIndex());
			assertEquals(4200, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("*"));
			assertEquals(4262, jf.getMutantCase(3).getStartIndex());
			assertEquals(4263, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("*"));
			assertEquals(4295, jf.getMutantCase(4).getStartIndex());
			assertEquals(4296, jf.getMutantCase(4).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#subDiv()}
	 */
	@Test
	public void subDiv() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.subDiv();
			assertEquals(5, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("/"));
			assertEquals(4112, jf.getMutantCase(0).getStartIndex());
			assertEquals(4113, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("/"));
			assertEquals(4195, jf.getMutantCase(1).getStartIndex());
			assertEquals(4196, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("/"));
			assertEquals(4199, jf.getMutantCase(2).getStartIndex());
			assertEquals(4200, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("/"));
			assertEquals(4262, jf.getMutantCase(3).getStartIndex());
			assertEquals(4263, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("-"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("/"));
			assertEquals(4295, jf.getMutantCase(4).getStartIndex());
			assertEquals(4296, jf.getMutantCase(4).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#mulAdd()}
	 */
	@Test
	public void mulAdd() {
		try {

			JavaFile jf = new JavaFile();
			jf.readFile(
					"testMutateBuildRun/TestMutations/src/testingmutation/Addition.java",
					EOL.DOS);
			jf.mulAdd();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("*"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("+"));
			assertEquals(685, jf.getMutantCase(0).getStartIndex());
			assertEquals(686, jf.getMutantCase(0).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#mulSub()}
	 */
	@Test
	public void mulSub() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile(
					"testMutateBuildRun/TestMutations/src/testingmutation/Addition.java",
					EOL.DOS);
			jf.mulSub();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("*"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("-"));
			assertEquals(685, jf.getMutantCase(0).getStartIndex());
			assertEquals(686, jf.getMutantCase(0).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#mulDiv()}
	 */
	@Test
	public void mulDiv() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile(
					"testMutateBuildRun/TestMutations/src/testingmutation/Addition.java",
					EOL.DOS);
			jf.mulDiv();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("*"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("/"));
			assertEquals(685, jf.getMutantCase(0).getStartIndex());
			assertEquals(686, jf.getMutantCase(0).getStopIndex());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#divAdd()}
	 */
	@Test
	public void divAdd() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/divAdd.in", EOL.DOS);
			jf.setWindowsLineEnd();
			jf.divAdd();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("/"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("+"));
			assertEquals(77, jf.getMutantCase(0).getStartIndex());
			assertEquals(78, jf.getMutantCase(0).getStopIndex());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#divSub()}
	 */
	@Test
	public void divSub() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/divAdd.in", EOL.DOS);
			jf.divSub();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("/"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("-"));
			assertEquals(77, jf.getMutantCase(0).getStartIndex());
			assertEquals(78, jf.getMutantCase(0).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#divMul()}
	 */
	@Test
	public void divMul() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/divAdd.in", EOL.DOS);
			jf.divMul();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("/"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("*"));
			assertEquals(77, jf.getMutantCase(0).getStartIndex());
			assertEquals(78, jf.getMutantCase(0).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#orAnd()}
	 */
	@Test
	public void orAnd() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.orAnd();
			assertEquals(8, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("&&"));
			assertEquals(2478, jf.getMutantCase(0).getStartIndex());
			assertEquals(2480, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("&&"));
			assertEquals(2490, jf.getMutantCase(1).getStartIndex());
			assertEquals(2492, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("&&"));
			assertEquals(2502, jf.getMutantCase(2).getStartIndex());
			assertEquals(2504, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("&&"));
			assertEquals(2514, jf.getMutantCase(3).getStartIndex());
			assertEquals(2516, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("&&"));
			assertEquals(2640, jf.getMutantCase(4).getStartIndex());
			assertEquals(2642, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("&&"));
			assertEquals(2652, jf.getMutantCase(5).getStartIndex());
			assertEquals(2654, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("&&"));
			assertEquals(2664, jf.getMutantCase(6).getStartIndex());
			assertEquals(2666, jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken()
					.equals("||"));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("&&"));
			assertEquals(3417, jf.getMutantCase(7).getStartIndex());
			assertEquals(3419, jf.getMutantCase(7).getStopIndex());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for
	 * {@link edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile#andOr()}
	 */
	@Test
	public void andOr() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.andOr();
			assertEquals(1, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("&&"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("||"));
			assertEquals(3202, jf.getMutantCase(0).getStartIndex());
			assertEquals(3204, jf.getMutantCase(0).getStopIndex());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * A test case for change while to If
	 */
	@Test
	public void whileIf() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.whileIf();
			assertEquals(2, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("while"));
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("while"));
			assertEquals(1443, jf.getMutantCase(0).getStartIndex());
			assertEquals(1448, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCase(0).getReplacment().equals("if"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("if"));
			assertEquals(3229, jf.getMutantCase(1).getStopIndex());
			assertEquals(3224, jf.getMutantCase(1).getStartIndex());

		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * A test case to add a one to constant
	 */
	@Test
	public void constantPlusOne() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/addSub.in");
			jf.constantPlusOne();
			assertEquals(4, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("10"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("10 + 1"));
			assertEquals(165, jf.getMutantCase(0).getStartIndex());
			assertEquals(167, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("10"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("10 + 1"));
			assertEquals(180, jf.getMutantCase(1).getStartIndex());
			assertEquals(182, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("10"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("10 + 1"));
			assertEquals(236, jf.getMutantCase(2).getStartIndex());
			assertEquals(238, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("5.2"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("5.2 + 1"));
			assertEquals(247, jf.getMutantCase(3).getStartIndex());
			assertEquals(250, jf.getMutantCase(3).getStopIndex());

		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * A test case to subtract a one from constant
	 */
	@Test
	public void constantMinusOne() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/replaceIfCondTrue.in");
			jf.constantMinusOne();
			assertEquals(5, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("5"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("5 - 1"));
			assertEquals(151, jf.getMutantCase(0).getStartIndex());
			assertEquals(152, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("6"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("6 - 1"));
			assertEquals(165, jf.getMutantCase(1).getStartIndex());
			assertEquals(166, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("10"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("10 - 1"));
			assertEquals(182, jf.getMutantCase(2).getStartIndex());
			assertEquals(184, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("11"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("11 - 1"));
			assertEquals(270, jf.getMutantCase(3).getStartIndex());
			assertEquals(272, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("11"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("11 - 1"));
			assertEquals(350, jf.getMutantCase(4).getStartIndex());
			assertEquals(352, jf.getMutantCase(4).getStopIndex());

		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * A test case to multiply a constant by 1.001
	 */
	@Test
	public void constantBy1001() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.constantBy1001();
			assertEquals(13, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("0 * 1.001"));
			assertEquals(1435, jf.getMutantCase(0).getStartIndex());
			assertEquals(1436, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("0 * 1.001"));
			assertEquals(2311, jf.getMutantCase(1).getStartIndex());
			assertEquals(2312, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("0 * 1.001"));
			assertEquals(2578, jf.getMutantCase(2).getStartIndex());
			assertEquals(2579, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("0 * 1.001"));
			assertEquals(2609, jf.getMutantCase(3).getStartIndex());
			assertEquals(2610, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("0 * 1.001"));
			assertEquals(3089, jf.getMutantCase(4).getStartIndex());
			assertEquals(3090, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("0 * 1.001"));
			assertEquals(3632, jf.getMutantCase(5).getStartIndex());
			assertEquals(3633, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("1 * 1.001"));
			assertEquals(4009, jf.getMutantCase(6).getStartIndex());
			assertEquals(4010, jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("0 * 1.001"));
			assertEquals(4091, jf.getMutantCase(7).getStartIndex());
			assertEquals(4092, jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(8).getReplacment().equals("1 * 1.001"));
			assertEquals(4114, jf.getMutantCase(8).getStartIndex());
			assertEquals(4115, jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(9).getReplacment().equals("1 * 1.001"));
			assertEquals(4138, jf.getMutantCase(9).getStartIndex());
			assertEquals(4139, jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(10).getReplacment().equals("1 * 1.001"));
			assertEquals(4197, jf.getMutantCase(10).getStartIndex());
			assertEquals(4198, jf.getMutantCase(10).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(11).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(11).getReplacment().equals("1 * 1.001"));
			assertEquals(4264, jf.getMutantCase(11).getStartIndex());
			assertEquals(4265, jf.getMutantCase(11).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(12).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(12).getReplacment().equals("1 * 1.001"));
			assertEquals(4297, jf.getMutantCase(12).getStartIndex());
			assertEquals(4298, jf.getMutantCase(12).getStopIndex());

		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * A test case to multiply a constant by 0.999
	 */
	@Test
	public void constantBy0999() {
		try {
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.constantBy0999();
			assertEquals(13, jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("0 * 0.999"));
			assertEquals(1435, jf.getMutantCase(0).getStartIndex());
			assertEquals(1436, jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("0 * 0.999"));
			assertEquals(2311, jf.getMutantCase(1).getStartIndex());
			assertEquals(2312, jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("0 * 0.999"));
			assertEquals(2578, jf.getMutantCase(2).getStartIndex());
			assertEquals(2579, jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("0 * 0.999"));
			assertEquals(2609, jf.getMutantCase(3).getStartIndex());
			assertEquals(2610, jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("0 * 0.999"));
			assertEquals(3089, jf.getMutantCase(4).getStartIndex());
			assertEquals(3090, jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("0 * 0.999"));
			assertEquals(3632, jf.getMutantCase(5).getStartIndex());
			assertEquals(3633, jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("1 * 0.999"));
			assertEquals(4009, jf.getMutantCase(6).getStartIndex());
			assertEquals(4010, jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken()
					.equals("0"));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("0 * 0.999"));
			assertEquals(4091, jf.getMutantCase(7).getStartIndex());
			assertEquals(4092, jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(8).getReplacment().equals("1 * 0.999"));
			assertEquals(4114, jf.getMutantCase(8).getStartIndex());
			assertEquals(4115, jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(9).getReplacment().equals("1 * 0.999"));
			assertEquals(4138, jf.getMutantCase(9).getStartIndex());
			assertEquals(4139, jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(10).getReplacment().equals("1 * 0.999"));
			assertEquals(4197, jf.getMutantCase(10).getStartIndex());
			assertEquals(4198, jf.getMutantCase(10).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(11).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(11).getReplacment().equals("1 * 0.999"));
			assertEquals(4264, jf.getMutantCase(11).getStartIndex());
			assertEquals(4265, jf.getMutantCase(11).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(12).getOriginalToken()
					.equals("1"));
			assertTrue(jf.getMutantCase(12).getReplacment().equals("1 * 0.999"));
			assertEquals(4297, jf.getMutantCase(12).getStartIndex());
			assertEquals(4298, jf.getMutantCase(12).getStopIndex());

		} catch (Exception e) {
			fail();
		}

	}
	@Test
	public void ltGt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.ltGt();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">"));
			assertEquals(130 , jf.getMutantCase(0).getStartIndex());
			assertEquals(131 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals(">"));
			assertEquals(170 , jf.getMutantCase(1).getStartIndex());
			assertEquals(171 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals(">"));
			assertEquals(204 , jf.getMutantCase(2).getStartIndex());
			assertEquals(205 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals(">"));
			assertEquals(480 , jf.getMutantCase(3).getStartIndex());
			assertEquals(481 , jf.getMutantCase(3).getStopIndex());
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	@Test
	public void ltGe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.ltGe();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">="));
			assertEquals(130 , jf.getMutantCase(0).getStartIndex());
			assertEquals(131 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals(">="));
			assertEquals(170 , jf.getMutantCase(1).getStartIndex());
			assertEquals(171 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals(">="));
			assertEquals(204 , jf.getMutantCase(2).getStartIndex());
			assertEquals(205 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals(">="));
			assertEquals(480 , jf.getMutantCase(3).getStartIndex());
			assertEquals(481 , jf.getMutantCase(3).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void ltEqual(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.ltEqual();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("=="));
			assertEquals(130 , jf.getMutantCase(0).getStartIndex());
			assertEquals(131 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("=="));
			assertEquals(170 , jf.getMutantCase(1).getStartIndex());
			assertEquals(171 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("=="));
			assertEquals(204 , jf.getMutantCase(2).getStartIndex());
			assertEquals(205 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("=="));
			assertEquals(480 , jf.getMutantCase(3).getStartIndex());
			assertEquals(481 , jf.getMutantCase(3).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void writeMutationsLog() throws Exception {
		try {
			/**
			 * Writes the log and dispose the java file
			 */
			if (true) {
				JavaFile jf = new JavaFile();
				jf.readFile("testfile/TrieParser.in", EOL.DOS);
				jf.constantBy0999();
				jf.writeMutationsLog(
						"testMutateBuildRun/TestMutations/mutantDir/logs/mutationsEmployed.txt",
						EOL.DOS);
			}

			/**
			 * read the log and check if javafile can read the mutations correct
			 */
			if (true) {
				JavaFile jf = new JavaFile();
				jf.readMutationsLog("testMutateBuildRun/TestMutations/mutantDir/logs/mutationsEmployed.txt",EOL.DOS);
				assertEquals(13, jf.getMutantCaseVector().getSize());
				log.debug(jf.getMutantCaseOperations(0).getOriginalToken().toString());
				log.debug(jf.getMutantCase(0).getStartIndex());
				log.debug(jf.getMutantCase(0).getStopIndex());
				log.debug(jf.getMutantCase(0).getReplacment());
				assertTrue(jf.getMutantCaseOperations(0).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(0).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(1435, jf.getMutantCase(0).getStartIndex());
				assertEquals(1436, jf.getMutantCase(0).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(1).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(1).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(2311, jf.getMutantCase(1).getStartIndex());
				assertEquals(2312, jf.getMutantCase(1).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(2).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(2).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(2578, jf.getMutantCase(2).getStartIndex());
				assertEquals(2579, jf.getMutantCase(2).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(3).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(3).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(2609, jf.getMutantCase(3).getStartIndex());
				assertEquals(2610, jf.getMutantCase(3).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(4).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(4).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(3089, jf.getMutantCase(4).getStartIndex());
				assertEquals(3090, jf.getMutantCase(4).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(5).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(5).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(3632, jf.getMutantCase(5).getStartIndex());
				assertEquals(3633, jf.getMutantCase(5).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(6).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(6).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4009, jf.getMutantCase(6).getStartIndex());
				assertEquals(4010, jf.getMutantCase(6).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(7).getOriginalToken()
						.equals("0"));
				assertTrue(jf.getMutantCase(7).getReplacment()
						.equals("0 * 0.999"));
				assertEquals(4091, jf.getMutantCase(7).getStartIndex());
				assertEquals(4092, jf.getMutantCase(7).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(8).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(8).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4114, jf.getMutantCase(8).getStartIndex());
				assertEquals(4115, jf.getMutantCase(8).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(9).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(9).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4138, jf.getMutantCase(9).getStartIndex());
				assertEquals(4139, jf.getMutantCase(9).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(10).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(10).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4197, jf.getMutantCase(10).getStartIndex());
				assertEquals(4198, jf.getMutantCase(10).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(11).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(11).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4264, jf.getMutantCase(11).getStartIndex());
				assertEquals(4265, jf.getMutantCase(11).getStopIndex());
				assertTrue(jf.getMutantCaseOperations(12).getOriginalToken()
						.equals("1"));
				assertTrue(jf.getMutantCase(12).getReplacment()
						.equals("1 * 0.999"));
				assertEquals(4297, jf.getMutantCase(12).getStartIndex());
				assertEquals(4298, jf.getMutantCase(12).getStopIndex());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	@Test
	public void gtle(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.gtle();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<="));
			assertEquals(138 , jf.getMutantCase(0).getStartIndex());
			assertEquals(139 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("<="));
			assertEquals(178 , jf.getMutantCase(1).getStartIndex());
			assertEquals(179 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("<="));
			assertEquals(205 , jf.getMutantCase(2).getStartIndex());
			assertEquals(206 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("<="));
			assertEquals(488 , jf.getMutantCase(3).getStartIndex());
			assertEquals(489 , jf.getMutantCase(3).getStopIndex());

			
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void gtGe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.gtGe();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">="));
			assertEquals(138 , jf.getMutantCase(0).getStartIndex());
			assertEquals(139 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals(">="));
			assertEquals(178 , jf.getMutantCase(1).getStartIndex());
			assertEquals(179 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals(">="));
			assertEquals(205 , jf.getMutantCase(2).getStartIndex());
			assertEquals(206 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals(">="));
			assertEquals(488 , jf.getMutantCase(3).getStartIndex());
			assertEquals(489 , jf.getMutantCase(3).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	@Test
	public void gtEqual(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.gtEqual();
			assertEquals(4 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("=="));
			assertEquals(138 , jf.getMutantCase(0).getStartIndex());
			assertEquals(139 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("=="));
			assertEquals(178 , jf.getMutantCase(1).getStartIndex());
			assertEquals(179 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("=="));
			assertEquals(205 , jf.getMutantCase(2).getStartIndex());
			assertEquals(206 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("=="));
			assertEquals(488 , jf.getMutantCase(3).getStartIndex());
			assertEquals(489 , jf.getMutantCase(3).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	@Test
	public void geLt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testMutateBuildRun/TestMutations/src/testingmutation/Addition.java", EOL.DOS);
			jf.geLt();
			assertEquals(0 , jf.getMutantCaseVector().getSize());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void geGt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.geGt();
			assertEquals(0 , jf.getMutantCaseVector().getSize());

			
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void geLe(){
		try{
			
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.geLe();
			assertEquals(0 , jf.getMutantCaseVector().getSize());

			
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void geEqual(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.DOS);
			jf.geEqual();
			assertEquals(0 , jf.getMutantCaseVector().getSize());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void leGt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.leGt();
			assertEquals(1 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">"));
			assertEquals(3894 , jf.getMutantCase(0).getStartIndex());
			assertEquals(3896 , jf.getMutantCase(0).getStopIndex());
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void leGe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.leGe();
			assertEquals(1 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">="));
			assertEquals(3894 , jf.getMutantCase(0).getStartIndex());
			assertEquals(3896 , jf.getMutantCase(0).getStopIndex());


		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void leEqual(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.leEqual();
			assertEquals(1 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("=="));
			assertEquals(3894 , jf.getMutantCase(0).getStartIndex());
			assertEquals(3896 , jf.getMutantCase(0).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void leLt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.leLt();
			assertEquals(1 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<"));
			assertEquals(3894 , jf.getMutantCase(0).getStartIndex());
			assertEquals(3896 , jf.getMutantCase(0).getStopIndex());

			
		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void gtLt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.gtLt();
			assertEquals(6 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<"));
			assertEquals(1221 , jf.getMutantCase(0).getStartIndex());
			assertEquals(1222 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("<"));
			assertEquals(1306 , jf.getMutantCase(1).getStartIndex());
			assertEquals(1307 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("<"));
			assertEquals(1493 , jf.getMutantCase(2).getStartIndex());
			assertEquals(1494 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("<"));
			assertEquals(2091 , jf.getMutantCase(3).getStartIndex());
			assertEquals(2092 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("<"));
			assertEquals(2179 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2180 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals(">"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("<"));
			assertEquals(3003 , jf.getMutantCase(5).getStartIndex());
			assertEquals(3004 , jf.getMutantCase(5).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void ltLe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.ltLe();
			assertEquals(9 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<="));
			assertEquals(1196 , jf.getMutantCase(0).getStartIndex());
			assertEquals(1197 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("<="));
			assertEquals(1281 , jf.getMutantCase(1).getStartIndex());
			assertEquals(1282 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("<="));
			assertEquals(1413 , jf.getMutantCase(2).getStartIndex());
			assertEquals(1414 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("<="));
			assertEquals(1480 , jf.getMutantCase(3).getStartIndex());
			assertEquals(1481 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("<="));
			assertEquals(2078 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2079 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("<="));
			assertEquals(2154 , jf.getMutantCase(5).getStartIndex());
			assertEquals(2155 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("<="));
			assertEquals(2334 , jf.getMutantCase(6).getStartIndex());
			assertEquals(2335 , jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("<="));
			assertEquals(3144 , jf.getMutantCase(7).getStartIndex());
			assertEquals(3145 , jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken().equals("<"));
			assertTrue(jf.getMutantCase(8).getReplacment().equals("<="));
			assertEquals(4024 , jf.getMutantCase(8).getStartIndex());
			assertEquals(4025 , jf.getMutantCase(8).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	@Test
	public void equalLt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.equalLt();
			assertEquals(11 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<"));
			assertEquals(2400 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2402 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("<"));
			assertEquals(2412 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2414 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("<"));
			assertEquals(2424 , jf.getMutantCase(2).getStartIndex());
			assertEquals(2426 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("<"));
			assertEquals(2436 , jf.getMutantCase(3).getStartIndex());
			assertEquals(2438 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("<"));
			assertEquals(2448 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2450 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("<"));
			assertEquals(2555 , jf.getMutantCase(5).getStartIndex());
			assertEquals(2557 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("<"));
			assertEquals(2567 , jf.getMutantCase(6).getStartIndex());
			assertEquals(2569 , jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("<"));
			assertEquals(2579 , jf.getMutantCase(7).getStartIndex());
			assertEquals(2581 , jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(8).getReplacment().equals("<"));
			assertEquals(2591 , jf.getMutantCase(8).getStartIndex());
			assertEquals(2593 , jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(9).getReplacment().equals("<"));
			assertEquals(3316 , jf.getMutantCase(9).getStartIndex());
			assertEquals(3318 , jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(10).getReplacment().equals("<"));
			assertEquals(3346 , jf.getMutantCase(10).getStartIndex());
			assertEquals(3348 , jf.getMutantCase(10).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void equalGt(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.equalGt();
			assertEquals(11 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">"));
			assertEquals(2400 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2402 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(1).getReplacment().equals(">"));
			assertEquals(2412 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2414 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(2).getReplacment().equals(">"));
			assertEquals(2424 , jf.getMutantCase(2).getStartIndex());
			assertEquals(2426 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(3).getReplacment().equals(">"));
			assertEquals(2436 , jf.getMutantCase(3).getStartIndex());
			assertEquals(2438 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(4).getReplacment().equals(">"));
			assertEquals(2448 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2450 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(5).getReplacment().equals(">"));
			assertEquals(2555 , jf.getMutantCase(5).getStartIndex());
			assertEquals(2557 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(6).getReplacment().equals(">"));
			assertEquals(2567 , jf.getMutantCase(6).getStartIndex());
			assertEquals(2569 , jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(7).getReplacment().equals(">"));
			assertEquals(2579 , jf.getMutantCase(7).getStartIndex());
			assertEquals(2581 , jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(8).getReplacment().equals(">"));
			assertEquals(2591 , jf.getMutantCase(8).getStartIndex());
			assertEquals(2593 , jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(9).getReplacment().equals(">"));
			assertEquals(3316 , jf.getMutantCase(9).getStartIndex());
			assertEquals(3318 , jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(10).getReplacment().equals(">"));
			assertEquals(3346 , jf.getMutantCase(10).getStartIndex());
			assertEquals(3348 , jf.getMutantCase(10).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	@Test
	public void equalGe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.equalGe();
			assertEquals(11 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals(">="));
			assertEquals(2400 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2402 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(1).getReplacment().equals(">="));
			assertEquals(2412 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2414 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(2).getReplacment().equals(">="));
			assertEquals(2424 , jf.getMutantCase(2).getStartIndex());
			assertEquals(2426 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(3).getReplacment().equals(">="));
			assertEquals(2436 , jf.getMutantCase(3).getStartIndex());
			assertEquals(2438 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(4).getReplacment().equals(">="));
			assertEquals(2448 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2450 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(5).getReplacment().equals(">="));
			assertEquals(2555 , jf.getMutantCase(5).getStartIndex());
			assertEquals(2557 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(6).getReplacment().equals(">="));
			assertEquals(2567 , jf.getMutantCase(6).getStartIndex());
			assertEquals(2569 , jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(7).getReplacment().equals(">="));
			assertEquals(2579 , jf.getMutantCase(7).getStartIndex());
			assertEquals(2581 , jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(8).getReplacment().equals(">="));
			assertEquals(2591 , jf.getMutantCase(8).getStartIndex());
			assertEquals(2593 , jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(9).getReplacment().equals(">="));
			assertEquals(3316 , jf.getMutantCase(9).getStartIndex());
			assertEquals(3318 , jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(10).getReplacment().equals(">="));
			assertEquals(3346 , jf.getMutantCase(10).getStartIndex());
			assertEquals(3348 , jf.getMutantCase(10).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void equalLe(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.equalLe();
			assertEquals(11 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("<="));
			assertEquals(2400 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2402 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("<="));
			assertEquals(2412 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2414 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("<="));
			assertEquals(2424 , jf.getMutantCase(2).getStartIndex());
			assertEquals(2426 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("<="));
			assertEquals(2436 , jf.getMutantCase(3).getStartIndex());
			assertEquals(2438 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("<="));
			assertEquals(2448 , jf.getMutantCase(4).getStartIndex());
			assertEquals(2450 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("<="));
			assertEquals(2555 , jf.getMutantCase(5).getStartIndex());
			assertEquals(2557 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("<="));
			assertEquals(2567 , jf.getMutantCase(6).getStartIndex());
			assertEquals(2569 , jf.getMutantCase(6).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(7).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(7).getReplacment().equals("<="));
			assertEquals(2579 , jf.getMutantCase(7).getStartIndex());
			assertEquals(2581 , jf.getMutantCase(7).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(8).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(8).getReplacment().equals("<="));
			assertEquals(2591 , jf.getMutantCase(8).getStartIndex());
			assertEquals(2593 , jf.getMutantCase(8).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(9).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(9).getReplacment().equals("<="));
			assertEquals(3316 , jf.getMutantCase(9).getStartIndex());
			assertEquals(3318 , jf.getMutantCase(9).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(10).getOriginalToken().equals("=="));
			assertTrue(jf.getMutantCase(10).getReplacment().equals("<="));
			assertEquals(3346 , jf.getMutantCase(10).getStartIndex());
			assertEquals(3348 , jf.getMutantCase(10).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void ifConditionTrue(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.ifConditionTrue();
			assertEquals(7 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("(c == '&' || c == '?' || c == '!' || c == ':' || c == ',')"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("(true)"));
			assertEquals(2397 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2455 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("(c == '!' || c == '?' || c == ':' || c == ',')"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("(true)"));
			assertEquals(2552 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2598 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("(domain.length() > 0)"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("(true)"));
			assertEquals(2986 , jf.getMutantCase(2).getStartIndex());
			assertEquals(3007 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("(c != '?' && c != ',')"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("(true)"));
			assertEquals(3102 , jf.getMutantCase(3).getStartIndex());
			assertEquals(3124 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("(encoded.charAt(idx) == '?' || encoded.charAt(idx) == ',')"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("(true)"));
			assertEquals(3295 , jf.getMutantCase(4).getStartIndex());
			assertEquals(3353 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("(length <= 1)"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("(true)"));
			assertEquals(3886 , jf.getMutantCase(5).getStartIndex());
			assertEquals(3899 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("(Character.isSurrogatePair(buffer[i], buffer[i - 1]))"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("(true)"));
			assertEquals(4094 , jf.getMutantCase(6).getStartIndex());
			assertEquals(4147 , jf.getMutantCase(6).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void ifConditionFalse(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.ifConditionFalse();
			assertEquals(7 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("(c == '&' || c == '?' || c == '!' || c == ':' || c == ',')"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("(false)"));
			assertEquals(2397 , jf.getMutantCase(0).getStartIndex());
			assertEquals(2455 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("(c == '!' || c == '?' || c == ':' || c == ',')"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("(false)"));
			assertEquals(2552 , jf.getMutantCase(1).getStartIndex());
			assertEquals(2598 , jf.getMutantCase(1).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(2).getOriginalToken().equals("(domain.length() > 0)"));
			assertTrue(jf.getMutantCase(2).getReplacment().equals("(false)"));
			assertEquals(2986 , jf.getMutantCase(2).getStartIndex());
			assertEquals(3007 , jf.getMutantCase(2).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(3).getOriginalToken().equals("(c != '?' && c != ',')"));
			assertTrue(jf.getMutantCase(3).getReplacment().equals("(false)"));
			assertEquals(3102 , jf.getMutantCase(3).getStartIndex());
			assertEquals(3124 , jf.getMutantCase(3).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(4).getOriginalToken().equals("(encoded.charAt(idx) == '?' || encoded.charAt(idx) == ',')"));
			assertTrue(jf.getMutantCase(4).getReplacment().equals("(false)"));
			assertEquals(3295 , jf.getMutantCase(4).getStartIndex());
			assertEquals(3353 , jf.getMutantCase(4).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(5).getOriginalToken().equals("(length <= 1)"));
			assertTrue(jf.getMutantCase(5).getReplacment().equals("(false)"));
			assertEquals(3886 , jf.getMutantCase(5).getStartIndex());
			assertEquals(3899 , jf.getMutantCase(5).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(6).getOriginalToken().equals("(Character.isSurrogatePair(buffer[i], buffer[i - 1]))"));
			assertTrue(jf.getMutantCase(6).getReplacment().equals("(false)"));
			assertEquals(4094 , jf.getMutantCase(6).getStartIndex());
			assertEquals(4147 , jf.getMutantCase(6).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void whileConditionTrue(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.whileConditionTrue();
			assertEquals(2 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("(idx < encodedLen)"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("(true)"));
			assertEquals(1408 , jf.getMutantCase(0).getStartIndex());
			assertEquals(1426 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("(idx < encodedLen)"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("(true)"));
			assertEquals(3139 , jf.getMutantCase(1).getStartIndex());
			assertEquals(3157 , jf.getMutantCase(1).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void whileConditionFalse(){
		try{
			JavaFile jf = new JavaFile();
			jf.readFile("testfile/TrieParser.in", EOL.NIX);
			jf.whileConditionFalse();
			assertEquals(2 , jf.getMutantCaseVector().getSize());
			assertTrue(jf.getMutantCaseOperations(0).getOriginalToken().equals("(idx < encodedLen)"));
			assertTrue(jf.getMutantCase(0).getReplacment().equals("(false)"));
			assertEquals(1408 , jf.getMutantCase(0).getStartIndex());
			assertEquals(1426 , jf.getMutantCase(0).getStopIndex());
			assertTrue(jf.getMutantCaseOperations(1).getOriginalToken().equals("(idx < encodedLen)"));
			assertTrue(jf.getMutantCase(1).getReplacment().equals("(false)"));
			assertEquals(3139 , jf.getMutantCase(1).getStartIndex());
			assertEquals(3157 , jf.getMutantCase(1).getStopIndex());

		}catch (Exception e){
			e.printStackTrace();
			fail();
		}
		
	}

}
