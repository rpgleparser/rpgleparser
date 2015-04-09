package org.rpgleparser.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.rpgleparser.RpgParser;
import org.rpgleparser.utils.TestUtils;
import org.rpgleparser.utils.TreeUtils;

/**
 * Run a test over each *.rpgle file in src/test/resources/org/rpgleparser/tests
 * @author ryaneberly
 *
 */
@RunWith(Parameterized.class)
public class TestFiles {
	
	String sourceFile;
	final boolean autoReplaceFailed;
	
	public TestFiles(String sourceFile) {
		super();
		this.sourceFile = sourceFile;
		autoReplaceFailed = "Y".equalsIgnoreCase(System.getProperty("AutoReplaceFailedTest", "N"));
	}
	
	@Test
	public void test() throws IOException, URISyntaxException{
		final String inputString = TestUtils.loadFile(new File("src/test/resources/" + sourceFile));
		final File expectedFile = new File("src/test/resources/" + sourceFile.replaceAll("\\.rpgle", ".expected.tree.txt"));
		String expectedTree = expectedFile.exists()?TestUtils.loadFile(expectedFile):null;
		final List<String> errors = new ArrayList<String>();
		final RpgParser parser = TestUtils.initialiseParser(inputString, errors);
		final ParseTree parseTree = parser.r();

        assertThat(errors, is(empty()));
    	if(expectedTree==null || expectedTree.trim().length() == 0){
    		System.out.println("Tree written to " + expectedFile);
			final FileOutputStream fos = new FileOutputStream(expectedFile,false);
			fos.write(TreeUtils.printTree(parseTree, parser).getBytes());
			fos.close();
		}else{
			final String actualTree = TreeUtils.printTree(parseTree, parser);
			if(autoReplaceFailed && !actualTree.equals(expectedTree)){
				System.out.println("Replaced content of " + expectedFile);
				expectedTree = actualTree;
				final FileOutputStream fos = new FileOutputStream(expectedFile,false);
				fos.write(actualTree.getBytes());
				fos.close();
			}
        	assertEquals("Parse trees do not match",expectedTree,actualTree);
        }
	}

	@Parameterized.Parameters(name="{index}{0}")
	public static Collection<Object[]> primeNumbers() throws URISyntaxException, IOException {
		final ArrayList<Object[]> retval = new ArrayList<Object[]>();
    	final List<String> listing = new ArrayList<String>();
    	fillResourceListing(new File("src/test/resources/org/rpgleparser/tests"), listing);
    	for(String s: listing){
			retval.add(new Object[]{s});
		}
		return retval;
	}
	
    private static void fillResourceListing(File file, List<String> retval)  {
    	if(file != null){
    		if(file.isDirectory()){
    			for(File subfile: file.listFiles()){
    				fillResourceListing(subfile, retval);
    			}
    		}else if(file.getName().toLowerCase().endsWith(".rpgle")){
    			retval.add(file.getPath().replaceAll(".*resources.", "").replace('\\', '/'));
    		}
    	}
    }
}
