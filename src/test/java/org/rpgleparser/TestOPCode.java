package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestOPCode {

    @Test
    public void testACQ() {
        final String inputString = "           ACQ(E) device workstnfile;     \r\n";
        expectTokensForSourceLines(inputString, "ACQ", "(", "E", ")", "device", "workstnfile", ";");
    }

    @Test
    public void testCLEAR() {
        final String inputString = "CLEAR ZDGT(X);";
        expectTokensForFreeSnippet(inputString, "CLEAR", "ZDGT", "(", "X", ")", ";");
    }

    @Test
    public void testDOW() {
        final String inputString = "           dow *in99 = *on;        \r\n        enddo;";
        expectTokensForSourceLines(inputString, "dow", "*in99", "=", "*on", ";", "enddo", ";");
    }

    @Test
    public void testDSPLY() {
        final String inputString = "DSPLY(E) 'Unable to write copy to Infosys/Claim survey results';";
        expectTokensForFreeSnippet(inputString, "DSPLY", "(", "E", ")", "'",
                "Unable to write copy to Infosys/Claim survey results", "'", ";");
    }

    @Test
    public void testDSPLY2() {
        final String inputString = "DSPLY msg ' ' resp;";
        expectTokensForFreeSnippet(inputString, "DSPLY", "msg", "'", "", "'", "resp", ";");
    }

    @Test
    public void testFOR() {
        final String inputString = "for Ptr = 1 to 40;\r\nendfor;";
        expectTokensForFreeSnippet(inputString, "for", "Ptr", "=", "1", "to", "40", ";", "endfor", ";");
    }

    @Test
    public void testIN() {
        final String inputString = "           in(e) *lock Program21;     \r\n";
        expectTokensForSourceLines(inputString, "in", "(", "e", ")", "*lock", "Program21", ";");
    }

    @Test
    public void testREADE() {
        final String inputString = "      READE TODAY AR1007D;";
        expectTokensForSourceLines(inputString, "READE", "TODAY", "AR1007D", ";");
    }

    @Test
    public void testSETLL() {
        final String inputString = "setll (Term: State: EffDate) AR1014D; // position on db";
        expectTokensForFreeSnippet(inputString, "setll", "(", "Term", ":", "State", ":", "EffDate", ")", "AR1014D",
                ";");
    }

    @Test
    public void testSORTA() {
        final String inputString = "SORTA ZCAR;";
        expectTokensForFreeSnippet(inputString, "SORTA", "ZCAR", ";");
    }

    @Test
    public void testXMLINTO() {
        final String inputString = "xml-into Endorsements %xml(XMLString :\r\n"
                + "'doc=string case=any allowextra=yes allowmissing=yes');";
        expectTokensForFreeSnippet(inputString, "xml-into", "Endorsements", "%xml", "(", "XMLString", ":", "'",
                "doc=string case=any allowextra=yes allowmissing=yes", "'", ")", ";");
    }

}
