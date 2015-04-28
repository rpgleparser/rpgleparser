package org.rpgleparser;

import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestD {

    @Test
    public void testDSpec_Continuation1() {
        String inputString =
                "     D CalcNewSweepDate...\r\n" +
                        "     D  123            PR                  EXTPGM('MSR173')";
        expectTokensForSourceLines(inputString, "D","CalcNewSweepDate","123","","","PR","","","","","","EXTPGM","(","'","MSR173","'",")","");
    }

    @Test
    public void testDSpec_Continuation2() {
        String inputString =
                "     D CalcNewSweepDate...\r\n" +
                        "     D                 PR                  EXTPGM('MSR173')";
        expectTokensForSourceLines(inputString, "D", "CalcNewSweepDate", "", "", "", "PR", "", "", "", "", "", "EXTPGM","(","'","MSR173","'",")", "");
    }

//    @Test
//    public void testDSpec_Continuation3() {
//        String inputString =
//                "     D  Goodville_VehSymbolCd...                                                Pers risk symbol\r\n" +
//                        "     D                               17a";
//        expectTokensForSourceLines(inputString, "D","Goodville_VehSymbolCd","","","","","","17","a","","","");
//    }

}
