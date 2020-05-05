package nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Test11SocketChannnel {
public static void main(String[] args) throws IOException {
	SocketChannel socketchannel=SocketChannel.open();
	InetSocketAddress address= new InetSocketAddress("localhost", 1234);
	socketchannel.connect(address);
	while (!socketchannel.finishConnect()) {
		System.out.println("请等待，正在连接服务器......");
	}
	System.out.println("连接成功");
	//给服务器发送消息
	ByteBuffer buf=ByteBuffer.wrap("hello I comefrom client socket".getBytes());
	while (buf.hasRemaining()) {
		socketchannel.write(buf);
	}
	//接收服务器的消息
	buf.clear();
	socketchannel.read(buf);
	buf.flip();
	CharBuffer charbuf=Charset.defaultCharset().decode(buf);
	System.out.println(charbuf);
}
}
