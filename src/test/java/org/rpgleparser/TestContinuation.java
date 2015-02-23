package org.rpgleparser;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.util.List;

import static org.rpgleparser.utils.TestUtils.assertTokens;

public class TestContinuation {

    @Test
    public void testFree_Continuation_Not1() {
        String inputstr = TestUtils.pad280Free(
                "x = a + \r\n" +
                        "b;");
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "x", "=", "a", "+", "b", ";", "");
    }

    @Test
    public void testFree_Continuation_FreePlus1() {
        String inputstr = TestUtils.pad280Free(
                "x = 'ab+\r\n" +
                        "c';");
        System.out.println(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);

        assertTokens(tokenList, "x", "=", "'", "ab", "c", "'", ";", "");
    }

    @Test
    public void testFree_Continuation_FreePlus2() {
        String inputstr = TestUtils.pad280Free(
                "x = 'ab+\r\n" +
                        "             c';");
        System.out.println(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);

        assertTokens(tokenList, "x", "=", "'", "ab", "c", "'", ";", "");
    }

    @Test
    public void testFree_Continuation_FreeMinus1() {
        String inputstr = TestUtils.pad280Free(
                "x = 'ab-\r\n" +
                        "c';");
        System.out.println(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "x", "=", "'", "ab", "c", "'", ";", "");
    }

    @Test
    public void testFree_Continuation_FreeMinus2() {
        String inputstr = TestUtils.pad280Free(
                "x = 'ab-\r\n" +
                        "             c';");
        System.out.println(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);

        assertTokens(tokenList, "x", "=", "'", "ab", "c", "'", ";", "");
    }

    @Test
    public void testCSpec_EVAL_continuation() {
        String inputstr =
                "     C                   eval      DATA = 'Current Policy Term:  ' +\r\n" +
                        "     C                                    %trim(EffDateChr) + ' to ' +\r\n" +
                        "     C                                    %trim(ExpDateChr)\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        TestUtils.showToks(tokenList);
        assertTokens(tokenList, "C", "", "", "", "eval", "DATA", "=", "'",
                "Current Policy Term:", "'", "+", "%trim", "(", "EffDateChr",
                ")", "+", "'", "to", "'", "+", "%trim", "(", "ExpDateChr", ")",
                "");
    }

    @Test
    public void testCSpec_EVAL_continuation2() {
        String inputstr =
                "     C                   EVAL      A = (B*D)/ C +\r\n" +
                        "     C                             24\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "EVAL", "A", "=", "(", "B", "*", "D", ")", "/", "C", "+", "24", "");
    }

    @Test
    public void testCSpec_EVAL_continuation3() {
        String inputstr =
                "     C                   eval      InstallDue = ZAmountDue(Ptr)\r\n" +
                        "     C                                        - ZAmountPaid(Ptr)\r\n" +
                        "     C                                        + ZServChrgDue(Ptr)\r\n" +
                        "     C                                        - ZServChrgPaid(Ptr)\r\n" +
                        "     C                   eval      InstClosed = ZInstClosed(Ptr)\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<CommonToken> tokenList = TestUtils.runX(inputstr);
        assertTokens(tokenList, "C", "", "", "", "eval", "InstallDue", "=",
                "ZAmountDue", "(", "Ptr", ")", "-", "ZAmountPaid", "(", "Ptr",
                ")", "+", "ZServChrgDue", "(", "Ptr", ")", "-",
                "ZServChrgPaid", "(", "Ptr", ")", "", "C", "", "", "", "eval",
                "InstClosed", "=", "ZInstClosed", "(", "Ptr", ")", "");
    }


}

