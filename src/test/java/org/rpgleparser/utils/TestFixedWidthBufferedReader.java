package org.rpgleparser.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class TestFixedWidthBufferedReader {


	@Test
	public void testFullBuffer() throws IOException{
		Reader is = new FixedWidthBufferedReader("xx\r\nyy\r\n");
		char[]b= new char[1000];
		int size = is.read(b);
		is.close();
		assertEquals("xx                                                                                                  \r\n" + 
				"yy                                                                                                  \r\n" 
				,new String(b,0,size));
	}

	@Test
	public void testFullBuffer2() throws IOException{
		Reader is = new FixedWidthBufferedReader("xx\r\nyy");
		char[]b= new char[1000];
		int size = is.read(b);
		is.close();
		assertEquals("xx                                                                                                  \r\n" + 
				"yy                                                                                                  " 
				,new String(b,0,size));
	}
	
	@Test
	public void testShortBuffer() throws IOException{
		Reader is = new FixedWidthBufferedReader("xx\r\nyy\r\n");
		StringBuilder sb = new StringBuilder();
		char[]b= new char[10];
		int size = is.read(b);
		while(size>0){
			sb.append(new String(b,0,size));
			size = is.read(b);
		}
		is.close();
		
		assertEquals("xx                                                                                                  \r\n" + 
				"yy                                                                                                  \r\n" 
				,sb.toString());
	}
	
	@Test
	public void testUnchanged() throws IOException{
		String input = "xx                                                                                                  \r\n" + 
				"yy                                                                                                  \r\n";
		Reader is = new FixedWidthBufferedReader(input);
		StringBuilder sb = new StringBuilder();
		char[]b= new char[30];
		int size = is.read(b);
		while(size>0){
			sb.append(new String(b,0,size));
			size = is.read(b);
		}
		is.close();
		
		assertEquals( input
				,sb.toString());
	}
}
