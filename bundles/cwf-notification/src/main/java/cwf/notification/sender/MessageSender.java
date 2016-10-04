package cwf.notification.sender;

import javax.jms.ObjectMessage;

import org.springframework.stereotype.Component;

@Component
public interface MessageSender {

	void sendMessage(ObjectMessage objectMessage);

	void sendSms(ObjectMessage objectMessage);
}
