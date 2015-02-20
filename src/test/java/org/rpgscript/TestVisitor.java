package org.rpgscript;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Test;
import org.rpgscript.RpgParser.*;
import org.rpgscript.utils.TestUtils;

public class TestVisitor {

    @Test
    public void test() throws Exception {
        String inputstr = "        cmd = 123;\r\n" +
                "        xyzzy = '123abc';\r\n" +
                "        f1('123abcx');\r\n";
        ANTLRInputStream input = new ANTLRInputStream(TestUtils.pad280(inputstr));
        TokenStream tokens = new CommonTokenStream(new RpgLexer(input));
        RpgParser parser = new RpgParser(tokens);

        // Specify our entry point
        RpgParser.RContext entry = parser.r();

        RpgParserVisitor<Object> visitor = new RpgParserBaseVisitor<Object>() {

            @Override
            public Object visitExpression(ExpressionContext ctx) {
                System.out.println("Expression:  " + ctx.getText() + "  " + ctx.getChild(0).getClass());
                return super.visitExpression(ctx);
            }

            @Override
            public Object visitFunction(FunctionContext ctx) {
                System.out.println("Function:  " + ctx.getText());
                return super.visitFunction(ctx);
            }

            @Override
            public Object visitIdentifier(IdentifierContext ctx) {
                System.out.println("Identifier:  " + ctx.getText());
                return super.visitIdentifier(ctx);
            }

            @Override
            public Object visitLiteral(LiteralContext ctx) {
                System.out.println("Literal:  " + ctx.getText());
                return super.visitLiteral(ctx);
            }

            @Override
            public Object visitFunctionName(FunctionNameContext ctx) {
                System.out.println("FunctionName:  " + ctx.getText());
                return super.visitFunctionName(ctx);
            }

        };

        Object t = visitor.visitR(entry);
        System.out.println("---> " + t);
    }

}
