package org.rpgleparser.tokens;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.WritableToken;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.utils.FixedWidthBufferedReader;

public class PreprocessTokenSource extends TransformTokenSource{

	protected CopyBookProvider copyBookProvider=null;

	public PreprocessTokenSource(TokenSource tokenSource, Lexer inclLexer) {
		super(tokenSource, inclLexer);
	}

	public PreprocessTokenSource(TokenSource tokenSource, CopyBookProvider copyBookProvider) {
		super(tokenSource);
		this.copyBookProvider=copyBookProvider;
	}

	List<Integer> CHECKED_TOKENS = Arrays.asList(new Integer[] {
			RpgLexer.DIR_IF,RpgLexer.DIR_ELSEIF, RpgLexer.DIR_ELSE,RpgLexer.DIR_ENDIF ,RpgLexer.DIR_DEFINE,RpgLexer.DIR_COPY });

	/*
	 * This method can change the token,
	 * Replace it, or remove it from the stream (return null)
	 */
	public Token queryToken(final Token nextToken){
		
		if(directives.size()>0 && !ifCondition){
			hide(nextToken);
		}
		if(nextToken.getType() != RpgLexer.DIRECTIVE){
			return nextToken;
		}
		Token currentToken = getTokenSource().nextToken();
		if(directives.size()>0 && !ifCondition){
			hide(currentToken);
		}
		if(!CHECKED_TOKENS.contains(currentToken.getType())){
			//hide tokens that are in an IF directive that is not true
			addToken(currentToken);
			return nextToken;
		}
		hide(nextToken);
		hideAndAdd(currentToken);
		if(currentToken.getType() == RpgLexer.DIR_IF || currentToken.getType() == RpgLexer.DIR_ELSEIF){
			consumeIfDirective(currentToken);
		}
		else if(currentToken.getType() == RpgLexer.DIR_ELSE){
			consumeElseDirective(currentToken);
		}
		else if(currentToken.getType() == RpgLexer.DIR_ENDIF){
			consumeEndIfDirective(currentToken);
		}
		else if(currentToken.getType() == RpgLexer.DIR_DEFINE){
			consumeDefineDirective(currentToken);
		}
		else if(currentToken.getType() == RpgLexer.DIR_UNDEFINE){
			consumeUnDefineDirective(currentToken);
		}
		else if(currentToken.getType() == RpgLexer.DIR_COPY){
			consumeCopyDirective(currentToken);
		}
		return nextToken;
	}
	

	List<Integer> IF_TOKENS = Arrays.asList(new Integer[] {
			RpgLexer.EOF,RpgLexer.EOL, RpgLexer.DIR_NOT, RpgLexer.DIR_DEFINE, RpgLexer.DIR_OtherText });
	List<Integer> OTHERTEXT_TOKENS = Arrays.asList(new Integer[] {RpgLexer.EOF,RpgLexer.EOL, RpgLexer.DIR_OtherText });
	List<Integer> EOL_TOKENS = Arrays.asList(new Integer[] {
			RpgLexer.EOF,RpgLexer.EOL});
	List<Integer> EOL_OR_TEXT_TOKENS = Arrays.asList(new Integer[] {
			RpgLexer.EOF,RpgLexer.EOL,RpgLexer.DIR_OtherText,RpgLexer.StringContent});
	
	Set<String> defined = new HashSet<String>();
	Stack<String> directives = new Stack<String>();
	boolean ifCondition = false;
	boolean ifConditionWasMatched = false;
	
