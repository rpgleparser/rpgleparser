package org.rpgleparser;


import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestH {

    @Test
    public void testFixedH() {
        String inputString = "     H DFTACTGRP(*NO) ACTGRP(*CALLER)";
        expectTokensForSourceLines(inputString, "H", "DFTACTGRP", "(", "*NO", ")", "ACTGRP");
    }

    @Test
    public void testFixedH2() {
        String inputString = "     H OPTION(*NODEBUGIO:*SRCSTMT)";
        expectTokensForSourceLines(inputString, "H", "OPTION", "(", "*NODEBUGIO", ":", "*SRCSTMT", ")", "");
    }

    @Test
    public void testFixedH3() {
        String inputString = "     H BNDDIR('KLEMENT/JDBC')";
        expectTokensForSourceLines(inputString, "H", "BNDDIR", "(", "'", "KLEMENT/JDBC", "'", ")", "");
    }

    @Test
    public void testFixedH4_Close() {
        String inputString = "     HDEBUG(*YES)";
        expectTokensForSourceLines(inputString, "H", "DEBUG", "(", "*YES", ")", "");
    }

}
