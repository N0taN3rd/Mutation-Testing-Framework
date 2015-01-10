package edu.odu.cs.cs350.yellow1.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * This class runs all mutants and tests via {@link ExecuteJar}. 
 * <br>The running of each ExecuteJar is done in parallel using {@link ExecutorService} with a thread pool of size
 * <b>Max(2,availableProcessors/2)</b> since most cpus are now dual core
 * <br>Expects multiple mutant jars to be present for execution for best results
 * @author jberlin
 *
 */
public class JarExecutor {

	private static final Logger logger = LogManager
			.getLogger(JarExecutor.class);
	
	private FilenameFilter jarFilter = FileFilterUtils.suffixFileFilter(".jar");

	private File jarDir;
	private File logDir;

	private List<ExecutionResults> mutationTestingResults;
	private Queue<File> jarsToExecute;
	
	private String pathToJarsDir;
	private String pathToLogDir;
	private String pathToCompareScript;
	private String pathToTestSuit;
	private String pathToGold;

	private int mutantsKilled = 0;
	private int mutantsNotKilled = 0;
	private int logsCreated = 0;
	private int numTests = 0;

	private ExecutionState executionState = ExecutionState.multiFile;

	/**
	 * Construct a new JarExecutor instance with specified
	 * {@link ExecutionState}
	 * 
	 * @param pathToJarsDir
	 *            absolutePath to the directory holding the created jars
	 * @param pathToLogDir
	 *            absolutePath to the directory where to captured output should
	 *            be placed
	 * @param pathToCompareScript
	 *            the absolutePath to the compare script
	 * @param executionState
	 *            the {@link ExecutionState} for the jarExecutor to be in
	 * @param pathToTestSuite
	 *            absolutePath to testSuite
	 */
	public JarExecutor(String pathToJarsDir, String pathToLogDir,
			String pathToCompareScript, ExecutionState executionState,
			String pathToTestSuite,String pathToGold) {
		this.pathToTestSuit = pathToTestSuite;
		this.pathToJarsDir = pathToJarsDir;
		this.pathToLogDir = pathToLogDir;
		this.pathToCompareScript = pathToCompareScript;
		this.pathToGold = pathToGold;
		this.jarDir = new File(pathToJarsDir);
		this.logDir = new File(pathToLogDir);
		this.executionState = executionState;
		this.mutationTestingResults = new ArrayList<>();
	}

	/**
	 * Construct a new JarExecutor instance with default ExecutionState
	 * {@link ExecutionState#multiFile}
	 * 
	 * @param pathToJarsDir
	 *            absolutePath to the directory holding the created jars
	 * @param pathToLogDir
	 *            absolutePath to the directory where to captured output should
	 *            be placed
	 * @param pathToCompareScript
	 *            the absolutePath to the compare script
	 * @param pathToTestSuite
	 *            absolutePath to testSuite
	 */
	public JarExecutor(String pathToJarsDir, String pathToLogDir,
			String pathToCompareScript, String pathToTestSuite,String pathToGold) {
		this.pathToTestSuit = pathToTestSuite;
		this.pathToJarsDir = pathToJarsDir;
		this.pathToLogDir = pathToLogDir;
		this.pathToGold = pathToGold;
		this.pathToCompareScript = pathToCompareScript;
		this.jarDir = new File(pathToJarsDir);
		this.logDir = new File(pathToLogDir);
		this.executionState = ExecutionState.multiFile;
		this.mutationTestingResults = new ArrayList<>();
	}

	/**
	 * Set the execution state for the
	 * 
	 * @param executionsState
	 */
	public void setExecutionState(ExecutionState executionsState) {
		this.executionState = executionsState;
	}

	/**
	 * Set up the executor by looking for and getting the jars and then check to
	 * see if the log directory <br>
	 * exists if not create it
	 * 
	 * @return if set up was successful
	 */
	public boolean setUp() {
		if (executionState.equals(ExecutionState.multiFile)) {
			jarsToExecute = new ArrayDeque<File>();
			jarsToExecute.addAll(Arrays.asList(jarDir.listFiles(jarFilter)));
			if (jarsToExecute.isEmpty()) {
				logger.error("JarExecutor no jars were found in jar directory");
				return false;
			}

		} else {
			if (jarDir.listFiles(jarFilter).length <= 0) {
				logger.error("JarExecutor executing in singleFile mode found no jar");
				return false;
			}

		}

		if (!logDir.exists()) {
			return logDir.mkdir();
		}
		
		
		
		return true;
	}

	/**
	 * Concurrently run all jars created with the test suite
	 * For each test in the suite run them against every mutant using 
	 * <br>For Each mutant create an {@link ExecuteJar} then execute them and 
	 * gather information from the results which are available through other method calls to this class
	 * @return true
	 */
	public boolean start() {
		ExecutorService threadPool = Executors.newFixedThreadPool(Math.max(2,
				Runtime.getRuntime().availableProcessors() / 2));
		List<Callable<ExecutionResults>> tasks = new ArrayList<>();

		//since the only operations on the shared files are reads it 
		//is relatively safe to do so in parallel 
		File testDir = new File(pathToTestSuit);
		List<File> tests = Arrays.asList(testDir.listFiles());
		numTests = tests.size();
		while (!jarsToExecute.isEmpty()) {
			File executeJar = jarsToExecute.poll();
			tasks.add(new ExecuteJar(executeJar,
					pathToGold, pathToLogDir,
					tests));
		}

		try {
			List<Future<ExecutionResults>> results = threadPool
					.invokeAll(tasks);
			for (Future<ExecutionResults> rets : results) {
				ExecutionResults result = rets.get();
				mutationTestingResults.add(result);
				System.out.println(result.toString());
				if (result.isKilled()) {
					++mutantsKilled;
				} else
					++mutantsNotKilled;
				
				logsCreated += result.getStandardErrOutput().size() + result.getStandardOutput().size();
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage());
			// e.printStackTrace();
		}
	
		return true;
	}

	/**
	 * Get the results of the mutation testing
	 * 
	 * @return List of ExecutionResults representing the execution of each
	 *         mutant
	 */
	public List<ExecutionResults> getMutationTestingResults() {
		return mutationTestingResults;
	}

	/**
	 * Get the number of successful executions ie killed
	 * 
	 * @return number of successful executions
	 */
	public int getNumberOfMutantsKilled() {
		return mutantsKilled;
	}

	/**
	 * Get the number of failed executions ie not killed by any test
	 * 
	 * @return number of failed executions
	 */
	public int getNumberOfMutantsNotKilled() {
		return mutantsNotKilled;
	}

	/**
	 * Get the number of logs created
	 * 
	 * @return number of logs created
	 */
	public int getNumberOfLogsCreated() {
		return logsCreated;
	}

	/**
	 * Get the number of tests in the test suite
	 * 
	 * @return number of tests in the test suite
	 */
	public int getNumberOfTests() {
		return numTests;
	}

	@Override
	public String toString() {
		return "JarExecutor [mutantsKilled=" + mutantsKilled
				+ ", mutantsNotKilled=" + mutantsNotKilled + ", logsCreated="
				+ logsCreated + ", numTests=" + numTests + "]";
	}
}
