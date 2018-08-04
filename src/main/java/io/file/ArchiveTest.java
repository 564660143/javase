package io.file;

import org.junit.Test ;

/**
 * 文件归档,解档测试
 * 归档格式
 * 一个字节存放文件类型
 * 四个字节存放文件长度
 * @author Administrator
 *
 */
public class ArchiveTest {
	/**
	 * 文件归档
	 */
	@Test
	public void testArchive(){
		Archive arch = new Archive();
		String[] files = {"E:/test/1.jpg", "E:/test/2.jpg", "E:/test/3.jpg", "E:/test/5.txt"};
		arch.archive(files, "E:/test/target/all.qiye");
	}
	
	/**
	 * 追加文件
	 */
	@Test
	public void testArchiveAppend(){
		Archive arch = new Archive();
		arch.append("E:/test/4.jpg", "E:/test/target/all.qiye");
	}
	
	/**
	 * 文件解档
	 */
	@Test
	public void testUnArchive(){
		UnArchive unArch = new UnArchive();
		unArch.unArchive("E:/test/target/all.qiye", "E:/test/target");
	}
	
}
