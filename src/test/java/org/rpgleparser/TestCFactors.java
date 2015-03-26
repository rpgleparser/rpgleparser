package org.rpgleparser;

import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

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

    @Test
    public void testMove(){
    	String inputString = 
    			"     C                   MOVE      ' '           PSLFLG            1\r\n";
    	TestUtils.showParseTree(inputString, false);
    }
    @Test
    public void testMove2(){
    	String inputString = 
    			"     C                   MOVE      '  '          PSLFLG            2\r\n";
    	TestUtils.showParseTree(inputString, false);
    }
    @Test
    public void testMoveSymConst(){
    	String inputString = 
    			"     C                   MOVE      *BLANKS       PSLFLG            1\r\n";
    	TestUtils.showParseTree(inputString, false);
    }
    @Test
    public void testMoveLiteralChar(){
    	String inputString = 
    			"     C                   MOVE      'C'           PSLFLG            1\r\n";
    	TestUtils.showParseTree(inputString, false);
    }

}