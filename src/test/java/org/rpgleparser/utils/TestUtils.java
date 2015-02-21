package org.rpgleparser.utils;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.junit.Assert;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.RContext;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class TestUtils {
    public static String _pad280(String input) {
        return (input + "                                                                                                                  ").substring(0, 112);
    }

    public static String pad280(String input) {
        BufferedReader r = new BufferedReader(new StringReader(input));
        StringBuilder output = new StringBuilder();
        String line = null;
        try {
            while ((line = r.readLine()) != null) {
                output.append(_pad280(line)).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static String pad280Free(String input) {
        BufferedReader r = new BufferedReader(new StringReader(input));
        StringBuilder output = new StringBuilder();
        String line = null;
        try {
            while ((line = r.readLine()) != null) {
                if (!line.startsWith("        ")) {
                    line = "       " + line;
                }
                output.append(_pad280(line)).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void showToks(List<CommonToken> tokenList) {
        for (CommonToken tok : tokenList) {
            System.out.println(tok);
        }
    }

    public static List<CommonToken> runX(String inputstr) {
        List<String> errors = new ArrayList<String>();
        List<CommonToken> retval = runX(inputstr, errors, false, false);
        if (errors.size() > 0) {
            throw new RuntimeException(errors.toString());
        }
        return retval;
    }

    public static List<CommonToken> runX(String inputstr, final List<String> errors) {
        return runX(inputstr, errors, false, false);
    }

    public static List<CommonToken> runXQuietly(String inputstr, final List<String> errors) {
        return runX(inputstr, errors, true, false);
    }

    public static List<CommonToken> runXGUI(String inputstr) {
        return runX(inputstr, null, true, true);
    }

    public static List<CommonToken> runX(String inputstr, final List<String> errors, final boolean quietly, final boolean gui) {
        ANTLRInputStream input = new ANTLRInputStream(inputstr);
        RpgLexer lexer = new RpgLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        final RpgParser parser = new RpgParser(tokens);

        if (errors != null) {
            parser.addErrorListener(new ANTLRErrorListener() {

                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol, int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    errors.add("Line:" + line + " " + msg + parser.getDFAStrings());

                }

                @Override
                public void reportContextSensitivity(Parser recognizer, DFA dfa,
                                                     int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void reportAttemptingFullContext(Parser recognizer, DFA dfa,
                                                        int startIndex, int stopIndex, BitSet conflictingAlts,
                                                        ATNConfigSet configs) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex,
                                            int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
                    // TODO Auto-generated method stub

                }
            });
        }
        RpgParser.RContext entry = parser.r();
        List<CommonToken> tokenList = new ArrayList<CommonToken>();

        for (int i = 0; i < entry.getChildCount(); i++) {
            doTokenList(entry.getChild(i), tokenList);
        }

        if (gui || (errors != null && errors.size() > 0 && !quietly)) {

            for (CommonToken tok : tokenList) {
                System.out.println(tok + " - " + (tok.getType() > 0 ? lexer.getRuleNames()[tok.getType() - 1] : ""));
            }
            String tree = entry.toStringTree(parser).replaceAll("\\\\r\\\\n", "\\r\\n\r\n");
            System.out.println(tree);

            if (tree.length() < 1000)
                showGUI(entry, parser);
        }
        return tokenList;
    }

    private static void showGUI(RContext tree, RpgParser parser) {
        //JFrame frame = new JFrame("Antlr AST");
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
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    static void doTokenList(ParseTree parseTree, List<CommonToken> tokenList) {
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            ParseTree payload = parseTree.getChild(i);

            if (payload.getPayload() instanceof CommonToken) {
                tokenList.add((CommonToken) payload.getPayload());
            } else {
                doTokenList(payload, tokenList);
            }

        }
    }

    public static String loadFile(String path) throws IOException {
        InputStream is = TestUtils.class.getResourceAsStream(path);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        return new String(b);
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

    public static void assertTokens(List<CommonToken> tokenList, String... tokens) {
        Iterator<CommonToken> toks = tokenList.iterator();
        String stok = null;
        for (String tok : tokens) {
            if (!toks.hasNext())
                return;
            if (!tok.trim().equals((stok = toks.next().getText()).trim())) {
                String message = toString(tokenList);
                System.out.println(message);
                Assert.assertEquals(message, tok.trim(), stok.trim());
            }
        }
    }

    public static String toString(List<CommonToken> tokenList) {
        final StringBuilder b = new StringBuilder();
        final Iterator<CommonToken> toks = tokenList.iterator();
        while (toks.hasNext()) {
            b.append(",\"").append(toks.next().getText().trim()).append("\"");
        }
        return b.toString().substring(1);
    }
}
