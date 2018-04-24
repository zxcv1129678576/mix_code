package NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NIOStudy01 {
	
	public void testChannel() throws IOException{
		
		Charset chrst = Charset.forName("UTF-8");
		RandomAccessFile aFile = new RandomAccessFile("data/sida.xml", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024*1024);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
		System.out.println("Read " + bytesRead);
		buf.flip();
		while(buf.hasRemaining()){
		String ss=chrst.decode(buf).toString();
	    System.out.print(ss);
			
		//System.out.print( buf.get());
		
		}
		buf.clear();
		bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}
	public static void main(String[] args) throws IOException {
		new NIOStudy01().testChannel();
		
	}

}
