package org.rpgscript;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgscript.RpgLexer;
import org.rpgscript.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.rpgscript.utils.TestUtils.assertTokens;

public class TestComments {

    @Test
    public void testLeadComments() {
        String inputstr = "cmntsC        \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals("C", tokenList.get(0).getText().trim());
        assertEquals("", tokenList.get(1).getText().trim());
        for (int i = 2; i < tokenList.size(); i++) {
            assertEquals("", tokenList.get(i).getText().trim());
        }
    }

    @Test
    public void testEmptyLine() {
        String inputstr = "     C        \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals("C", tokenList.get(0).getText().trim());
        for (int i = 1; i < tokenList.size(); i++) {
            assertEquals("", tokenList.get(i).getText().trim());
        }
    }


    @Test
    public void testFixedCommentWithComma() {
        String inputstr = "     �*  Prototype for call to program MSR141A\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "�*", "Prototype for call to program MSR141A");
    }

    @Test
    public void testAsterisk1() {
        String inputstr = "     C* This is a comment\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals("C*", tokenList.get(0).getText().trim());
        //assertEquals("This is a comment",tokenList.get(1).getText().trim());
        //assertEquals(HelloLexer.COMMENTS_TEXT,tokenList.get(1).getType());
    }

    @Test
    public void testAsterisk2() {
        String inputstr = "      *=====================================================\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals("*", tokenList.get(0).getText().trim());
        //assertEquals("=====================================================",tokenList.get(1).getText().trim());
        //assertEquals(HelloLexer.COMMENTS_TEXT,tokenList.get(1).getType());
    }

    @Test
    public void testAllAsterisk() {
        String inputstr = "********************************************************\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        //assertEquals("*****",tokenList.get(0).getText().trim());
        assertEquals("**", tokenList.get(0).getText().trim());
        //assertEquals(HelloLexer.COMMENTS_TEXT,tokenList.get(1).getType());
    }

    @Test
    public void test2AsteriskEndOfSource() {
        String inputstr =
                "** ZLNG LONG CONSTANTS    Char(25)\r\n" +
                        "some text here \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "** ZLNG LONG CONSTANTS    Char(25)", "some text here", "");
    }

    @Test
    public void testBlankLine() {
        String inputstr = "\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals("", tokenList.get(0).getText().trim());
        assertEquals(RpgLexer.BLANK_LINE, tokenList.get(0).getType());
    }


    @Test
    public void testSlash() {
        String inputstr = "       //This is a comment\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        System.out.println("tokens:" + tokenList.size());
        //assertEquals("//",tokenList.get(0).getText().trim());
        //assertEquals("This is a comment",tokenList.get(1).getText().trim());
        //assertEquals(HelloLexer.COMMENTS_TEXT,tokenList.get(1).getType());
    }

    @Test
    public void testSlashes() {
        String inputstr = "       //                  \r\n" +
                "       //  external calls                       \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        System.out.println("tokens:" + tokenList.size());
        //assertEquals("//",tokenList.get(0).getText().trim());
        //assertEquals("",tokenList.get(1).getText().trim());
        //assertEquals("external calls",tokenList.get(4).getText().trim());
        //assertEquals(HelloLexer.COMMENTS_TEXT,tokenList.get(4).getType());
    }

    @Test
    public void testSlashesCloseToFront() {
        String inputstr = "      //-----------------------------------------------------------------";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "//", "-----------------------------------------------------------------");
    }

    @Test
    public void testMixedcomment() {
        String inputstr = "      DCL-DS POLHLD LEN(107);\r\n"
                + "      // DS TO SAVE SECOND POLICYHOLDER INFORMATION FOR OUTPUT\r\n"
                + "      OPRLNM CHAR(15) POS(5);\r\n" +
                " END-DS;";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "DCL-DS", "POLHLD", "LEN", "(", "107", ")", ";", "OPRLNM", "CHAR", "(", "15", ")", "POS", "(", "5", ")", ";", "END-DS", ";");
    }


}
