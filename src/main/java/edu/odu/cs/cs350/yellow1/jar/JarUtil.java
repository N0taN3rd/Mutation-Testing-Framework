package edu.odu.cs.cs350.yellow1.jar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;

import com.google.common.io.Files;



/**
 * 
 * This class represents utility operations needed on jar files for the mutation testing project
 * It provides the means of moving a jar from a origin directory to another directory denoted target
 * As well as naming options if the jar to be moved need to be given a new name
 * 
 * @author jberlin
 *
 */
public class JarUtil {
	private File origin;
	private File target;
	private String targetpath;
	private File originCop;
	private FilenameFilter ff = FileFilterUtils.suffixFileFilter(".jar");
	private int increment = 1;
	private TargetHandleingState targetState = TargetHandleingState.targetExists;
	private List<String> movedNames;
	
	/**
	 * Construct a new instance of the JarUtil class
	 * This constructor is for when the directory to hold the mutatant jars is already created
	 * @param originJarDir the absolute path to the directory containing the original jar
	 * @param targetJarDir the absolute path to the directory where the jar(s) should be copied to
	 */
	public JarUtil(String originJarDir,String targetJarDir){
		this.origin = new File(originJarDir);
		this.target = new File(targetJarDir);
		this.targetpath = targetJarDir+File.separator;
		this.movedNames = new ArrayList<>();
	}
	
	
	
	/**
	 * Construct a new instance of the JarUtil class 
	 * This constructor is for when the directory to hold the mutatant jars is not created 
	 * set createTargetDirectory to true to create the target directory 
	 * @param originJarDir the absolute path to the directory containing the original jar
	 * @param targetJarDir the absolute path to the directory where the jar(s) should be copied to
	 * @param targetState the manner by which to handle the target {@link TargetHandleingState}
	 */
	public JarUtil(String originJarDir,String targetJarDir,TargetHandleingState targetState) {
		this.origin = new File(originJarDir);
		this.target = new File(targetJarDir);
		this.targetpath = targetJarDir+File.separator;
		this.targetState = targetState;
		this.movedNames = new ArrayList<>();
	}
	
	/**
	 * Look for the existence of the origin and target directories 
	 * if create Target Directory is set to true this method will create the target directory if it does not
	 * exist
	 * @return true if both origin and target exist or if origin exists and target is created otherwise 
	 * @throws FileNotFoundException if the origin and target do not exist 
	 */
	public boolean setUp() throws FileNotFoundException{
		if(!origin.exists())
			throw new FileNotFoundException("Jar Util The origin direcory does not exists path= "+origin.getAbsolutePath());
		if(!origin.isDirectory())
			throw new FileNotFoundException("Jar Util The origin direcory is not a directory at all path= "+origin.getAbsolutePath());
		if(targetState.equals(TargetHandleingState.createTarget)){
			if(!target.exists())
				return target.mkdir();
		}else{
			if(!target.exists())
				throw new FileNotFoundException("Jar Util The target direcory does not exists path= "+target.getAbsolutePath());	
		
			if(!target.isDirectory())
				throw new FileNotFoundException("Jar Util The target direcory is not a directory at all path= "+target.getAbsolutePath());
		}
		return true;
	}
	
	/**
	 * Simply move the jar found in the origin directory to the target directory 
	 * @return true if the move happened
	 * @throws IOException if unable to move 
	 */
	public boolean moveJarToDest() throws IOException {
		for (File f : origin.listFiles(ff)) {
			originCop = f;
			break;
		}
		movedNames.add(originCop.getName());
		Files.copy(originCop, new File(targetpath+originCop.getName()+".jar"));
		return true;
	}
	
	/**
	 * Move the jar found in the origin directory to the target directory renamed to newJarname
	 * @param newJarName the new name the jar found in the origin directory is to be named to
	 * @return true if the move happened
	 * @throws IOException if unable to move 
	 */
	public boolean moveJarToDest(String newJarName) throws IOException {
		for (File f : origin.listFiles(ff)) {
			originCop = f;
			break;
		}
		movedNames.add(targetpath+newJarName+Integer.toString(increment)+".jar");
		Files.copy(originCop, new File(targetpath+newJarName+Integer.toString(increment)+".jar"));
		++increment;
		return true;
	}
	
	/**
	 * Move the jar found in the origin directory to the target directory with original name plus increment number 
	 * @return true if the move happened
	 * @throws IOException if unable to move 
	 */
	public boolean moveJarToDestNumbered() throws IOException {
		for (File f : origin.listFiles(ff)) {
			originCop = f;
			break;
		}
		String name  = originCop.getName();
		String newName = name.substring(0, name.indexOf("."));
		movedNames.add(targetpath+newName+Integer.toString(increment)+".jar");
		Files.copy(originCop, new File(targetpath+newName+Integer.toString(increment)+".jar"));
		++increment;
		return true;
	}
	
	/**
	 * Move the jar found in the origin directory to the target directory renamed to newJarname plus increment number
	 * @param newJarName the new name the jar found in the origin directory is to be named to
	 * @return true if the move happened
	 * @throws IOException if unable to move 
	 */
	public boolean moveJarToDestNumbered(String newJarName) throws IOException {
		for (File f : origin.listFiles(ff)) {
			originCop = f;
			break;
		}
		movedNames.add(targetpath+newJarName+Integer.toString(increment)+".jar");
		Files.copy(originCop, new File(targetpath+newJarName+Integer.toString(increment)+".jar"));
		++increment;
		return true;
	}
	
	
	/**
	 * Delete all created jars in moved to the target directory
	 * @return true if the all jars were deleted successfully otherwise false on first problem
	 */
	public boolean cleanJarsInTargetDir(){
		boolean cleaned = true;
		for (File jar : target.listFiles(ff)) {
			cleaned = jar.delete();
			if(!cleaned)
				return cleaned;
		}
		
		return cleaned;
	}
	
	/**
	 * Get the list of src names moved by this class
	 * @return list of src names moved
	 */
	public List<String> getMovedNames(){
		return movedNames;
	}
	
	/**
	 * Remove the added src names added to the list of moved files
	 */
	public void resetMovedNames(){
		movedNames.clear();
	}
	
	/**
	 * 
	 * @return the value of the increment currently
	 */
	public int getIncrement(){
		return increment;
	}
	
	/**
	 * Set the increment to desired number
	 * @param increment
	 */
	public void setIncrement(int increment) {
		this.increment = increment;
	}

	/**
	 * Get the origin file object
	 * @return the origin file object
	 */
	public File getOrigin() {
		return origin;
	}
	
	
	/**
	 * Get the target file object
	 * @return the target file object
	 */
	public File getTarget() {
		return target;
	}


	/**
	 * Set the target by giving a file object
	 * @param target
	 */
	public void setTarget(File target) {
		this.target = target;
	}


	/**
	 * Get the absolutepath to the target 
	 * @return absolutepath to the target 
	 */
	public String getTargetpath() {
		return targetpath;
	}


	/**
	 * Set the absolutepath to the target 
	 * @param targetpath
	 */
	public void setTargetpath(String targetpath) {
		this.targetpath = targetpath;
	}


	/**
	 * Get the {@link TargetHandleingState} operating in
	 * @return the state operating in
	 */
	public TargetHandleingState getTargetState() {
		return targetState;
	}


	/**
	 * Set the target state to operate in
	 * @param targetState 
	 */
	public void setTargetState(TargetHandleingState targetState) {
		this.targetState = targetState;
	}

}