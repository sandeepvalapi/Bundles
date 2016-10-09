package cwf.sms;

import cwf.sms.expose.SmsUtilities;
import cwf.sms.expose.SmsUtilitiesImpl;

public class TestSms {

	/**
	 * Testing Purpose Change mobile number and messages
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SmsUtilities utils = new SmsUtilitiesImpl();
		// sending message(mobile numbers,message)
		String messageId = utils.sendSms("9959377576",
				"Greetings from WhiteBay. Our SMS Channel is working.");
		System.out.println("Message ID : " + messageId);
		String status = utils.deliveryStatus(messageId);
		System.out.println("Message Status : " + status);
		String balance = utils.balanceCheck();
		System.out.println("Balance Count :" + balance);

	}
}
