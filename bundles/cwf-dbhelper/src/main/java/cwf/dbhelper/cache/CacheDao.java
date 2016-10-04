package cwf.dbhelper.cache;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Below mentioned methods are used to interact with cache server
 * 
 * @author svalapi
 *
 */
public interface CacheDao {

	/**
	 * Saves value with given key name If key name exists in cache, then below
	 * method will override the value with latest value
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	<T> boolean save(String key, T value) throws JsonProcessingException;

	/**
	 * Gets value associated with the key
	 * 
	 * @param key
	 * @return string object
	 */
	public String get(String key);

	/**
	 * Remove key and its value from cache
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean remove(String key);

	/**
	 * Remove set of keys and its associated values from cache
	 * 
	 * @param keys
	 * @return boolean
	 */
	public boolean remove(String[] keys);

}
