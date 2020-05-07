package nio.buffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
//RandomAccessFile的channnel可以双向双向读写hjdsjdsa
public class Test06 {
public static void main(String[] args) {
	File file=new File("out.txt");
	try {
		RandomAccessFile ramdonfile=new RandomAccessFile(file, "rw");
		 FileChannel filechannel=ramdonfile.getChannel();
		 
		 MappedByteBuffer buf=filechannel.map(MapMode.READ_WRITE, 0, file.length());
		 
		 filechannel.write(buf, file.length());
		 
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
