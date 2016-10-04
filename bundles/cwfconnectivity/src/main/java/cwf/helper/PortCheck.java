package cwf.helper;

import java.net.Socket;

/*
 * Returns True if MQ is in Running state. Else False
 */
public class PortCheck {
	public boolean isPortAvailable(String hostName, int portNumber) {
		try {
			Socket socket = new Socket(hostName, portNumber);
			socket.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
