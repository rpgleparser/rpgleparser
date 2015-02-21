package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.List;

import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestOPCode {

    @Test
    public void testACQ() {
        String inputstr = "           ACQ(E) device workstnfile;     \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "ACQ", "(E)", "device", "workstnfile");
    }

    @Test
    public void testIN() {
        String inputstr = "           in(e) *lock Program21;     \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "in", "(e)", "*lock", "Program21");
    }

    @Test
    public void testDOW() {
        String inputstr = "           dow *in99 = *on;        \r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "dow", "*in99", "=");
    }

    @Test
    public void testREADE() {
        String inputstr = "      READE TODAY AR1007D;";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "READE", "TODAY", "AR1007D");
    }

    @Test
    public void testFOR() {
        String inputstr = "for Ptr = 1 to 40;";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "for", "Ptr", "=", "1", "to", "40", ";", "");
    }

    @Test
    public void testSETLL() {
        String inputstr = "setll (Term: State: EffDate) AR1014D; // position on db";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "setll", "(", "Term", ":", "State", ":", "EffDate", ")", "AR1014D", ";", "//", "position on db", "");
    }

    @Test
    public void testXMLINTO() {
        String inputstr = "xml-into Endorsements %xml(XMLString :\r\n" +
                "'doc=string case=any allowextra=yes allowmissing=yes');";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "xml-into", "Endorsements", "%xml", "(",
                "XMLString", ":", "'",
                "doc=string case=any allowextra=yes allowmissing=yes", "'",
                ")", ";");
    }

    @Test
    public void testSORTA() {
        String inputstr = "SORTA ZCAR;";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "SORTA", "ZCAR", ";");
    }

    @Test
    public void testCLEAR() {
        String inputstr = "CLEAR ZDGT(X);";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "CLEAR", "ZDGT", "(", "X", ")", ";");
    }

    @Test
    public void testDSPLY() {
        String inputstr = "DSPLY(E) 'Unable to write copy to Infosys/Claim survey results';";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "DSPLY", "(E)", "'", "Unable to write copy to Infosys/Claim survey results", "'", ";");
    }

    @Test
    public void testDSPLY2() {
        String inputstr = "DSPLY msg ' ' resp;";
        inputstr = TestUtils.pad280Free(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "DSPLY", "msg", "'", "", "'", "resp", ";");
    }


}
