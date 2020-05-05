package nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.net.www.URLConnection;


//文件的属性和文件内容通过Gather的形式写到另一个文件中
public class Test09 {
public static void main(String[] args) throws IOException {
	File file=new File("out.txt");
	String path=file.getAbsolutePath();
	
	  long lastmodi= file.lastModified();
       Date date=new Date(lastmodi);
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String lastmodefied =sdf.format(date);
       String headertext="filename:"+path+"\r\n"
    		   +"lastmodi:"+lastmodefied+"\r\n";
       //文件属性保存到缓冲区
       ByteBuffer header=ByteBuffer.wrap(headertext.getBytes());
	  //存储文件内容类型缓冲区
       ByteBuffer contentBuf=ByteBuffer.allocate(128);
       //缓冲区数组
       ByteBuffer[] gather= {header,contentBuf,null};
       
       String contenttype="unknown";
       long contentLength=-1;
       FileChannel fc=new FileInputStream(file).getChannel();
       MappedByteBuffer filedata= fc.map(MapMode.READ_ONLY, 0, fc.size());
       //把文件内容保存到缓冲区数组
       gather[2]=filedata;
       
       contentLength=fc.size();
       contenttype=URLConnection.guessContentTypeFromName(path);
       
       //存入缓冲数组第二个缓冲区
       StringBuilder sb=new StringBuilder();
       sb.append("contenttype :").append(contenttype).append("\r\n");
       sb.append("contentLength :").append(contentLength).append("\r\n");
       contentBuf.put(sb.toString().getBytes());
       contentBuf.flip();
       //Gather的形式把多个缓冲区写到另一个文件中其实一行代码
       FileChannel oc=new FileOutputStream("gather.txt").getChannel();
       while (oc.write(gather)>0) {}
	
       
       
       
}
}
