package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestCFactors {

    @Test
    public void testMove() {
        final String inputString = "     C                   MOVE      ' '           PSLFLG            1\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "MOVE", "'", " ", "'", "PSLFLG", "1", "", "", "",
                "", "");
    }

    @Test
    public void testMove2() {
        final String inputString = "     C                   MOVE      '  '          PSLFLG            2\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "MOVE", "'", "  ", "'", "PSLFLG", "2", "", "", "",
                "", "");
    }

    @Test
    public void testMoveLiteralChar() {
        final String inputString = "     C                   MOVE      'C'           PSLFLG            1\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "MOVE", "'", "C", "'", "PSLFLG", "1", "", "", "",
                "", "");

    }

    @Test
    public void testMoveSymConst() {
        final String inputString = "     C                   MOVE      *BLANKS       PSLFLG            1\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "MOVE", "*BLANKS", "PSLFLG", "1", "", "", "", "",
                "");

    }

    @Test
    public void testSplitFactor2() {
        final String inputString = "     C  NL9LOANDATE      ADDDUR    XX:YEARS      DUEDATE\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "LOANDATE", "ADDDUR", "XX", ":", "YEARS", "DUEDATE",
                "", "", "", "", "", "");
    }

    @Test
    public void testSplitFactor2WSymbol() {
        final String inputString = "     C  NL9LOANDATE      ADDDUR    XX:*YEARS     DUEDATE\r\n";
        expectTokensForSourceLines(inputString, "C", "", "N", "L9", "LOANDATE", "ADDDUR", "XX", ":", "*YEARS",
                "DUEDATE", "", "", "", "", "", "");
    }

}