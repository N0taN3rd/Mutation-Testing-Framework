package edu.odu.cs.cs350.yellow1.ant;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Hashtable;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;

/**
 * <br>This class is responsible for building and running all ant projects pragmatically
 * <br>Tasks able to be execute using this class expect the ant naming convention found at <a href="https://wiki.apache.org/ant/TheElementsOfAntStyle">wiki.apache.org/ant</a>
 * <nl> and expects a target named "run" to run the executable produced by the target compile, build or dist
 * 
 * @author jberlin
 */
public class AntRunner {
	
	private File projectFolder;
	private File buildFile;
	private Hashtable<String,Target> targets;
	private Project antProject;
	private ProjectHelper projectHelp;
	private DefaultLogger consoleLogger;
	private boolean displayOutPut = true;
	private boolean requiresInit = false;
	private FileFilter ff = FileFilterUtils.suffixFileFilter(".xml");
	
	/**
	 * flag for project requires init task to be called 
	 */
	public static int INIT = 1;
	
	/**
	 * flag to display all output from ant
	 */
	public static int OUTPUT = 2;
	
	public static int ALL = 3;

	
	/**
	 * Constructs a new AntRunner with default flag 0
	 * @param projectFolderLoc the absolute path way to the project folder
	 */
	public AntRunner(String projectFolderLoc){
		projectFolder = new File(projectFolderLoc);
		System.out.println(projectFolder.getAbsolutePath());
	}
	
	/**
	 * Constructs a new AntRunner with flags
	 * To set flags use 
	 * @param projectFolderLoc
	 * @param flags 
	 */
	public AntRunner(String projectFolderLoc, int flags){
		projectFolder = new File(projectFolderLoc);
		switch(flags){
		case 1:
			requiresInit = true;
			break;
		case 2:
			displayOutPut = true;
			break;
		case 3:
			requiresInit = true;
			displayOutPut = true;
			break;
		}
	}
	
	/**
	 * Set the flag for displaying the output from ant
	 * @param displayOutPut
	 */
	public void displayOutPut(boolean displayOutPut){
		this.displayOutPut = displayOutPut;
	}
	
	/**
	 * Set the flag for calling init in setup
	 * @param requiresInit
	 */
	public void requiresInit(boolean requiresInit){
		this.requiresInit = requiresInit;
	}
	
	
	/**
	 * Discover the build file and set up project execution
	 * @throws BuildFileNotFoundException
	 * @throws FileNotFoundException 
	 */
	public void setUp() throws BuildFileNotFoundException, FileNotFoundException{
			
		if(!projectFolder.isDirectory())
			throw new FileNotFoundException("The project folder "+projectFolder.getName()+" is not a directory");
		
		for(File f : projectFolder.listFiles(ff)){
			System.out.println(f.getName());
			if(f.getName().equals("build.xml"))
				buildFile = f;
				break;
		}
	
		if(buildFile == null)
			throw new BuildFileNotFoundException("The build file for project at: "+projectFolder.getAbsolutePath()+" can not be found");
		
		antProject = new Project();
		antProject.init();
		antProject.setUserProperty("ant.file",buildFile.getAbsolutePath());
		
		projectHelp = ProjectHelper.getProjectHelper();
		antProject.addReference("ant.projectHelper", projectHelp);
		
		projectHelp.parse(antProject, buildFile);
		targets = antProject.getTargets();
		
		if(displayOutPut){
			consoleLogger = new DefaultLogger();
			consoleLogger.setErrorPrintStream(System.err);
			consoleLogger.setOutputPrintStream(System.out);
			consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
			antProject.addBuildListener(consoleLogger);
		}
		
		if(requiresInit)
			antProject.executeTarget(targets.get("init").getName());
			
	}
	
	/**
	 * Execute the target all
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean all() throws TargetNotFoundException, BuildException {
		Target t = targets.get("all");
		if (t == null)
			throw new TargetNotFoundException(
					"The target all was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}
	
	/**
	 * Execute the target compile
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean compile() throws TargetNotFoundException, BuildException {
		Target t = targets.get("compile");
		if (t == null)
			throw new TargetNotFoundException(
					"The target compile was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}
	
	/**
	 * Execute the target build
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean build() throws TargetNotFoundException, BuildException {
		Target t = targets.get("build");
		if (t == null)
			throw new TargetNotFoundException(
					"The target build was not found in the build file");
		antProject.fireBuildStarted();
		antProject.executeTarget(t.getName());
		return true;
	}
	
	/**
	 * Execute the target test
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean test() throws TargetNotFoundException, BuildException {
		Target t = targets.get("test");
		if (t == null)
			throw new TargetNotFoundException(
					"The target test was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}

	/**
	 *  Execute the target run
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean run() throws TargetNotFoundException, BuildException {
		Target t = targets.get("run");
		if (t == null)
			throw new TargetNotFoundException(
					"The target run was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}

	/**
	 * Execute the target clean
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean clean() throws TargetNotFoundException, BuildException {
		Target t = targets.get("clean");
		if (t == null)
			throw new TargetNotFoundException(
					"The target clean was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}
	
	/**
	 * Execute the target cleanall
	 * @return true if execution of the task was successful 
	 * @throws TargetNotFoundException
	 * @throws BuildException
	 */
	public boolean cleanAll() throws TargetNotFoundException, BuildException {
		Target t = targets.get("cleanall");
		if (t == null)
			throw new TargetNotFoundException(
					"The target cleanall was not found in the build file");
		antProject.executeTarget(t.getName());
		return true;
	}
	
	/**
	 * 
	 * @return  true|false if the the AntBuilder is set to display building output
	 */
	public boolean isDisplayingOutPut(){
		return displayOutPut;
	}
	
	/**
	 * 
	 * @return true|false if the the AntBuilder is set to require intit
	 */
	public boolean isRequiringInIt(){
		return requiresInit;
	}
	
	/**
	 * 
	 * @return the path to the project
	 */
	public String getProjectPath(){
		return projectFolder.getAbsolutePath();
	}
}
