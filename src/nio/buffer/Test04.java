package nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

//缓冲区的复制与分割
public class Test04 {
public static void main(String[] args) {
	//复制的缓冲区其实引用同一个数组
	CharBuffer buf=CharBuffer.allocate(16);
	buf.put("hello");
	System.out.println("buf.position ="+buf.position());//5
	CharBuffer buf2=buf.duplicate();
	System.out.println("buf2.capacity ="+buf2.capacity()+",buf2.limit="+buf2.limit()+",buf2.position="+buf2.position());
	
	buf2.flip();
	System.out.println(buf2);
	System.out.println("buf2.capacity ="+buf2.capacity()+",buf2.limit="+buf2.limit()+",buf2.position="+buf2.position());
	buf2.clear();
	System.out.println(buf2);
	System.out.println("buf2.capacity ="+buf2.capacity()+",buf2.limit="+buf2.limit()+",buf2.position="+buf2.position());
	buf2.put("verygoods");
	buf.flip();
	System.out.println(buf);
	//缓冲区分割
	buf2.position(3);
	CharBuffer buf3=buf2.slice();
	System.out.println("buf3.capacity ="+buf3.capacity()+",buf3.limit="+buf3.limit()+",buf3.position="+buf3.position());
	
	//创建直接字节缓冲区更适合io，但是创建成本高，非直接字节缓冲区通道会先创建临时字节缓冲区把非直接字节缓冲区的内容复制再进行io
	ByteBuffer.allocateDirect(16);
}
}
