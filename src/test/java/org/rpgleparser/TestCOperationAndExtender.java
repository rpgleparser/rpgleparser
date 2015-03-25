package org.rpgleparser;

import org.junit.Test;
import static org.rpgleparser.utils.TestUtils.expectTokensForSourceLines;

/**
 * Tests whitespace at various points within the opcode and extender.
 * Note: expected tokens are the same for all tests
 * @author eberlyrh
 *
 */
public class TestCOperationAndExtender {

    @Test
    public void testOperation1LeftJustified() {
        String inputString =
                "     C     JOBVARX       MULT(H)   DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString,
        		"C","","","","JOBVARX","MULT","(","H",")","DATE_JIS","CHAR_DATE","","","","","","");
    }
    
    @Test
    public void testOperationLeadWhitespace() {
        String inputString =
                "     C     JOBVARX        MULT(H)  DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString,
        		"C","","","","JOBVARX","MULT","(","H",")","DATE_JIS","CHAR_DATE","","","","","","");
    }
    
    
    @Test
    public void testOperationMidWhitespace() {
        String inputString =
                "     C     JOBVARX       MULT (H)  DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString,
        		"C","","","","JOBVARX","MULT","(","H",")","DATE_JIS","CHAR_DATE","","","","","","");
    }
    
    
    @Test
    public void testOperationXWhitespace() {
        String inputString =
                "     C     JOBVARX       MULT ( H )DATE_JIS      CHAR_DATE";
        expectTokensForSourceLines(inputString,
        		"C","","","","JOBVARX","MULT","(","H",")","DATE_JIS","CHAR_DATE","","","","","","");
    }
    

}
