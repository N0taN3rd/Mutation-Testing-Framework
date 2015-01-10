package edu.odu.cs.cs350.yellow1.mutationgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author msoliman
 * 
 *         This object is a vector of mutation cases
 */
public class MutantCaseVector {
	private ArrayList<MutantCase> mutantcases = new ArrayList<MutantCase>();

	public void add(int startIndex, int stopIndex, String replacment) {

		if (this.contains(startIndex, stopIndex, replacment) < 0)
			this.add(new MutantCase(startIndex, stopIndex, replacment));
	}

	public void add(MutantCase _mc) {
		if (this.contains(_mc.getStartIndex(), _mc.getStopIndex(),
				_mc.getReplacment()) < 0)
			mutantcases.add(_mc);
	}

	public int contains(int startIndex, int stopIndex, String replacment) {
		MutantCase cm = new MutantCase(startIndex, stopIndex, replacment);
		for (int i = 0; i < this.mutantcases.size(); i++)
			if (mutantcases.get(i).equals(cm))
				return i;
		return -1;
	}

	public int getSize() {
		return this.mutantcases.size();
	}

	public MutantCase get(int i) {
		return mutantcases.get(i);
	}

	/**
	 * print the cases in the mutation case vector
	 */
	public void print() {
		for (int i = 0; i < mutantcases.size(); i++) {
			System.out.print(mutantcases.get(i).getStartIndex());
			System.out.print(" ");
			System.out.print(mutantcases.get(i).getStopIndex());
			System.out.print(" ");
			System.out.println(mutantcases.get(i).getReplacment());
		}
	}

}
