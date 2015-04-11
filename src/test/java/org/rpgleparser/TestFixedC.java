package org.rpgleparser;

import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestFixedC {
    @Test
    public void testKLIST() {
        String inputString = "     C     WCKEY1        KLIST";
        expectTokensForSourceLines(inputString, "C", "", "", "", "WCKEY1", "KLIST", "", "", "", "", "", "", "", "");
    }

    @Test
    public void testKFLD() {
        String inputString = "     C     WCKEY1        KLIST\r\n"
        		 + "     C                   KFLD                    WCREGN";
        expectTokensForSourceLines(inputString, "C", "", "", "", "WCKEY1", "KLIST", "", "", "", "", "", "", "", "", 
        		"C", "", "", "", "", "KFLD", "", "WCREGN", "", "", "", "", "", "");
    }

}
