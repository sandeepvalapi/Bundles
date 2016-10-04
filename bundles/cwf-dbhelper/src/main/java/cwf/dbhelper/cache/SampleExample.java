package cwf.dbhelper.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SampleExample {

	/**
	 * Sample example and usage of cache interaction in application
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(CacheConfig.class);
		CacheDao dao = ctx.getBean(CacheDaoImpl.class);
		Employee one = new Employee();
		Employee two = new Employee();
		try {
			one.setFirstName("Sandeep kumar");
			one.setLastName("valapi");
			one.setId(386906);

			two.setFirstName("Ravikanth");
			two.setLastName("Shetty");
			two.setId(386907);

			//Imagine you have a java object
			List<Employee> list = new ArrayList<>();
			list.add(one);
			list.add(two);

			ObjectMapper mapper = new ObjectMapper();
//			System.out.println("Input object as string : " + mapper.writeValueAsString(list));
//			String employeeString = mapper.writeValueAsString(list);
			//use dao.save(key, value) for saving values to cache
			dao.save("testvalue", list);
			
			//To get value from cache
			String fromCache = dao.get("testvalue");
			System.out.println("getCachedString : " + fromCache);
			
			/*
			 * If we want to map value from cache then use below method style
			 * Employee obj = mapper.readValue(getCachedString, Employee.class);->if you save only one object
			 */
			ArrayList<Employee> obj = (ArrayList<Employee>) MapperOperation.mapValue(fromCache,
					new ArrayList<Employee>());
			Employee etr = mapper.convertValue(obj.get(1), Employee.class);
			System.out.println("After getting Cached: " + etr.getFirstName() + obj.size());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
