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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Antlr Library imports
 */
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.v4.codegen.model.chunk.ThisRulePropertyRef_ctx;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * antlr generated java files imports
 */
import edu.odu.cs.cs350.yellow1.grammar.java.JavaLexer;
import edu.odu.cs.cs350.yellow1.grammar.java.JavaParser;

/**
 * apache common lang imports
 */
import org.apache.commons.lang3.math.NumberUtils;

/**
 log4j imports
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Michael Soliman <msoliman@cs.odu.edu> This object is responsible for
 *         generating mutuants for given javafile
 * 
 * 
 *         It is expected from this class to generate mutations that is
 *         incorrect in syntax and leads to compilation failure
 *
 */
public class JavaFile implements ICommonMutationOperations {
	private String filePath = new String();
	private String fileContents = new String();
	MutantCaseVector mvc = null;
	/** see the constructor */

	private static final Logger log = LogManager.getLogger(JavaFile.class);

	/**
	 * @return file name
	 */
	public String getFileName() {
		return Paths.get(this.filePath).getFileName().toString();
	}

	/**
	 * Initializes new mutant case vector
	 */
	public JavaFile() {
		mvc = new MutantCaseVector();
	}

	/**
	 * if one have a group of mutant cases, one can use it and to it more mutant
	 * cases
	 * 
	 * @param _mvc
	 */

	public JavaFile(MutantCaseVector _mvc) {
		this.mvc = _mvc;
	}

	/**
	 * @author msoliman
	 *
	 */
	public enum EOL {
		DOS, NIX;
	}

	/**
	 * changes file EOL to Windows/Dos Format
	 */
	public void setWindowsLineEnd() {
		/**
		 * preserve original CRLFs
		 */
		this.fileContents = this.fileContents.replaceAll("\\r\\n",
				"cat45413xdsfgds");

		/**
		 * Changes all EOLs to CRLFs
		 */
		this.fileContents = this.fileContents.replaceAll("\\n",
				"cat45413xdsfgds");
		this.fileContents = this.fileContents.replaceAll("\\r",
				"cat45413xdsfgds");

		/**
		 * Restore original CRLFs
		 */
		this.fileContents = this.fileContents.replaceAll("cat45413xdsfgds",
				"\r\n");
	}

	public void setNixLineEnd() {
		this.fileContents = this.fileContents.replaceAll("\\r\\n", "\n");
		this.fileContents = this.fileContents.replaceAll("\\r", "\n");
	}

	/**
	 * 
	 * @return a vector for Mutant Cases
	 */
	public MutantCaseVector getMutantCaseVector() {
		return mvc;
	}

	/**
	 * 
	 * @param i
	 *            the index of the mutant case
	 * @return one mutant case given its index
	 */
	public MutantCase getMutantCase(int i) {
		return mvc.get(i);
	}

	public MutantCase.Operations getMutantCaseOperations(int i) {
		return mvc.get(i).applyAgainst(fileContents);
	}

	/**
	 * Open the file and load its contents to this class
	 * 
	 * @param path
	 *            path the absolute path or relative path
	 * @param provides
	 *            encoding the file encoding method
	 * @throws IOException
	 */

