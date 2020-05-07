package nio.buffer;

import java.nio.CharBuffer;
import java.util.Arrays;
// chuan  ccc   
//创建缓冲区的两种方式
//1.allocate 2.wrap
public class Test03 {
public static void main(String[] args) {
	CharBuffer buf1=CharBuffer.allocate(16);
	
	char[] array=new char[16];
	CharBuffer buf2=CharBuffer.wrap(array);
	buf2.put("hello");
	buf2.flip();
	System.out.println(buf2);
	System.out.println(Arrays.toString(array));
	
	array[0]='X';
	System.out.println(buf2);
	//不管是allocate还是wrap创建的缓冲区都是间接缓冲区，间接缓冲区会使用备份数组
	if(buf2.hasArray()) {
		char[] arr2= buf2.array();
		System.out.println(Arrays.toString(arr2));
	}
	if(buf1.hasArray()) {
		 char[] arr1=buf1.array();
		 System.out.println(Arrays.toString(arr1));
	}
	
}
}
