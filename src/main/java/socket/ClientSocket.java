package socket;

import java.io.OutputStream ;
import java.net.Socket ;

public class ClientSocket {
	
	public static void main(String [] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 8888);
		OutputStream os = socket.getOutputStream();
		while(true){
			os.write("Hello World\n".getBytes());
		}
		
	}
	
}
