package org.rpgleparser.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.RContext;

public class TestUtils {
    public static class ErrorListener implements ANTLRErrorListener {

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

    static Vocabulary vocabulary;

    private static void assertParsedTokens(final String paddedInput, final String[] expectedTokens) {
        final List<String> errors = new ArrayList<>();
        final List<CommonToken> tokenList = getParsedTokens(paddedInput, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, expectedTokens);
    }

    public static void assertTokens(final List<CommonToken> parsedTokens, final String... expectedTokens) {
        final Iterator<CommonToken> commonTokens = parsedTokens.iterator();
        final Iterator<String> stringTokens = Arrays.asList(expectedTokens).iterator();

        while (commonTokens.hasNext() || stringTokens.hasNext()) {
            final String message = toString(parsedTokens);
            final String expected = stringTokens.hasNext() ? stringTokens.next().trim() : null;
            final String parsed = commonTokens.hasNext() ? commonTokens.next().getText().trim() : null;
            assertEquals(message, expected, parsed);
        }
    }

    public static void assertTree(final String paddedInput, final String expectedTree) {
        final List<String> errors = new ArrayList<>();
        final RpgParser parser = initialiseParser(paddedInput, errors);
        final ParseTree parseTree = parser.r();
        assertThat(errors, is(empty()));
        if (expectedTree.contains("\n")) {
            assertEquals("The parse trees do not match", expectedTree, TreeUtils.printTree(parseTree, parser));
        } else {// compare with flat tree
            assertEquals("The parse trees do not match", expectedTree, parseTree.toStringTree(parser));
        }
    }

    public static void expectTokensForFreeSnippet(final String input, final String... expectedTokens) {
        final String paddedInput = TestUtils.padSourceLines(input, true);
        assertParsedTokens(paddedInput, expectedTokens);
    }

    public static void expectTokensForSourceLines(final String input, final String... expectedTokens) {
        final String paddedInput = TestUtils.padSourceLines(input, false);
        assertParsedTokens(paddedInput, expectedTokens);
    }

    public static void expectTreeForFreeSnippet(final String input, final String expectedTree) {
        final String paddedInput = TestUtils.padSourceLines(input, true);
        assertTree(paddedInput, expectedTree);
    }

    public static void expectTreeForSourceLines(final String input, final String expectedTree) {
        final String paddedInput = TestUtils.padSourceLines(input, false);
        assertTree(paddedInput, expectedTree);
    }

    public static void fillTokenList(final ParseTree parseTree, final List<CommonToken> tokenList) {
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            final ParseTree payload = parseTree.getChild(i);

            if (payload.getPayload() instanceof CommonToken) {
                tokenList.add((CommonToken) payload.getPayload());
            } else {
                fillTokenList(payload, tokenList);
            }

        }
    }

    public static List<CommonToken> getParsedTokens(final String inputstr, final List<String> errors) {
        return parseAndGetTokens(inputstr, errors, false);
    }

    public static String getTokenString(final List<? extends Token> tokens, final Vocabulary vocabulary) {
        final StringBuilder sb = new StringBuilder();
        for (final Token token : tokens) { // A token from a ParseTree object
            String displayName = vocabulary.getDisplayName(token.getType());
            if (token.getChannel() == 1) {
                displayName = "HIDDEN:" + displayName;
            }
            sb.append(displayName);
            if (displayName.length() < 25) {
                sb.append("                         ".substring(displayName.length(), 25));
            }
            sb.append('<');
            sb.append(token.getText());
            sb.append('>');
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private static void initialiseFrame(final JDialog frame, final JScrollPane viewport) {
        frame.add(viewport);
        frame.setSize(1100, 800);
        frame.setLocation(new Point(200, 100));
        frame.setModal(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static RpgParser initialiseParser(final String inputString, final List<String> errors) {
        ANTLRInputStream input;
        input = new ANTLRInputStream(inputString);
        final RpgLexer lexer = new RpgLexer(input);
        vocabulary = lexer.getVocabulary();
        final TokenStream tokens = new CommonTokenStream(lexer);
        final RpgParser parser = new RpgParser(tokens);

        if (errors != null) {
            final ErrorListener errorListener = new ErrorListener(errors, lexer, parser);
            lexer.addErrorListener(errorListener);
            parser.addErrorListener(errorListener);
        }
        return parser;
    }

    private static TreeViewer initialiseTreeViewer(final RContext parseTree, final RpgParser parser) {
        final TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        viewer.setAutoscrolls(true);
        viewer.setScale(1.5);
        return viewer;
    }

    private static JScrollPane initialiseViewport(final TreeViewer viewer) {
        final JScrollPane viewport = new JScrollPane();
        viewport.setSize(100, 100);
        viewport.setViewportView(viewer);
        return viewport;
    }

    public static String loadFile(final File file) throws IOException {
        final InputStream is = new FileInputStream(file);
        final byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        return new String(b);
    }

    public static String loadFileByPath(final String path) throws IOException {
        return loadFile(new File(path));
    }

    public static String padSourceLines(final String input, final boolean isFreeSnippet) {
        final StringBuilder output = new StringBuilder();
        final String[] lines = input.split("\\r?\\n");
        final String lineStart = "        ";

        for (String line : lines) {
            if (isFreeSnippet && !line.startsWith(lineStart)) {
                line = lineStart + line;
            }
            output.append(String.format("%-112s", line)).append("\r\n");
        }

        return output.toString();
    }

    private static List<CommonToken> parseAndGetTokens(final String inputString, final List<String> errors,
            final boolean gui) {
        final RpgParser parser = initialiseParser(inputString, errors);

        final RpgParser.RContext parseTree = parser.r();
        final List<CommonToken> tokenList = new ArrayList<>();
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            fillTokenList(parseTree.getChild(i), tokenList);
        }

        if (gui) {
            showGUI(parseTree, parser);
        }

        return tokenList;
    }

    public static void printTokens(final List<? extends Token> tokens) {
        System.out.println(getTokenString(tokens, vocabulary));

    }

    public static void printTokens(String inputString, final Boolean isFreeSnippet) {
        if (isFreeSnippet != null) {
            inputString = padSourceLines(inputString, isFreeSnippet.booleanValue());
        }
        final ANTLRInputStream input = new ANTLRInputStream(inputString);
        final RpgLexer lexer = new RpgLexer(input);
        vocabulary = lexer.getVocabulary();
        printTokens(lexer.getAllTokens());
    }

    public static String printTokens(final TokenSource tokenSource, final Vocabulary vocabulary) {
        final List<Token> tokens = new ArrayList<>();
        Token t = tokenSource.nextToken();
        while (t.getType() != Token.EOF) {
            tokens.add(t);
            t = tokenSource.nextToken();
        }
        return getTokenString(tokens, vocabulary);
    }

    private static void showGUI(final RContext parseTree, final RpgParser parser) {
        final JDialog frame = new JDialog();
        final TreeViewer viewer = initialiseTreeViewer(parseTree, parser);
        final JScrollPane viewport = initialiseViewport(viewer);
        initialiseFrame(frame, viewport);
    }

    public static String toString(final List<CommonToken> tokenList) {
        final StringBuilder stringTokens = new StringBuilder();
        final StringBuilder tokenNames = new StringBuilder();
        for (final CommonToken token : tokenList) {
            stringTokens.append(",\"").append(token.getText().trim()).append("\"");
            tokenNames.append(", ").append(vocabulary.getDisplayName(token.getType()));
        }
        return stringTokens.substring(1) + "\n" + tokenNames.substring(1);
    }
}
