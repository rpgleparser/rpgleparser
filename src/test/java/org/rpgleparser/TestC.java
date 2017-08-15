package org.rpgleparser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

public class TestC {

    @Test
    public void testContinuation() {
        final String inputString = "     C                   EVAL      QuiteLongFieldName...\r\n"
                + "     C                             ThatCannotAlwaysFitInOneLine = 'abc'\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "EVAL", "QuiteLongFieldName", "...",
                "ThatCannotAlwaysFitInOneLine", "=", "'", "abc", "'", "");
    }

    @Test
    public void testCSpec() {
        String inputString = "     C  NL9FACTOR1       XXAL      FACTOR2\r\n";
        inputString = TestUtils.padSourceLines(inputString, false);
        // TestUtils.printTokens(inputString, false);
        final List<String> errors = new ArrayList<>();
        final List<CommonToken> tokenList = TestUtils.getParsedTokens(inputString, errors);
        assertThat(errors, is(empty()));
        assertEquals(RpgLexer.CS_FactorContent, tokenList.get(4).getType());
        assertEquals(RpgLexer.CS_OperationAndExtender, tokenList.get(5).getType());
        assertEquals(RpgLexer.CS_FactorContent, tokenList.get(6).getType());
        assertEquals("FACTOR1", tokenList.get(4).getText());
        assertEquals("XXAL", tokenList.get(5).getText());
        assertEquals("FACTOR2", tokenList.get(6).getText());
    }

    // @Test
    // public void testCSpec_EVAL(){
    // String inputString=" C FACTOR1 EVAL FACTOR2 is really long long
    // long\r\n";
    // inputString = TestUtils.padSourceLines(inputString);
    // List<String> errors = new ArrayList<String>();
    // List<CommonToken> tokenList = TestUtils.getParsedTokens(inputString,
    // errors);
    // assertThat(errors, is(empty()));
    // assertEquals(RpgLexer.C_FACTOR,tokenList.get(4).getType());
    // assertEquals(RpgLexer.C2_FACTOR2,tokenList.get(6).getType());
    // assertEquals("FACTOR1",tokenList.get(4).getText().trim());
    // assertEquals("FACTOR2 is really long long
    // long",tokenList.get(6).getText().trim());
    // }

    @Test
    public void testCSpec_ACQ() {
        final String inputString = "     C  NL9FACTOR1       ACQ       FACTOR2\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "FACTOR1", "ACQ", "FACTOR2", "", "", "", "", "", "",
                "");
    }

    @Test
    public void testCSpec_ADD() {
        final String inputString = "     C  NL9FACTOR1       ADD       FACTOR2       RESULT\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "FACTOR1", "ADD", "FACTOR2", "RESULT", "", "", "",
                "", "", "");
    }

    @Test
    public void testCSpec_ADD_H() {
        final String inputString = "     C  NL9FACTOR1       ADD (H)   FACTOR2       RESULT\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "FACTOR1", "ADD", "(", "H", ")", "FACTOR2",
                "RESULT", "", "", "", "", "", "");
    }

    @Test
    public void testCSpec_CALLB_1Extender() {
        final String inputString = "     C  NL9              CALLB(D)  FACTOR2       RESULT\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "", "CALLB", "(", "D", ")", "FACTOR2", "RESULT", "",
                "", "", "", "", "");
    }

    @Test
    public void testCSpec_CALLB_2Extenders() {
        final String inputString = "     C  NL9              CALLB(D E)FACTOR2       RESULT\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "", "CALLB", "(", "D", "E", ")", "FACTOR2",
                "RESULT", "", "", "", "", "", "");
    }

    @Test
    public void testCSpec_CALLB_notExtender() {
        final String inputString = "     C  NL9              CALLB     FACTOR2       RESULT\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "", "CALLB", "FACTOR2", "RESULT", "", "", "", "",
                "", "");
    }

    // Remove test, PARM must be preceded by CALL,CALLB or PLIST
    // @Test
    // public void testCSpec_Comments() {
    // String inputString =
    // " C PARM POLNBR POLICY NUMBER\r\n";
    // expectTokensForSourceLines(inputString,
    // "C", "", "", "", "", "PARM", "", "POLNBR", "", "", "", "", "","POLICY
    // NUMBER", "");
    // }

    @Test
    public void testCSpec_Callp() {
        final String inputString = "     C                   callp     Cmd('OVRPRTF FILE(QSYSPRT) OUTQ(PRT30\\)' +\r\n"
                + "     C                             ' DRAWER(5) HOLD(*YES) SAVE(*YES) ' +\r\n"
                + "     C                             'DEVTYPE(*AFPDS) PAGRTT(0) ':200)\r\n"
                + "     C                   open      QSYSPRT";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "callp", "Cmd", "(", "'",
                "OVRPRTF FILE(QSYSPRT) OUTQ(PRT30\\)", "'", "+", "'", "DRAWER(5) HOLD(*YES) SAVE(*YES)", "'", "+", "'",
                "DEVTYPE(*AFPDS) PAGRTT(0)", "'", ":", "200", ")", "", "C", "", "", "", "", "open", "QSYSPRT", "", "",
                "", "", "", "", "");
    }

    @Test
    public void testCSpec_Comments2() {
        final String inputString = "     C                   IF        WHOS = 'G'                                   Home office cpy\r\n"
                + "     C                   ENDIF";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "IF", "WHOS", "=", "'", "G", "'", "", "C", "", "",
                "", "", "ENDIF", "", "", "", "", "", "", "", "");
    }

    @Test
    public void testCSpec_Comments3() {
        final String inputString = "                                                                                Get next number2\r\n"
                + "     C                   eval      @IRDRW = 'PHAR'";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "eval", "@IRDRW", "=", "'", "PHAR", "'", "");
    }

    @Test
    public void testCSpec_ENTRYPLIST() {
        final String inputString = "     C     *ENTRY        PLIST\r\n"
                + "     C                   PARM                    TODAY";
        expectTokensForSourceLines(inputString, "C", "", "", "", "*ENTRY", "PLIST", "", "", "", "", "", "", "", "\r\n",
                "C", "", "", "", "", "PARM", "", "TODAY", "", "", "", "", "", "\r\n");
    }

    @Test
    public void testCSpec_ExecSQL() {
        final String inputString = "     C/Exec SQL\r\n" + "     C+ call GETCANCPOLICYOS(:ReWritePol,\r\n"
                + "     C+                      :ActiveSts,\r\n" + "     C+                      :AmountOS)\r\n"
                + "     C/End-Exec";
        expectTokensForSourceLines(inputString, "C/Exec SQL", "call GETCANCPOLICYOS(:ReWritePol,", ":ActiveSts,",
                ":AmountOS)", "C/End-Exec");
    }

    @Test
    public void testCSpec_ExecSQLWComments() {
        final String inputString = "     C/Exec SQL\r\n" + "     C+ call GETCANCPOLICYOS(:ReWritePol,\r\n"
                + "c123 C+                      :ActiveSts,\r\n" + "     C+                      :AmountOS)\r\n"
                + "     C/End-Exec";
        expectTokensForSourceLines(inputString, "C/Exec SQL", "call GETCANCPOLICYOS(:ReWritePol,", ":ActiveSts,",
                ":AmountOS)", "C/End-Exec");
    }

    @Test
    public void testCSpec_IF() {
        final String inputString = "     C                   IF        WHOS = 'G'\r\n"
                + "     C                   ENDIF";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "IF", "WHOS", "=", "'", "G", "'", "", "C", "", "",
                "", "", "ENDIF", "", "", "", "", "", "", "", "");
    }

    @Test
    public void testCSpec_MOVEL() {
        final String inputString = "     C                   MOVEL     'P'           B8BATX\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "MOVEL", "'", "P", "'", "B8BATX", "", "", "", "",
                "", "\r\n");
    }

    @Test
    public void testCSpec_SetONLR() {
        final String inputString = "     C                   seton                                        lr    		";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "seton", "", "", "", "", "lr", "", "", "");
    }

    @Test
    public void testCSpec_TIME() {
        final String inputString = "     C                   TIME                    CTIME             6 0\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "TIME", "", "CTIME", "6", "0", "", "", "", "\r\n");
    }
}
