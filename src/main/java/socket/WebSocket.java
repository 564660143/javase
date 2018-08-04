package socket;

import java.io.IOException ;
import java.io.InputStream ;
import java.net.ServerSocket ;
import java.net.Socket ;

public class WebSocket {
	
	public static void main(String [] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket accept = serverSocket.accept();
		System.out.println("有连接进来") ;
		while (true) {
			InputStream is = accept.getInputStream();
			int available = is.available() ;
			byte[] msg = new byte[available];
			is.read(msg);
			System.out.println(new String(msg)) ;
			
		}
		
	}
	
	
}
