package cwf.sms;

import java.util.ResourceBundle;

/*
 * Constants are being called from exposed API.
 * Please keep key and username confidential.
 */
public class SmsConstants {
	/*
	 * Resource Location of Config file
	 */
	static ResourceBundle resource = ResourceBundle.getBundle("config");
	/*
	 * Main URL is used to send Actual SMS
	 */
	public static String MAIN_URL = "http://smshorizon.co.in/api/sendsms.php?user=" + resource.getString("username")
			+ "&apikey=" + resource.getString("apikey");

	public static String DELIVERY_URL = "http://smshorizon.co.in/api/status.php?user=" + resource.getString("username")
			+ "&apikey=" + resource.getString("apikey");

	public static String BALANCE_CHECK_URL = "http://smshorizon.co.in/api/balance.php?user="
			+ resource.getString("username") + "&apikey=" + resource.getString("apikey");

	public static String SENDER_ID = resource.getString("senderid");

	public static String TYPE = resource.getString("type");
}
