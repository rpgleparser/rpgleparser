package org.rpgscript;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgscript.utils.TestUtils;

import java.util.List;

import static org.rpgscript.utils.TestUtils.assertTokens;

public class TestO {

    @Test
    public void testOSpecs() {
        String inputstr =
                "     OQSYSPRT   E            HDR1           1  3\r\n" +
                        "     O                       BegMonth              D\r\n" +
                        "     O                                           40 'Loss Detail HIPT207 '";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        for (CommonToken tok : tokenList) {
            System.out.println(tok);
        }
        assertTokens(tokenList, "O", "QSYSPRT", "E", "", "", "", "", "HDR1",
                "", "1", "3", "", "", "O", "", "", "", "", "BegMonth", "", "",
                "", "D", "", "O", "", "", "", "", "", "", "", "40", "",
                "'Loss Detail HIPT207 '");
    }

    @Test
    public void testOSpec_ProgramDescribed1() {
        String inputstr =
                "     OFilename++DF  N01N02N03Excnam++++001002003004                             Comment+++++++++++++";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "Filename++", "D", "F", "N01", "N02", "N03", "Excnam++++", "001", "002", "003", "004", "", "");
    }


    @Test
    public void testOSpec_ProgramDescribed1b() {
        String inputstr =
                "     OFilename++DF  N01N02N03Excnam++++                                         Comment+++++++++++++";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "Filename++", "D", "F", "N01", "N02", "N03", "Excnam++++", "", "", "", "", "", "");
    }

    @Test
    public void testOSpec_ProgramDescribed2() {
        String inputstr =
                "     OFilename++DAddN01N02N03Excnam++++                                         Comment+++++++++++++";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "Filename++", "D", "Add", "N01", "N02", "N03", "Excnam++++", "", "");
    }

    @Test
    public void testOSpec_ProgramDescribedField() {
        String inputstr =
                "     O              N01N02N03Field+++++++++YB End++PConstant/editword/DTformat++Comment+++++++++++++";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "", "N01", "N02", "N03", "Field+++++++++", "Y", "B", "End++", "P", "Constant/editword/DTformat++", "");
    }
    //O..............................................Constant/editword-ContinutioComment+++++++++++++
//     

    @Test
    public void testOSpec_LowerCase() {
        String inputstr =
                "     O              n78      LicIssDte           56";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "", "n78", "", "", "LicIssDte", "", "", "56", "", "", "");
    }

    @Test
    public void testOSpec_2() {
        String inputstr =
                "     O         AND   10N73N07";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "O", "AND", "10", "N73", "N07", "", "", "", "", "", "");
    }


}
