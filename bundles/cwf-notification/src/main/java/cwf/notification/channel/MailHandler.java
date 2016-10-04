package cwf.notification.channel;

import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 * This interface should have SMS and other channel notifications
 * 
 * @author svalapi
 *
 */
public interface MailHandler {

	/**
	 * Sends an email with four mandatory fields.
	 * 
	 * "toMailId": "mandatory", "subject": "optional", "body": "mandatory",
	 * "fileLocation": optional, "cc": optional, "bcc": optional "htmlPart":
	 * Optional
	 * 
	 * This method uses spring-mail.xml beanid = "mailSender" Configure
	 * mailSender bean to fit for your business email needs
	 * 
	 * 
	 * @param activeMQObjectMessage
	 */
	void sendMail(ActiveMQObjectMessage activeMQObjectMessage);

}
