package edu.odu.cs.cs350.yellow1.file;

/**
 * A named exception for moving files encounters an exception
 * @author jberlin
 *
 */
public class FileMovingException extends Exception {

	public FileMovingException() {
		super("A file moving exception happened");
	}

	public FileMovingException(String message) {
		super(message);
	}
}
