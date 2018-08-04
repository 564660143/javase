package io;

import java.io.FileInputStream ;
import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.OutputStream ;
import org.junit.Test ;

public class ByteStreamTest {
	
	@Test
	public void testCopyFile() throws IOException{
		String sourceFile = "G:\\16112.jpg";
		String targetFile = "G:\\lyf.jpg";
		InputStream fis = new  FileInputStream(sourceFile);
		OutputStream fos = new FileOutputStream(targetFile);
		byte[] b = new byte[1024];
//		int c = 0;
//		while ((c = fis.read()) != -1) {
//			fos.write(c);
//		}
		
		while (fis.read(b) != -1) {
			fos.write(b);
		}	
		fis.close();
		fos.close();
	}
	
}
