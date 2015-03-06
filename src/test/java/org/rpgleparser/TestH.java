package org.rpgleparser;


import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestH {

    @Test
    public void testFixedH() {
        String inputstr = "     H DFTACTGRP(*NO) ACTGRP(*CALLER)";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "H", "DFTACTGRP", "(", "*NO", ")", "ACTGRP");
    }

    @Test
    public void testFixedH2() {
        String inputstr = "     H OPTION(*NODEBUGIO:*SRCSTMT)";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "H", "OPTION", "(", "*NODEBUGIO", ":", "*SRCSTMT", ")", "");
    }

    @Test
    public void testFixedH3() {
        String inputstr = "     H BNDDIR('KLEMENT/JDBC')";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "H", "BNDDIR", "(", "'", "KLEMENT/JDBC", "'", ")", "");
    }

    @Test
    public void testFixedH4_Close() {
        String inputstr = "     HDEBUG(*YES)";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
       // TestUtils.showToks(tokenList);
        assertTokens(tokenList, "H", "DEBUG", "(", "*YES", ")", "");
    }

}
