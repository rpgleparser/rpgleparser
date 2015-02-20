package org.rpgscript;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class TestListener {

    @Test
    public void test() throws Exception {
        ANTLRInputStream input = new ANTLRInputStream("hello world\r\nhello bob");
        TokenStream tokens = new CommonTokenStream(new RpgLexer(input));
        RpgParser parser = new RpgParser(tokens);

        // Specify our entry point
        RpgParser.RContext entry = parser.r();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        RpgParserBaseListener listener = new RpgParserBaseListener() {

            @Override
            public void enterR(RpgParser.RContext ctx) {
                System.out.println("enter:" + ctx);
                System.out.println(ctx.getText());
            }

            @Override
            public void exitR(RpgParser.RContext ctx) {
                System.out.println("exit:" + ctx);
            }

        };
        walker.walk(listener, entry);
        entry = parser.r();
        walker.walk(listener, entry);
        walker.walk(listener, entry);

    }

    @Test
    public void testRPG() throws Exception {
        //ANTLRInputStream input = new ANTLRInputStream("     dcl-s Cmd char(1000)");
        ANTLRInputStream input = new ANTLRInputStream("       dcl-s Cmd char(123);");
        TokenStream tokens = new CommonTokenStream(new RpgLexer(input));
        RpgParser parser = new RpgParser(tokens);

        // Specify our entry point
        RpgParser.RContext entry = parser.r();

        System.out.println(entry.getChildCount());
        System.out.println(entry.getText());
        TokenStream str = parser.getTokenStream();
        for (int i = 0; i < str.size(); i++) {
            System.out.println(str.get(i));
        }

    }

    @Test
    public void testRPgfixed() throws Exception {
        //ANTLRInputStream input = new ANTLRInputStream("     DstrVar          s        \r\n");
        ANTLRInputStream input = new ANTLRInputStream("     D strVar         s           32765b                       \r\n");
        TokenStream tokens = new CommonTokenStream(new RpgLexer(input));
        RpgParser parser = new RpgParser(tokens);

        // Specify our entry point
        System.out.println("enter");
        RpgParser.RContext entry = parser.r();

        System.out.println(entry.getChildCount());
        System.out.println(entry.getChild(0).toStringTree());
        for (int i = 0; i < entry.getChild(0).getChildCount(); i++) {
            System.out.println("\t" + entry.getChild(0).getChild(i).toStringTree() + "  " + entry.getChild(0).getChild(i).getPayload());

        }
        System.out.println(entry.getText());
        for (String s : parser.getDFAStrings()) {
            System.out.println(s);
        }
    }

    @Test
    public void testFSpec() {
        String inputstr = "     FCZGD02A0  IFEAAE           K DISK    INCLUDE(CZTD02A2) \r\n";
        runX(inputstr);
    }

    @Test
    public void testFSpecAndDSpec() {
        String inputstr = "     FCZGD02A0  IFEAAE           K DISK    INCLUDE(CZTD02A2)        \r\n" +
                "     D strVar         s           32765b                       \r\n";
        runX(inputstr);
    }

    @Test
    public void testFFree() {
        String inputstr = "      dcl-f myfile usage(*input : *output : *update);";
        //String inputstr="      dcl-f myfile usage";
        runX(inputstr);
    }

    public void runX(String inputstr) {
        System.out.println(inputstr);
        ANTLRInputStream input = new ANTLRInputStream(inputstr);
        TokenStream tokens = new CommonTokenStream(new RpgLexer(input));
        RpgParser parser = new RpgParser(tokens);

        // Specify our entry point
        System.out.println("enter");
        RpgParser.RContext entry = parser.r();

        System.out.println(entry.getChildCount());
        System.out.println(entry.getChild(0).toStringTree());
        for (int i = 0; i < entry.getChild(0).getChildCount(); i++) {
            System.out.println("\t" + entry.getChild(0).getChild(i).toStringTree() + "  " + entry.getChild(0).getChild(i).getPayload());

        }
        System.out.println(entry.getText());
        for (String s : parser.getDFAStrings()) {
            System.out.println(s);
        }
    }
}
