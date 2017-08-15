package org.rpgleparser.tokens;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

public class WrappedTokenSource implements TokenSource {

    TokenSource tokenSource;

    public WrappedTokenSource(final TokenSource tokenSource) {
        this.tokenSource = tokenSource;
    }

    /**
     * Return a list of all Token objects in input char stream. Forces load of
     * all tokens. Does not include EOF token.
     */
    public List<? extends Token> getAllTokens() {
        final List<Token> tokens = new ArrayList<>();
        Token t = nextToken();
        while (t.getType() != Token.EOF) {
            tokens.add(t);
            t = nextToken();
        }
        return tokens;
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

    public TokenSource getTokenSource() {
        return tokenSource;
    }

    @Override
    public Token nextToken() {
        return tokenSource.nextToken();
    }

    @Override
    public void setTokenFactory(final TokenFactory<?> tokenFactory) {
        tokenSource.setTokenFactory(tokenFactory);
    }

}
