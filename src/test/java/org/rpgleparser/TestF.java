package org.rpgleparser;

import org.junit.Test;

import static org.rpgleparser.utils.TestUtils.expectTokensForFreeSnippet;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestF {

    @Test
    public void testFSpec1() {
        String inputString =
                "     FARLT201C  IF   E           K DISK\r\n";
        expectTokensForSourceLines(inputString, "F","ARLT201C","I","F","","","","E","","","","K","","DISK","","");
    }

//    @Test
//    public void testFSpecFree2() {
//        String inputString =
//                "DCL-F CLP029 DISK(740) USAGE(*OUTPUT);";
//        expectTokensForFreeSnippet(inputString, "DCL-F", "CLP029", "DISK", "(", "740", ")", "USAGE", "(", "*OUTPUT", ")", ";");
//    }


}
