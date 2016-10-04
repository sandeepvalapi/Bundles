package cwf.notification.receiver;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;

import cwf.notification.channel.SmsHandler;

public class SmsReceiverImpl implements MessageReceiver {
	private static final Logger LOGGER = Logger.getLogger(SmsReceiverImpl.class);
	private SmsHandler smsHandler;

	public void setSmsHandler(SmsHandler smsHandler) {
		this.smsHandler = smsHandler;
	}

	/*
	 * javax.jms.MessageListener configured in the XML file for a particular
	 * queue javax.jms.MessageListener listen that particular queue and calls
	 * the onMessage method and gives this message to SmsHandler
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) message;
			LOGGER.info("[INFO] : Reading Message");
			smsHandler.sendSms(activeMQObjectMessage);
		}
	}

}
