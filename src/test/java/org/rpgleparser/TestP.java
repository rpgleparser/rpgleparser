package org.rpgleparser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

public class TestP {
    
    @Test
    public void testProcedureInterface(){
    	String inputstr =
    		"     P getThingAvailable...\r\n" +
    		"     P                 B\r\n"+
    		"     D                 PI                  like(typeQuantity)\r\n"+ 
    		"     D  SomeNumber                         like(typeP50u)                 const\r\n";
        inputstr = TestUtils.pad280(inputstr);
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXGUI(inputstr);
        assertThat(errors, is(empty()));

    }



}
