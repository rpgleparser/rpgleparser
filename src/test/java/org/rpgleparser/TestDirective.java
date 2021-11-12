package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestDirective {

    @Test
    public void testEJECT() {
        final String inputString = "      /EJECT";
        expectTokensForSourceLines(inputString, " /", "EJECT", "");
    }

    @Test
    public void testFREE() {
        final String inputString = "     D/FREE";
        expectTokensForSourceLines(inputString, "D/", "FREE", "");
    }

    @Test
    public void testSPACE() {
        final String inputString = "      /SPACE 2";
        expectTokensForSourceLines(inputString, "/", "SPACE", "2", "");
    }

    @Test
    public void testTITLE() {
        final String inputString = "     D/TITLE There is a title here";
        expectTokensForSourceLines(inputString, "D/", "TITLE", "There is a title here", "");
    }

    @Test
    public void testIF() {
        final String inputString = "      /IF NOT DEFINED(Name)";
        expectTokensForSourceLines(inputString, "/", "IF", "NOT", "DEFINED", "(", "Name", ")", "");
    }

}
