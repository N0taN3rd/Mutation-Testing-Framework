package edu.odu.cs.cs350.yellow1.mutationgeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author msoliman This class stores the mutant case as start Index, stop
 *         index, and replacement. it does not store the original text or the
 *         original token string
 * 
 *         If one have the start index, stop index, and the original text, one
 *         can get the original token.
 */
public class MutantCase {
	private int startIndex;
	private int stopIndex;
	private String replacment;

	public MutantCase(int startIndex, int stopIndex, String replacment) {
		this.startIndex = startIndex;
		this.stopIndex = stopIndex;
		this.replacment = replacment;
	}

	/**
	 * 
	 * @return the start index of the original token
	 */

	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 
	 * @return the stop index of the original token
	 */
	public int getStopIndex() {
		return stopIndex;
	}

	/**
	 * 
	 * @return the replacement
	 */
	public String getReplacment() {
		return replacment;
	}

	/**
	 * passes text to MutantCase.Operations
	 * 
	 * @param _text
	 * @return MutantCase.Operations object
	 */
	public Operations applyAgainst(String _text) {
		Operations o = new Operations(_text);
		return o;
	}

	/**
	 * Checks if this mutant case is equal to another one
	 * 
	 * @param mc
	 *            another MutantCase
	 * @return true if the two MutantCases match
	 */

	public boolean equals(MutantCase mc) {
		return (this.startIndex == mc.getStartIndex()
				&& this.stopIndex == mc.getStopIndex() && this.replacment == mc
					.getReplacment());
	}

	/**
	 * After passing text here you can get the original token string
	 * 
	 * @author msoliman
	 *
	 */
	public class Operations {
		String text;

		/**
		 * This function get the original token string after applying it to some
		 * text. it get the start index of the token, the stop index from the
		 * parent class which is MutantCase
		 * 
		 * @return the original token string
		 */
		public String getOriginalToken() {
			return this.text.substring(getStartIndex(), getStopIndex());
		}

		/**
		 * Constructor for the operations class. the purpose of this constructor
		 * is to pass the applyAgainst String to this class
		 * 
		 * @param _text
		 */
		public Operations(String _text) {
			this.text = _text;
		}

		/**
		 * This function writes mutuant file to the disk it repalces the
		 * original string given its start character index and its end character
		 * index with the replacement string which can be got by call the
		 * function getReplacment()
		 * 
		 * @param filePath
		 *            where to save the mutant java file. java file name is
		 *            included
		 * 
		 */

		public void writeMutation(String filePath) {
			try {
				File file = new File(filePath);
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				this.text.substring(getStartIndex(), getStopIndex());
				String newName = this.text;
				newName = newName.substring(0, getStartIndex())
						+ getReplacment() + newName.substring(getStopIndex());
				output.write(newName);
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
