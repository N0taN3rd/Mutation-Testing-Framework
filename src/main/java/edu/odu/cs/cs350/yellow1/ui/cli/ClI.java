 /**
 *	Copyright (C) 2014  John Berlin, James Ord
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

package edu.odu.cs.cs350.yellow1.ui.cli;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.collections4.map.HashedMap;
/**
 * 
 * @author jberlin
 * @author jord
 */
public class ClI {
	private String[] args;
	private Options options;
	private CommandLineParser clp;
	private CommandLine cmd;
	private Map<String,String> recievedArgutments;
	private Set<String> requiredArgs;
	
	/**
	 * Construct a new Instance of the commandline parser
	 * @param args arguments user inputed
	 */
	public ClI(String[] args) {
		this.args = args;
		recievedArgutments = new HashedMap<>();
		requiredArgs = new HashSet<>();
		this.options = getOptions();
		requiredArgs.add("src");
		requiredArgs.add("mutF");
		requiredArgs.add("testSte");
		requiredArgs.add("goldOut");
	}

	@SuppressWarnings("static-access")
	private static Options getOptions() {
		Options opts = new Options();
		opts.addOption("help", false, "Display help information");
		Option src = OptionBuilder.withArgName("src folder")
				.hasArg()
				.withDescription("The parent folder for the coding project")
				.create("src");
		Option zipd = OptionBuilder
				.hasArg()
				.withArgName("zipped src folder").hasArg()
				.withDescription("the src folder is zipped y:n").create('z');
		Option mutFile = OptionBuilder
				.hasArg()
				.withArgName("file to be mutated").hasArg()
				.withDescription("The file name to be mutated. Requires full path").create("mutF");
		Option testSuitePath = OptionBuilder
				.hasArg()
				.withArgName("project test suite").hasArg()
				.withDescription("The test suite for the project. Requires full path").create("testSte");
		Option goldOutput = OptionBuilder
				.hasArg()
				.withArgName("gold version output").hasArg()
				.withDescription("The output for the gold program. Requires full path").create("goldOut");
		opts.addOption(src);
		opts.addOption(zipd);
		opts.addOption(mutFile);
		opts.addOption(testSuitePath);	
		opts.addOption(goldOutput);
		return opts;
	}
	
	/**
	 * Process the arguments received from the user
	 * If the required arguments are not received throws an exception<br> with a message containing the list of argument not received 
	 * @throws RequireArgumentsNotRecieved 
	 */
	public void parse() throws RequireArgumentsNotRecieved {
		clp = new BasicParser();
	
		try {
			cmd = clp.parse(options, args);
			
			if (cmd.hasOption("help")) {
				HelpFormatter formater = new HelpFormatter();
				formater.printHelp("-src [SOURCE FOLDER] -mutF [SOURCE FILE] -testSte [TEST SUITE PATH] -goldOut [GOLD VERSION OUTPUT]" , options);
				System.out.println("To read output log: [Changed File] ,, [Start Index] ,, [Stop Index] ,, [Original Token] ,, [Mutated Token] ;;");
				System.exit(0);
			}else{
				for(Option option : cmd.getOptions()){
					System.out.println(option.getOpt()+" "+option.getValue());
					recievedArgutments.put(option.getOpt(), option.getValue());
				}
			}
			
			for(String key : recievedArgutments.keySet()){
				if(requiredArgs.contains(key)){
					requiredArgs.remove(key);
				}
			}
			
			if(!requiredArgs.isEmpty()){
				throw new RequireArgumentsNotRecieved("The required arguments: "+requiredArgs.toString()+" were not recieved");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Get the received arguments paired to the -name 
	 * @return arguments received
	 */
	public Map<String,String> getRecievedArguments(){
		return recievedArgutments;
	}
}
