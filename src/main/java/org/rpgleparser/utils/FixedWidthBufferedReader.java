package org.rpgleparser.utils;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;

public class FixedWidthBufferedReader extends Reader {

    final static boolean isEOL(final char x) {
        return (x == '\r') || (x == '\n');
    }

    final PushbackReader reader;
    int column = 0;
    boolean atEOL = false;

    int numberOfColumns = 100;

    public FixedWidthBufferedReader(final PushbackReader reader) {
        super();
        this.reader = reader;
    }

    public FixedWidthBufferedReader(final Reader reader) {
        super();
        this.reader = new PushbackReader(reader, 10000);
    }

    public FixedWidthBufferedReader(final String input) {
        super();
        this.reader = new PushbackReader(new StringReader(input), 10000);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public void mark(final int readAheadLimit) throws IOException {
        reader.mark(readAheadLimit);
    }

    @Override
    public boolean markSupported() {
        return reader.markSupported();
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public int read(final char[] b, final int off, final int len) throws IOException {
        final char tmp[] = new char[len];

        final int size = reader.read(tmp, 0, len);
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
            reader.unread(tmp, currentInPos, size - currentInPos);
        }
        if (currentOutPos < len) {
            final int nextChar = reader.read();
            if (nextChar < 0) {
                while ((currentOutPos < len) && (++column < numberOfColumns)) {
                    b[off + currentOutPos++] = ' ';
                }
            } else {
                reader.unread(nextChar);
            }
        }

        return currentOutPos;
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return reader.read(target);
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public void reset() throws IOException {
        reader.reset();
    }

    public void setNumberOfColumns(final int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    @Override
    public long skip(final long n) throws IOException {
        return reader.skip(n);
    }
}
