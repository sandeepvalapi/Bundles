package cwf.notification.queue;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import cwf.network.PortCheck;
import cwf.notification.dto.EmailMessage;
import cwf.notification.dto.SmsMessage;
import cwf.notification.sender.MessageSenderImpl;

@Component
@PropertySource("classpath:amqlocation.properties")
public class QueueSystemServiceImpl implements QueueSystemService, InitializingBean {
	private static final Logger LOGGER = Logger.getLogger(QueueSystemServiceImpl.class);
	@Autowired
	private PortCheck portCheck;

	/*
	 * // TODO convert to XML final String[] AMQLocation = { "cmd.exe", "/C",
	 * "Start", "C:\\SandeepFiles\\JAVAPOC\\amq\\bin\\win64\\activemq.bat" };
	 * final String HOST_NAME = "localhost"; final int PORT_NUMBER = 8161;
	 */

	public void setPortCheck(PortCheck portCheck) {
		this.portCheck = portCheck;
	}

	@Value("${AMQLocation}")
	private String AMQLocation;

	@Value("${PORT_NUMBER}")
	private String HOST_NAME;
	/*
	 * @Value("#{new Integer.parseInt('${PORT_NUMBER}')}") private Integer
	 * PORT_NUMBER;
	 */
	@Autowired
	private MessageSenderImpl messageSender;

	public void setMessageSender(MessageSenderImpl messageSender) {
		this.messageSender = messageSender;
	}

	/**
	 * convert and sends EmailMessage to ObjectMessage and then sends to
	 * Activemq server Activemq details and Queue details are configured in XML
	 * properties. These values are configured for organization.
	 *
	 * @param EmailMessage
	 *            TODO: Add BCC and CC
	 */
	@Override
	public void pushMailToQueue(EmailMessage emailMessage) throws JMSException {
		ObjectMessage activeMqObjectMessage = new ActiveMQObjectMessage();
		activeMqObjectMessage.setStringProperty("toMailId", emailMessage.getToMailId());
		activeMqObjectMessage.setStringProperty("subject", emailMessage.getSubject());
		activeMqObjectMessage.setStringProperty("body", emailMessage.getBody());
		activeMqObjectMessage.setStringProperty("htmlPart", emailMessage.getHtmlPart());
		activeMqObjectMessage.setStringProperty("fileLocation", emailMessage.getFileLocation());
		messageSender.sendMessage(activeMqObjectMessage);
		LOGGER.info("[INFO] : Pushed Mail to Active MQueue");
	}

	/**
	 * convert and sends SmsMessage to ObjectMessage and then sends to Activemq
	 * server Activemq details and Queue details are configured in XML
	 * properties. These values are configured for organization.
	 *
	 * @param SmsMessage
	 */
	@Override
	public void pushSmsToQueue(SmsMessage smsMessage) throws JMSException {
		ObjectMessage activeMqObjectMessage = new ActiveMQObjectMessage();
		activeMqObjectMessage.setStringProperty("mobileNumber", smsMessage.getMobileNumber());
		activeMqObjectMessage.setStringProperty("message", smsMessage.getMessage());
		messageSender.sendSms(activeMqObjectMessage);
		LOGGER.info("[INFO] : Pushed SMS Message to Active MQueue");
	}

	/**
	 * It is initializer method when ever we deploy this application after beans
	 * initialize this method runs automatically it checks the Activemq port is
	 * active or not,and it activates theActivemq server automatically if it is
	 * not active
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(AMQLocation);
		// if (portCheck.isPortAvailable(HOST_NAME, 8161)) {
		// LOGGER.info("AMQ is Activate");
		// } else {
		// LOGGER.info("Starting AMQ Server");
		// Process p = Runtime.getRuntime().exec(AMQLocation);
		// }
	}
}
