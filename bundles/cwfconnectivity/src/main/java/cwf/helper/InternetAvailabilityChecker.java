package cwf.helper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Use this class to check for Internet availability 
 */
public class InternetAvailabilityChecker {
	
	/**
	 * Looks for internet availability
	 * @return true, if connected to internet
	 * @throws IOException
	 */
	public boolean isInternetAvailable() throws IOException
    {
        return isHostAvailable("google.com");
    }

    private boolean isHostAvailable(String hostName) throws IOException
    {
        try(Socket socket = new Socket())
        {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);
            return true;
        }
        catch(UnknownHostException unknownHost)
        {
            return false;
        }
    }
}
