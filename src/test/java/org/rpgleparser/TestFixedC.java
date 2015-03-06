package org.rpgleparser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.rpgleparser.utils.TestUtils.assertTokens;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

public class TestFixedC {
    @Test
    public void testKLIST() {
        String inputstr = "     C     WCKEY1        KLIST";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "C", "","","WCKEY1","KLIST","","","","","","");
    }
    @Test
    public void testKFLD() {
        String inputstr = "     C                   KFLD                    WCREGN";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "C", "","","","KFLD","","WCREGN","","","","");
    }

}
