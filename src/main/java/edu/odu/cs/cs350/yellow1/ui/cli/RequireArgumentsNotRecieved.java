package edu.odu.cs.cs350.yellow1.ui.cli;

/**
 * Named exception for when the required arguments are not supplied by the user
 * @author jberlin
 *
 */
public class RequireArgumentsNotRecieved extends Exception {

	public RequireArgumentsNotRecieved() {
		super("The arguments required were not given to program");
	}

	public RequireArgumentsNotRecieved(String message) {
		super(message);
	}

	

}
