package edu.odu.cs.cs350.yellow1.jar;


/**
 * Allow quick and easy construction of the JarExecutor class using the builder
 * pattern
 * 
 * @author jberlin
 *
 */
public final class JarExecutorBuilder {

	private static String pathToJarsDir;
	private static String pathToLogDir;
	private static String pathToCompareScript;
	private static String pathToTestSuit;
	private static String pathToGold;
	private static ExecutionState executionState;

	//singleton instance of the JarExecutorBuilder
	private static JarExecutorBuilder instance = new JarExecutorBuilder();
	
	//hide the constructor for the singleton pattern
	private JarExecutorBuilder() {

	}

	/**
	 * Set the jar directory path for the JarExecutor to use
	 * 
	 * @param jarDirectoryPath
	 *            the absolute path to directory containing the mutatant jars
	 * @return JarExecutorBuilder with the jar Directory path set
	 */
	public static JarExecutorBuilder pathToJarDirectory(String jarDirectoryPath) {
		JarExecutorBuilder.pathToJarsDir = jarDirectoryPath;
		return instance;
	}

	/**
	 * Set the log directory path for the JarExecutor to use
	 * 
	 * @param logDirectoryPath
	 *            the absolutepath to the log directory
	 * @return JarExecutorBuilder with the log directory path set
	 */
	public static JarExecutorBuilder pathToLogDir(String logDirectoryPath) {
		JarExecutorBuilder.pathToLogDir = logDirectoryPath;
		return instance;
	}

	/**
	 * Set the compare path for the JarExecutor to use
	 * 
	 * @param compareScriptPath
	 *            absolutepath to the compare script
	 * @return JarExecutorBuilder with the compare script path set
	 */
	public static JarExecutorBuilder pathToCompareScript(
			String compareScriptPath) {
		JarExecutorBuilder.pathToCompareScript = compareScriptPath;
		return instance;
	}

	/**
	 * Set the path to the test suite 
	 * 
	 * @param testSuitPath the absolute path to the testsuite 
	 *           
	 * @return JarExecutorBuilder with the path to the testSuite set
	 */
	public static JarExecutorBuilder withTestSuitePath(String testSuitPath) {
		JarExecutorBuilder.pathToTestSuit = testSuitPath; 
		return instance;
	}
	
	
	/**
	 * Set the path to the gold version 
	 * @param pToGold the absolutepath to the gold file
	 * @return JarExecutorBuilder with the path to the gold file set
	 */
	public static JarExecutorBuilder pathToGold(String pToGold){
		JarExecutorBuilder.pathToGold = pToGold;
		return instance;
	}

	
	/**
	 * Set the ExecutionState for the JarExecutor to use
	 * 
	 * @param executionState
	 * @return JarExecutorBuilder with the ExecutionState set
	 */
	public static JarExecutorBuilder withExecutionState(
			ExecutionState executionState) {
		JarExecutorBuilder.executionState = executionState;
		return instance;
	}

	/**
	 * Create a new JarExecutor instance with the options set
	 * if the ExecutionState is not set uses default state in JarExecutor
	 * 
	 * @return the created JarExecutor instance or null if required arguments have not been supplied
	 */
	public static JarExecutor create() {
		
		if(checkNull())
			return null;
		if (executionState != null) {
			JarExecutor it = new JarExecutor(pathToJarsDir, pathToLogDir,
					pathToCompareScript, executionState, pathToTestSuit,pathToGold);
			reset();
			return it;
		} else {
			JarExecutor it = new JarExecutor(pathToJarsDir, pathToLogDir,
					pathToCompareScript, pathToTestSuit,pathToGold);
			reset();
			return it;
		}

	}

	private static boolean checkNull() {
		if (pathToCompareScript == null)
			return true;
		if (pathToJarsDir == null)
			return true;
		if (pathToLogDir == null)
			return true;
		if (pathToTestSuit == null)
			return true;
		if (pathToGold == null)
			return true;
		return false;
	}
	
	// return JarExecutorBuilder to begining state
	private static void reset() {
		pathToCompareScript = null;
		pathToJarsDir = null;
		pathToLogDir = null;
		pathToTestSuit = null;
		pathToGold = null;
		executionState = null;
	}

}
