package edu.odu.cs.cs350.yellow1.jar;

/**
 * The execution state determines if there should be multiple files = multiFile 
 * or only one file = singleFile 
 * @author jberlin
 *
 */
public enum ExecutionState {
	/**
	 * Expect only on file
	 */
	singleFile,
	
	/**
	 * Expect multiple files
	 */
	multiFile
}
