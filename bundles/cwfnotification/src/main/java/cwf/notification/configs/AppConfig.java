package cwf.notification.configs;

import java.util.Properties;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cwf.network.utils.PortCheck;
import cwf.notification.channel.MailHandlerImpl;
import cwf.notification.channel.SmsHandlerImpl;
import cwf.notification.queue.QueueSystemService;
import cwf.notification.queue.QueueSystemServiceImpl;
import cwf.notification.receiver.MessageReceiverImpl;
import cwf.notification.receiver.SmsReceiverImpl;
import cwf.notification.sender.MessageSenderImpl;
import cwf.notification.sms.SmsConstants;
import cwf.notification.sms.SmsUtilitiesImpl;

@Configuration

// @ComponentScan(basePackages = { "cwf.notification.*" })
@PropertySources({ @PropertySource("classpath:sms.properties"), @PropertySource("classpath:email.properties"),
		@PropertySource("classpath:queue.properties") })

public class AppConfig {

	// Configure values from queue properties
	@Value("${NOTIFICATION_QUEUE}")
	String NOTIFICATION_QUEUE;

	@Value("${SMS_CHANNEL_QUEUE}")
	String SMS_CHANNEL_QUEUE;

	@Value("${BROKER_URL}")
	String BROKER_URL;

	// Configure values from email properties
	@Value("${EMAIL_HOST_NAME}")
	String EMAIL_HOST_NAME;

	// @Value("#{new Integer.parseInt('${EMAIL_PORT}')}")

	@Value("#{new Integer('${EMAIL_PORT}')}")
	Integer EMAIL_PORT;

	@Value("${EMAIL_USERNAME}")
	String EMAIL_USERNAME;

	@Value("${EMAIL_PASSWORD}")
	String EMAIL_PASSWORD;

	// Configure values from sms properties
	@Value("${SMS_SENDER_HOST_URL}")
	String SMS_SENDER_HOST_URL;

	@Value("${SMS_DELIVERY_HOST_URL}")
	String SMS_DELIVERY_HOST_URL;

	@Value("${BALANCE_CHECK_URL}")
	String BALANCE_CHECK_URL;

	@Value("${SENDER_ID}")
	String SENDER_ID;

	@Value("${TYPE}")
	String TYPE;

	// Size : 256M
	@Value("268435456")
	long MAX_UPLOAD_SIZE;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		propertyConfigIn();
		connectionFactory.setBrokerURL(BROKER_URL);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		// jmsTemplate.setDefaultDestination(mailDestination());
		propertyConfigIn();
		jmsTemplate.setDefaultDestinationName(NOTIFICATION_QUEUE);
		return jmsTemplate;
	}

	@Bean
	public JmsTemplate jmsSmsTemplate() {
		JmsTemplate jmsSmsTemplate = new JmsTemplate();
		jmsSmsTemplate.setConnectionFactory(connectionFactory());
		propertyConfigIn();
		jmsSmsTemplate.setDefaultDestinationName(SMS_CHANNEL_QUEUE);
		return jmsSmsTemplate;
	}

	@Bean
	public MessageSenderImpl messageSender() {
		MessageSenderImpl messagesenderr = new MessageSenderImpl();
		messagesenderr.setJmsTemplate(jmsTemplate());
		messagesenderr.setJmsSmsTemplate(jmsSmsTemplate());
		return messagesenderr;
	}

	@Bean
	public MessageReceiverImpl messageReceiver() {
		MessageReceiverImpl messageReceiver = new MessageReceiverImpl();
		messageReceiver.setMailHandler(mailHandler());
		return messageReceiver;

	}

	@Bean
	public SmsReceiverImpl smsReceiver() {
		SmsReceiverImpl smsImpl = new SmsReceiverImpl();
		smsImpl.setSmsHandler(smsHandler());
		return smsImpl;
	}

	@Bean
	public DefaultMessageListenerContainer jmsContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		// **container.setDestination(mailDestination());
		// container.setDestinationName("NOTIFICATION_QUEUE");
		propertyConfigIn();
		container.setDestinationName(NOTIFICATION_QUEUE);
		container.setMessageListener(messageReceiver());
		return container;
	}

	@Bean
	public DefaultMessageListenerContainer jmsSmsContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		propertyConfigIn();
		container.setDestinationName(SMS_CHANNEL_QUEUE);
		container.setMessageListener(smsReceiver());
		return container;
	}

	@Bean
	public MailHandlerImpl mailHandler() {
		MailHandlerImpl handlerImpl = new MailHandlerImpl();
		handlerImpl.setMailSender(mailSender());
		return handlerImpl;

	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		propertyConfigIn();
		javaMailSender.setHost(EMAIL_HOST_NAME);
		javaMailSender.setPort(EMAIL_PORT);
		javaMailSender.setUsername(EMAIL_USERNAME);
		javaMailSender.setPassword(EMAIL_PASSWORD);
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", Boolean.TRUE.toString());
		prop.setProperty("mail.smtp.starttls.enable", Boolean.TRUE.toString());
		javaMailSender.setJavaMailProperties(prop);
		return javaMailSender;

	}

	@Bean
	public SmsConstants smsConstants() {
		SmsConstants sms = new SmsConstants();
		propertyConfigIn();
		sms.setMAIN_URL(SMS_SENDER_HOST_URL);
		sms.setDELIVERY_URL(SMS_DELIVERY_HOST_URL);
		sms.setBALANCE_CHECK_URL(BALANCE_CHECK_URL);
		sms.setSENDER_ID(SENDER_ID);
		sms.setTYPE(TYPE);
		return sms;
	}

	@Bean
	public SmsUtilitiesImpl smsUtils() {
		SmsUtilitiesImpl smsUtilImpl = new SmsUtilitiesImpl();
		smsUtilImpl.setSmsConstants(smsConstants());
		return smsUtilImpl;
	}

	@Bean
	public SmsHandlerImpl smsHandler() {
		SmsHandlerImpl smsHndlImpl = new SmsHandlerImpl();
		smsHndlImpl.setSmsUtils(smsUtils());
		return smsHndlImpl;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		propertyConfigIn();
		resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
		return resolver;
	}

	@Bean
	public PortCheck portCheck() {
		PortCheck portCheck = new PortCheck();
		return portCheck;
	}

	@Bean
	public QueueSystemService queueSystemService() {
		QueueSystemServiceImpl queueSystemService = new QueueSystemServiceImpl();
		propertyConfigIn();
		queueSystemService.setPortCheck(portCheck());
		queueSystemService.setMessageSender(messageSender());
		return queueSystemService;

	}

}
