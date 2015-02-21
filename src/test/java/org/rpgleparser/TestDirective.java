package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.List;

import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestDirective {

    @Test
    public void testFREE() {
        String inputstr =
                "     D/FREE";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "D/", "FREE", "");
    }

    @Test
    public void testEJECT() {
        String inputstr =
                "      /EJECT";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, " /", "EJECT", "");
    }

    @Test
    public void testTITLE() {
        String inputstr =
                "     D/TITLE There is a title here";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "D/", "TITLE", "", "There", "", "is", "", "a", "", "title", "", "here", "");
    }

    @Test
    public void testSPACE() {
        String inputstr =
                "      /SPACE 2";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "/", "SPACE", "", "2", "");
    }

}
