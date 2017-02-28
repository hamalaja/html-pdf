package vn.hamalaja.converter.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author lamhm
 *
 */
public class DateExUtils {
	public static final long MILISECOND_IN_DAY = 86400000;


	/**
	 * Format thời gian theo pattern dd-MM-yyyy HH:mm:ss
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(long time) {
		return format(new DateTime(time), "dd-MM-yyyy HH:mm:ss");
	}


	/**
	 * Format thời gian theo pattern
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(long time, String format) {
		return format(new DateTime(time), format);
	}


	/**
	 * Format thời gian theo pattern
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(DateTime dateTime, String format) {
		return DateTimeFormat.forPattern(format).print(dateTime);
	}


	/**
	 * Format thời gian theo pattern
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String formatFinishTime(long time) {
		String result = "Thứ ";
		DateTime dateTime = new DateTime(time);
		int i = dateTime.dayOfWeek().get();
		if (i == 7) {
			result = "Chủ nhật";
		} else {
			result += String.valueOf(i + 1);
		}

		return result + ", " + format(dateTime, "dd/MM/yyyy hh:mm a");
	}
}
