package org.rpgleparser.utils;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.RContext;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;

public class TestUtils {
    static Vocabulary vocabulary;

    public static String padSourceLines(String input, boolean isSnippet) {
        StringBuilder output = new StringBuilder();
        String[] lines = input.split("\\r?\\n");
        String lineStart = "        ";

        for (String line : lines) {
            if (isSnippet && !line.startsWith(lineStart)) {
                line = lineStart + line;
            }
            output.append(String.format("%-112s", line))
                    .append("\r\n");
        }

        return output.toString();
    }

    public static List<CommonToken> parseInput(String inputstr, final List<String> errors) {
        return runX(inputstr, errors, false);
    }

    public static List<CommonToken> showParseTree(String inputstr) {
        return runX(inputstr, null, true);
    }

    public static List<CommonToken> runX(String inputstr, final List<String> errors, final boolean gui) {
        ANTLRInputStream input = new ANTLRInputStream(inputstr);
        RpgLexer lexer = new RpgLexer(input);
        vocabulary = lexer.getVocabulary();
        TokenStream tokens = new CommonTokenStream(lexer);
        final RpgParser parser = new RpgParser(tokens);

        if (errors != null) {
            ErrorListener errorListener = new ErrorListener(errors, lexer, parser);
            lexer.addErrorListener(errorListener);
            parser.addErrorListener(errorListener);
        }

        RpgParser.RContext entry = parser.r();
        List<CommonToken> tokenList = new ArrayList<CommonToken>();
        for (int i = 0; i < entry.getChildCount(); i++) {
            fillTokenList(entry.getChild(i), tokenList);
        }

        if (gui) {
            for (CommonToken token : tokenList) {
                System.out.println(token + " - " + (token.getType() > 0 ? lexer.getRuleNames()[token.getType() - 1] : ""));
            }
            String tree = entry.toStringTree(parser).replaceAll("\\\\r\\\\n", "\\r\\n\r\n");
            System.out.println(tree);
            if (tree.length() < 1000)
                showGUI(entry, parser);
        }
        return tokenList;
    }

    private static void showGUI(RContext tree, RpgParser parser) {
        JDialog frame = new JDialog();
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewr.setAutoscrolls(true);
        viewr.setScale(1.5);//scale a little

        JScrollPane viewport = new JScrollPane();
        viewport.setSize(100, 100);
        viewport.setViewportView(viewr);

        frame.add(viewport);
        frame.setSize(1100, 800);
        frame.setLocation(new Point(200, 100));
        frame.setModal(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    static void fillTokenList(ParseTree parseTree, List<CommonToken> tokenList) {
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            ParseTree payload = parseTree.getChild(i);

            if (payload.getPayload() instanceof CommonToken) {
                tokenList.add((CommonToken) payload.getPayload());
            } else {
                fillTokenList(payload, tokenList);
            }

        }
    }

    public static String loadFileByPath(String path) throws IOException {
        return loadFile(new File(path));
    }

    public static String loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        return new String(b);
    }

    public static void expectTokensForSourceLines(String input, String... expectedTokens) {
        String paddedInput = TestUtils.padSourceLines(input, false);
        assertParsedTokens(paddedInput, expectedTokens);
    }

    public static void expectTokensForFreeSnippet(String input, String... expectedTokens) {
        String paddedInput = TestUtils.padSourceLines(input, true);
        assertParsedTokens(paddedInput, expectedTokens);
    }

    private static void assertParsedTokens(String paddedInput, String[] expectedTokens) {
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.parseInput(paddedInput, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, expectedTokens);
    }

    public static void assertTokens(List<CommonToken> parsedTokens, String... expectedTokens) {
        Iterator<CommonToken> commonTokens = parsedTokens.iterator();
        Iterator<String> stringTokens = Arrays.asList(expectedTokens).iterator();

        while (commonTokens.hasNext() || stringTokens.hasNext()) {
            String message = toString(parsedTokens);
            String expected = stringTokens.hasNext() ? stringTokens.next().trim() : null;
            String parsed = commonTokens.hasNext() ? commonTokens.next().getText().trim() : null;
            assertEquals(message, expected, parsed);
        }
    }

    public static String toString(List<CommonToken> tokenList) {
        final StringBuilder stringTokens = new StringBuilder();
        final StringBuilder tokenNames = new StringBuilder();
        for (CommonToken token : tokenList) {
            stringTokens.append(",\"").append(token.getText().trim()).append("\"");
            tokenNames.append(", ").append(vocabulary.getDisplayName(token.getType()));
        }
        return stringTokens.substring(1) + "\n" + tokenNames.substring(1);
    }

    private static class ErrorListener implements ANTLRErrorListener {

        private final List<String> errors;
        private final RpgLexer lexer;
        private final RpgParser parser;

        public ErrorListener(List<String> errors, RpgLexer lexer, RpgParser parser) {
            this.errors = errors;
            this.lexer = lexer;
            this.parser = parser;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            if (recognizer instanceof RpgParser) {
                errors.add("Line: " + line + " " + msg + parser.getDFAStrings());
            } else if (recognizer instanceof RpgLexer) {
                errors.add("Line: " + line + " " + msg);
            }
        }

        @Override
        public void reportContextSensitivity(Parser recognizer, DFA dfa,
                                             int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        }

        @Override
        public void reportAttemptingFullContext(Parser recognizer, DFA dfa,
                                                int startIndex, int stopIndex, BitSet conflictingAlts,
                                                ATNConfigSet configs) {
        }

        @Override
        public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex,
                                    int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        }
    }
}
