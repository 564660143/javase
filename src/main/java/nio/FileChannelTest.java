package nio;

import java.io.RandomAccessFile ;
import java.nio.ByteBuffer ;
import java.nio.channels.FileChannel ;
import org.junit.Test ;

public class FileChannelTest {
	
	@Test
	public  void testCopyFile() throws Exception{
		String srcFile = "F://1.jpg";
		String targetFile = "F://2.jpg";
		copyFile(srcFile, targetFile);
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public void copyFile(String srcFile, String targetFile) throws Exception{
		RandomAccessFile srcRandom = new RandomAccessFile(srcFile, "r");
		FileChannel srcChannel = srcRandom.getChannel();
		RandomAccessFile targetRandom = new RandomAccessFile(targetFile, "rw");
		FileChannel targetChannel = targetRandom.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
		while (srcChannel.read(buffer) != -1) {
			buffer.flip();
			targetChannel.write(buffer);
			buffer.clear();
		}
		
		srcChannel.close();
		targetChannel.close();
		srcRandom.close();
		targetRandom.close();
		
	}
}
