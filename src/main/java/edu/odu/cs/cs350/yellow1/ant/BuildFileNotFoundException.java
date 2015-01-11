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
 * Named exception for when the build file is not found in the target project
 * @author jberlin
 *
 */
public class BuildFileNotFoundException extends Exception {
	public BuildFileNotFoundException() {
		super("The build file was not found");
	}
	
	public BuildFileNotFoundException(String message) {
		super(message);
	}
}
