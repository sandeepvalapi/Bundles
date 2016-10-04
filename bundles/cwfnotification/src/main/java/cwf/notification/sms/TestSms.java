package cwf.notification.sms;

public class TestSms {

	/**
	 * Testing Purpose Change mobile number and messages
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SmsUtilities utils = new SmsUtilitiesImpl();
		// sending message(mobile numbers,message)
		String messageId = utils.sendSms("9030782250", "Greetings from WhiteBay. Our SMS Channel is working.");
		System.out.println(messageId);
		String status = utils.deliveryStatus(messageId);
		System.out.println(status);
		String balance = utils.balanceCheck();
		System.out.println(balance);

	}
}
