package edu.odu.cs.cs350.yellow1.jar;

/**
 * The targets state denoting exists and does not require creation : createTarget<br>
 * The targets state denoting non-existing and requires creation : targetExists
 * @author jberlin
 *
 */
public enum TargetHandleingState {
	/**
	 * Create the target file or directory
	 */
	createTarget,
	/**
	 * The target file or directory already exists
	 */
	targetExists
}
