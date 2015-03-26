package org.rpgleparser;

import org.junit.Test;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

public class TestCFactors {

    @Test
    public void testSplitFactor2WSymbol() {
        String inputString =
        		"     C  NL9LOANDATE      ADDDUR    XX:*YEARS     DUEDATE\r\n";
        expectTokensForSourceLines(inputString,
        		"C","","N","L9","LOANDATE","ADDDUR","XX",":","*YEARS","DUEDATE","","","","","","");
    }

    @Test
    public void testSplitFactor2() {
        String inputString =
        		"     C  NL9LOANDATE      ADDDUR    XX:YEARS      DUEDATE\r\n";
        expectTokensForSourceLines(inputString,
        		"C","","N","L9","LOANDATE","ADDDUR","XX",":","YEARS","DUEDATE","","","","","","");
    }


}