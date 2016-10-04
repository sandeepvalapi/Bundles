package cwf.adapters.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cwf.adapters.config.EmailConfig;
import cwf.adapters.vo.EmailMessage;

@Component
public class Email {

	@Autowired
	EmailConfig emailConfig;

	@Autowired
	RestTemplate restTemplateConfig;

	/**
	 * email.setToMailId("XXXX@XXXX.com");
	 *
	 * email.setSubject("Subject Content"); email.setHtmlPart(
	 * "<html><h1>Welcome Message<h1></html>");
	 *
	 * email.setBody( "Body Message");
	 *
	 * email.setFileLocation("C://Users//XXXXX//XXX//XXX.pdf");
	 *
	 * @param email
	 * @return
	 */
	public boolean send(EmailMessage email) {
		try {
			restTemplateConfig.postForObject(emailConfig.getUrl(), email, EmailMessage.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
