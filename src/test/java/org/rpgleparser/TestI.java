package org.rpgleparser;

import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestI {

    @Test
    public void testRecordId() {
        String inputString = "     IAR1203B\r\n";
        expectTokensForSourceLines(inputString, "I", "AR1203B", "", "", "", "\r\n");
    }

    @Test
    public void testFieldDesc() {
        String inputString = "     I              AOPLNO                      WCPLNO";
        expectTokensForSourceLines(inputString, "I", "AOPLNO", "WCPLNO", "", "", "", "", "", "");
    }

    @Test
    public void testFieldDesc2() {
        String inputString = "     I                        Fmt+SPFrom+To+++09Field+++++++++L1M101H1U1RT      Comments++++++++++++";
        expectTokensForSourceLines(inputString, "I","Fmt+","S","P","From+To+++","09","Field+++++++++","L1","M1","01","H1","U1","RT","");
    }

    @Test
    public void testFieldDesc3() {
        String inputString = "01170IINTAPE    AB  01    2 C5                                                  MPR009";
        TestUtils.printTokens(inputString, false);
        expectTokensForSourceLines(inputString, "I", "INTAPE", "AB", "", "", "01", "2 C5", "");
    }

}
