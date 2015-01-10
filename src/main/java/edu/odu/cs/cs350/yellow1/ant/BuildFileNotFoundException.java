package edu.odu.cs.cs350.yellow1.ant;

/**
 * Named exception for when the build file is not found in the target project
 * @author jberlin
 *
 */
public class BuildFileNotFoundException extends Exception {
	public BuildFileNotFoundException() {
		super("The build file was not found");
	}
	
	public BuildFileNotFoundException(String message) {
		super(message);
	}
}
