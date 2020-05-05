package nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

//            -----ServerSocketChannel-----
//ServerSocket-------SocketChannnel---------Socket       通道关系如左图
//            -----------------------------  
public class Test10ServerSocketChannel {
public static void main(String[] args) throws IOException, InterruptedException {
	int port=1234;
	ServerSocketChannel sc=ServerSocketChannel.open();
	sc.socket().bind(new InetSocketAddress(port));
	sc.configureBlocking(false);
	
	 while (true) {
		 SocketChannel socketchannnel=	sc.accept();
		if (socketchannnel==null) {
			Thread.sleep(2000);
			System.out.println("服务等待连接中......");
		} else {
           System.out.println("客户端链接成功！");
           //发送消息给客户端
           ByteBuffer buf=ByteBuffer.allocate(1024);
           buf.put("hello!欢迎连接ServerSocket".getBytes());
           buf.flip();
           socketchannnel.write(buf);
           //接收客户端消息
           buf.clear();
           socketchannnel.read(buf);
           buf.flip();
           //解码
           CharSequence charbuf=Charset.defaultCharset().decode(buf);
           System.out.println(charbuf);
           socketchannnel.close();
		}
	}
}
}
