package cwf.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class CwfClockImpl implements CwfClock {
	private static final String TZ_IST = "IST";

	synchronized void init() {
		// initTimeZone();
		// TODO : init some time zones here - new
	}

	@Override
	public long millis() {
		return System.currentTimeMillis();
	}

	@Override
	public TimeZone getTimeZone() {
		return null;
	}

	@Override
	public Calendar cal() {
		TimeZone.setDefault(TimeZone.getTimeZone(TZ_IST));
		return cal(TimeZone.getDefault(), Locale.getDefault());
	}

	@Override
	public Calendar cal(TimeZone timeZone, Locale locale) {
		return Calendar.getInstance(timeZone, locale);
	}

	@Override
	public String getCurrentDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatType.ISO_DATE_FORMAT.getByFormat());
		return dateFormat.format(cal().getTime());
	}

	@Override
	public Date getCurrentDate() {
		return cal().getTime();
	}
}
