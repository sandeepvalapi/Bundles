package cwf.dbhelper.cache;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

/**
 * @author svalapi
 *
 */
@Service
public class CacheDaoImpl implements CacheDao {

	@SuppressWarnings("resource")
	@Override
	public <T> boolean save(String key, T value) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Input object as string : " + mapper.writeValueAsString(value));
		String stringValue;
		stringValue = mapper.writeValueAsString(value);

		Jedis jedis = new Jedis();
		jedis.set(key, stringValue);
		return true;

	}

	@SuppressWarnings("resource")
	@Override
	public String get(String key) {
		Jedis jedis = new Jedis();
		return jedis.get(key);
	}

	/**
	 * Remove single entry from cache
	 */
	@Override
	public boolean remove(String key) {
		Jedis jedis = new Jedis();
		return jedis.del(key) != null ? true : false;
	}

	/**
	 * Remove bulk keys from cacje
	 */
	@Override
	public boolean remove(String[] keys) {
		Jedis jedis = new Jedis();
		return jedis.del(keys) != null ? true : false;
	}
}
