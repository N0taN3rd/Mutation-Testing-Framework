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
 */

package edu.odu.cs.cs350.yellow1.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.io.Files;

/**
 *Utility class that moves all files and there contents in a origin directory
 * to the target file
 * 
 * @author jberlin
 *
 */
public class FileMover {
	
	private static final Logger logger = LogManager.getLogger(FileMover.class);
	private File origin;
	private File target;
	private byte[] restoreTarget;
	private boolean noDelete = false;
	private Queue<File> moveFiles;
	private FileFilter ff = FileFilterUtils.suffixFileFilter(".java");
	
	
	/**
	 * 
	 * @param orginDirectoryPath
	 *            the absolute path way to the directory holding the files to
	 *            move
	 * @param targetFilePath 
	 *            the absolute path to the file to receive the contents of the
	 *            origin file(s)
	 */
	public FileMover(String orginDirectoryPath, String targetFilePath) throws FileNotFoundException {
		this.origin = new File(orginDirectoryPath);
		this.target = new File(targetFilePath);		
	}

	/**
	 * 
	 * @param orginDirectoryPath
	 *            the absolute path way to the directory holding the files to
	 *            move
	 * @param targetFilePath
	 *            the absolute path to the file to receive the contents of the
	 *            origin file(s)
	 * @param noDelete
	 *            true|false if the mutated files are to be persevered true
	 *            otherwise false
	 */
	public FileMover(String orginDirectoryPath, String targetFilePath,
			boolean noDelete) throws FileNotFoundException {
		this.origin = new File(orginDirectoryPath);
		this.target = new File(targetFilePath);
		this.noDelete = noDelete;	
	}
	
	/**
	 * Call this method once before any operations to
	 * @return true if setup was complete
	 * @throws FileNotFoundException
	 */
	public boolean setUp() throws FileNotFoundException{
		if (!origin.exists()){
			logger.error("FileMover: The Origin direcory does not exists path= "
					+ origin.getAbsolutePath());
			throw new FileNotFoundException(
					"FileMover: The Origin direcory does not exists path= "
							+ origin.getAbsolutePath());
		}
		if (!target.exists()){
			logger.error("FileMover: The target file does not exists path= " + target.getAbsolutePath());
			throw new FileNotFoundException(
					"FileMover: The target file does not exists path= " + target.getAbsolutePath());
		}	
		try {
			restoreTarget = Files.toByteArray(target);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Set the FileMover to work with another file and outputDirectory pair from absolutepaths
	 * calls {@link FileMover#setUp()} and {@link FileMover#pullFiles()}
	 * @param orginDirectoryPath   the absolute path way to the directory holding the files to
	 *            move
	 * @param targetFilePath  the absolute path to the file to receive the contents of the
	 *            origin file(s)
	 * @throws FileNotFoundException 
	 */
	public void setNewOriginTarget(String orginDirectoryPath,
			String targetFilePath) throws FileNotFoundException {
		this.origin = new File(orginDirectoryPath);
		this.target = new File(targetFilePath);
		moveFiles.clear();
		setUp();
		pullFiles();
	}
	
	/**
	 * Set the FileMover to work with another file and outputDirectory file object pair
	 * calls {@link FileMover#setUp()} and {@link FileMover#pullFiles()}
	 * @param origin Origin File Object 
	 * @param target Target File Object
	 * @throws FileNotFoundException
	 */
	public void setNewOriginTarget(File origin, File target) throws FileNotFoundException {
		this.target = target;
		this.origin = origin;
		moveFiles.clear();
		setUp();
		pullFiles();
	}
	
	/**
	 * Set the noDelete flag 
	 * @param noDelete
	 */
	public void setNoDelete(boolean noDelete){
		this.noDelete = noDelete;
	}

	/**
	 * 
	 * @return true|false true if there are more files to be moved otherwise
	 *         false
	 */
	public boolean hasMoreFiles() {
		return !moveFiles.isEmpty();
	}
	
	/**
	 * Retrieve files in the target directory only after setup return true
	 */
	public void pullFiles(){
		moveFiles = new ArrayDeque<File>(Arrays.asList(origin.listFiles(ff)));
		System.out.println("Pulling files");
	}

	/**
	 * Move current file 
	 * Deletes the files if noDelete is false otherwise the files to be moved
	 * are preserved
	 * 
	 * @return true|false if the current file is moved otherwise false
	 * @throws FileMovingException
	 */
	public boolean moveNextFile() throws FileMovingException  {
		try {
			File moveMe = moveFiles.poll();
			if (!moveMe.isFile())
				return false;
			Files.copy(moveMe, target);
			if(!noDelete)
				moveMe.delete();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new FileMovingException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return the number of files to be moved in the origin directory
	 */
	public int getFileToBeMovedCount(){
		return moveFiles.size();
	}
	
	/**
	 * Restore the target file back to original state before moving any file contents to it
	 * @return true if restore was successful false if an exception was thrown
	 */
	public boolean restorTarget(){
		try {
			Files.write(restoreTarget, target);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}
		
		return true;
	}
	

}