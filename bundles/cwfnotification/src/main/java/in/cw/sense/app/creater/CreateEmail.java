package in.cw.sense.app.creater;

import cwf.notification.creator.Payload;

public abstract class CreateEmail {

	public static String getCreateEmail(Payload payload, TemplateSource ts) {
		if (payload.getTemplateId() == 1001) {
			return new E1001(payload, ts).getEmailContent();
		} else if (payload.getTemplateId() == 1002) {
			return new E1002(payload, ts).getEmailContent();
		}
		return null;
	}

}
