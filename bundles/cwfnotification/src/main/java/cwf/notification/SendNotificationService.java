package cwf.notification;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cwf.notification.dto.EmailMessage;
import cwf.notification.dto.SmsMessage;
import cwf.notification.queue.QueueSystemService;

/**
 * Returns Default message object type TODO : Test - Remove after use
 * 
 * @author svalapi
 *
 */
@RestController
@RequestMapping(value = "/notification")
public class SendNotificationService {

	@Autowired
	private QueueSystemService queueSystemService;

	/**
	 * Sends email to email server. SMTP details and user details are configured
	 * in XML properties. These values are configured for organization.
	 *
	 * @param EmailMessage
	 */
	@RequestMapping(value = "/email", method = RequestMethod.POST, headers = "Accept=application/json")
	public void sendMail(@RequestBody EmailMessage emailMessage) {
		try {
			// TODO: [Ganesh] - Create a properties file with sender object.
			// Expected dynamic from address and configuration behavior
			queueSystemService.pushMailToQueue(emailMessage);
		} catch (JMSException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * TODO: Remove this method after testing
	 *
	 * @param emailMessage
	 */
	@RequestMapping(value = "/multipartemail", method = RequestMethod.POST, headers = "Accept=application/json")
	public void sendMailTesting(@RequestBody EmailMessage emailMessage) {
		try {
			// Below three lines need to be called from sense application
			// Creater creater = new MailMessageCreater();
			// Payload payload = createPayload();
			// creater.createEmailMessage(emailMessage, payload);
			queueSystemService.pushMailToQueue(emailMessage);
		} catch (JMSException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Sends SMS. SMS details and user details are configured in XML properties.
	 * These values are configured for organization.
	 *
	 * @param SmsMessage
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.POST, headers = "Accept=application/json")
	public void sendSms(@RequestBody SmsMessage smsMessage) {
		try {
			queueSystemService.pushSmsToQueue(smsMessage);
		} catch (JMSException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @return Sample Request Object
	 */
	@RequestMapping(method = RequestMethod.GET)
	public EmailMessage getMessage() {
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setToMailId("wow.ganiwow@gmail.com");
		emailMessage.setSubject("tesing1");
		emailMessage.setHtmlPart("<html><h1>hai gani<h1></html>");
		emailMessage.setBody("this is body part");
		emailMessage.setFileLocation("C://log.txt");
		return emailMessage;
	}
}