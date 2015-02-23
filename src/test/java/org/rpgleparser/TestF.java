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

public class TestF {

    @Test
    public void testFSpec1() {
        String inputstr =
                "     FARLT201C  IF   E           K DISK\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "F", "ARLT201C", "I", "F", "", "", "", "E", "", "", "", "K", "", "DISK", "", "", "");
    }

    @Test
    public void testFSpecFree() {
        String inputstr =
                "DCL-F PPI061A PRINTER(*EXT) USAGE(*OUTPUT) OFLIND(*IN09);";
        inputstr = TestUtils.pad280Free(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "DCL-F", "PPI061A", "PRINTER", "(", "*EXT", ")", "USAGE", "(", "*OUTPUT", ")", "OFLIND", "(", "*IN09", ")", ";");
    }

    @Test
    public void testFSpecFree2() {
        String inputstr =
                "DCL-F CLP029 DISK(740) USAGE(*OUTPUT);";
        inputstr = TestUtils.pad280Free(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "DCL-F", "CLP029", "DISK", "(", "740", ")", "USAGE", "(", "*OUTPUT", ")", ";");
    }


}
