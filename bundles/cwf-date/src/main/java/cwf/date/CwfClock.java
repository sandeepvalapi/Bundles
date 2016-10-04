package cwf.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * CwfClock contain predefined methods related to clock
 *
 * @author svalapi
 *
 */
public interface CwfClock {
	long millis();

	TimeZone getTimeZone();

	Calendar cal();

	Calendar cal(TimeZone timeZone, Locale locale);

	String getCurrentDateTime();

	Date getCurrentDate();
}
