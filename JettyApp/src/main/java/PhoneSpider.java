
import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileFilter;
import java.util.List;
import java.util.ArrayList;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler; // 部署运行Web APP的方式1;

public class PhoneSpider extends AbstractHandler
{
  private List<String> m_listPhone = new ArrayList<String>();
  private List<Vender> m_listVender = new ArrayList<Vender>();
  private List<Status> m_listStatus = new ArrayList<Status>();
  
  public PhoneSpider()
  {
    
  }
  
  public void init()
  {
    this.m_listPhone = new ArrayList<String>();
    this.m_listVender = new ArrayList<Vender>();
    this.m_listStatus = new ArrayList<Status>();
    
    //初始化Vender信息;
    Vender vender = null;
    vender = new Vender("链家", "链家校验手机号码是否已注册的API地址");
    this.m_listVender.add(vender);
    
    vender = new Vender("58同城", "58同城校验手机号码是否已注册的API地址");
    this.m_listVender.add(vender);
  }
  
  //返回从当前文件中读到的手机号码数量;
  public int getPhoneList(List<String> phoneList, String filePath)
  {
    int count = 0;
    try
    {
      String strLine = null;
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      while((strLine = br.readLine()) != null)
      {
        //System.out.println(strLine);
        phoneList.add(strLine.trim());
        count++;
      }
      br.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.out.println(">>>>> " + e.getLocalizedMessage());
    }
    return count;
  }
  
  //返回文件数量;
  public int getAllPhoneList(List<String> phoneList, String dirPath)
  {
    int countFiles = 0, countLines = 0;
    
    File dir = new File(dirPath);
    File[] fileList = dir.listFiles();
    for(File fs: fileList)
    {
      if(fs.isDirectory()) //不处理目录;
      {
        continue;
      }
      
      String filePath = fs.getAbsolutePath();
      if(filePath.endsWith(".txt") == false) //只读取txt文件;
      {
        continue;
      }
      
      countLines = this.getPhoneList(this.m_listPhone, filePath);
      System.out.println(">>> " + filePath + " --> " + countLines);
      countFiles++;
    }
    return countFiles;
  }
  
  public boolean checkPhone(String uri, String phone)
  {
    boolean result = false;
    try
    {
      String strURL = uri + phone;
      URL url = new URL(strURL);
      URLConnection urlc = url.openConnection();
      //System.out.println("编码: " + urlc.getContentType());

      HttpURLConnection httpConn = (HttpURLConnection)urlc;
      
      int responseCode = httpConn.getResponseCode();
      //System.out.println("应答: " + responseCode + "--" + httpConn.getResponseMessage());
      if(responseCode == 200) //HTTP OK
      {
        InputStream in = urlc.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder sbr = new StringBuilder();
        String str = null;
        while((str = br.readLine()) != null)
        {
          sbr.append(str);
          //System.out.println(str);
        }
        br.close();
        isr.close();
        in.close();
        
        String responseMessage = sbr.toString();
        if(responseMessage.length() == 0)
        {
          result = false;
        }
        else
        {
          result = true;
        }
        System.out.println(responseMessage);
      }
      else  //HTTP ERROR
      {
        result = false;
      }
    }
    catch(Exception e)
    {
      result = false;
      e.printStackTrace();
    }
    return result;
  }
  
  //获取手机号码列表:
  public void onGetPhoneList(PrintWriter out)
  {
    this.m_listPhone.clear();
    
    int count = this.getAllPhoneList(this.m_listPhone, "E:/");
    out.println("<H3>共有读取 " + count + " 个文件, 有 "+ this.m_listPhone.size() +" 个手机号码</H3>");
    StringBuilder sbr = new StringBuilder();
    for(String phone: this.m_listPhone)
    {
      sbr.append(phone+ "<BR>");
    }
    out.println("<H4>" + sbr.toString() + "</H4>");
  }
  
  //校验手机号是否已经在房产网上注册过
  public void onCheck(PrintWriter out)
  {
    this.m_listStatus.clear();
    
    //遍历手机号码列表:
    for(String phone: this.m_listPhone)
    {
      Status status = new Status(phone);
      
      //遍历房产商列表:
      for(Vender vender: this.m_listVender)
      {
        boolean result = true;//this.checkPhone(vender.getAddr(), phone);
        if(result == true) //该手机号在当前房产网上注册过;
        {
          List<Vender> venders = status.getVenders();
          venders.add(vender);
        }//endif
      }//end-for
      
      //把该手机号码的校验结果添加到结果列表中;
      this.m_listStatus.add(status);
    }//end-for
    
    //输出校验结果:
    for(Status status: this.m_listStatus)
    {
      StringBuilder sbr = new StringBuilder(status.getPhone());
      sbr.append(": [ ");
      List<Vender> venders = status.getVenders();
      int num = venders.size(); 
      for(int i = 0; i < num; i++)
      {
        sbr.append(venders.get(i).getName());
        if(i < (num - 1))
        {
          sbr.append(", ");
        }
      }
      sbr.append(" ]");
      out.print(sbr.toString() + "<BR>");
    }
  }
  
