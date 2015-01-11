package edu.odu.cs.cs350.yellow1.ant;

/**
 *	Copyright (C) 2014  John Berlin
 *
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see http://www.gnu.org/licenses/
 *
 * Allow quick and easy construction of the AntRunner class using the builder
 * pattern
 * 
 * @author jberlin
 *
 */
public final class AntBuilder {
	private static String projectDirLoc;
	private static boolean displayOutPut;
	private static boolean requiresInit;

	// use singleton instance for global access to builder
	private static AntBuilder instance = new AntBuilder();

	// hide the constructor for the builder pattern
	private AntBuilder() {

	}

	/**
	 * Set the directory path to be used by the AntRunner to be created
	 * 
	 * @param dirLoc
	 *            the absolutePath to the ant project
	 * @return AntBuilder with the directory path set
	 */
	public static AntBuilder projectDirLocation(String dirLoc) {
		projectDirLoc = dirLoc;
		return instance;
	}

	/**
	 * Set the display output flag to be used by the AntRunner to be created
	 * 
	 * @param display
	 *            display all output if any generated from the ant project
	 * @return AntBuilder with the displayOutPut flag set
	 */
	public static AntBuilder displayOutPut(boolean display) {
		displayOutPut = display;
		return instance;
	}

	/**
	 * Set the call init flag to be used by the AntRunner to be created
	 * 
	 * @param init
	 *            set the call init flag for the AntRuner
	 * @return AntBuilder with the call init flag for set
	 */
	public static AntBuilder requiresInit(boolean init) {
		requiresInit = init;
		return instance;
	}

	/**
	 * Create a new AntRunner with options set
	 * 
	 * @return the created AntRunner instance or null if the project directory field was not supplied
	 */
	public static AntRunner create() {
		if(checkNull())
			return null;
		AntRunner it = new AntRunner(projectDirLoc);
		if (displayOutPut)
			it.displayOutPut(displayOutPut);
		if (requiresInit)
			it.requiresInit(requiresInit);
		reset();
		return it;
	}
	
	private static boolean checkNull(){
		if(projectDirLoc == null)
			return true;
		return false;
	}
	
	// reset feilds
	private static void reset() {
		projectDirLoc = null;
		displayOutPut = false;
		requiresInit = false;
	}

}
