package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.List;

import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestD {

    @Test
    public void testDSpec_Continuation1() {
        String inputstr =
                "     D CalcNewSweepDate...\r\n" +
                        "     D  123            PR                  EXTPGM('MSR173')";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        for (CommonToken tok : tokenList) {
            System.out.println(tok);
        }
        assertTokens(tokenList, "D", "CalcNewSweepDate", "123", "", "", "PR", "", "", "", "", "", "EXTPGM('MSR173')", "");
    }

    @Test
    public void testDSpec_Continuation2() {
        String inputstr =
                "     D CalcNewSweepDate...\r\n" +
                        "     D                 PR                  EXTPGM('MSR173')";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "D", "CalcNewSweepDate", "", "", "", "PR", "", "", "", "", "", "EXTPGM('MSR173')", "");
    }

    @Test
    public void testDSpec_Continuation3() {
        String inputstr =
                "     D  Goodville_VehSymbolCd...                                                Pers risk symbol\r\n" +
                        "     D                               17a";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "D", "Goodville_VehSymbolCd", "", "", "", "", "", "17", "a", "", "", "", "");
    }

}
