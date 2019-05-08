/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public static final String DEFAULT_DAY_PATTERN = "MMMM d, yyyy";
	public static final String DEFAULT_TIME_PATTERN = DEFAULT_DAY_PATTERN + " hh:mm:ss a";
	
	public static final String UI_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	
	public static String format(long millis, String pattern){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(cal.getTime());
	}
	
	public static String getCopyrightYear(){
		return "" + Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static long millis(){
		return millis(Locale.getDefault());
	}
	
	public static long millis(Locale locale){
		Calendar cal = Calendar.getInstance(locale);
		return cal.getTimeInMillis();
	}
	
	public static long millis(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis();
	}

	public static java.sql.Date formatUiDate(String dateStr) {
		return formatUiDate(dateStr, Locale.getDefault());
	}
	
	public static java.sql.Date formatUiDate(String dateStr, Locale locale) {
		long millis = 0;
		try{
			 Date utilDate = ui_date_formatter.parse(dateStr);
			 millis = utilDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(millis == 0){
			millis = millis();
		}
		return new java.sql.Date(millis);
	}

	public static String getUiDate() {
		return getUiDate(millis());
	}
	
	public static boolean isValidUiDate(String dateStr){
		boolean result = false;
		try{
			 ui_date_formatter.parse(dateStr);
			 result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getUiDate(java.sql.Date date) {
		if(date == null){
			return "";
		}
		return getUiDate(date.getTime());
	}
	
	public static String getUiDate(long millis) {
		return ui_date_formatter.format(new Date(millis));
	}
	
	public static String getDateRange(){
		return getDateRange(30);
	}
	
	public static String getDateRange(int daysDifference){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -(daysDifference));	// Last 1 month
		String fromDate = getUiDate(cal.getTimeInMillis());
		String toDate = getUiDate();
		return fromDate + " - " + toDate;
	}

	public static String[] getDateRange(String dateRange)throws Exception{
		String[] arr = dateRange.split("-");
		arr[0] = getDbDate(arr[0], -1);
		arr[1] = getDbDate(arr[1], 1);
		return arr;
	}
	
	static SimpleDateFormat ui_date_formatter = new SimpleDateFormat(UI_DATE_FORMAT);
	static SimpleDateFormat db_date_formatter = new SimpleDateFormat(DB_DATE_FORMAT);
	
	public static String getDbDate(String dateStr){
		return getDbDate(dateStr, 0);
	}
	public static String getDbDate(String dateStr, int dateAddVal){
		java.sql.Date date = formatUiDate(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dateAddVal);
		dateStr = db_date_formatter.format(cal.getTime());
		return dateStr;
	}
}
