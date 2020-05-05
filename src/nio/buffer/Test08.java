package nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

//通道与通道之间的传输
public class Test08 {
public static void main(String[] args) {
	File file=new File("out.txt");
	
	try (
			RandomAccessFile random=new RandomAccessFile(file, "rw");
			FileChannel inchannel=random.getChannel();
			
			FileOutputStream fos=new FileOutputStream("out3.txt");
			FileChannel outchannel=fos.getChannel();
			){
		
		//通道之间传输代码就这一句其实
		inchannel.transferTo(0, file.length(), outchannel);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
}
}
