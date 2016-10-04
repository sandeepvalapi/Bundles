package cwf.helper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {

	/**
	 * @return true, If connected to External Network
	 * @throws IOException
	 */
	public boolean isConnected() throws IOException {
		return isHostAvailable("google.com");
	}

	private boolean isHostAvailable(String hostName) throws IOException {
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 3000);
			return true;
		} catch (UnknownHostException unknownHost) {
			return false;
		}
	}
}