	private void consumeIfDirective(Token currentToken) {
		boolean negate = false;
		ifCondition = false;
		ifConditionWasMatched = false;
		if(currentToken.getType() == RpgLexer.DIR_IF){
			directives.push("IF");
		}
		currentToken = consumeAndHideUntil(IF_TOKENS);
        if(currentToken.getType() == RpgLexer.DIR_NOT){
    		hideAndAdd(currentToken);
    		negate = true;
        }
        currentToken = getTokenSource().nextToken();
        hideAndAdd(currentToken);
        if(currentToken.getType() == RpgLexer.DIR_DEFINED){
        	currentToken = consumeAndHideUntil(OTHERTEXT_TOKENS);
            if(!ifConditionWasMatched && currentToken.getType() == RpgLexer.DIR_OtherText){
            	ifCondition = defined.contains(currentToken.getText());
            	if (negate){
            		ifCondition = !ifCondition;
            	}
            }
        }
        ifConditionWasMatched = ifConditionWasMatched || ifCondition;
        consumeAndHideUntil(EOL_TOKENS);
	}
	private void consumeElseDirective(Token currentToken) {
		ifCondition = !ifConditionWasMatched;
        consumeAndHideUntil(EOL_TOKENS);
	}
	private void consumeCopyDirective(Token currentToken) {
		Token otherTextToken = consumeAndHideUntil(EOL_OR_TEXT_TOKENS);
		List<Token> copyTokens = new ArrayList<Token>();
		while(otherTextToken != null && !EOL_TOKENS.contains(otherTextToken.getType())){
			hideAndAdd(otherTextToken);
			copyTokens.add(otherTextToken);
			otherTextToken = consumeAndHideUntil(EOL_OR_TEXT_TOKENS);
		}
		if(copyTokens.size()>0){
			try{
				String inputString = copyBookProvider != null? copyBookProvider.lookup(copyTokens):null;
				final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(inputString));
				final RpgLexer rpglexer = new RpgLexer(input);
				tokenQueue.addAll(rpglexer.getAllTokens());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private void consumeEndIfDirective(Token currentToken) {
		directives.pop();
		consumeAndHideUntil(EOL_TOKENS);
        ifCondition = false;
	}
	
	private void consumeDefineDirective(Token currentToken) {
        if(currentToken.getType() == RpgLexer.DIR_DEFINE){
        	currentToken = getTokenSource().nextToken();
        	if(currentToken.getType() == RpgLexer.DIR_OtherText){
        		defined.add(currentToken.getText());
        		hideAndAdd(currentToken);
            }
        	consumeAndHideUntil(EOL_TOKENS);
        }
	}
	private void consumeUnDefineDirective(Token currentToken) {
        if(currentToken.getType() == RpgLexer.DIR_UNDEFINE){
        	currentToken = getTokenSource().nextToken();
        	if(currentToken.getType() == RpgLexer.DIR_OtherText){
        		defined.remove(currentToken.getText());
        		hideAndAdd(currentToken);
            }
    		consumeAndHideUntil(EOL_TOKENS);
        }
	}

	
	private Token consumeAndHideUntil(List<Integer> stopAt) {
		Token currentToken =null;
        while (!stopAt.contains((currentToken = getTokenSource().nextToken()).getType())) {
    		hideAndAdd(currentToken);
        }
        return currentToken;
	}

	private void hide(final Token t){
        if ( t instanceof WritableToken ) {
            ((WritableToken)t).setChannel(RpgLexer.HIDDEN);
        }		
		
	}
	
	private void hideAndAdd(final Token t){
        if ( t instanceof WritableToken ) {
            ((WritableToken)t).setChannel(RpgLexer.HIDDEN);
        }		
        addToken(t);
	}
    public static String loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        return new String(b);
    }
    
    public static interface CopyBookProvider{
    	String lookup(List<Token> copy);
    }
    /**
     * Default implementation of the provider which returns the file in a given folder.
     * Note: the file must have the .copy extension.
     * @author Ryan
     *
     */
    public static class FileFolderCopyBookProvider implements CopyBookProvider{

    	File sourceFolder;
		public FileFolderCopyBookProvider(File sourceFolder) {
			super();
			if(sourceFolder.isFile())
				this.sourceFolder = sourceFolder;
			else
				this.sourceFolder = sourceFolder.getParentFile();
		}
		@Override
		public String lookup(List<Token> copy) {
			String sourceMember = copy.get(copy.size()-1).getText();
			try{
				File sourceFile = new File(sourceFolder.getPath() + File.separator+sourceMember + ".copy");
				return loadFile(sourceFile);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
    }
}
