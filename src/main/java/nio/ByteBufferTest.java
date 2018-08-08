package nio;

import java.nio.ByteBuffer ;
import org.junit.Test ;

public class ByteBufferTest {
	
	@Test
	public void testBuffer(){
		// 创建一个ByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(10);
		System.out.println(buffer.capacity()) ;
		System.out.println(buffer.limit()) ;
		System.out.println(buffer.position()) ;
		System.out.println(buffer.remaining()) ;
		System.out.println(buffer.mark()) ;
		buffer.put((byte)1);
		buffer.put((byte)2);
		buffer.put((byte)3);
		buffer.put((byte)4);
		buffer.mark();
		System.out.println("---------------------") ;
		System.out.println(buffer.capacity()) ;
		System.out.println(buffer.limit()) ;
		System.out.println(buffer.position()) ;
		System.out.println(buffer.remaining()) ;
		System.out.println(buffer.mark()) ;
		System.out.println("---------------------") ;
//		buffer.flip();
//		buffer.clear();
		buffer.rewind();
		System.out.println(buffer.get()) ;
		System.out.println(buffer.get()) ;
		System.out.println(buffer.get()) ;
		System.out.println(buffer.get()) ;

	}
	
}
