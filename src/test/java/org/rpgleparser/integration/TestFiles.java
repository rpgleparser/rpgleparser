package org.rpgleparser.integration;

import static java.util.ResourceBundle.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.tokens.PreprocessTokenSource;
import org.rpgleparser.utils.FixedWidthBufferedReader;
import org.rpgleparser.utils.TestUtils;
import org.rpgleparser.utils.TestUtils.ErrorListener;
import org.rpgleparser.utils.TreeUtils;

/**
 * Run a test over each *.rpgle file in src/test/resources/org/rpgleparser/tests
 * @author ryaneberly
 *
 */
@RunWith(Parameterized.class)
public class TestFiles {
	
	private File sourceFile;
	private boolean autoReplaceFailed=false;
	private static String singleTestName=null;
	static{
		try{
			singleTestName= getBundle("org.rpgleparser.tests.test").getString("RunSingleTest");
		}catch(Exception ignored){}
	}

	public TestFiles(File sourceFile) {
		super();
		this.sourceFile = sourceFile;
		try {
			autoReplaceFailed="Y".equalsIgnoreCase(getBundle("org.rpgleparser.tests.test").getString("AutoReplaceFailedTestResults"));
		} catch(Exception ignore){ /* */ }
	}
	
	@Test
	public void test() throws IOException, URISyntaxException{
		final String inputString = TestUtils.loadFile(sourceFile);
		final File expectedFile = new File(sourceFile.getPath().replaceAll("\\.rpgle", ".expected.txt"));
		final String expectedFileText = expectedFile.exists()?TestUtils.loadFile(expectedFile):null;
		final String expectedTokens = getTokens(expectedFileText);
		String expectedTree = getTree(expectedFileText);
		final List<String> errors = new ArrayList<String>();
        final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(inputString));
		final RpgLexer rpglexer = new RpgLexer(input);
        final TokenSource lexer = new PreprocessTokenSource(rpglexer);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
                
        final RpgParser parser = new RpgParser(tokens);

        final ErrorListener errorListener = new ErrorListener(errors, rpglexer, parser);
        rpglexer.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);
        
		final String actualTokens = TestUtils.printTokens(lexer,rpglexer.getVocabulary());
        boolean rewriteExpectFile=false;
		if(expectedTokens != null && expectedTokens.trim().length()>0 ){
			if(autoReplaceFailed && !expectedTokens.equals(actualTokens)){
				rewriteExpectFile=true;
			}else{
				assertEquals("Token lists do not match",expectedTokens,actualTokens);
			}
		}
		rpglexer.reset();
		
		parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
		parser.reset();
		final ParseTree parseTree = parser.r();
		
		final String actualTree = TreeUtils.printTree(parseTree, parser);
		if(!errors.isEmpty()){
			System.out.println("/*===TOKENS===*/\n" + actualTokens + "\n");
			System.out.println("/*===TREE===*/\n" + actualTree + "\n/*======*/");
		}
		assertThat(errors, is(empty()));
		
    	if(expectedTree==null || expectedTree.trim().length() == 0||rewriteExpectFile){
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
		fos.write("/*===TOKENS===*/\n".getBytes());
		fos.write(actualTokens.getBytes());
		fos.write("\n/*===TREE===*/\n".getBytes());
		fos.write(actualTree.getBytes());
		fos.write("\n/*======*/".getBytes());
		fos.close();
		
	}

	public static String printTokens(RpgLexer lexer){
    	return TestUtils.getTokenString(lexer.getAllTokens(), lexer.getVocabulary());
    }
    
	private String getTokens(String expectedFileText) {
		if(expectedFileText != null && expectedFileText.contains("/*===TOKENS===*/")){
			int startIdx = expectedFileText.indexOf("/*===TOKENS===*/") + 16; // notice + 16 at the end, len of the searched text
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
    	final List<File> listing = new ArrayList<File>();
    	fillResourceListing(new File("src/test/resources/org/rpgleparser/tests"), listing);
    	for(File s: listing){
			retval.add(new Object[]{s});
		}
		return retval;
	}
	
	private static void fillResourceListing(File file, List<File> retval)  {
    	if(file != null){
    		if(file.isDirectory()){
    			for(File subfile: file.listFiles()){
    				fillResourceListing(subfile, retval);
    			}
    		}else if(file.getName().toLowerCase().endsWith(".rpgle")){
    			if(singleTestName == null || singleTestName.equals(file.getName())){
    				retval.add(file);
    			}
    			else if(singleTestName.equals("*LAST")){
    				if(retval.size()==0 || file.lastModified() > retval.get(0).lastModified()){
    					retval.clear();
    					retval.add(file);
    				}
    			}
    		}
    	}
    }
}
