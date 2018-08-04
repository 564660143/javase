package io.file;

import java.io.RandomAccessFile ;

public class CopyFileByRand {
	// 定义每个线程复制的文件块大小
	private final int BLOCK = 1024 * 1024 * 64;
	
	/**
	 * 多线程复制文件
	 * @param souceFile 源文件存放路径
	 * @param targetFile 复制文件存放路径
	 */
	public void currentCopyFile(String souceFile, String targetFile){
		// jdk7新写法,try()表示最终try块执行完毕之后,()中的资源会被释放,等同于在finally块中释放资源
		try ( 
				RandomAccessFile source = new RandomAccessFile(souceFile, "rw");
				RandomAccessFile target = new RandomAccessFile(targetFile, "rw");
				) {
			    // 获取文件长度 
				long fileLength = source.length();
			
				// 获取需要几个线程进行读取,最后一个线程读取剩余的
				long count = fileLength / BLOCK;
				for (int i = 0; i < count; i++) {
					// 设置目标文件长度
					target.setLength(fileLength);
					// "rw" 表示文件可读可写
					source.seek(i * BLOCK);
					target.seek(i * BLOCK);
					// 判断是否是最后一次
					if (i == count -1) {
						// 最后一次时,复制文件长度=文件块大小+最后一小块因为fileLength / BLOCK丢失的小数部分文件块大小
						long copyFileLength = BLOCK + fileLength % BLOCK;
						// 不是最后一次,文件长度就等于文件块长度
						new Thread(	new CopyFileThread(souceFile, targetFile, copyFileLength, i * BLOCK)).start();
					} else{
						// 不是最后一次,文件长度就等于文件块长度
						new Thread(	new CopyFileThread(souceFile, targetFile, BLOCK, i * BLOCK)).start();
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String [] args) {
		CopyFileByRand copy = new CopyFileByRand();
		copy.currentCopyFile("G:/迅雷下载/变形金刚5.mp4", "E:/变形金刚5.mp4");
	}
	
	
	
	/**
	 * 文件复制线程
	 * @author Administrator
	 *
	 */
	class CopyFileThread implements Runnable{
		// 源文件路径
		private  String souceFile;
		// 目标文件路径
		private  String targetFile;
		// 复制文件长度
		private  Long copyLength;
		// seek指定文件位置
		private  Long seekIndex;
		
		
		public CopyFileThread(String souceFile, String targetFile, long copyLength , long seekIndex) {
			this.souceFile = souceFile;
			this.targetFile =  targetFile;
			this.copyLength = copyLength;
			this.seekIndex = seekIndex;
		}

		@Override
		public void run() {
			// 设置读写文件缓冲区,减少文件读写对磁盘的访问次数,提高性能
			byte [] buffer = new byte[1024];
			try (	RandomAccessFile souce = new RandomAccessFile(souceFile, "rw") ;
					RandomAccessFile target =  new RandomAccessFile(targetFile, "rw") ;) {
				// 计算需要读取的文件次数
				long  readCount = copyLength / 1024;
				for (int i = 0; i < readCount; i++) {
					if (i != readCount -1 ) {
						// 不是最后一次时
						souce.read(buffer);
						target.write(buffer);
					} else {
						// 最后一次时,获取剩余文件长度
						int tempBufferLength = 1024 + (int)(copyLength % 1024);
						byte[] tempBuffer = new byte[tempBufferLength];
						souce.read(tempBuffer);
						target.write(tempBuffer);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
	}
	
}
