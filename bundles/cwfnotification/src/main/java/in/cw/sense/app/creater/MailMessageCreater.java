package in.cw.sense.app.creater;

import cwf.notification.creator.Creater;
import cwf.notification.creator.Payload;
import cwf.notification.dto.EmailMessage;

public class MailMessageCreater extends Creater {
	TemplateSource source;

	public MailMessageCreater(TemplateSource source) {
		super();
		this.source = source;
	}

	public MailMessageCreater() {
		// TODO Auto-generated constructor stub
	}

	public TemplateSource getSource() {
		return source;
	}

	public void setSource(TemplateSource source) {
		this.source = source;
	}

	@Override
	public EmailMessage createEmailMessage(EmailMessage emailMessage, Payload payload) {
		source = new TemplateSource();
		// Get Template message from DB, to be called from sense
		source.setOuter(
				"<html><body align=\"center\"><h1>${invoice}</h1>UserName12341001 : ${username}<br>OrderId : ${orderId}<br><br><table align=\"center\"><tbody><tr><th style=\"width: 150px;\">Items</th><th style=\"width: 150px;\">Quantity</th><th style=\"width: 150px;\">Price</th></tr>${repeat-values}</tbody></table><h2>Address : ${address}<br> Contact : ${contact}</h2></body></html>");
		source.setInner(
				"<tr align=\"center\"><td style=\"width: 150px;\">${name}</td><td style=\"width: 150px;\">${price}</td></tr>");
		emailMessage.setHtmlPart(CreateEmail.getCreateEmail(payload, source));
		return emailMessage;
	}
}
