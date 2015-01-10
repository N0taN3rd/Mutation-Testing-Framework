package edu.odu.cs.cs350.yellow1.ant;

/**
 * Named exception for when a user chooses a target to execute and it is not found 
 * in the ant build file
 * @author jberlin
 *
 */
public class TargetNotFoundException extends Exception {

	public TargetNotFoundException() {
		super("The desired target was not found in the build file");
	}

	public TargetNotFoundException(String message) {
		super(message);
	
	}

	

	

}
