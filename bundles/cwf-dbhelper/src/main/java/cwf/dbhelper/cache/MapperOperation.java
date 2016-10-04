package cwf.dbhelper.cache;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Use below mapper operation class when you want to convert a string to a generic object
 * @author svalapi
 * Returns mentioned object
 */
public class MapperOperation {

	public static <T> T mapValue(final String value, final T t)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		T obj = (T) mapper.readValue(value, t.getClass());
		return obj;
	}

}
