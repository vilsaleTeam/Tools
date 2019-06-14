package et.common.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *  时间查询工具类
 * @author robin
 */
public class DateUtilsCommon_dailyreport implements Serializable {

	/**
	 * 时间类型
	 * @author 李雅翔
	 * @date 2018年4月2日
	 */
	 public enum TimeType {
	    DAY, HOUR, MINUTE, WEEK, MONTH, YEAR
	 }
	 
	private static final long serialVersionUID = 2088912783406807079L;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/* 今天的日期 */
	public static String getTodayFormat() {
		return dateFormat.format(new Date());
	}

	/**
	 * 获取当天的日期，转化为指定的格式
	 * @param formatDate 输出的日期格式
	 * @return 
	 * @author lyx
	 * @date 2018-04-02
	 */
	public static String getTodayFormat(String formatDate) {
		SimpleDateFormat sf = new SimpleDateFormat(formatDate);
		return sf.format(new Date());
	}
	
	/** 获得当前日期与本周一相差的天数 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		// System.out.println("***old一周的第几天:"+dayOfWeek);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/** 获得 该日期 与 周一相差的天数 */
	public static int getNewMondayPlus(String date) {
		// int year =
		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		// System.out.println("***new一周的第几天:"+dayOfWeek);
		// System.out.println(dayOfWeek);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/** 获得当前周- 周一的日期 */
	@SuppressWarnings("unused")
	public static String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return preMonday;
	}

	/** 获得某一天所在周- 周一的日期 */
	public static String getDayOfWeeksMonday(String date) {

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:"+dateFormat.format(cal.getTime()));
		// //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = dateFormat.format(cal.getTime());
		// System.out.println("所在周星期一的日期："+imptimeBegin);
		return imptimeBegin;
	}

	/** 获得该日期所在周- 周日 的日期 */
	@SuppressWarnings("unused")
	public static String getDayOfWeeksSunday(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:"+dateFormat.format(cal.getTime()));
		// //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		//System.out.println(cal.getFirstDayOfWeek() + "=" + day);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = dateFormat.format(cal.getTime());
		// System.out.println("所在周星期一的日期："+imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = dateFormat.format(cal.getTime());
		// System.out.println("所在周星期日的日期："+imptimeEnd);
		return imptimeEnd;
	}

