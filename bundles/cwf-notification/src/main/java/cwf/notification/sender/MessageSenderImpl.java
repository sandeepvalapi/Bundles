package cwf.notification.sender;

import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MessageSenderImpl implements MessageSender {

	private static final Logger LOGGER = Logger.getLogger(MessageSenderImpl.class);
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private JmsTemplate jmsSmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setJmsSmsTemplate(JmsTemplate jmsSmsTemplate) {
		this.jmsSmsTemplate = jmsSmsTemplate;
	}

	/*
	 * sends the Email message to the Activemq server
	 *
	 * @param ObjectMessage
	 */
	@Override
	public void sendMessage(ObjectMessage objectMessage) {
		LOGGER.info("[INFO] : Send Message From -> MessageSenderImpl");
		jmsTemplate.convertAndSend(objectMessage);
	}

	/*
	 * sends the Sms message to the Activemq server
	 *
	 * @param ObjectMessage
	 */
	@Override
	public void sendSms(ObjectMessage smsMessage) {
		LOGGER.info("[INFO] : Send SMS Message From -> MessageSenderImpl");
		jmsSmsTemplate.convertAndSend(smsMessage);
	}
}