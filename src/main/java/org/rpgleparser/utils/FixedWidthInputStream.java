package org.rpgleparser.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class FixedWidthInputStream extends InputStream {

    final static boolean isEOL(final int x) {
        return (x == '\r') || (x == '\n');
    }

    final PushbackInputStream pushbackInputStream;
    int column = 0;
    boolean atEOL = false;

    int numberOfColumns = 100;

    // @Override
    // public int read(byte[] b) throws IOException {
    // // TODO Auto-generated method stub
    // return super.read(b);
    // }

    public FixedWidthInputStream(final InputStream pushbackInputStream) {
        super();
        this.pushbackInputStream = new PushbackInputStream(pushbackInputStream, 10000);
    }

    @Override
    public int available() throws IOException {
        return pushbackInputStream.available();
    }

    @Override
    public void close() throws IOException {
        pushbackInputStream.close();
    }

    @Override
    public synchronized void mark(final int readlimit) {
        pushbackInputStream.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return pushbackInputStream.markSupported();
    }

    @Override
    public int read() throws IOException {
        return pushbackInputStream.read();
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final byte tmp[] = new byte[len];

        final int size = pushbackInputStream.read(tmp, 0, len);

        int currentInPos = 0;
        int currentOutPos = 0;
        if (size <= 0) {
            if (column >= numberOfColumns) {
                return size;
            }
            while ((currentOutPos < len) && (column++ < numberOfColumns)) {
                b[off + currentOutPos++] = ' ';
            }
            return currentOutPos;
        }
        while ((currentInPos < size) && (currentOutPos < len)) {
            if (atEOL) {
                if (++column < numberOfColumns) {
                    b[off + currentOutPos++] = ' ';
                } else if (isEOL(tmp[currentInPos])) {
                    b[off + currentOutPos++] = tmp[currentInPos++];
                } else {
                    column = 0;
                    atEOL = false;
                }
            } else {
                b[off + currentOutPos++] = tmp[currentInPos++];
                column++;
                if ((currentInPos < size) && isEOL(tmp[currentInPos])) {
                    atEOL = true;
                }
            }
        }
        if (size > currentInPos) {
            pushbackInputStream.unread(tmp, currentInPos, size - currentInPos);
        }
        if (currentOutPos < len) {
            final int nextChar = pushbackInputStream.read();
            if (nextChar < 0) {
                while ((currentOutPos < len) && (++column < numberOfColumns)) {
                    b[off + currentOutPos++] = ' ';
                }
            } else {
                pushbackInputStream.unread(nextChar);
            }
        }

        return currentOutPos;
    }

    @Override
    public synchronized void reset() throws IOException {
        pushbackInputStream.reset();
    }

    public void setNumberOfColumns(final int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public long skip(final long n) throws IOException {
        return pushbackInputStream.skip(n);
    }
}
