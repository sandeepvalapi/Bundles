package cwf.notification.dto;

import java.io.Serializable;

public class EmailMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String toMailId;
	private String subject;
	private String body;
	private String fileLocation;
	private String cc;
	private String bcc;
	private String htmlPart;

	public String getToMailId() {
		return toMailId;
	}

	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getHtmlPart() {
		return htmlPart;
	}

	public void setHtmlPart(String htmlPart) {
		this.htmlPart = htmlPart;
	}

}
