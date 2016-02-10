package org.rpgleparser.tokens;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

public class WrappedTokenSource implements TokenSource{

	TokenSource tokenSource;
	
	
	public WrappedTokenSource(TokenSource tokenSource) {
		this.tokenSource = tokenSource;
	}

	@Override
	public int getCharPositionInLine() {
		return tokenSource.getCharPositionInLine();
	}

	@Override
	public CharStream getInputStream() {
		return tokenSource.getInputStream();
	}

	@Override
	public int getLine() {
		return tokenSource.getLine();
	}

	@Override
	public String getSourceName() {
		return tokenSource.getSourceName();
	}

	@Override
	public TokenFactory<?> getTokenFactory() {
		return tokenSource.getTokenFactory();
	}

	@Override
	public Token nextToken() {
		return tokenSource.nextToken();
	}

	@Override
	public void setTokenFactory(TokenFactory<?> tokenFactory) {
		tokenSource.setTokenFactory(tokenFactory);
	}

	public TokenSource getTokenSource() {
		return tokenSource;
	}
	
	/** Return a list of all Token objects in input char stream.
	 *  Forces load of all tokens. Does not include EOF token.
	 */
	public List<? extends Token> getAllTokens() {
		List<Token> tokens = new ArrayList<Token>();
		Token t = nextToken();
		while ( t.getType()!=Token.EOF ) {
			tokens.add(t);
			t = nextToken();
		}
		return tokens;
	}

}
