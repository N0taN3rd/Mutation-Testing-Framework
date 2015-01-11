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
