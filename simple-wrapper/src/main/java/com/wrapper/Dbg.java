package com.wrapper;

public final class Dbg
{
  /**
   * 返回"File:Class:Method:Line"格式的代码行信息
   * @return {@code String} 代码行的丰富信息
   * */
  public static String extract()
  {
    StackTraceElement ste = (new Throwable()).getStackTrace()[1]; //0-表示当前行; 1-表示函数extract()被调用的行;
    String strLineInfo = String.format("%s:%s:%s():%d", ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber());
    return strLineInfo;
  }
  
  /**
   * 返回"File:Line"格式的代码行信息
   * @return {@code String} 代码行的简单信息
   * */
  public static String simple()
  {
    StackTraceElement ste = (new Throwable()).getStackTrace()[1]; //0-表示当前行; 1-表示函数simple()被调用的行;
    String strLineInfo = String.format("%s:%d", ste.getFileName(), ste.getLineNumber());
    return strLineInfo;
  }
  
  /**
   * 返回"File:Method:Line"格式的代码行信息
   * @return {@code String} 代码行的丰富信息
   * */
  public static String line()
  {
    StackTraceElement ste = (new Throwable()).getStackTrace()[1]; //0-表示当前行; 1-表示函数line()被调用的行;
    String strLineInfo = String.format("%s:%s():%d", ste.getFileName(),ste.getMethodName(), ste.getLineNumber());
    return strLineInfo;
  }
}
