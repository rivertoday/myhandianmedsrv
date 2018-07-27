package com.hrsb.cg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期工具类
 * 
 * @author risenb-java001
 * 
 */
public class DateUtil extends DateUtils{
	private static Log log = LogFactory.getLog(DateUtil.class);
	static SimpleDateFormat sdf = new SimpleDateFormat();

	public final static String PATTERN_SIMPLE = "yyyy-MM-dd";
	
	public final static String PATTERN_WHOLE = "yyyy-MM-dd HH:mm:ss";
	
	public final static String PATTERN_COMPACT = "yyyyMMddHHmmss";

	public static Integer getYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR);
	}

	public static Integer getMonth() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int dom = cal.get(Calendar.DAY_OF_MONTH);
		int doy = cal.get(Calendar.DAY_OF_YEAR);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static String formatDate(Date date, String pattern) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String createTradeNo() {
		String time = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = time.substring(8, time.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		return strTime + strRandom;
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 5天以后的日期
	 * 
	 * @param n
	 * @param starttime
	 * @return
	 */
	public static Date afterDay(int n, Date starttime) {
		Calendar c = Calendar.getInstance();
		c.setTime(starttime);
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		return d2;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		long hourdif = (time2 - time1) % (1000 * 3600 * 24) / 1000 * 60 * 60;

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数小时数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static String daysAndHorusBetween(Date smdate, Date bdate)
			throws ParseException {
		if (smdate.after(bdate)) {
			return "-1";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600);// 相差总共的小时数
		long hourdif1 = between_days / 24;// 得到的天数
		long hourdif2 = between_days % 24;

		if (between_days < 1) {
			return "-2";
		}
		
		if (hourdif1 == 0) {
			return hourdif2 + "小时";
		}

		return hourdif1 + "天" + hourdif2 + "小时";
	}
	
	/**
	 * 计算时间差 
	 * 结果为：xx 天 xx 小时 xx 分 xx 秒
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static String daysAndHorusAndSecondBetween(Date smdate, Date bdate)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		
		long l = smdate.getTime() - bdate.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		
		String dateResultString = day + "天" + hour + "小时" + min + "分" + s + "秒";
		return dateResultString;
	}

	/**
	 * 计算两个日期之间相差的秒数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int secondBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 1000;
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date getValidtime(Date date, int valid) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.parse(sdf.format(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long time1 = cal.getTimeInMillis();
		long time2 = time1 + valid * 60 * 1000;
		return new Date(time2);
	}

	public static void main(String[] args) throws Exception {
		System.out
				.println(secondBetween(new Date(), afterDay(1, new Date())) / 3600);
	}

	/**
	 * 获取标准的当前日期，返回值为yyyy-MM-dd格式
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getStandardDate(new Date());
	}

	/**
	 * 返回标准的yyyy-MM-dd格式的当前日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getStandardDate(Date date) {
		return TenpayUtil.date2String(date, PATTERN_SIMPLE);
	}
	
	/**
	 * 获取标准的当前日期，返回值为yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return getStandardDateTime(new Date());
	}
	
	/**
	 * 返回标准的yyyy-MM-dd HH:mm:ss格式的当前时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getStandardDateTime(Date date) {
		return dateTostr(date, PATTERN_WHOLE);
	}
	
	/**
	 * 将日期格式化成字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateTostr(Date date, String pattern) {
		log.debug("pattern:" + pattern);
		return DateFormatUtils.format(date, pattern);
	}
	/**
	 * 获取当前日期时间的紧凑格式 如：20130923110202
	 * @return
	 */
	public static String getCompactCurrentDateTime() {
		return dateTostr(new Date(), PATTERN_COMPACT);
	}
}