	/** 获得当前周- 周日 的日期 */
	@SuppressWarnings("unused")
	public static String getPreviousSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return preMonday;
	}

	/** 获得当前月--开始日期 */
	public static String getMinMonthDate(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 获得当前月--结束日期 */
	public static String getMaxMonthDate(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 获得几天前的一天 */
	@SuppressWarnings("unused")
	public static String getPreviousDate(int num) {
		// int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return preMonday;
		// return null;
	}

	/** 获得几月前的那个月的当前日 */
	@SuppressWarnings("unused")
	public static String getPreciousMonthDay(int num) {

		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return preMonday;

	}

	
	/**
	 * 获得几月前的那个月的指定日的日期 
	 * @param strDate 传入日期
	 * @param monthNum	相差月份
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static String getPreciousMonthDay(String strDate,int monthNum) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(sdf.parse(strDate));//把指定时间赋给日历
		calendar.add(calendar.MONTH, monthNum);  //设置为前3月
		dBefore = calendar.getTime();   //得到前3月的时间
		String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
		return defaultStartDate;
	}
	
	/** 获得几月前的一月的第一天 */
	@SuppressWarnings("unused")
	public static String getPreciousMonth(int num) {

		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return getMinMonthDate(preMonday);

	}
	
	/** 获得传入日期 , 几月前的一月的第一天 
	 * @throws ParseException */
	@SuppressWarnings({ "unused"})
	public static String getPreciousMonthByDay(int num,String date) throws Exception {
		GregorianCalendar currentDate = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			currentDate.setTime(sdf.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return getMinMonthDate(preMonday);

	}

	/**
	 * 获得传入日期 , 几月前的一月的最后一天
	 * @param num 正整数，往前推的月数
	 * @param date 日期 yyyy-MM-dd 样式
	 * @return
	 * @throws Exception
	 * ct 2018-06-14
	 */
	@SuppressWarnings({ "unused"})
	public static String getMaxDayByDay(int num,String date) throws Exception {
		GregorianCalendar currentDate = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			currentDate.setTime(sdf.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return getMaxMonthDate(preMonday);

	}
	
	
	/** 获得传入月份 , 几月前的月份
	 * @throws ParseException */
	@SuppressWarnings({ "unused"})
	public static String getPreciousMonthByMonth(int num,String month) throws Exception {
		GregorianCalendar currentDate = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		currentDate.setTime(sdf.parse(month));
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonth = sdf.format(monday);
		return preMonth;

	}
	/** 获得几月前的一月的最后一天 */
	@SuppressWarnings("unused")
	public static String getPreciousMonthMaxDay(int num) {

		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, -num);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = dateFormat.format(monday);
		return getMaxMonthDate(preMonday);

	}

	/**
	 * 获取当前日期之后的某天日期
	 * 
	 * @author AMS-ZXH
	 * @param num
	 * @return
	 */
	public static Date getAfterDate(int afterDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, afterDay);
		return c.getTime();
	}
	
	
	/**************************************************************/
	/**                李雅翔  2017-08-03新增                                                              */
	/**************************************************************/
	/**
	 * 获取输入日期的前 几 天或后 几 天 的日期
	 * @param strDate 输入日期字符串
	 * @param day 天数 正数为后几天,负数为前几天
	 * @return Stirng
	 */
	public static String getOtherDay(String strDate,int day){
		String string = "";
		try {
			string =  getOtherDay(strDate, TimeType.DAY, day, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 *  获取输入时间的前 几 （天\小时\分钟\周\月\年） 或后 几 （天\小时\分钟\周\月\年） 的时间
	 * @param strDate		输入日期
	 * @param timeType		时间类型 day, hours, minute,week
	 * @param times			天数 正数为后几天,负数为前几天/小时数，正数为后几小时,负数为前几小时/...
	 * @param formatDate	输入输出的日期格式
	 * @return
	 * @throws Exception
	 * @author 李雅翔
	 * @date 2018-04-02
	 */
	public static String getOtherDay(String strDate, TimeType timeType, int times, String formatDate) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat(formatDate);
		Date date = null;
		try {
			date = sf.parse(strDate);
		} catch (ParseException e) {
			System.out.println("输入的日期错误");
			throw e;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (timeType) {
		
		case MINUTE:
			c.add(Calendar.MINUTE, times);
			break;
		case HOUR:
			c.add(Calendar.HOUR, times);
			break;
		case DAY:
			c.add(Calendar.DATE, times);
			break;
		case MONTH:
			c.add(Calendar.MONTH, times);
			break;
		case WEEK:
			c.add(Calendar.WEDNESDAY, times);
			break;
		case YEAR:
			c.add(Calendar.YEAR, times);
			break;
		default:
			throw new Exception("暂不支持其他类型，要支持自行添加。输入日期类型为: "+timeType);

		}
		return sf.format(c.getTime());
	}
//	public static void main(String[] args) throws ParseException {
//		System.out.println(differentDays("2017-06-25", "2018-05-25"));
//	}
	/**
	 * 两个日期相差天数
	 * 支持跨年
	 * @param start_date 开始日期	2017-06-25
	 * @param end_date	结束日期	2018-05-25
	 * @return				    334
	 * @throws ParseException
	 * 2017-12-05
	 * @author 李雅翔
	 */
	public static int differentDays(String start_date,String end_date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(start_date);
		Date date2 = sdf.parse(end_date);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);//一年中的第几天
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);//一年中的第几天
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2) {
			//不同年
			if (year1 < year2) {
				int timeDistance = 0 ;
				for(int i = year1 ; i < year2 ; i ++){
					if (i%4==0 && i%100!=0 || i%400==0) {
						//闰年  
						timeDistance += 366;
					} else {
						//不是闰年
						timeDistance += 365;
					}
				}
				return timeDistance + (day2-day1) ;
			}else {
				int timeDistance = 0 ;
				for(int i = year2 ; i < year1 ; i ++){
					if (i%4==0 && i%100!=0 || i%400==0) {
						//闰年  
						timeDistance -= 366;
					} else {
						//不是闰年
						timeDistance -= 365;
					}
				}
				return timeDistance + (day2-day1) ;
			}
			
			
		}
		else {
			//同一年
			return day2-day1;
		}
	}
	
	 /**
	  * 两个日期相差月份
	  * @param befor_date  前面日期
	  * @param after_date	后面日期
	  * @return	befor_date<after_date 返回正数
	  * @throws ParseException
	  * 
	  * @author 李雅翔
	  */
	public static int differentMonths(String befor_date, String after_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar befor = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		befor.setTime(sdf.parse(befor_date));
		after.setTime(sdf.parse(after_date));
		int result = after.get(Calendar.MONTH) - befor.get(Calendar.MONTH);
		int month = (after.get(Calendar.YEAR) - befor.get(Calendar.YEAR)) * 12;
		return month + result;
	}
	
	 /**
	  * 两个时间相差分钟数，同一天比较
	  * @param befor_date  前面日期		16:35:20
	  * @param after_date	后面日期		18:27:11
	  * @return	befor_date<after_date 返回正数   2*60+(-8) = 112
	  * @throws ParseException
	  * 2018-04-28
	  * @author 李雅翔
	  */
	public static int differentMinute(String befor_date, String after_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar befor = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		befor.setTime(sdf.parse(befor_date));
		after.setTime(sdf.parse(after_date));
		int hour = after.get(Calendar.HOUR_OF_DAY) - befor.get(Calendar.HOUR_OF_DAY);
		int minute = (after.get(Calendar.MINUTE) - befor.get(Calendar.MINUTE));
		return hour*60+minute;
	}
	
//	public static void main(String[] args) throws ParseException {
//		System.out.println(differentWeek( "2018-01-01","2018-01-015"));
//	}
	/**
	 * 两个时间相差星期数
	 * @param befor_date  前面日期		16:35:20
	 * @param after_date	后面日期		18:27:11
	 * @return	befor_date<after_date 返回正数   2*60+(-8) = 112
	 * @throws ParseException
	 * 2018-04-28
	 * @author 李雅翔
	 */
	public static int differentWeek(String befor_date, String after_date) throws ParseException {
		befor_date = getDayOfWeeksMonday(befor_date);
		after_date = getDayOfWeeksMonday(after_date);
		int day = differentDays(befor_date, after_date);
		return day/7;
	}
	
	/**
	 * 获取输入日期是星期几，服务器使用 日期格式化会出问题
	 * @param date	2018-04-03
	 * @return		星期二
	 * @throws ParseException
	 * 
	 * @author 李雅翔
	 */
	public static String getWeekOfDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        int num = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (num < 0)
        	num = 0;
        return weekDays[num];
    }
	
	/**
	 * 获取输入日期是星期几 显示为周几，服务器使用 日期格式化会出问题
	 * @param date	2018-04-03
	 * @return		周二
	 * @throws ParseException
	 * 
	 * @author 李雅翔
	 */
	public static String getWeekOfDateZhou(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        int num = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (num < 0)
        	num = 0;
        return weekDays[num];
    }
	
	/**
	 * 将秒数转换为日时分秒，如：2天3小时23分32秒
	 * @param second 秒
	 * @return
	 * 
	 * @author 李雅翔
	 */
    public static String secondToTime(long second){
		long days = second / 86400;            //转换天数
		second = second % 86400;            //剩余秒数
		long hours = second / 3600;            //转换小时
		second = second % 3600;                //剩余秒数
		long minutes = second /60;            //转换分钟
		second = second % 60;                //剩余秒数
		if(days>0){
			return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
		}else if (hours>0) {
			return hours + "小时" + minutes + "分" + second + "秒";
		}else if (minutes>0) {
			return  minutes + "分" + second + "秒";
		}else {
			return  second + "秒";
		}
    }
    /**
     * 对输入的日期进行格式化
     * @param date				需要格式化的日期
     * @param outputFormatter	输出日期格式
     * @return
     * 2018-05-10
     * @author 李雅翔
     * @throws ParseException 
     */
    public static String getDateFormatter(String date, String outputFormatter) throws ParseException {
    	SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormatter);
		return outputSdf.format(dateFormat.parse(date));
    }
    
    /**
     * 对输入的日期进行格式化
     * @param date				需要格式化的日期
     * @param inputFormatter	输入日期格式
     * @param outputFormatter	输出日期格式
     * @return
     * 2018-05-10
     * @author 李雅翔
     * @throws ParseException 
     */
    public static String getDateFormatter(String date, String inputFormatter, String outputFormatter) throws ParseException {
    	SimpleDateFormat inputSdf = new SimpleDateFormat(inputFormatter);
    	SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormatter);
		return outputSdf.format(inputSdf.parse(date));
    }
    
    /** 
      * 时间戳转换成日期格式字符串 
      * @param seconds 精确到毫秒的字符串 
      * @return 
     */  
    public static String timeStampToDate(long seconds, String formatDate) {  
    	SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }
    
	/**
	 * 日期列表填充
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @param subDay	步进天数
	 * @return	指定时间段所有日期
	 * 2019-05-08
	 * 李雅翔
	 * @throws Exception
	 */
	public static List<String> getDayList(String startDate, String endDate,int subDay) throws Exception{
		List<String> list = new ArrayList<>();
		//校验
		if (DateUtilsCommon.differentDays(startDate, endDate) <= 0) {
			throw new Exception("获取日期列表，开始日期必须早于结束日期,开始日期为:"+startDate+",结束日期为："+endDate);
		}
		//填充
		String date = startDate;
		while (DateUtilsCommon.differentDays(date, endDate) > 0) {
			list.add(date);
			date = DateUtilsCommon.getOtherDay(date, subDay);
		}
		return list;
	}
	
	/**
	 * 月份列表填充
	 * @param startMonth	开始日期  2019-01
	 * @param endMonth	结束日期 2019-12
	 * @return	指定时间段所有日期 [2019-01, 2019-02, 2019-03, 2019-04, 2019-05, 2019-06, 2019-07, 2019-08, 2019-09, 2019-10, 2019-11]
	 * @throws Exception
	 * 2019-05-08
	 * 李雅翔
	 */
	public static List<String> getMonthList(String startMonth, String endMonth) throws Exception{
		List<String> list = new ArrayList<>();
		//校验
		if (DateUtilsCommon_dailyreport.differentMonths(startMonth, endMonth) <= 0) {
			throw new Exception("获取日期列表，开始日期必须早于结束日期,开始日期为:"+startMonth+",结束日期为："+endMonth);
		}
		//填充
		String date = startMonth;
		while (DateUtilsCommon_dailyreport.differentMonths(date, endMonth)>0) {
			list.add(date);
			date = DateUtilsCommon_dailyreport.getOtherDay(date, TimeType.MONTH, 1, "yyyy-MM");
		}
		return list;
	}
    
}
