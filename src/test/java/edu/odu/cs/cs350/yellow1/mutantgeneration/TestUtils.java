package edu.odu.cs.cs350.yellow1.mutantgeneration;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author msoliman This class contains some methods that one might need in
 *         testing the mutuation generation process
 */
public final class TestUtils {

    /**
     * Delete files with given start string and given end string. For example,
     * to Delete files in the form form of asdf*sdfsd.out.java where '*' is a
     * wildcard CleanDirectory("baseDir/path","asdf","sdfsd.out.java");
     * 
     * @param baseDir
     *            the path of the directory may be absolute path or relative
     *            path
     * @param StartWith
     *            the start string of the files that one wants to delete
     * @param endwith
     *            the end string of the files that one wants to delete
     */
    public static void CleanDirectory(String baseDir, final String StartWith,
	    final String endwith) {
	final File folder = new File(baseDir);
	final File[] files = folder.listFiles(new FilenameFilter() {
	    @Override
	    public boolean accept(final File dir, final String name) {
		return name.matches(StartWith + "*\\" + endwith);
	    }
	});
	for (final File file : files) {
	    if (file.exists())
		if (file.delete())
		    assertTrue(true);

	}

    }

    /**
     * Read file from given file path and returns String
     * 
     * @param filePath
     *            the file path of the file. filePath may be relative path or
     *            absolute path.
     * @return file contents in a String
     */
    public static String readFile(String filePath) throws Exception {
	if (filePath == "")
	    throw new IOException("M-S: No file found");
	byte[] encoded = Files.readAllBytes(Paths.get(filePath));
	return new String(encoded, Charset.defaultCharset());
    }

    public static void echoln(String text) {
	System.out.println(text);
    }

}
