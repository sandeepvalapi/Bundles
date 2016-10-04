package cwf.sms.expose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import cwf.sms.SmsConstants;

public class SmsUtilitiesImpl implements SmsUtilities {

	/**
	 * Sends SMS for given mobile number.
	 */
	@Override
	public String sendSms(final String mobileNo, final String message) {
		StringBuilder urlLocator = new StringBuilder(SmsConstants.MAIN_URL);
		urlLocator.append("&mobile=" + mobileNo);
		try {
			String encoded_message = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
			urlLocator.append("&message=" + encoded_message);
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
			// TODO: Handle Runtime with appropriate error message
			throw new RuntimeException("Failed to Encode message");
		}
		urlLocator.append("&senderid=" + SmsConstants.SENDER_ID);
		urlLocator.append("&type=" + SmsConstants.TYPE);
		System.out.println("Sending SMS: " + urlLocator.toString());
		return urlHit(urlLocator.toString());
	}

	/**
	 * Check Delivery Status of Message
	 */
	@Override
	public String deliveryStatus(String messageId) {
		StringBuilder sBuilder = new StringBuilder(SmsConstants.DELIVERY_URL);
		sBuilder.append("&msgid=" + messageId);
		String url = sBuilder.toString();
		return urlHit(url);
	}

	/**
	 * Checks Balance count
	 */
	@Override
	public String balanceCheck() {
		return urlHit(SmsConstants.BALANCE_CHECK_URL);
	}

	private String urlHit(String urlPath) {
		URLConnection urlConnection = null;
		URL url = null;
		String response = null;
		BufferedReader reader;
		try {
			url = new URL(urlPath);
			urlConnection = url.openConnection();
			urlConnection.connect();
			reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			response = reader.readLine();
			reader.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void sendBulkSms(List<String> mobileNos, String message) {
		for (String mobileNumber : mobileNos) {
			sendSms(mobileNumber, message);
		}
	}

}