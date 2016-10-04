package cwf.notification.queue;

import javax.jms.JMSException;

import org.springframework.stereotype.Component;

import cwf.notification.dto.EmailMessage;
import cwf.notification.dto.SmsMessage;

@Component
public interface QueueSystemService {

	void pushMailToQueue(EmailMessage emailMessage) throws JMSException;

	void pushSmsToQueue(SmsMessage smsMessage) throws JMSException;
}