  /**
   * target: 就是URL中去掉主机地址和参数之后剩余的部分;
   *         例如:"http://localhost:54321/api/v1/get_user_info?name=zhangsan"中的"/api/v1/get_user_info";
   *         其中,target指的就是"/api/v1/get_user_info";
   */
  @Override
  public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    //把请求中的数据编码格式设置为UTF-8;
    request.setCharacterEncoding("UTF-8");
    
    //把应答中的数据编码格式设置为UTF-8;
    response.setContentType("text/html; charset=UTF-8");
    
    //HTTP应答码设为OK-200;
    response.setStatus(HttpServletResponse.SC_OK);
    
    //???
    baseRequest.setHandled(true);
    
    //从应答对象中获取输出流对象;
    PrintWriter out = response.getWriter();
    
    //通过target参数解析功能码:也可以通过HttpServletRequest的getPathInfo()或getRequestURI()函数得到请求路径信息,然后再解析出功能码;
    String[] pathList = target.split("/");
    if(pathList.length <= 1)
    {
      out.println("<H3>路径格式错误!!!</H3>");
      return;
    }
    
    //取URI中最后一个串作为功能码使用:
    String funcCode = pathList[pathList.length - 1];
    System.out.println("功能码 = " + funcCode);
    
    //依据功能码来实现对应功能:
    if(funcCode.equalsIgnoreCase("do"))
    {
      //获取请求参数:opcode-当前功能中的某一种操作码;
      String opCode = request.getParameter("op");
      if(opCode == null)
      {
        out.println("<H3>请求格式错误!!!<BR>正确格式为:<BR>http://地址:端口/功能码?op=操作码<BR>目前,功能码只有do</H3>");
        return;
      }
      
      if(opCode.equalsIgnoreCase("load")) //加载手机号码文件中的号码列表;
      {
        this.onGetPhoneList(out);
      }
      else if(opCode.equalsIgnoreCase("check")) //去校验手机号码列表中的号码是否已经在房产网上注册过;
      {
        //Add check code here
        this.onCheck(out);
      }
      else
      {
        out.println("<H3 style='color:red'>功能" + funcCode + " 的 " + opCode + " 操作未实现!!!</H3>");
      }
    }
    else if(funcCode.equalsIgnoreCase("load")) //加载手机号码文件中的号码列表;
    {
      this.onGetPhoneList(out);
    }
    else if(funcCode.equalsIgnoreCase("check")) //去校验手机号码列表中的号码是否已经在房产网上注册过;
    {
      this.onCheck(out);
    }
    else
    {
      out.println("<H3 style='color:red'>功能 " + funcCode + " 未实现!!!</H3>");
    }
    
    //输出之后就关闭输出流对象;
    out.flush();
    out.close();
  }
  
  public static void main(String[] args) throws Exception
  {
    Server service = new Server(9000);

    // 设置在JVM退出时关闭Jetty的钩子;
    service.setStopAtShutdown(true);

    //初始化请求处理器;
    PhoneSpider phoneSpider = new PhoneSpider();
    phoneSpider.init();
    
    //设置请求处理器;
    service.setHandler(phoneSpider);

    // 启动服务;
    service.start();
    System.out.println("服务已启动");

    // 让服务等待;
    service.join();
  }
}

class Vender
{
  private String name;
  private String addr;
  
  public Vender()
  {
    this.name = null;
    this.addr = null;
  }
  
  public Vender(String name, String addr)
  {
    this.name = name;
    this.addr = addr;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setAddr(String addr)
  {
    this.addr = addr;
  }
  
  public String getAddr()
  {
    return this.addr;
  }
};

class Status1
{
  private String phone;
  private Vender vender;
  private int    status;  //0-未注册; 1-已注册;
  
  public Status1()
  {
    this.phone = null;
    this.vender = null;
    this.status = 0;
  }
  
  public Status1(String phone, Vender vender, int status)
  {
    this.phone = phone;
    this.vender = vender;
    this.status = status;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setVender(Vender vender)
  {
    this.vender = vender;
  }
  
  public Vender getVender()
  {
    return this.vender;
  }
  
  public void setStatus(int status)
  {
    this.status = status;
  }
  
  public int getStatus()
  {
    return this.status;
  }
};

class Status
{
  private String phone;
  private List<Vender> venders;
  
  public Status()
  {
    this.phone = null;
    this.venders = new ArrayList<Vender>();
  }
  
  public Status(String phone)
  {
    this.phone = phone;
    this.venders = new ArrayList<Vender>();
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setVenders(List<Vender> venders)
  {
    this.venders = venders;
  }
  
  public List<Vender> getVenders()
  {
    return this.venders;
  }
};
