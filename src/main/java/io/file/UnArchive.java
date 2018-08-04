package io.file;

import static org.hamcrest.CoreMatchers.nullValue ;
import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.OutputStream ;

/**
 * 文件归档
 * 归档格式
 * 一个字节存放文件类型
 * 四个字节存放文件长度
 * @author Administrator
 *
 */
public class UnArchive {
	
	/**
	 * 文件归档
	 * @param souceFile 归档后的文件
	 * @param soucePath 解档后的目标文件夹
	 */
	public void unArchive(String souceFile, String targetPath){
		
		
		try (FileInputStream fis = new FileInputStream(souceFile);) {
			// 读取文件类型
			while (readNextFile(fis, targetPath)) {
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 读取下一个文件
	 * @param fis
	 * @return
	 */
	public boolean readNextFile(FileInputStream fis, String targetPath){
		
		FileOutputStream fos = null;
		try {
			int type = fis.read();
			if (type == -1) {
				fis.close();
				return false;
			} else {
				// 生成解档文件名
				String extName = getExtName(type);
				// 读取文件长度
				byte[] bytes = new byte[4];
				fis.read(bytes);
				// 将byte数组转换成int
				int fileLength = bytesToInt(bytes);
				// 设置读取缓冲区
				byte[] buffer = new byte[1024];
				// 循环读取文件次数
				int count;
				// 计算余数
				int remain = fileLength % 1024;
				if (remain == 0) {
					count = fileLength / 1024;
				} else {
					count = fileLength / 1024 + 1;
				}
				
				String fileName = targetPath + "/" + System.currentTimeMillis() + extName;
				fos = new FileOutputStream(fileName);
				// 读取文件内容
				for (int i = 0; i < count; i++) {
					if ((i == count -1) && (remain != 0)) {
						byte[] temps = new byte[remain];
						fis.read(temps);
						fos.write(temps);
					} else {
						fis.read(buffer);
						fos.write(buffer);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return true;
	}
	
	
	/**
	 * 获取文件扩展名
	 * @param num
	 * @return
	 */
	private String getExtName(int num){
		String fileType = ".temp";
		switch (num) {
			case 0:
				fileType = ".txt";
				break ;
			case 1:
				fileType = ".jpg";
				break ;
			case 2:
				fileType = ".png";
				break ;
			case 3:
				fileType = ".gif";
				break ;
			case 4:
				fileType = ".exe";
				break ;
			default:
				break ;
		}
		
		return fileType;
	}

	/**
	 * 字符数组转换成int
	 * @param bytes
	 * @return
	 */
	private int bytesToInt(byte[] bytes) {
		int i0 = (bytes[3]<<24);
		int i1 = (bytes[2]& 0xff)<<16 ;
		int i2 = (bytes[1]& 0xff)<<8;
		int i3 = bytes[0] & 0xff;
		return i0 | i1 | i2 | i3 ;
	}
	
	
}
