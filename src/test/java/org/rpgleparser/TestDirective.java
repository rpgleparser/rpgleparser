package org.rpgleparser;

import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestDirective {

    @Test
    public void testFREE() {
        String inputString =
                "     D/FREE";
        expectTokensForSourceLines(inputString, "D/", "FREE", "");
    }

    @Test
    public void testEJECT() {
        String inputString =
                "      /EJECT";
        expectTokensForSourceLines(inputString, " /", "EJECT", "");
    }

    @Test
    public void testTITLE() {
        String inputString =
                "     D/TITLE There is a title here";
        expectTokensForSourceLines(inputString, "D/", "TITLE", "", "There", "", "is", "", "a", "", "title", "", "here", "");
    }

    @Test
    public void testSPACE() {
        String inputString =
                "      /SPACE 2";
        expectTokensForSourceLines(inputString, "/", "SPACE", "", "2", "");
    }

}
