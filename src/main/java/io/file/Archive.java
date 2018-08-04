package io.file;

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
public class Archive {
	
	/**
	 * 文件归档
	 * @param files 要归档的文件列表
	 * @param soucePath 归档后文件路径
	 */
	public void archive(String[] files, String soucePath){
		try (FileOutputStream fos = new FileOutputStream(soucePath)) {
			for (String str : files) {
				// 归档文件
				append(str,fos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 归档文件,将单个文件追加到归档文件的输出流中
	 * @param str
	 * @param fos
	 */
	private void append(String filepath, FileOutputStream fos) {
		// 获取文件类型
		int type = getFileType(filepath);
		try (FileInputStream fis = new FileInputStream(filepath)) {
			// 获取文件长度
			int length = fis.available();
			// 将文件长度转换成4个字节的byte数组
			byte[] bytes = intToBytes(length);
			// 将文件类型写入归档文件
			byte fType = (byte) type;
			fos.write(new byte[]{fType});
			// 将文件长度写入
			fos.write(bytes);
			byte[] buffer = new byte[1024];
			int count;
			// 计算余数
			int remain = length % 1024;
			if (remain == 0) {
				count = length / 1024;
			} else {
				count = length / 1024 + 1;
			}

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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte [] intToBytes(int num) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) num;
		bytes[1] = (byte) (num >> 8);
		bytes[2] = (byte) (num >> 16);
		bytes[3] = (byte) (num >> 24);
		return bytes ;
	}
	
	/**
	 * 获取文件类型
	 * 0:txt,1:jpg,2:png,3:gif,4:exe
	 * @param file
	 * @return
	 */
	private int getFileType(String str) {
		String extName = str.substring(str.lastIndexOf(".")).toLowerCase();
		int type = -1;
		switch (extName) {
			case ".txt":
				type = 0;
				break ;
			case ".jpg":
				type = 1;
				break ;
			case ".png":
				type = 2;
				break ;
			case ".gif":
				type = 3;
				break ;
			case ".exe":
				type = 4;
				break ;
		}
		
		return type ;
	}
	
	/**
	 * 归档文件,将单个文件追加到归档文件的输出流中
	 * @param str
	 * @param fos
	 */
	public void append(String sourceFile, String target) {
		
		try (FileOutputStream fos = new FileOutputStream(target, true)) {
			append(sourceFile, fos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
