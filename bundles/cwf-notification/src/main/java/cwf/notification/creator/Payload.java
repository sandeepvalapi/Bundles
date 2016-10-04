package cwf.notification.creator;

public class Payload {
	Integer templateId;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Payload(Integer templateId) {
		this.templateId = templateId;
	}

	public Payload() {
	}

}
