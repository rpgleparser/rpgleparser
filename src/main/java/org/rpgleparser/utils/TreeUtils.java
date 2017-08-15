package org.rpgleparser.utils;

import java.util.Arrays;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.rpgleparser.RpgParser;

public class TreeUtils {

    public static String normalizeWhiteSpace(final String input) {
        return input.replaceAll("\\s+", " ").replaceAll("([^\\w])\\s+", "$1");
    }

    /**
     * Print the tree, splitting at appropriate points, instead of rendering a
     * single long line.
     * 
     * @param parseTree
     * @param parser
     * @return
     */
    public static String printTree(final ParseTree parseTree, final RpgParser parser) {
        final TreePrinterListener listener = new TreePrinterListener(Arrays.asList(parser.getRuleNames()));
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        return listener.toString();
    }
}
