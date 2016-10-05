package cwf.app.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Component;

/**
 * The Class NumberUtils.
 */
@Component
public class NumberUtils {

	/**
	 * Round to two decimal places.
	 *
	 * @param value
	 *            the value
	 * @return the double
	 */
	public double roundToTwoDecimalPlaces(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	/**
	 * Round the value to the next closest value
	 *
	 * Ex : 1205.1 will be converted to 1205
	 *
	 * 1245.67 will be converted to 1246
	 *
	 * @param value
	 *            the value
	 * @return the int
	 */
	public int round(double value) {
		return (int) Math.round(value);
	}

	public BigDecimal toBigDecimal(String str) {
		return (str != null) ? new BigDecimal(str) : null;
	}

	public BigDecimal toBigDecimal(Number value) {
		if (value == null) {
			return null;
		}

		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}

		if (value instanceof BigInteger) {
			return new BigDecimal((BigInteger) value);
		}

		if ((value instanceof Double) || (value instanceof Float)) {
			return BigDecimal.valueOf(value.doubleValue());
		}
		return BigDecimal.valueOf(value.longValue());
	}

	public Integer toInteger(Number value) {
		return (value != null) ? Integer.valueOf(value.intValue()) : null;
	}

	public Integer toInteger(String str) {
		return (str != null) ? Integer.valueOf(str) : null;
	}

	public Long toLong(Number value) {
		return (value != null) ? Long.valueOf(value.longValue()) : null;
	}

	public Long toLong(String str) {
		return (str != null) ? Long.valueOf(str) : null;
	}

	public String toString(Number value) {
		return (value != null) ? value.toString() : null;
	}

	public boolean equals(BigInteger value, Number other) {
		if (value == other) {
			return true;
		} else if ((value == null) || (other == null)) {
			return false;
		}

		if (other instanceof BigInteger) {
			return value.equals(other);
		} else if (other instanceof BigDecimal) {
			return value.equals(((BigDecimal) other).toBigInteger());
		}
		return value.equals(BigInteger.valueOf(other.longValue()));
	}

	public int compare(Integer value, Number other) {
		if (value == null) {
			return (other != null) ? -1 : 0;
		}
		return compare((int) value.longValue(), other);
	}
}
