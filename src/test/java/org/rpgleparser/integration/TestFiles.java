package org.rpgleparser.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.utils.FixedWidthBufferedReader;
import org.rpgleparser.utils.TestUtils;
import org.rpgleparser.utils.TreeUtils;
import org.rpgleparser.utils.TestUtils.ErrorListener;

/**
 * Run a test over each *.rpgle file in src/test/resources/org/rpgleparser/tests
 * @author ryaneberly
 *
 */
@RunWith(Parameterized.class)
public class TestFiles {
	
	String sourceFile;
	boolean autoReplaceFailed=false;
	
	public TestFiles(String sourceFile) {
		super();
		this.sourceFile = sourceFile;
		try{
			autoReplaceFailed="Y".equalsIgnoreCase(ResourceBundle.getBundle("org.rpgleparser.tests.test").getString("AutoReplaceFailedTestResults"));
		}catch(Exception e){}
	}
	
	@Test
	public void test() throws IOException, URISyntaxException{
		final String inputString = TestUtils.loadFile(new File("src/test/resources/" + sourceFile));
		final File expectedFile = new File("src/test/resources/" + sourceFile.replaceAll("\\.rpgle", ".expected.txt"));
		final String expectedFileText = expectedFile.exists()?TestUtils.loadFile(expectedFile):null;
		final String expectedTokens = getTokens(expectedFileText);
		String expectedTree = getTree(expectedFileText);
		final List<String> errors = new ArrayList<String>();
        final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(inputString));
        char b[] = new char[10000];
        new FixedWidthBufferedReader(inputString).read(b);
        System.out.println(new String(b).replace('\r','!'));
        System.out.println();
		final RpgLexer lexer = new RpgLexer(input);
        final TokenStream tokens = new CommonTokenStream(lexer);
        final RpgParser parser = new RpgParser(tokens);

        final ErrorListener errorListener = new ErrorListener(errors, lexer, parser);
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);
        
		final String actualTokens = printTokens(lexer);
		if(expectedTokens != null && expectedTokens.trim().length()>0){
			assertEquals("Token lists do not match",expectedTokens,actualTokens);
		}
		lexer.reset();
		
		final ParseTree parseTree = parser.r();
		
		if(!errors.isEmpty()){
			System.out.println("/*===TOKENS===*/\r\n" + actualTokens + "\r\n/*======*/");
		}
		assertThat(errors, is(empty()));
		final String actualTree = TreeUtils.printTree(parseTree, parser);
		
    	if(expectedTree==null || expectedTree.trim().length() == 0){
    		writeExpectFile(expectedFile,actualTokens,actualTree);
    		System.out.println("Tree written to " + expectedFile);
		}else{
			if(autoReplaceFailed && !actualTree.equals(expectedTree)){
				System.out.println("Replaced content of " + expectedFile);
				expectedTree = actualTree;
				writeExpectFile(expectedFile,actualTokens,actualTree);
			}
        	assertEquals("Parse trees do not match",expectedTree,actualTree);
        }
	}

    private void writeExpectFile(File expectedFile, String actualTokens,
			String actualTree) throws IOException {
		final FileOutputStream fos = new FileOutputStream(expectedFile,false);
		fos.write("/*===TOKENS===*/\r\n".getBytes());
		fos.write(actualTokens.getBytes());
		fos.write("\r\n/*===TREE===*/\r\n".getBytes());
		fos.write(actualTree.getBytes());
		fos.write("\r\n/*======*/".getBytes());
		fos.close();
		
	}

	public static String printTokens(RpgLexer lexer){
    	return TestUtils.getTokenString(lexer.getAllTokens(), lexer.getVocabulary());
    }
    
	private String getTokens(String expectedFileText) {
		if(expectedFileText != null && expectedFileText.contains("/*===TOKENS===*/")){
			int startIdx = expectedFileText.indexOf("/*===TOKENS===*/") + 16;
			while(expectedFileText.charAt(startIdx) == '\r' || expectedFileText.charAt(startIdx) == '\n'){
				startIdx++;
			}
			int endIndex = expectedFileText.indexOf("/*===",startIdx);
			if(endIndex > startIdx){
				while(expectedFileText.charAt(endIndex-1) == '\r' || expectedFileText.charAt(endIndex-1) == '\n'){
					endIndex--;
				}
				return expectedFileText.substring(startIdx, endIndex);
			}
		}
		return null;
	}

	private String getTree(String expectedFileText) {
		if(expectedFileText != null && expectedFileText.contains("/*===TREE===*/")){
			int startIdx = expectedFileText.indexOf("/*===TREE===*/") + 14;
			while(expectedFileText.charAt(startIdx) == '\r' || expectedFileText.charAt(startIdx) == '\n'){
				startIdx++;
			}
			int endIndex = expectedFileText.indexOf("/*======*/",startIdx);
			if(endIndex > startIdx){
				while(expectedFileText.charAt(endIndex-1) == '\r' || expectedFileText.charAt(endIndex-1) == '\n'){
					endIndex--;
				}
				return expectedFileText.substring(startIdx, endIndex);
			}
		}
		if(expectedFileText != null && expectedFileText.contains("/*===")){
			return null;
		}
		return expectedFileText;
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
