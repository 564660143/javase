package gui;

import javax.swing.JFrame ;
import javax.swing.JTextArea ;

public class MainWindow  {
	
	public static void main(String [] args) {
		// 窗口
		JFrame jFrame = new JFrame("主窗口");
		jFrame.setBounds(100, 100, 800, 600);
		jFrame.setVisible(true);
		
		// 文本框
		JTextArea jTextArea = new JTextArea();
		jTextArea.setBounds(100, 100, 600, 100);
		jFrame.add(jTextArea);
		
	}
	
}
