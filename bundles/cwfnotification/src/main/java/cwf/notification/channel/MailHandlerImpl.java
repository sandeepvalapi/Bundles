package cwf.notification.channel;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailHandlerImpl implements MailHandler {

	private static final Logger LOGGER = Logger.getLogger(MailHandlerImpl.class);
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Sends email to the Recipient
	 * 
	 * @param ActiveMQObjectMessage
	 *            Object
	 */

	@Override
	public void sendMail(final ActiveMQObjectMessage message) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			/**
			 * Prepares an email object and sends
			 */

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				System.out.println("Inside Prepare()");
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(message.getStringProperty("toMailId")));
				mimeMessage.setSubject(message.getStringProperty("subject"));
				mimeMessage.setText(message.getStringProperty("body"));
				mimeMessage.setRecipients(Message.RecipientType.BCC, message.getStringProperty("bcc"));
				mimeMessage.setRecipients(Message.RecipientType.CC, message.getStringProperty("cc"));
				if (!StringUtils.isBlank(message.getStringProperty("htmlPart"))
						|| !StringUtils.isBlank(message.getStringProperty("fileLocation"))) {

					Multipart multipart = new MimeMultipart();

					/**
					 * Adds the html content to multipart
					 */
					MimeBodyPart htmlpart = new MimeBodyPart();
					if (!StringUtils.isBlank(message.getStringProperty("htmlPart"))) {
						LOGGER.info("[INFO] : Contains HTML Part Data");
						System.out.println("[INFO] : Contains HTML Part Data");
						System.out.println("[INFO] : Contains HTML data ::" + message.getStringProperty("htmlPart"));
						htmlpart.setContent(message.getStringProperty("htmlPart"), "text/html;charset=UTF-8");
						multipart.addBodyPart(htmlpart);
					}
					/**
					 * Adds the attachment part to multipart
					 */
					MimeBodyPart attachmentPart = new MimeBodyPart();
					if (!StringUtils.isBlank(message.getStringProperty("fileLocation"))) {
						LOGGER.info("[INFO] : Contains Attachment");
						String filename = message.getStringProperty("fileLocation");
						DataSource source = new FileDataSource(filename);
						attachmentPart.setDataHandler(new DataHandler(source));
						attachmentPart.setFileName(filename);
						multipart.addBodyPart(attachmentPart);
					}
					mimeMessage.setContent(multipart);
				}
			}
		};
		System.out.println("Test....." + preparator.toString());
		this.mailSender.send(preparator);
		LOGGER.info("[INFO] : Mail Sent");
	}
}