package org.rpgscript;


import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgscript.utils.TestUtils;

import java.util.List;

import static org.rpgscript.utils.TestUtils.assertTokens;

public class TestH {

    @Test
    public void testFixedH() {
        String inputstr = "     H DFTACTGRP(*NO) ACTGRP(*CALLER)";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "H", "DFTACTGRP", "(", "*NO", ")", "ACTGRP");
    }

    @Test
    public void testFixedH2() {
        String inputstr = "     H OPTION(*NODEBUGIO:*SRCSTMT)";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "H", "OPTION", "(", "*NODEBUGIO", ":", "*SRCSTMT", ")", "");
    }

    @Test
    public void testFixedH3() {
        String inputstr = "     H BNDDIR('KLEMENT/JDBC')";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "H", "BNDDIR", "(", "'", "KLEMENT/JDBC", "'", ")", "");
    }

    @Test
    public void testFixedH4_Close() {
        String inputstr = "     HDEBUG(*YES)";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "H", "DEBUG", "(", "*YES", ")", "");
    }

}
