package org.rpgscript;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgscript.RpgLexer;
import org.rpgscript.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.rpgscript.utils.TestUtils.assertTokens;

public class TestC {

    @Test
    public void testContinuation() {
        String inputstr =
                "     C                   EVAL      QuiteLongFieldName...\r\n" +
                        "     C                             ThatCannotAlwaysFitInOneLine = 'abc'\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "EVAL", "QuiteLongFieldName", "...", "ThatCannotAlwaysFitInOneLine", "=", "'", "abc", "'", "");
    }

    @Test
    public void testCSpec() {
        String inputstr = "     C     FACTOR1       XXAL      FACTOR2\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertEquals(RpgLexer.C_FACTOR, tokenList.get(3).getType());
        assertEquals(RpgLexer.C_FACTOR, tokenList.get(5).getType());
        assertEquals(RpgLexer.C_FACTOR, tokenList.get(6).getType());
        assertEquals("FACTOR1", tokenList.get(3).getText().trim());
        assertEquals("FACTOR2", tokenList.get(5).getText().trim());
        assertEquals("", tokenList.get(6).getText().trim());
    }

//	@Test
//	public void testCSpec_EVAL(){
//		String inputstr="     C     FACTOR1       EVAL      FACTOR2 is really long long long\r\n";
//		inputstr = TestUtils.pad280(inputstr);
//		List<CommonToken> tokenList = TestUtils.runX(inputstr);
//		assertEquals(RpgLexer.C_FACTOR,tokenList.get(4).getType());
//		assertEquals(RpgLexer.C2_FACTOR2,tokenList.get(6).getType());
//		assertEquals("FACTOR1",tokenList.get(4).getText().trim());
//		assertEquals("FACTOR2 is really long long long",tokenList.get(6).getText().trim());
//	}

    @Test
    public void testCSpec_ENTRYPLIST() {
        String inputstr = "     C     *ENTRY        PLIST\r\n" +
                "     C                   PARM                    TODAY";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "*ENTRY", "PLIST");
    }

    @Test
    public void testCSpec_IF() {
        String inputstr = "     C                   IF        WHOS = 'G'";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "IF", "WHOS", "=", "'", "G", "'", "");
    }

    @Test
    public void testCSpec_TIME() {
        String inputstr = "     C                   TIME                    CTIME             6 0\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "TIME", "", "CTIME");
    }

    @Test
    public void testCSpec_MOVEL() {
        String inputstr = "     C                   MOVEL     'P'           B8BATX\r\n";
        ;
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "MOVEL", "'P'", "B8BATX");
    }

    @Test
    public void testCSpec_ExecSQL() {
        String inputstr = "     C/Exec SQL\r\n" +
                "     C+ call GETCANCPOLICYOS(:ReWritePol,\r\n" +
                "     C+                      :ActiveSts,\r\n" +
                "     C+                      :AmountOS)\r\n" +
                "     C/End-Exec";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C/Exec SQL",
                "call GETCANCPOLICYOS(:ReWritePol,", ":ActiveSts,",
                ":AmountOS)", "C/End-Exec");
    }

    @Test
    public void testCSpec_ExecSQLWComments() {
        String inputstr = "     C/Exec SQL\r\n" +
                "     C+ call GETCANCPOLICYOS(:ReWritePol,\r\n" +
                "c123 C+                      :ActiveSts,\r\n" +
                "     C+                      :AmountOS)\r\n" +
                "     C/End-Exec";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C/Exec SQL",
                "call GETCANCPOLICYOS(:ReWritePol,", ":ActiveSts,",
                ":AmountOS)", "C/End-Exec");
    }

    @Test
    public void testCSpec_Comments() {
        String inputstr = "     C                   PARM                    POLNBR                         POLICY NUMBER\r\n";
        ;
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "PARM", "", "POLNBR", "", "", "", "POLICY NUMBER", "");
    }

    @Test
    public void testCSpec_Comments2() {
        String inputstr = "     C                   IF        WHOS = 'G'                                   Home office cpy";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "IF", "WHOS", "=", "'", "G", "'", "");
    }

    @Test
    public void testCSpec_Callp() {
        String inputstr = "     C                   callp     Cmd('OVRPRTF FILE(QSYSPRT) OUTQ(PRT30\\)' +\r\n" +
                "     C                             ' DRAWER(5) HOLD(*YES) SAVE(*YES) ' +\r\n" +
                "     C                             'DEVTYPE(*AFPDS) PAGRTT(0) ':200)\r\n" +
                "     C                   open      QSYSPRT";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "callp", "Cmd", "(", "'",
                "OVRPRTF FILE(QSYSPRT) OUTQ(PRT30\\)", "'", "+", "'",
                "DRAWER(5) HOLD(*YES) SAVE(*YES)", "'", "+", "'",
                "DEVTYPE(*AFPDS) PAGRTT(0)", "'", ":", "200", ")", "", "C", "",
                "", "", "open", "QSYSPRT", "", "", "", "", "");
    }


    @Test
    public void testCSpec_Comments3() {
        String inputstr = "                                                                                Get next number2\r\n" +
                "     C                   eval      @IRDRW = 'PHAR'";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "eval", "@IRDRW", "=", "'", "PHAR", "'", "");
    }

    @Test
    public void testCSpec_SetONLR() {
        String inputstr = "     C                   seton                                        lr    		";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "seton", "", "", "", "", "lr", "");
    }


}
