package cwf.notification.sms;

/*
 * Constants are being called from exposed API.
 * Please keep key and username confidential.
 */
public class SmsConstants {
	private String MAIN_URL;
	private String DELIVERY_URL;
	private String BALANCE_CHECK_URL;
	private String SENDER_ID;
	private String TYPE;

	public String getMAIN_URL() {
		return MAIN_URL;
	}

	public void setMAIN_URL(String MAIN_URL) {
		this.MAIN_URL = MAIN_URL;
	}

	public String getDELIVERY_URL() {
		return DELIVERY_URL;
	}

	public void setDELIVERY_URL(String DELIVERY_URL) {
		this.DELIVERY_URL = DELIVERY_URL;
	}

	public String getBALANCE_CHECK_URL() {
		return BALANCE_CHECK_URL;
	}

	public void setBALANCE_CHECK_URL(String BALANCE_CHECK_URL) {
		this.BALANCE_CHECK_URL = BALANCE_CHECK_URL;
	}

	public String getSENDER_ID() {
		return SENDER_ID;
	}

	public void setSENDER_ID(String SENDER_ID) {
		this.SENDER_ID = SENDER_ID;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}

	/*
	 * Main URL is used to send Actual SMS
	 */

}
