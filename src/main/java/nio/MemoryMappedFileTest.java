package nio;

import java.io.FileNotFoundException ;
import java.io.RandomAccessFile ;
import java.nio.MappedByteBuffer ;
import java.nio.channels.FileChannel ;
import java.nio.channels.FileChannel.MapMode ;
import org.junit.Test ;

public class MemoryMappedFileTest {
	
	
	@Test
	public void testCopyByMemoryMappedFile() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("E://1.jpg", "rw");
		FileChannel fc = raf.getChannel();
		MappedByteBuffer map = fc.map(MapMode.READ_WRITE, 0, raf.length());
		RandomAccessFile rout = new RandomAccessFile("E://2.jpg", "rw");
		FileChannel fout = rout.getChannel(); 
		fout.write(map);
		fout.close();
		rout.close();
		raf.close();
	}
	
	
	@Test
	public void testWrite() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("E://2.jpg", "rw");
		FileChannel fc = raf.getChannel();
		MappedByteBuffer map = fc.map(MapMode.READ_WRITE, 0, raf.length());
		for (int i = 500; i < 3000; i++) {
			map.put(i, (byte)0);
		}
		raf.close();
	}
	
	
}
