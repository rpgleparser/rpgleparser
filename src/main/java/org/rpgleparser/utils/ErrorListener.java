package org.rpgleparser.utils;

import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;

public  class ErrorListener implements ANTLRErrorListener {

    private final List<String> errors;
    private final RpgLexer lexer;
    private final RpgParser parser;

    public ErrorListener(final List<String> errors, final RpgLexer lexer, final RpgParser parser) {
        this.errors = errors;
        this.lexer = lexer;
        this.parser = parser;
    }

    @Override
    public void reportAmbiguity(final Parser recognizer, final DFA dfa, final int startIndex, final int stopIndex,
            final boolean exact, final BitSet ambigAlts, final ATNConfigSet configs) {
    }

    @Override
    public void reportAttemptingFullContext(final Parser recognizer, final DFA dfa, final int startIndex,
            final int stopIndex, final BitSet conflictingAlts, final ATNConfigSet configs) {
    }

    @Override
    public void reportContextSensitivity(final Parser recognizer, final DFA dfa, final int startIndex,
            final int stopIndex, final int prediction, final ATNConfigSet configs) {
    }

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
            final int charPositionInLine, final String msg, final RecognitionException e) {
        if (recognizer instanceof RpgParser) {
            errors.add("Line: " + line + " " + msg + parser.getDFAStrings());
        } else if (recognizer instanceof RpgLexer) {
            errors.add("Line: " + line + " " + msg);
        }
    }
}
