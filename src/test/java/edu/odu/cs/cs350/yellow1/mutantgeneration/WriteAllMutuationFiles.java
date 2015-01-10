package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import edu.odu.cs.cs350.yellow1.mutationgeneration.JavaFile;
import edu.odu.cs.cs350.yellow1.mutationgeneration.MutantCaseVector;

/**
 * 
 * @author msoliman
 * an example
 *
 */
public class WriteAllMutuationFiles {

    @Ignore
    public void exampleUsage() throws IOException {
	JavaFile a = new JavaFile();
	a.readFile("testfile/addSub.in");
	
	/**
	 * Executes all the mutation cases
	 */
	a.executeAll();
	for (int i = 0; i < a.getMutantCaseVector().getSize() ; i++) {
	    /**
	     * this writes this mutation case to a given filePath
	     */
	    a.getMutantCaseOperations(i).writeMutation("c:/cat/" + i + ".java");
	    /**
	     * this gets the original token
	     */
	    a.getMutantCaseOperations(i).getOriginalToken();
	    
	    /**
	     * returns the start index of the original token
	     */
	    a.getMutantCase(i).getStartIndex();
	    
	    /**
	     * returns the stop index of the original token
	     */
	    a.getMutantCase(i).getStartIndex();
	    
	    /**
	     * returns the replacement token
	     */
	    a.getMutantCase(i).getReplacment();
	}
    }

}
