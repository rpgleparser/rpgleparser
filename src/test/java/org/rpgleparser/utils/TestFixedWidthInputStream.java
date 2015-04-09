package org.rpgleparser.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestFixedWidthInputStream {

	@Test
	public void testFullBuffer() throws IOException{
		InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy\r\n".getBytes()));
		byte[]b= new byte[1000];
		int size = is.read(b);
		is.close();
		assertEquals("xx                                                                                                  \r\n" + 
				"yy                                                                                                  \r\n" 
				,new String(b,0,size));
	}
	@Test
	public void testFullBuffer2() throws IOException{
		InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy".getBytes()));
		byte[]b= new byte[1000];
		int size = is.read(b);
		is.close();
		assertEquals("xx                                                                                                  \r\n" + 
				"yy                                                                                                  " 
				,new String(b,0,size));
	}
	
	@Test
	public void testShortBuffer() throws IOException{
		InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy\r\n".getBytes()));
		StringBuilder sb = new StringBuilder();
		byte[]b= new byte[10];
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
		InputStream is = new FixedWidthInputStream(new ByteArrayInputStream(input.getBytes()));
		StringBuilder sb = new StringBuilder();
		byte[]b= new byte[30];
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
