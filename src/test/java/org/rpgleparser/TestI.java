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

public class TestI {

    @Test
    public void testRecordId() {
        String inputstr = "     IAR1203B\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "I", "AR1203B", "");
    }

    @Test
    public void testFieldDesc() {
        String inputstr = "     I              AOPLNO                      WCPLNO";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "I", "AOPLNO", "WCPLNO", "", "", "", "");
    }

    @Test
    public void testFieldDesc2() {
        String inputstr = "     I                        Fmt+SPFrom+To+++09Field+++++++++L1M101H1U1RT      Comments++++++++++++";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "I", "Fmt+", "S", "P", "From+To+++", "09", "Field+++++++++", "L1", "M1", "01", "H1U1RT", "");
    }

    @Test
    public void testFieldDesc3() {
        String inputstr = "01170IINTAPE    AB  01    2 C5                                                  MPR009";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        //TestUtils.showToks(tokenList);
        assertTokens(tokenList, "I", "INTAPE", "AB", "", "", "01", "2 C5", "");
    }

}
