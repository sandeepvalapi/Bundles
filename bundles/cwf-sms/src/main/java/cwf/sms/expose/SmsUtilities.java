package cwf.sms.expose;

import java.util.List;

public interface SmsUtilities {
	/*
	 * Use below method for sending message to one mobile number. After sending
	 * SMS, check for Delivery Status in case of any delivery report
	 */
	public String sendSms(String mobileNo, String messageBody);

	/*
	 * All Bulk SMS need to be handled by Message Queues
	 */
	public void sendBulkSms(List<String> mobileNos, String messageBody);

	/*
	 * Check Message Delivery Status.
	 */
	public String deliveryStatus(String messageId);

	/**
	 * Check SMS balance and Make decision to send SMS or not.
	 * 
	 * @return Remaining Balance
	 */
	public String balanceCheck();
}
