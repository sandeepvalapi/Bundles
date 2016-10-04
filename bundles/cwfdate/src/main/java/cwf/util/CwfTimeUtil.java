package cwf.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cwf.date.CwfClock;

/**
 * CwfTimeUtil- contains utility methods related to time
 * @author svalapi
 *
 */
@Component
public class CwfTimeUtil {
	@Autowired 
	CwfClock cwfClock;
	
	/**
	 * Convert Date object to Calendar
	 * @param date
	 * @return
	 */
	public Calendar toCalendar(Date date) {
		if(date == null) {
			return null;
		}
		Calendar cal = cwfClock.cal();
		cal.setTime(date);
		return cal;
	}
}
