package io;

import java.io.BufferedWriter ;
import java.io.FileWriter ;
import java.io.IOException ;
import org.junit.Test ;

public class WriterTest {
	
	/**
	 * 测试FileWriter与BufferWriter性能
	 * @throws IOException
	 */
	@Test
	public void testFileWriterAndBufferWriter() throws IOException{
		FileWriter fr = new FileWriter("E://hello1.txt");
		long l = System.currentTimeMillis();
		for(int i = 0; i<10000000 ;i++){
			fr.write(i);
		}
		System.out.println(System.currentTimeMillis()-l) ;
		
		l = System.currentTimeMillis();
		FileWriter fr1 = new FileWriter("E://hello2.txt");
		BufferedWriter bf = new BufferedWriter(fr1);
		for(int i = 0; i<10000000 ;i++){
			bf.write(i);
		}
		System.out.println(System.currentTimeMillis()-l) ;
		
	}
	
	
}
