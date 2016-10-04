package cwf.notification.channel;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import cwf.notification.sms.SmsUtilitiesImpl;

public class SmsHandlerImpl implements SmsHandler {

	private static final Logger LOGGER = Logger.getLogger(SmsHandlerImpl.class);

	private SmsUtilitiesImpl smsUtils;

	public void setSmsUtils(SmsUtilitiesImpl smsUtils) {
		this.smsUtils = smsUtils;
	}

	/**
	 * Sends SMS to the Recipient
	 * 
	 * @param ActiveMQObjectMessage
	 *            Object
	 */

	@Override
	public void sendSms(ActiveMQObjectMessage activeMQObjectMessage) {
		LOGGER.info("[INFO] : On SMS Queue initiated");
		String deliveryReport;
		try {
			deliveryReport = smsUtils.sendSms(activeMQObjectMessage.getStringProperty("mobileNumber"),
					activeMQObjectMessage.getStringProperty("message"));

			String status = smsUtils.deliveryStatus(deliveryReport);
			String balance = smsUtils.balanceCheck();
			LOGGER.info("[INFO] : Message Delivered - " + status + " Remaining Balance : " + balance);
		} catch (JMSException e) {
			// TODO : Auto-generated catch block
			e.printStackTrace();
		}
	}
}