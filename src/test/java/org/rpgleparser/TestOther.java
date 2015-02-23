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


public class TestOther {

    @Test
    public void testDirective() {
        String inputstr = "       /FREE  \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "/", "FREE", "");
    }

    @Test
    public void testDirective2() {
        String inputstr = "      /Free              \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "/", "Free", "");
    }

    @Test
    public void testCopyDirective() {
        String inputstr = "      /COPY PPLMISHL/QRPGLESRC,ENC320CLE";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "/", "COPY", "", "PPLMISHL/QRPGLESRC,ENC320CLE", "");
    }

    @Test
    public void testTitleDirective() {
        String inputstr = "      /TITLE Info for the web";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "/", "TITLE", "", "Info", "", "for", "", "the", "", "web", "");
    }


    @Test
    public void testDOW() {
        String inputstr = "       dow *in99 = *on ; \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        assertTokens(tokenList, "dow", "*in99", "=", "*on", ";", "");
    }

    @Test
    public void testSubProc() {
        String inputstr =
                "     P Roundit         B";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "P", "Roundit", "B", "", "");
    }

    @Test
    public void testSubProc2() {
        String inputstr =
                "     P Roundit         B\r\n" +
                        "      *           procedure interface definition\r\n" +
                        "     D Roundit         PI            11p 2\r\n" +
                        "     D    Premium                    11p 2";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(inputstr, errors);
        assertThat(errors, is(empty()));
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "P", "Roundit", "B", "", "*",
                "procedure interface definition", "", "D", "Roundit", "", "",
                "PI", "", "11", "p", "2", "", "", "", "D", "Premium", "", "",
                "", "", "11", "p", "2", "", "", "");
    }


}
