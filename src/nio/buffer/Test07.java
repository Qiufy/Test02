package nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


//读写文件时缓冲区固定大小
public class Test07 {
public static void main(String[] args) {
	try (FileChannel inchannel = new FileInputStream("out.txt").getChannel();
			FileChannel outchannel=new FileOutputStream("out2.txt").getChannel();
			)
		
		{ByteBuffer buf=ByteBuffer.allocate(40);
		int ss=inchannel.read(buf);
		while (ss!=-1) {
			buf.flip();
			outchannel.write(buf);
			buf.clear();
			ss=inchannel.read(buf);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
