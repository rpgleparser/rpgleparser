package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestO {

    @Test
    public void testOSpec_LowerCase() {
        final String inputString = "     O              n78      LicIssDte           56";
        expectTokensForSourceLines(inputString, "O", "", "n", "78", "", "", "", "", "LicIssDte", "", "", "56", "", "");
    }

    @Test
    public void testOSpec_ProgramDescribed1() {
        final String inputString = "     OFilename++DF  N01N02N03Excnam++++001002003004                             Comment+++++++++++++";
        expectTokensForSourceLines(inputString, "O", "Filename++", "D", "F", "N", "01", "N", "02", "N", "03",
                "Excnam++++", "001", "002", "003", "004", "", "");
    }

    @Test
    public void testOSpec_ProgramDescribed1b() {
        final String inputString = "     OFilename++DF  N01N02N03Excnam++++                                         Comment+++++++++++++";
        expectTokensForSourceLines(inputString, "O", "Filename++", "D", "F", "N", "01", "N", "02", "N", "03",
                "Excnam++++", "", "", "", "", "", "");
    }

    @Test
    public void testOSpec_ProgramDescribed2() {
        final String inputString = "     OFilename++DAddN01N02N03Excnam++++                                         Comment+++++++++++++";
        expectTokensForSourceLines(inputString, "O", "Filename++", "D", "Add", "N", "01", "N", "02", "N", "03",
                "Excnam++++", "", "");
    }

    // @Test
    // public void testOSpec_ProgramDescribedField() {
    // String inputString =
    // " O N01N02N03Field+++++++++YB
    // End++PConstant/editword/DTformat++Comment+++++++++++++";
    // expectTokensForSourceLines(inputString,
    // "O", "", "N","01", "N","02", "N","03", "Field+++++++++", "Y", "B",
    // "End++", "P", "Constant/editword/DTformat++","");
    // }
    // O..............................................Constant/editword-ContinutioComment+++++++++++++

    @Test
    public void testOSpecs() {
        final String inputString = "     OQSYSPRT   E            HDR1           1  3\r\n"
                + "     O                       BegMonth              D\r\n"
                + "     O                                           40 'Loss Detail HIPT207 '";
        expectTokensForSourceLines(inputString, "O", "QSYSPRT", "E", "", "", "", "", "", "", "", "HDR1", "", "1", "3",
                "", "", "", "O", "", "", "", "", "", "", "", "BegMonth", "", "", "", "D", "", "O", "", "", "", "", "",
                "", "", "", "", "", "40", "", "'", "Loss Detail HIPT207 ", "'", "");

    }

    // @Test
    // public void testOSpec_2() {
    // String inputString =
    // " O AND 10N73N07";
    // expectTokensForSourceLines(inputString,
    // "O", "AND", "","10", "N","73", "N","07", "", "", "", "", "", "","","");
    // }
}
