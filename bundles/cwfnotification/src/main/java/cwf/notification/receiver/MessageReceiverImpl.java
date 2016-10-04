package cwf.notification.receiver;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cwf.notification.channel.MailHandler;

public class MessageReceiverImpl implements MessageReceiver {
	private static final Logger LOGGER = Logger.getLogger(MessageReceiverImpl.class);
	@Autowired
	MailHandler mailHandler;

	public void setMailHandler(MailHandler mailHandler) {
		this.mailHandler = mailHandler;
	}

	/*
	 * javax.jms.MessageListener configured in the XML file for a particular
	 * queue javax.jms.MessageListener listen that particular queue and calls
	 * the onMessage method and gives this message to MailHandler
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) message;
			LOGGER.info("[INFO] : Reading Message");
			mailHandler.sendMail(activeMQObjectMessage);
		}
	}

}
