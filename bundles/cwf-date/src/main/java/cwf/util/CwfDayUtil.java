package cwf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cwf.date.CwfClock;

/**
 * CwfDayUtil - contains day time related methods
 * 
 * @author svalapi
 *
 */
@Component
public class CwfDayUtil {
	@Autowired
	CwfClock cwfClock;

	/**
	 * Returns todays calendar object
	 * 
	 * @return
	 */
	public Calendar today() {
		return clearTime(cwfClock.cal());
	}

	public boolean equals(Calendar cal, Object obj) {
		if (!(obj instanceof Calendar)) {
			return false;
		}
		return (compare(cal, (Calendar) obj) == 0);
	}

	public int compare(Calendar cal, Calendar other) {
		return clearTime(cal).compareTo(clearTime(other));
	}

	/**
	 * Returns difference between two dates
	 * 
	 * @param cal
	 * @param other
	 * @return
	 */
	public int daysBetween(Calendar cal, Calendar other) {
		Calendar day1 = clearTime(cal);
		Calendar day2 = clearTime(other);
		long duration = day2.getTimeInMillis() - day1.getTimeInMillis();
		return (int) TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
	}

	public Calendar clearTime(Calendar cal) {
		return (cal == null) ? cal : setClearTime((Calendar) cal.clone());
	}

	/**
	 * Returns abs difference between two dates
	 * 
	 * @param cal
	 * @param other
	 * @return
	 */
	public int daysBetweenAbs(Calendar cal, Calendar other) {
		return Math.abs(daysBetween(cal, other));
	}

	/**
	 * Returns True, if other date is future date
	 * 
	 * @param cal
	 * @param other
	 * @return
	 */
	public boolean isAfter(Calendar cal, Calendar other) {
		return compare(cal, other) > 0;
	}

	/**
	 * Returns True if given calendar is current date
	 * 
	 * @param cal
	 * @return boolean
	 */
	public boolean isToday(Calendar cal) {
		return compare(cal, cwfClock.cal()) == 0;
	}

	public Calendar setClearTime(Calendar cal) {
		if (cal != null) {
			cal.clear(Calendar.DST_OFFSET);
			cal.clear(Calendar.ZONE_OFFSET);
			cal.clear(Calendar.MILLISECOND);
			cal.clear(Calendar.SECOND);
			cal.clear(Calendar.MINUTE);
			cal.clear(Calendar.HOUR_OF_DAY);
			cal.clear(Calendar.HOUR);
			cal.clear(Calendar.AM_PM);
		}
		return cal;
	}

	public String formatDate(Calendar unFormattedDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(unFormattedDate.getTime());
	}
}
