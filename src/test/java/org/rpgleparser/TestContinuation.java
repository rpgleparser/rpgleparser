package org.rpgleparser;

import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

import org.junit.Test;

public class TestContinuation {

    @Test
    public void testCSpec_EVAL_continuation() {
        final String inputString = "     C                   eval      DATA = 'Current Policy Term:  ' +\r\n"
                + "     C                                    %trim(EffDateChr) + ' to ' +\r\n"
                + "     C                                    %trim(ExpDateChr)\r\n";

        expectTokensForSourceLines(inputString, "C", "", "", "", "", "eval", "DATA", "=", "'", "Current Policy Term:",
                "'", "+", "%trim", "(", "EffDateChr", ")", "+", "'", "to", "'", "+", "%trim", "(", "ExpDateChr", ")",
                "");
    }

    @Test
    public void testCSpec_EVAL_continuation2() {
        final String inputString = "     C                   EVAL      A = (B*D)/ C +\r\n"
                + "     C                             24\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "EVAL", "A", "=", "(", "B", "*", "D", ")", "/",
                "C", "+", "24", "");
    }

    @Test
    public void testCSpec_EVAL_continuation3() {
        final String inputString = "     C                   eval      InstallDue = ZAmountDue(Ptr)\r\n"
                + "     C                                        - ZAmountPaid(Ptr)\r\n"
                + "     C                                        + ZServChrgDue(Ptr)\r\n"
                + "     C                                        - ZServChrgPaid(Ptr)\r\n"
                + "     C                   eval      InstClosed = ZInstClosed(Ptr)\r\n";
        expectTokensForSourceLines(inputString, "C", "", "", "", "", "eval", "InstallDue", "=", "ZAmountDue", "(",
                "Ptr", ")", "-", "ZAmountPaid", "(", "Ptr", ")", "+", "ZServChrgDue", "(", "Ptr", ")", "-",
                "ZServChrgPaid", "(", "Ptr", ")", "", "C", "", "", "", "", "eval", "InstClosed", "=", "ZInstClosed",
                "(", "Ptr", ")", "");
    }

    @Test
    public void testFree_Continuation_FreeMinus1() {
        final String inputString = "x = 'ab-\r\n" + "c';";
        expectTokensForFreeSnippet(inputString, "x", "=", "'", "ab", "c", "'", ";");
    }

    @Test
    public void testFree_Continuation_FreeMinus2() {
        final String inputString = "x = 'ab-\r\n" + "             c';";
        expectTokensForFreeSnippet(inputString, "x", "=", "'", "ab", "c", "'", ";");
    }

    @Test
    public void testFree_Continuation_FreePlus1() {
        final String inputString = "x = 'ab+\r\n" + "c';";
        expectTokensForFreeSnippet(inputString, "x", "=", "'", "ab", "c", "'", ";");
    }

    @Test
    public void testFree_Continuation_FreePlus2() {
        final String inputString = "x = 'ab+\r\n" + "             c';";
        expectTokensForFreeSnippet(inputString, "x", "=", "'", "ab", "c", "'", ";");
    }

    @Test
    public void testFree_Continuation_Not1() {
        final String inputString = "x = a + \r\n" + "b;";
        expectTokensForFreeSnippet(inputString, "x", "=", "a", "+", "b", ";");
    }
}
