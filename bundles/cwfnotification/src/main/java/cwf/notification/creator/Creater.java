package cwf.notification.creator;

import cwf.notification.dto.EmailMessage;

public abstract class Creater {

	public abstract EmailMessage createEmailMessage(EmailMessage emailMessage, Payload payload);
}
