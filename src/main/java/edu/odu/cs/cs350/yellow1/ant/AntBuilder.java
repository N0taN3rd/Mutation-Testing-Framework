package edu.odu.cs.cs350.yellow1.ant;

/**
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
