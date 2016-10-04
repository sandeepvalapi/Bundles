package cwf.adapters.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cwf.adapters.config.SmsConfig;
import cwf.adapters.vo.EmailMessage;
import cwf.adapters.vo.SmsMessage;

@Component
public class Sms {

	@Autowired
	SmsConfig smsConfig;

	@Autowired
	RestTemplate restTemplateConfig;

	public boolean send(SmsMessage sms) {
		try {
			restTemplateConfig.postForObject(smsConfig.getUrl(), sms, EmailMessage.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
