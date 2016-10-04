package cwf.helper.logging;

public interface LoggerFactory {
	public Logger getLogger(String name);

	public Logger getLogger(Class<?> clazz);
}
