package nio;

import java.net.InetSocketAddress ;
import java.net.SocketAddress ;
import java.nio.channels.SocketChannel ;
import org.junit.Test ;

public class SocketChannelTest {
	
	@Test
	public void testClient() throws Exception{
		SocketAddress addr = new InetSocketAddress("localhost", 8888);
		SocketChannel ssc = SocketChannel.open(addr);
		ssc.configureBlocking(false);
//		boolean connect = ssc.connect();
		System.out.println("client" + ssc.isConnected()) ;
	}
	
}
