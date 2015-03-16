package org.rpgleparser;

import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestOPCode {

    @Test
    public void testACQ() {
        String inputString = "           ACQ(E) device workstnfile;     \r\n";
        expectTokensForSourceLines(inputString, "ACQ", "(E)", "device", "workstnfile",";");
    }

    @Test
    public void testIN() {
        String inputString = "           in(e) *lock Program21;     \r\n";
        expectTokensForSourceLines(inputString, "in", "(e)", "*lock", "Program21",";");
    }

    @Test
    public void testDOW() {
        String inputString = "           dow *in99 = *on;        \r\n";
        expectTokensForSourceLines(inputString, "dow", "*in99", "=","*on",";");
    }

    @Test
    public void testREADE() {
        String inputString = "      READE TODAY AR1007D;";
        expectTokensForSourceLines(inputString, "READE", "TODAY", "AR1007D",";");
    }

    @Test
    public void testFOR() {
        String inputString = "for Ptr = 1 to 40;";
        expectTokensForFreeSnippet(inputString, "for", "Ptr", "=", "1", "to", "40", ";");
    }

    @Test
    public void testSETLL() {
        String inputString = "setll (Term: State: EffDate) AR1014D; // position on db";
        expectTokensForFreeSnippet(inputString, "setll", "(", "Term", ":", "State", ":", "EffDate", ")", "AR1014D", ";");
    }

    @Test
    public void testXMLINTO() {
        String inputString = "xml-into Endorsements %xml(XMLString :\r\n" +
                "'doc=string case=any allowextra=yes allowmissing=yes');";
        expectTokensForFreeSnippet(inputString, "xml-into", "Endorsements", "%xml", "(",
                "XMLString", ":", "'",
                "doc=string case=any allowextra=yes allowmissing=yes", "'",
                ")", ";");
    }

    @Test
    public void testSORTA() {
        String inputString = "SORTA ZCAR;";
        expectTokensForFreeSnippet(inputString, "SORTA", "ZCAR", ";");
    }

    @Test
    public void testCLEAR() {
        String inputString = "CLEAR ZDGT(X);";
        expectTokensForFreeSnippet(inputString, "CLEAR", "ZDGT", "(", "X", ")", ";");
    }

    @Test
    public void testDSPLY() {
        String inputString = "DSPLY(E) 'Unable to write copy to Infosys/Claim survey results';";
        expectTokensForFreeSnippet(inputString, "DSPLY", "(E)", "'", "Unable to write copy to Infosys/Claim survey results", "'", ";");
    }

    @Test
    public void testDSPLY2() {
        String inputString = "DSPLY msg ' ' resp;";
        expectTokensForFreeSnippet(inputString, "DSPLY", "msg", "'", "", "'", "resp", ";");
    }


}
