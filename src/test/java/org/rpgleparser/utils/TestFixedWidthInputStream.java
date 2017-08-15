package org.rpgleparser.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestFixedWidthInputStream {

    @Test
    public void testFullBuffer() throws IOException {
        final InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy\r\n".getBytes()));
        final byte[] b = new byte[1000];
        final int size = is.read(b);
        is.close();
        assertEquals(
                "xx                                                                                                 \r\n"
                        + "yy                                                                                                 \r\n",
                new String(b, 0, size));
    }

    @Test
    public void testFullBuffer2() throws IOException {
        final InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy".getBytes()));
        final byte[] b = new byte[1000];
        final int size = is.read(b);
        is.close();
        assertEquals(
                "xx                                                                                                 \r\n"
                        + "yy                                                                                                 ",
                new String(b, 0, size));
    }

    @Test
    public void testShortBuffer() throws IOException {
        final InputStream is = new FixedWidthInputStream(new ByteArrayInputStream("xx\r\nyy\r\n".getBytes()));
        final StringBuilder sb = new StringBuilder();
        final byte[] b = new byte[10];
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
        final InputStream is = new FixedWidthInputStream(new ByteArrayInputStream(input.getBytes()));
        final StringBuilder sb = new StringBuilder();
        final byte[] b = new byte[30];
        int size = is.read(b);
        while (size > 0) {
            sb.append(new String(b, 0, size));
            size = is.read(b);
        }
        is.close();

        assertEquals(input, sb.toString());
    }
}
