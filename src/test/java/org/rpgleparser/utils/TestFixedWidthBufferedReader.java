package org.rpgleparser.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;

import org.junit.Test;

public class TestFixedWidthBufferedReader {

    @Test
    public void testFullBuffer() throws IOException {
        final Reader is = new FixedWidthBufferedReader("xx\r\nyy\r\n");
        final char[] b = new char[1000];
        final int size = is.read(b);
        is.close();
        assertEquals(
                "xx                                                                                                 \r\n"
                        + "yy                                                                                                 \r\n",
                new String(b, 0, size));
    }

    @Test
    public void testFullBuffer2() throws IOException {
        final Reader is = new FixedWidthBufferedReader("xx\r\nyy");
        final char[] b = new char[1000];
        final int size = is.read(b);
        is.close();
        assertEquals(
                "xx                                                                                                 \r\n"
                        + "yy                                                                                                 ",
                new String(b, 0, size));
    }

    @Test
    public void testShortBuffer() throws IOException {
        final Reader is = new FixedWidthBufferedReader("xx\r\nyy\r\n");
        final StringBuilder sb = new StringBuilder();
        final char[] b = new char[10];
        int size = is.read(b);
        while (size > 0) {
            sb.append(new String(b, 0, size));
            size = is.read(b);
        }
        is.close();

        assertEquals(
                "xx                                                                                                 \r\n"
                        + "yy                                                                                                 \r\n",
                sb.toString());
    }

    @Test
    public void testUnchanged() throws IOException {
        final String input = "xx                                                                                                  \r\n"
                + "yy                                                                                                  \r\n";
        final Reader is = new FixedWidthBufferedReader(input);
        final StringBuilder sb = new StringBuilder();
        final char[] b = new char[30];
        int size = is.read(b);
        while (size > 0) {
            sb.append(new String(b, 0, size));
            size = is.read(b);
        }
        is.close();

        assertEquals(input, sb.toString());
    }
}