	public void readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		this.fileContents = new String(encoded, encoding);
		this.filePath = path;
	}

	/**
	 * Open the file and load its contents to this class
	 * 
	 * @param path
	 *            the absolute path or relative path
	 * @throws IOException
	 */
	public void readFile(String path) throws IOException {
		readFile(path, Charset.defaultCharset());

	}

	/**
	 * Open the file and load its contents to this class then forces the line
	 * endings to NIX or DOS
	 * 
	 * @param path
	 *            the absolute path or relative path
	 * @throws IOException
	 */
	public void readFile(String path, EOL a) throws IOException {
		readFile(path, Charset.defaultCharset());
		if (a == EOL.DOS)
			this.setWindowsLineEnd();
		else if (a == EOL.NIX)
			this.setNixLineEnd();

	}

	/**
	 * change tokens and add the mutuant cases to the mutant case vector
	 * 
	 * @param originalthe
	 *            original token
	 * 
	 * @param by
	 *            the replacement token
	 * 
	 * @param operation
	 *            the operation name
	 * 
	 * 
	 *            it is acceptable int y = +10; to be converted to int y = /10;
	 *            because it will make the mutant project compilation fail and
	 *            this mutation will be discarded because the compilation has
	 *            failed
	 * 
	 * 
	 */
	private boolean applyTokenTransformation(String original, String by,
			String operation) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(fileContents.toString());
		JavaLexer lexer = new JavaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		JavaParser parser = new JavaParser(tokens);

		ParseTree tree = parser.compilationUnit();

		TokenRewriteStream trs = new TokenRewriteStream();

		ParseTreeWalker walker = new ParseTreeWalker(); // create standard

		for (Integer i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).getText().toString().equals(original)) {
				int startIndex = tokens.get(i).getStartIndex();
				int stopIndex = tokens.get(i).getStopIndex() + 1;
				mvc.add(startIndex, stopIndex, by);

			}
		}
		return true;
	}

	/**
	 * 
	 * @param text
	 * @return if the token was in the shape of "string" it returns true
	 */
	private boolean isTokenString(String text) {
		return String.valueOf(text.charAt(0)).equals('"')
				&& String.valueOf(text.charAt(text.length() - 1)).equals('"');
	}

	/**
	 * returns the file contents which have loaded
	 */
	public String toString() {
		return fileContents;
	}

	/**
	 * Changes addition with subtraction and add the mutant cases to the mutant
	 * case vector
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

	/**
	 * Changes addition with multiplication
	 * 
	 */
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

	/**
	 * Changes addition with division
	 */
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

	/**
	 * Changes subtraction with addition
	 */
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
	/**
	 * Replaces the < to <=
	 */
	@Override
	public void ltLe() {
		try {
			String original = "" + "<";
			String by = "" + "<=";
			applyTokenTransformation(original, by, "ltLe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces the < to >
	 */
	@Override
	public void ltGt() {
		try {
			String original = "" + "<";
			String by = "" + ">";
			applyTokenTransformation(original, by, "ltLe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Replaces < by >=
	 */
	@Override
	public void ltGe() {
		try {
			String original = "" + "<";
			String by = "" + ">=";
			applyTokenTransformation(original, by, "ltLe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces < by ==
	 */
	@Override
	public void ltEqual() {
		try {
			String original = "" + "<";
			String by = "" + "==";
			applyTokenTransformation(original, by, "ltEqual");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Replaces > by <
	 */
	@Override
	public void gtLt() {
		try {
			String original = "" + ">";
			String by = "" + "<";
			applyTokenTransformation(original, by, "gtLt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces > by <=
	 */
	@Override
	public void gtle() {
		try {
			String original = "" + ">";
			String by = "" + "<=";
			applyTokenTransformation(original, by, "gtle");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	/**
	 * Replaces > by >=
	 */
	@Override
	public void gtGe() {
		try {
			String original = "" + ">";
			String by = "" + ">=";
			applyTokenTransformation(original, by, "gtGe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces > by ==
	 */
	@Override
	public void gtEqual() {
		try {
			String original = "" + ">";
			String by = "" + "==";
			applyTokenTransformation(original, by, "gtEqual");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Replaces >= by <
	 */
	@Override
	public void geLt() {
		try {
			String original = "" + ">=";
			String by = "" + "<";
			applyTokenTransformation(original, by, "geLt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Replaces >= by >
	 */
	@Override
	public void geGt() {
		try {
			String original = "" + ">=";
			String by = "" + ">";
			applyTokenTransformation(original, by, "geGt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces >= by <=
	 */
	@Override
	public void geLe() {
		try {
			String original = "" + ">=";
			String by = "" + "<=";
			applyTokenTransformation(original, by, "geLe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces >= by ==
	 */
	@Override
	public void geEqual() {
		try {
			String original = "" + ">=";
			String by = "" + "==";
			applyTokenTransformation(original, by, "geEqual");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * replaces <= to <
	 */
	@Override
	public void leLt() {
		try {
			String original = "" + "<=";
			String by = "" + "<";
			applyTokenTransformation(original, by, "leLt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Replaces <= by >
	 */
	@Override
	public void leGt() {
		try {
			String original = "" + "<=";
			String by = "" + ">";
			applyTokenTransformation(original, by, "leGt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Replaces <= by >=
	 */
	@Override
	public void leGe() {
		try {
			String original = "" + "<=";
			String by = "" + ">=";
			applyTokenTransformation(original, by, "leGe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Replaces <= by ==
	 */
	@Override
	public void leEqual() {
		try {
			String original = "" + "<=";
			String by = "" + "==";
			applyTokenTransformation(original, by, "leEqual");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces == by <
	 */
	@Override
	public void equalLt() {
		try {
			String original = "" + "==";
			String by = "" + "<";
			applyTokenTransformation(original, by, "equalLt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces == by >
	 */
	@Override
	public void equalGt() {
		try {
			String original = "" + "==";
			String by = "" + ">";
			applyTokenTransformation(original, by, "equalGt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Replaces == by >=
	 */
	@Override
	public void equalGe() {
		try {
			String original = "" + "==";
			String by = "" + ">=";
			applyTokenTransformation(original, by, "equalGe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Replaces == by <=
	 */
	@Override
	public void equalLe() {
		try {
			String original = "" + "==";
			String by = "" + "<=";
			applyTokenTransformation(original, by, "equalLe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Changes boolean and operator with boolean or operator
	 */
	@Override
	public void andOr() {
		try {
			String original = "" + "&&";
			String by = "" + "||";
			applyTokenTransformation(original, by, "andOr");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * changes boolean Or operator with boolean And operator
	 */
	@Override
	public void orAnd() {
		try {
			String original = "" + "||";
			String by = "" + "&&";
			applyTokenTransformation(original, by, "orAnd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeNot() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param blockstart
	 *            accepts if or while
	 * 
	 * @param set
	 *            accepts true or false
	 * 
	 *            Replaces the condition after [blockstart] by [set]
	 */
	private void applyConditionTransformation(String blockstart, String set) {
		int lparen = 0;
		int rparen = 0;
		int startIndex = -1;
		int stopIndex = -1;
		boolean ifLock = false;

		ANTLRInputStream input = new ANTLRInputStream(fileContents.toString());
		JavaLexer lexer = new JavaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		ParseTree tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard
		for (Integer i = 0; i < tokens.size(); i++) {

			if (tokens.get(i).getText().toString().equals(blockstart)) {
				ifLock = true;
			}

			if (ifLock == true
					&& tokens.get(i).getText().toString().equals("(")) {
				lparen++;

				if (startIndex == -1)
					startIndex = tokens.get(i).getStartIndex();
			}

			if (ifLock == true
					&& tokens.get(i).getText().toString().equals(")"))

			{

				rparen++;

			}

			if (lparen != 0 && rparen != 0 && lparen == rparen
					&& ifLock == true) {
				ifLock = false;
				lparen = 0;
				rparen = 0;

				stopIndex = tokens.get(i).getStopIndex() + 1;

				String by = "(" + set + ")";

				this.mvc.add(startIndex, stopIndex, by);
				startIndex = -1;
				stopIndex = -1;
			}

		}

	}

	/**
	 * this function loads the mutation log and intialize the class
	 * 
	 * @param mutationLogFilePath
	 *            the file path of the mutation log
	 * @param eol
	 *            is not related to the mutation log by any way. It is related
	 *            to how one wants to read the source code file.
	 * @throws Exception
	 */
	public void readMutationsLog(String mutationLogFilePath, EOL eol)
			throws Exception {
		/**
		 * remove all line endings
		 */
		String mutationsLog = new String(Files.readAllBytes(Paths
				.get(mutationLogFilePath))).replaceAll("\\n", "").replaceAll(
				"\\r", "");
		/**
		 * set the file path of the file
		 */
		this.filePath = mutationsLog.split(";;")[0].split(",,")[0];
		/**
		 * read the file and set the file contents
		 */
		this.readFile(this.filePath, eol);

		String[] lines = mutationsLog.split(";;");
		for (int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split(",,");
			if (!this.filePath.equals(parts[0].toString()))
				throw new Exception("M-S corrupted mutation log file ");

			mvc.add((int) Integer.parseInt(parts[1].toString()),
					(int) Integer.parseInt(parts[2].toString()),
					parts[4].toString());
		}

	}

	/**
	 * 
	 * @param filePath
	 *            relative or absolute file path where one wants the report to
	 *            be saved. one must apply the file name within the file path
	 * @param eol
	 *            Line ending either DOS or *NIX. It is asks one how does one
	 *            want the mutation log line ending format to be either nix or
	 *            dos.
	 * 
	 * @throws Exception
	 */
	public void writeMutationsLog(String filePath, EOL eol) throws Exception {
		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		for (int i = 0; i < mvc.getSize(); i++) {
			sb.append(this.filePath);
			sb.append(",,");
			sb.append(this.getMutantCase(i).getStartIndex());
			sb.append(",,");
			sb.append(this.getMutantCase(i).getStopIndex());
			sb.append(",,");
			sb.append(this.getMutantCaseOperations(i).getOriginalToken());
			sb.append(",,");
			sb.append(this.getMutantCase(i).getReplacment());
			sb.append(";; ");
			sb.append(i+1);
			if (i < mvc.getSize() - 1)
				if (eol == EOL.DOS)
					sb.append("\r\n");
				else if (eol == EOL.NIX)
					sb.append("\n");

			BufferedWriter output = new BufferedWriter(new FileWriter(new File(
					filePath)));
			output.write(sb.toString());
			output.close();
		}
		log.debug(sb.toString());
	}

	@Override
	public void ifConditionTrue() {
		this.applyConditionTransformation("if", "true");
	}

	@Override
	public void ifConditionFalse() {
		this.applyConditionTransformation("if", "false");

	}

	@Override
	public void whileConditionTrue() {
		this.applyConditionTransformation("while", "true");

	}

	@Override
	public void whileConditionFalse() {
		this.applyConditionTransformation("while", "false");

	}

	/**
	 * executes all the mutations operations sequentially
	 */
	@Override
	public void executeAll() {
		this.addSub();
		this.addMul();
		this.addDiv();

		this.subAdd();
		this.subMul();
		this.subDiv();

		this.mulAdd();
		this.mulSub();
		this.mulDiv();

		this.divAdd();
		this.divSub();
		this.divMul();

		// < <=
		this.ltLe();

		// < >
		this.ltGt();

		// < >=
		this.ltGe();

		// < ==
		this.ltEqual();

		// > <
		this.gtLt();

		// > <=
		this.gtle();

		// > >=
		this.gtGe();

		// > ==
		this.gtEqual();

		// >= <
		this.geLt();

		// >= >
		this.geGt();

		// >= <=
		this.geLe();

		// >= ==
		this.geEqual();

		// <= <
		this.leLt();

		// <= >
		this.leGt();

		// <= >=
		this.leGe();

		// <= ==
		this.leEqual();

		// == <
		this.equalLt();

		// == >
		this.equalGt();

		// == >=
		this.equalGe();

		// == <=
		this.equalLe();

		this.andOr();
		this.orAnd();
		this.removeNot();

		this.ifConditionTrue();
		this.ifConditionFalse();

		this.whileConditionTrue();
		this.whileConditionFalse();
		this.whileIf();
		this.constantPlusOne();
		this.constantMinusOne();
		this.constantBy1001();
		this.constantBy0999();
	}

	/**
	 * changes while token to If
	 */
	@Override
	public void whileIf() {
		try {
			String original = "while";
			String by = "if";
			applyTokenTransformation(original, by, "whileIf");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void constantOperations(String required) {
		ANTLRInputStream input = new ANTLRInputStream(fileContents.toString());
		JavaLexer lexer = new JavaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		JavaParser parser = new JavaParser(tokens);

		ParseTree tree = parser.compilationUnit();

		TokenRewriteStream trs = new TokenRewriteStream();

		ParseTreeWalker walker = new ParseTreeWalker(); // create standard

		for (Integer i = 0; i < tokens.size(); i++) {
			if (NumberUtils.isNumber(tokens.get(i).getText().toString())) {
				int startIndex = tokens.get(i).getStartIndex();
				int stopIndex = tokens.get(i).getStopIndex() + 1;
				String by = fileContents.substring(startIndex, stopIndex)
						+ required;
				mvc.add(startIndex, stopIndex, by);
			}
		}
	}

	/**
	 * Generate mutant cases where every constant has one added to it
	 */
	@Override
	public void constantPlusOne() {
		this.constantOperations(" + 1");

	}

	/**
	 * Generate mutant cases where every constant has one subtracted from it
	 */
	@Override
	public void constantMinusOne() {
		this.constantOperations(" - 1");
	}

	/**
	 * Generate mutant cases where every constant has 1.001 multiplied to it
	 */
	@Override
	public void constantBy1001() {
		this.constantOperations(" * 1.001");

	}

	/**
	 * Generate mutant cases where every constant has 0.999 multiplied to it
	 */
	@Override
	public void constantBy0999() {
		this.constantOperations(" * 0.999");

	}
}
