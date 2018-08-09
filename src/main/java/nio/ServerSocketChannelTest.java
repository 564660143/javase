package nio;

import java.net.InetSocketAddress ;
import java.net.SocketAddress ;
import java.nio.channels.ServerSocketChannel ;
import java.nio.channels.SocketChannel ;
import org.junit.Test ;

public class ServerSocketChannelTest {
	
	@Test
	public void testServer() throws Exception{
		ServerSocketChannel ssc = ServerSocketChannel.open(); 
		SocketAddress addr = new InetSocketAddress("localhost", 8888);
		ssc.bind(addr);
		ssc.configureBlocking(false);
		while (true) {
			SocketChannel channel = ssc.accept();
			System.out.println("server:" + channel) ;
			Thread.sleep(3000);
		}
		
	}
	
}
