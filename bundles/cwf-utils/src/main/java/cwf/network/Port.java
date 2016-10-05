package cwf.network;

import java.net.Socket;

/*
 * Returns True if MQ is in Running state. Else False
 */
public class Port {
	public boolean isFree(String hostName, int portNumber) {
		try {
			Socket socket = new Socket(hostName, portNumber);
			socket.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
