package cwf.adapters.vo;

import java.io.Serializable;

public class SmsMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mobileNumber;
	private String message;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
