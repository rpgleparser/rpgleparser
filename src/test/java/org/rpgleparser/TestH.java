package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestH {

    @Test
    public void testFixedH() {
        final String inputString = "     H DFTACTGRP(*NO) ACTGRP(*CALLER)";
        expectTokensForSourceLines(inputString, "H", "DFTACTGRP", "(", "*NO", ")", "ACTGRP", "(", "*CALLER", ")",
                "\r\n");
    }

    @Test
    public void testFixedH2() {
        final String inputString = "     H OPTION(*NODEBUGIO:*SRCSTMT)";
        expectTokensForSourceLines(inputString, "H", "OPTION", "(", "*NODEBUGIO", ":", "*SRCSTMT", ")", "");
    }

    @Test
    public void testFixedH3() {
        final String inputString = "     H BNDDIR('KLEMENT/JDBC')";
        expectTokensForSourceLines(inputString, "H", "BNDDIR", "(", "'", "KLEMENT/JDBC", "'", ")", "");
    }

    @Test
    public void testFixedH4_Close() {
        final String inputString = "     HDEBUG(*YES)";
        expectTokensForSourceLines(inputString, "H", "DEBUG", "(", "*YES", ")", "");
    }

}
