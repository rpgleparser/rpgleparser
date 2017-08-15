package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

/**
 * Tests whitespace at various points within the opcode and extender. Note:
 * expected tokens are the same for all tests
 * 
 * @author eberlyrh
 *
 */
public class TestCOperationAndExtender {

    @Test
    public void testOperation1LeftJustified() {
        final String inputString = "     C     JOBVARX       MULT(H)   DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString, "C", "", "", "", "JOBVARX", "MULT", "(", "H", ")", "DATE_JIS",
                "CHAR_DATE", "", "", "", "", "", "");
    }

    @Test
    public void testOperationLeadWhitespace() {
        final String inputString = "     C     JOBVARX        MULT(H)  DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString, "C", "", "", "", "JOBVARX", "MULT", "(", "H", ")", "DATE_JIS",
                "CHAR_DATE", "", "", "", "", "", "");
    }

    @Test
    public void testOperationMidWhitespace() {
        final String inputString = "     C     JOBVARX       MULT (H)  DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString, "C", "", "", "", "JOBVARX", "MULT", "(", "H", ")", "DATE_JIS",
                "CHAR_DATE", "", "", "", "", "", "");
    }

    @Test
    public void testOperationXWhitespace() {
        final String inputString = "     C     JOBVARX       MULT ( H )DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString, "C", "", "", "", "JOBVARX", "MULT", "(", "H", ")", "DATE_JIS",
                "CHAR_DATE", "", "", "", "", "", "");
    }

}
