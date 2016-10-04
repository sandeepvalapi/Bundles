package cwf.notification.channel;

import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 * This interface should have SMS and other channel notifications
 * 
 * @author svalapi
 *
 */
public interface SmsHandler {

	void sendSms(ActiveMQObjectMessage activeMQObjectMessage);

}
