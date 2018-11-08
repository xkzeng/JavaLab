package com.wrapper;

import java.text.SimpleDateFormat;

public final class Print
{ 
  /** 默认输出级别 */
  private static final String DEFAULT_PRINT_LEVEL = "3";
  private static final String PRINT_LEVEL_PROP_NAME = "print.level";
  
  /** 使用String.format()格式化时间戳时用的时间格式字符串 */
  //private static String DATE_TIME_FORMAT = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS";
  private static final String DATE_TIME_FORMAT = "%1$tF %1$tT";
  
  /** 使用SimpleDateFormat格式化时间戳时用的时间格式字符串 */
  //private static final String DATE_TIME_FORMAT_SDF = "yyyy-MM-dd HH:mm:ss.SSS"; //精确到毫秒,便于观察程序运行时间和效率;
  private static final String DATE_TIME_FORMAT_SDF = "yyyy-MM-dd HH:mm:ss";
  
  /** 使用SimpleDateFormat实例格式化时间戳 */
  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_SDF);
  
  /**
   * 输出用于错误的格式化文本信息,可根据属性来决定是否需要输出;
   * @param format 格式化字符串
   * @param args 可变参数列表
   * @return void
   */
	public static void error(String format, Object ... args)
	{
		int printLevel = Integer.parseInt(System.getProperty(PRINT_LEVEL_PROP_NAME, DEFAULT_PRINT_LEVEL));
		if(printLevel >= PrintLevel.ERROR.ordinal())
		{
			String datetime = simpleDateFormat.format(System.currentTimeMillis());
			System.out.printf(datetime + " [ERR] " + format + "\n", args);
		}
	}
  
  /**
   * 输出用于异常的格式化文本信息,可根据属性来决定是否需要输出;
   * @param format 格式化字符串
   * @param args 可变参数列表
   * @return void
   */
	public static void exce(String format, Object ... args)
	{
		int printLevel = Integer.parseInt(System.getProperty(PRINT_LEVEL_PROP_NAME, DEFAULT_PRINT_LEVEL));
		if(printLevel >= PrintLevel.EXCEPTION.ordinal())
		{
			String datetime = simpleDateFormat.format(System.currentTimeMillis());
			System.out.printf(datetime + " [EXC] " + format + "\n", args);
		}
	}
  
  /**
   * 输出用于警告的格式化文本信息,可根据属性来决定是否需要输出;
   * @param format 格式化字符串
   * @param args 可变参数列表
   * @return void
   */
	public static void warn(String format, Object ... args)
	{
		int printLevel = Integer.parseInt(System.getProperty(PRINT_LEVEL_PROP_NAME, DEFAULT_PRINT_LEVEL));
		if(printLevel >= PrintLevel.WARNNING.ordinal())
		{
			String datetime = simpleDateFormat.format(System.currentTimeMillis());
			System.out.printf(datetime + " [WRN] " + format + "\n", args);
		}
	}
  
  /**
   * 输出普通的格式化文本信息
   * @param format 格式化字符串
   * @param args 可变参数列表
   * @return void
   */
	public static void info(String format, Object ... args)
	{
		int printLevel = Integer.parseInt(System.getProperty(PRINT_LEVEL_PROP_NAME, DEFAULT_PRINT_LEVEL));
		if(printLevel >= PrintLevel.INFORMATION.ordinal())
		{
			String datetime = String.format(DATE_TIME_FORMAT, System.currentTimeMillis());
			System.out.printf(datetime + " [INF] " + format + "\n", args);
		}
	}
  
  /**
   * 输出用于调试的格式化文本信息,可根据属性来决定是否需要输出;
   * @param format 格式化字符串
   * @param args 可变参数列表
   * @return void
   */
	public static void debug(String format, Object ... args)
	{
		int printLevel = Integer.parseInt(System.getProperty(PRINT_LEVEL_PROP_NAME, DEFAULT_PRINT_LEVEL));
		if(printLevel >= PrintLevel.DEBUG.ordinal())
		{
			String datetime = String.format(DATE_TIME_FORMAT, System.currentTimeMillis());
			System.out.printf(datetime + " [DBG] " + format + "\n", args);
		}
	}
}
