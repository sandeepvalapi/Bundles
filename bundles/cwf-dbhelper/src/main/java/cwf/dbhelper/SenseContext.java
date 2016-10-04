package cwf.dbhelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import cwf.dbhelper.sequencegenerator.SequenceDao;

/**
 * @author: yogeshsadula
 */
@Component
@Configuration
@PropertySource(value = "classpath:db.properties")
public class SenseContext {
	private static final String SENSE_MONGO_TEMPLATE_NAME = "senseMongoTemplate";
	
	@Autowired SequenceDao sequenceDao;
	@Autowired Environment environment;
	
	@SuppressWarnings("resource")
	public MongoTemplate getSenseDbInstance() {
		// Use below code for Java Annotation based configuration
		/* ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class); */

		// Below code is used for XML based configuration
		ApplicationContext context = new GenericXmlApplicationContext("mongo-config.xml");
		return (MongoTemplate) context.getBean(SENSE_MONGO_TEMPLATE_NAME);
	}

	public synchronized long getNextSequenceId(String tableName) {
		return sequenceDao.getNextSequenceId(tableName);
	}
}