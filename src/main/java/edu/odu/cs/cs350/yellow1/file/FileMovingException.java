 /**
 *	Copyright (C) 2014  <John Berlin>
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
 *	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
