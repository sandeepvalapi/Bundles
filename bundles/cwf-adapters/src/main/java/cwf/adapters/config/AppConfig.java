package cwf.adapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:adapterserver.properties")
public class AppConfig {

	@Value("${SERVER_NAME}")
	String SERVER_NAME;

	@Value("${EMAIL_ENDPOINT}")
	String EMAIL_ENDPOINT;

	@Value("${SMS_ENDPOINT}")
	String SMS_ENDPOINT;

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public RestTemplate restTemplateConfig() {
		return new RestTemplate();
	}

	@Bean
	public EmailConfig emailConfig() {
		propertySourcesPlaceholderConfigurer();
		EmailConfig config = new EmailConfig();
		config.setUrl(SERVER_NAME + EMAIL_ENDPOINT);
		return config;
	}

	@Bean
	public SmsConfig smsConfig() {
		propertySourcesPlaceholderConfigurer();
		SmsConfig config = new SmsConfig();
		config.setUrl(SERVER_NAME + SMS_ENDPOINT);
		return config;
	}
}
