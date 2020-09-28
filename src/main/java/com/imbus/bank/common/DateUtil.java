/**
 * Pinganfu.com Inc. Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.imbus.bank.common;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

	/** yyyyMMddHHmmss */
	public final static String LONG_FORMAT = "yyyyMMddHHmmss";

	/** yyyy-MM-dd HH:mm:ss */
	public final static String LONG_WEB_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** yyyyMMddHHmmssSSS */
	public final static String LONG_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	/** yyyy-MM-dd */
	public final static String WEB_FORMAT = "yyyy-MM-dd";

	public static String format(final Date date, final String format) {
		if (date == null || StringUtil.isBlank(format)) {
			return StringUtil.EMPTY_STRING;
		}

		return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).format(date);
	}

	public static String getTimestamp()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
	}

	public static String getDatestamp()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
	}

	public static String formatCurrent(final String format) {
		if (StringUtil.isBlank(format)) {
			return StringUtil.EMPTY_STRING;
		}

		return format(new Date(), format);
	}

	public static String formatLongWeb(final Date date) {
		return format(date, LONG_WEB_FORMAT);
	}


	public static Date parse(final String dateStr, final String format) throws ParseException {
		if (StringUtil.isBlank(format)) {
			throw new ParseException("format can not be null.", 0);
		}
		if (dateStr == null) {
			return null;
		}

		if (dateStr.length() < format.length()) {
			throw new ParseException("date string's length is too small.", 0);
		}

		return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).parse(dateStr);
	}

	/**
	 * 2016年10月12日 now
	 *
	 * @return new Date()
	 */
	public static Date getNow() {
		return new Date();
	}

	public static Date addMinutes(final Date date, final long minutes) {
		return new Date(date.getTime() + minutes * 60 * 1000L);
	}
	
	public static Date addDay(final Date date, final long day) {
		return new Date(date.getTime() + day * 60 * 1000L*60*24);
	}

	public static String formatWeb(final Date date) {
		return format(date, WEB_FORMAT);
	}

	/**
	 * @return 格式化的字符串
	 */
	public static String formatLongyyyyMMddHHmmssSSSFormatByDate() {
		return format(new Date(), LONG_yyyyMMddHHmmssSSS);
	}

	public static Date getCurrDayWithTime(final Date date) {
		// 日期
		Date dinnerDay = null;
		try {
			dinnerDay = DateUtil.parse(DateUtil.format(date, DateUtil.WEB_FORMAT), DateUtil.WEB_FORMAT);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return dinnerDay;
	}

	/**
	 * 获取0:00
	 */
	public static Date getZeroOClock(){
		Calendar yesterday = Calendar.getInstance();
		yesterday.set(Calendar.HOUR_OF_DAY,0);
		yesterday.set(Calendar.MINUTE,0);
		yesterday.set(Calendar.SECOND,0);
		return yesterday.getTime();
	}
}
