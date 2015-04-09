package org.rpgleparser.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class FixedWidthInputStream extends InputStream {

	final PushbackInputStream pushbackInputStream;
	int column=0;
	boolean atEOL = false;
	int numberOfColumns=100;
	
	public FixedWidthInputStream(InputStream pushbackInputStream) {
		super();
		this.pushbackInputStream = new PushbackInputStream(pushbackInputStream,10000);
	}

//	@Override
//	public int read(byte[] b) throws IOException {
//		// TODO Auto-generated method stub
//		return super.read(b);
//	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		byte tmp [] = new byte[len];
		
		int size = pushbackInputStream.read(tmp, off, len);

		int currentInPos = 0;
		int currentOutPos = 0;
		if(size <=0){
			if(column >= numberOfColumns){
				return size;
			}
			while(currentOutPos < len && column++ < numberOfColumns){
				b[currentOutPos++] = ' ';
			}
			return currentOutPos;
		}
		while(currentInPos < size && currentOutPos < len){
			if(atEOL){
				if(column++ < numberOfColumns){
					b[currentOutPos++] = ' ';
				}
				else if(isEOL(tmp[currentInPos])){
					b[currentOutPos++] = tmp[currentInPos++];
				}else{
					column=0;
					atEOL=false;
				}
			}else{
				b[currentOutPos++] = tmp[currentInPos++]; 
				column++;
				if(currentInPos < size && isEOL(tmp[currentInPos])){
					atEOL=true;
				}
			}
		}
		if(size > currentInPos){
			pushbackInputStream.unread(tmp, currentInPos, size-currentInPos);
		}
		if(currentOutPos < len){
			int nextChar = pushbackInputStream.read();
			if(nextChar <0){
				while(currentOutPos < len && column++ < numberOfColumns){
					b[currentOutPos++] = ' ';
				}
			}else{
				pushbackInputStream.unread(nextChar);
			}
		}
		
		return currentOutPos;
	}
	
	final static boolean isEOL(int x){
		return x=='\r' || x == '\n';
	}

	@Override
	public long skip(long n) throws IOException {
		return pushbackInputStream.skip(n);
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
	public synchronized void mark(int readlimit) {
		pushbackInputStream.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		pushbackInputStream.reset();
	}

	@Override
	public boolean markSupported() {
		return pushbackInputStream.markSupported();
	}

	@Override
	public int read() throws IOException {
		return pushbackInputStream.read();
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
}
