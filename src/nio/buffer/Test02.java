package nio.buffer;

import java.nio.CharBuffer;
import java.util.Arrays;
//批量读取和写入缓冲区buffer
public class Test02 {
public static void main(String[] args) {
	CharBuffer buf=CharBuffer.allocate(16);
	buf.put("hello你知道你真他妈的牛B吗");
	buf.flip();
	//System.out.println(buf);
	char [] det=new char[12];
	CharBuffer remainingcharbuffer=buf.get(det);
	//System.out.println(Arrays.toString(det));
	//System.out.println(remainingcharbuffer);
	buf.get(det, 0, buf.remaining());
	//System.out.println(det);
	System.out.println(buf);
	buf.clear();
	System.out.println(buf);
	while(buf.hasRemaining()) {
		int len=Math.min(det.length, buf.remaining());
		buf.get(det, 0, len);
		System.out.print(new String(det, 0, len));
	   System.out.println();
	}
	System.out.println(buf.capacity());
	System.out.println(buf.limit());
	System.out.println(buf.position());
	char [] contence = {'a','b','c','d','e'};
	buf.position(14);
	
	buf.put(contence, 0, buf.remaining());
	buf.flip();
	System.out.println(buf);
	
}

}
