package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.mutationgeneration.CFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCaseVector;
/**
 * 
 * @author Michael
 *
 */
public class TestCFile {

	/*
	 * Checks the replacement of the Add token by Sub Token
	 * for a cpp file
	 * 
	 */
	@Ignore
	public void addSubCpp() {

		try {
			CFile cf = new CFile();
			cf.readJavaFile("testfile/addSubCpp.in");
			cf.addSub();
		} catch (Exception e) {
			fail();
		}
	}
}
