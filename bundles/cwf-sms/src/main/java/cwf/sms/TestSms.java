package cwf.sms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cwf.sms.expose.SmsUtilities;
import cwf.sms.expose.SmsUtilitiesImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestSms {

	/**
	 * Testing Purpose Change mobile number and messages
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SmsUtilities utils = new SmsUtilitiesImpl();
		// sending message(mobile numbers,message)
		String messageId = utils.sendSms("9030782250", "Greetings from WhiteBay. Our SMS Channel is working.");
		System.out.println(messageId);
		String status = utils.deliveryStatus(messageId);
		System.out.println(status);
		String balance = utils.balanceCheck();
		System.out.println(balance);

	}
}
