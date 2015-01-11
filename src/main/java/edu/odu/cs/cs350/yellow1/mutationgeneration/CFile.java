/**
 *	Copyright (C) 2014  Michael Soliman
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

package edu.odu.cs.cs350.yellow1.mutationgeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import edu.odu.cs.cs350.yellow1.grammar.c.*;

/**
 * @author Michael Soliman <msoliman@cs.odu.edu>
 * 
 */

public class CFile implements ICommonMutationOperations {
	private String fileName = new String();
	private String fileContents = new String();
	private ArrayList<String> mutationApplied = new ArrayList<String>();

	public void addToApplied(String text) {
		if (mutationsAppliedContains(text) < 0) {
			mutationApplied.add(text);
		}
	}

	/*
	 * Need to be replace by binarySearch
	 */
	public int mutationsAppliedContains(String searchKeyword) {
		for (int i = 0; i < this.mutationApplied.size(); i++)
			if (mutationApplied.get(i).contains(searchKeyword))
				return i;
		return -1;
	}

	public int getMutationAppliedSize() {
		return this.mutationApplied.size();
	}

	public void readJavaFile(String path, Charset encoding) throws IOException {
		if (path == "")
			throw new IOException("M-S: No file found");
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		fileContents = new String(encoded, encoding);
	}

	public void readJavaFile(String path) throws IOException {
		readJavaFile(path, Charset.defaultCharset());

	}

	private boolean applyTokenTransformation(String original, String by, String operation) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(fileContents.toString());
		CLexer lexer = new CLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CParser parser = new CParser(tokens);
		ParseTree tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard

		for (Integer i = 0; i < tokens.size(); i++) {
			System.out.println("Tokens: " + tokens.get(i).getText().toString());
			if (tokens.get(i).getText().toString().equals(original)) {
				String s1 = ((Integer) tokens.get(i).getStartIndex()).toString();
				String s2 = ((Integer) tokens.get(i).getStopIndex()).toString();
				String uniqId = operation;
				uniqId = uniqId.concat(s1).concat(s2);

				String newName = fileContents.toString();
				if (mutationsAppliedContains(uniqId) < 0) {

					newName = newName.substring(0, tokens.get(i).getStartIndex()) + by + newName.substring(tokens.get(i).getStopIndex() + by.length());
					writeMutation(this.fileName + "." + uniqId, newName);
					this.addToApplied(uniqId);
				}
			}
		}
		return true;
	}

	public String toString() {
		return fileContents;
	}

	private void writeMutation(String fileName, String text) throws Exception {
		File file = new File("testfile/" + "addSub" + fileName + ".out");
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(text);
		output.close();
	}

	/*
	 * <pre> int y = +10; </pre> should be converted to <pre> int y = -10;
	 * </pre> because if the programmer wants it to be converted and he says it
	 * explicity if necessary add another function that will convert the add
	 * sign only and ignore +10
	 * 
	 * @see
	 * edu.odu.cs.cs350.mutationgeneration.CommonMutationOperations#addSub()
	 */
	@Override
	public void addSub() {
		try {
			String original = "" + '+';
			String by = "" + '-';
			applyTokenTransformation(original, by, "addSub");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addMul() {
		try {
			String original = "" + '+';
			String by = "" + '*';
			applyTokenTransformation(original, by, "addMul");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addDiv() {
		try {
			String original = "" + '+';
			String by = "" + '/';
			applyTokenTransformation(original, by, "addDiv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void subAdd() {
		try {
			String original = "" + '-';
			String by = "" + '+';
			applyTokenTransformation(original, by, "subAdd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void subMul() {
		try {
			String original = "" + '-';
			String by = "" + '*';
			applyTokenTransformation(original, by, "subMul");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void subDiv() {
		try {
			String original = "" + '-';
			String by = "" + '/';
			applyTokenTransformation(original, by, "subDiv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void mulAdd() {
		try {
			String original = "" + '*';
			String by = "" + '+';
			applyTokenTransformation(original, by, "mulAdd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void mulSub() {
		try {
			String original = "" + '*';
			String by = "" + '-';
			applyTokenTransformation(original, by, "mulSub");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void mulDiv() {
		try {
			String original = "" + '*';
			String by = "" + '/';
			applyTokenTransformation(original, by, "mulDiv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void divAdd() {
		try {
			String original = "" + '/';
			String by = "" + '+';
			applyTokenTransformation(original, by, "divAdd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void divSub() {
		try {
			String original = "" + '/';
			String by = "" + '-';
			applyTokenTransformation(original, by, "divSub");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void divMul() {
		try {
			String original = "" + '/';
			String by = "" + '*';
			applyTokenTransformation(original, by, "divMul");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ltLe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ltGt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ltGe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ltEqual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gtLt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gtle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gtGe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gtEqual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geLt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geGt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geLe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void geEqual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leLt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leGt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leGe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leEqual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equalLt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equalGt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equalGe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equalLe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void andOr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orAnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ifConditionTrue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ifConditionFalse() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void whileConditionTrue() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void whileConditionFalse() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void executeAll() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void whileIf() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void constantPlusOne() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void constantMinusOne() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void constantBy1001() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void constantBy0999() {
	    // TODO Auto-generated method stub
	    
	}
}
