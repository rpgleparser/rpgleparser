package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestI {

    @Test
    public void testFieldDesc() {
        final String inputString = "     I              AOPLNO                      WCPLNO";
        expectTokensForSourceLines(inputString, "I", "AOPLNO", "WCPLNO", "", "", "", "", "", "");
    }

    @Test
    public void testFieldDesc2() {
        final String inputString = "     I                        Fmt+SPFrom+To+++09Field+++++++++L1M101H1U1RT      Comments++++++++++++";
        expectTokensForSourceLines(inputString, "I", "Fmt+", "S", "P", "From+To+++", "09", "Field+++++++++", "L1", "M1",
                "01", "H1", "U1", "RT", "");
    }

    @Test
    public void testFieldDesc3() {
        final String inputString = "01170IINTAPE    AB  01    2 C5                                                  MPR009";
        expectTokensForSourceLines(inputString, "I", "INTAPE", "AB", "", "", "01", "2 C5", "");
    }

    @Test
    public void testRecordId() {
        final String inputString = "     IAR1203B\r\n";
        expectTokensForSourceLines(inputString, "I", "AR1203B", "", "", "", "\r\n");
    }

}
