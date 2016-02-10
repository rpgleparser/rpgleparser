package org.rpgleparser.tokens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;

public class TransformTokenSource extends WrappedTokenSource {

	final Lexer includesLexer;
	final Queue<Token> tokenQueue = new LinkedList<Token>();
	
	public TransformTokenSource(final TokenSource tokenSource) {
		super(tokenSource);
		includesLexer=null;
	}
	public TransformTokenSource(final TokenSource tokenSource, final Lexer inclLexer) {
		super(tokenSource);
		includesLexer = inclLexer;
	}
	@Override
	public Token nextToken() {
		while(true){
			if (!tokenQueue.isEmpty()){
				return tokenQueue.poll();
			}
			Token nextToken = super.nextToken();
			if(nextToken == null || nextToken.getType() == Lexer.EOF){
				return nextToken;
			}
			nextToken = queryToken(nextToken);
			if(nextToken != null){
				return nextToken;
			}
		}
	}
	
	public void addToken(final Token token){
		tokenQueue.add(token);
	}
	
	/*
	 * This method can change the token,
	 * Replace it, or remove it from the stream (return null).
	 * Derived classes should override this method.
	 */
	public Token queryToken(final Token nextToken){
		return nextToken;
	}

}
