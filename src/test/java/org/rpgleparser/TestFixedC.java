package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestFixedC {
    @Test
    public void testKFLD() {
        final String inputString = "     C     WCKEY1        KLIST\r\n"
                + "     C                   KFLD                    WCREGN";
        expectTokensForSourceLines(inputString, "C", "", "", "", "WCKEY1", "KLIST", "", "", "", "", "", "", "", "", "C",
                "", "", "", "", "KFLD", "", "WCREGN", "", "", "", "", "", "");
    }

    @Test
    public void testKLIST() {
        final String inputString = "     C     WCKEY1        KLIST";
        expectTokensForSourceLines(inputString, "C", "", "", "", "WCKEY1", "KLIST", "", "", "", "", "", "", "", "");
    }

}
