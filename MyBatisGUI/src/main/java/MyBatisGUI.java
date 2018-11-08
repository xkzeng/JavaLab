import java.util.List;
import java.util.Vector;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Button;
import java.awt.Checkbox;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.TextField;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Reader;
import java.sql.DatabaseMetaData;
import java.awt.FileDialog;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/* MyBatis */
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/* Entities for this application */
import com.learn.mybatis.entity.Book;
import com.learn.mybatis.mapper.IBookMapper;

/* 自定义库 */
import com.wrapper.Print;

import com.message.Message;
import com.message.MsgApp;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

class Parameters
{
	JProgressBar bar;
	JButton      btn;
};

public class MyBatisGUI extends JFrame
{
	private static final int MSG_START_PROGRESSBAR = 0x01;
	private static final int MSG_LOAD_BOOKLIST     = 0x02;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JFrame curFrame = this;
	private Font font;
  //Table Header Title
	private String[] columnNames = new String[] { "编号", "名称", "作者"};
	private DefaultTableModel tableModel = null; //使用JTable的关键点1;
	
	private MsgApp mHandler = new MsgApp()
	{
		/**
		 * Filter Message;<BR>
		 * Message which is filtered will not be handled, that means will not call handleMessage() for this message;<BR>
		 * Maybe subclasses implement this method or not;<BR>
		 * This method is optional;
		 * @param msg Message instance which will be filtered;
		 * @return <i>{@code true}</i> if the message is filtered; <i>{@code false}</i> otherwise;;
		 */
		@Override
		public boolean filterMessage(Message msg)
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		/**
		 * Handle message;<BR>
		 * Subclasses must implement this method to handle messages that received.<BR>
		 * @param msg Instance of Message that will be handled;
		 * @return <i>{@code true}</i> if this message have been handled, <i>{@code false}</i> otherwise;
		 */
		@Override
		public boolean handleMessage(Message msg)
		{
			int what = msg._what;
			switch(what)
			{
				case MSG_START_PROGRESSBAR:
				{
					onProgressBarStart((Parameters)msg._obj);
				}
				break;
				
				case MSG_LOAD_BOOKLIST:
				{
					onLoadBookList(msg);
				}
				break;
				
				default:
				{
					Print.info("MsgApp: unknown message<" + msg._what + ">");
				}
			}
			return true;
		}
		
		/**
		 * Message callback;<BR>
		 * Maybe subclasses implement this method or not.<BR>
		 * This method is optional;
		 * @param msg Instance of Message that have been handled;
		 * @param result <i>{@code true}</i> if this message have been handled successfully, <i>{@code false}</i> otherwise;
		 * @return <i>{@code void}setTitle("\u56FE\u4E66\u7BA1\u7406");
		setFont(new Font("新宋体", Font.PLAIN, 15));
		</i>
		 */
		@Override
		public void messageCallback(Message msg, boolean result)
		{
			int what = msg._what;
			switch(what)
			{
				case MSG_START_PROGRESSBAR:
				{
					Print.info("进度条已经启动<" + result + ">");
				}
				break;
				
				case MSG_LOAD_BOOKLIST:
				{
					Print.info("图书列表已经加载完毕<" + result + ">");
				}
				break;
				
				default:
				{
					Print.info("MsgApp: message " + msg._what + " have been handled:" + result);
				}
			}
		}
	};
	private JButton btnMySQL1;
	private JButton btnMySQL2;
	private JButton btnSQLite;
	private JTable tblBooks;
	private JScrollPane panelBook;
	
	private void onProgressBarStart(Parameters param)
	{
		Print.info("收到启动进度处理的消息");
		MyThread stepper = new MyThread("进度条控制线程");
		stepper.setProgressBar(param.bar);
		stepper.setButton(param.btn);
		stepper.start();
		Print.info("进度处理的启动请求已转交给后台线程");
	}
	
	private void onLoadBookList(Message msg)
	{
		Print.info("收到加载图书列表的通知消息");
		String dbId = (String)msg._obj;
		try
		{
			List<Book> bookList = this.getBookList(dbId);
			this.showBookList(bookList);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public MyBatisGUI()
	{
		initialize();
	}
	private void initialize() {
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				JOptionPane.showMessageDialog(null, "窗口正在关闭...", "Message", JOptionPane.WARNING_MESSAGE);
			}
			
			@Override
			public void windowClosed(WindowEvent e)
			{
				JOptionPane.showMessageDialog(null, "窗口已关闭", "Message", JOptionPane.WARNING_MESSAGE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\soft\\others\\on_off_red.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setFont(new Font("新宋体", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("\u8D26\u53F7:");
		lblUserName.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblUserName.setBounds(14, 13, 44, 18);
		getContentPane().add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtUserName.setBounds(58, 10, 145, 24);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801:");
		lblPassword.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblPassword.setBounds(226, 13, 38, 18);
		getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(267, 10, 145, 24);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(32);
		
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String message = "账号: " + txtUserName.getText() + "\n" + "密码: " + txtPassword.getText();
				JOptionPane.showMessageDialog(null, message, "登录", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLogin.setFont(new Font("新宋体", Font.PLAIN, 15));
		btnLogin.setBounds(14, 58, 71, 27);
		getContentPane().add(btnLogin);
		
		JButton btnRegist = new JButton("\u6CE8\u518C");
		btnRegist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String message = "账号: " + txtUserName.getText() + "\n" + "密码: " + txtPassword.getText();
				JOptionPane.showMessageDialog(null, message, "注册", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnRegist.setFont(new Font("新宋体", Font.PLAIN, 15));
		btnRegist.setBounds(99, 58, 71, 27);
		getContentPane().add(btnRegist);
		
		JButton btnReset = new JButton("\u53D6\u6D88");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(null, "真的要取消吗?", "确认", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION || result == JOptionPane.OK_OPTION)
				{
					mHandler.sendMessage(0);
					txtUserName.setText("");
					txtPassword.setText("");
					dispose();
					return;
				}
				
				if(result == JOptionPane.NO_OPTION)
				{
					JOptionPane.showMessageDialog(null, "哈哈哈,你后悔了！", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(result == JOptionPane.CANCEL_OPTION)
				{
					JOptionPane.showMessageDialog(null, "你取消了操作！你还有其它选择吗！", "提示", JOptionPane.INFORMATION_MESSAGE);
					//dispose(); //OK
					//System.exit(0); //OK
					return;
				}
			}
		});
		btnReset.setFont(new Font("新宋体", Font.PLAIN, 15));
		btnReset.setBounds(341, 58, 71, 27);
		getContentPane().add(btnReset);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("新宋体", Font.PLAIN, 15));
		progressBar.setIndeterminate(true);
		progressBar.setForeground(Color.BLUE);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(400,50));
		progressBar.setBounds(14, 98, 398, 24);
		getContentPane().add(progressBar);
		
		final JButton btnProgressBar = new JButton("\u8FDB\u5EA6\u6761");
		btnProgressBar.setFont(new Font("新宋体", Font.PLAIN, 15));
		btnProgressBar.addActionListener(new ActionListener()
		{
			private void startProgressBarByButtonEvent()
			{
				Print.info("收到进度处理的请求事件");
				MyThread stepper = new MyThread("进度条控制线程");
				stepper.setProgressBar(progressBar);
				stepper.setButton(btnProgressBar);
				stepper.start();
				Print.info("直接在按钮事件里面启动进度处理");
			}
			
			private void startProgressBarByMessage()
			{
				Parameters param = new Parameters();
				param.bar = progressBar;
				param.btn = btnProgressBar;
				mHandler.sendMessage(MSG_START_PROGRESSBAR, param, true);
			}
			
			public void actionPerformed(ActionEvent e)
			{
				//this.startProgressBarByButtonEvent();
				this.startProgressBarByMessage();
			}
		});
		btnProgressBar.setBounds(187, 58, 80, 27);
		getContentPane().add(btnProgressBar);
		
		final JTextArea txtSWINGMessage = new JTextArea();
		txtSWINGMessage.setWrapStyleWord(true);
		txtSWINGMessage.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtSWINGMessage.setBounds(14, 135, 396, 98);
		//getContentPane().add(txtSWINGMessage);
		
		final TextArea txtAWTMessage = new TextArea();
		txtAWTMessage.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtAWTMessage.setBounds(14, 366, 398, 115);
		getContentPane().add(txtAWTMessage);
		
		Label lblFile = new Label("\u9009\u62E9\u6587\u4EF6:");
		lblFile.setFont(new Font("新宋体", Font.PLAIN, 16));
		lblFile.setBounds(10, 252, 71, 25);
		getContentPane().add(lblFile);
		
		final TextField txtFile = new TextField();
		txtFile.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtFile.setBounds(14, 277, 321, 25);
		getContentPane().add(txtFile);
		
		final TextField txtFile2 = new TextField();
		txtFile2.setFont(new Font("新宋体", Font.PLAIN, 15));
		txtFile2.setBounds(14, 318, 321, 25);
		getContentPane().add(txtFile2);
		
		Button btnFile = new Button("\u6587\u4EF6AWT");
		btnFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				FileDialog fileDialog = new FileDialog(curFrame, "打开文件", FileDialog.LOAD);
				fileDialog.setFile("*.txt;*.java");
				//fileDialog.setMultipleMode(true);
				fileDialog.setVisible(true);
				
				String dirName = fileDialog.getDirectory();
				String fileName =  fileDialog.getFile();
				txtSWINGMessage.setText("DIR = " + dirName + ", File = " + fileName);
				txtAWTMessage.setText("DIR = " + dirName + ", File = " + fileName);
				txtFile.setText(dirName + fileName);
				txtFile2.setText(dirName + fileName);
			}
		});
		btnFile.setFont(new Font("新宋体", Font.PLAIN, 15));
		btnFile.setBounds(341, 277, 71, 25);
		getContentPane().add(btnFile);
		
		Button button = new Button("\u6587\u4EF6SWI");
		button.setFont(new Font("新宋体", Font.PLAIN, 15));
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//JFileChooser fileDialog = new JFileChooser();
				//fileDialog.setCurrentDirectory(new File("."));
				JFileChooser fileDialog = new JFileChooser(".", FileSystemView.getFileSystemView());
				fileDialog.setDialogTitle("选择一个文件");
				//fileDialog.setApproveButtonText("确定");
				//fileDialog.setMultiSelectionEnabled(true);
				fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = fileDialog.showOpenDialog(curFrame);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					String filePath = fileDialog.getSelectedFile().getPath();
					txtSWINGMessage.setText(filePath);
					txtAWTMessage.setText(filePath);
					txtFile.setText(filePath);
					txtFile2.setText(filePath);
					return;
				}
				
				if(result == JFileChooser.CANCEL_OPTION)
				{
					txtSWINGMessage.setText("你取消文件选择了");
					txtAWTMessage.setText("你取消文件选择了");
					txtFile.setText("你取消文件选择了");
					txtFile2.setText("你取消文件选择了");
					return;
				}
				
				if(result == JFileChooser.ERROR_OPTION)
				{
					txtSWINGMessage.setText("选择文件出错了");
					txtAWTMessage.setText("选择文件出错了");
					txtFile.setText("选择文件出错了");
					txtFile2.setText("选择文件出错了");
					return;
				}
			}
		});
		button.setBounds(341, 318, 71, 25);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 135, 396, 98);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtSWINGMessage);
		{
			this.btnMySQL1 = new JButton("MySQL1");
			this.btnMySQL1.addActionListener(new BtnMySQL1_ActionListener());
			this.btnMySQL1.setFont(new Font("新宋体", Font.PLAIN, 15));
			this.btnMySQL1.setBounds(426, 9, 113, 27);
			getContentPane().add(this.btnMySQL1);
		}
		{
			this.btnMySQL2 = new JButton("MySQL2");
			this.btnMySQL2.addActionListener(new BtnMySQL2_ActionListener());
			this.btnMySQL2.setFont(new Font("新宋体", Font.PLAIN, 15));
			this.btnMySQL2.setBounds(572, 9, 113, 27);
			getContentPane().add(this.btnMySQL2);
		}
		{
			this.btnSQLite = new JButton("SQLite");
			this.btnSQLite.addActionListener(new BtnSQLite_ActionListener());
			this.btnSQLite.setFont(new Font("新宋体", Font.PLAIN, 15));
			this.btnSQLite.setBounds(716, 9, 113, 27);
			getContentPane().add(this.btnSQLite);
		}
		
		this.tableModel = new DefaultTableModel(); //使用JTable的关键点2;
		{
			this.tblBooks = new JTable();
			this.tblBooks.setBackground(SystemColor.control);
			this.tblBooks.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
			this.tblBooks.setToolTipText("\u56FE\u4E66\u8868\u683C");
			this.tblBooks.setFont(new Font("新宋体", Font.PLAIN, 15));
			this.tblBooks.setBounds(426, 58, 403, 423);
			this.tblBooks.setRowHeight(24);
			this.tblBooks.setRowMargin(5);
			this.tblBooks.setModel(this.tableModel); //使用JTable的关键点3;
			//getContentPane().add(this.tblBooks);
		}
		{
			this.panelBook = new JScrollPane();
			this.panelBook.setBounds(426, 58, 403, 423);
			this.panelBook.setViewportView(this.tblBooks);  //使用JTable的关键点4; 方式1 OK
			//this.panelBook.getViewport().add(this.tblBooks);//使用JTable的关键点4; 方式2 OK
			getContentPane().add(this.panelBook);
		}
	}
	
	public void setUiFont(Font font)
	{
		FontUIResource fontResource = new FontUIResource(font);
		for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource)
			{
				UIManager.put(key, fontResource);
			}
		}
	}
	
	public void init(String title)
	{
		this.setTitle("我的应用程序");
		//this.setLocationRelativeTo(null);
		//this.setLocationByPlatform(true);
		this.setLocation(600, 300);
		this.setSize(850, 530);
		
	  //窗口自适应大小:
		//this.pack();
		//窗口大小不可改变;
		this.setResizable(false);
		
		this.font = new Font("新宋体", Font.PLAIN, 16);
		//this.setUiFont(font);
		UIManager.put("OptionPane.messageFont", font);
		UIManager.put("OptionPane.buttonFont", font);
		
		//启用消息机制:
		//this.mHandler.runForEver();
		
		//显示窗口:
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyBatisGUI app = new MyBatisGUI();
		app.init("我的应用程序");
	}
	
	private SqlSession getSqlSession(String configFilePath, String environment, Properties props)
	{
		SqlSession sqlSession = null;
		
		try
		{
			//加载MyBatis的全局配置信息:
			Reader mybatisConfiguration = Resources.getResourceAsReader(configFilePath);
			
			//实例化SqlSessionFactoryBuilder;
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = builder.build(mybatisConfiguration, environment, props);
			
			//打开数据库链接:
			sqlSession =  sqlSessionFactory.openSession();
		}
		catch(Exception e)
		{
			sqlSession = null;
			Print.exce("打开数据库异常:%s", e.getMessage());
		}
		finally
		{}
		return sqlSession;
	}
	
	private List<Book> getBookList(String dbId) throws Exception
	{
		String environment = dbId;
		String propsFile = "properties/" + environment + ".properties";
		Properties props = Resources.getResourceAsProperties(propsFile);
		Print.info("Props file: %s", propsFile);
		
		//从MyBatis的全局配置文件中读取数据库链接信息并打开数据库链接:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml", environment, props);
		if(session == null)
		{
			String title = "错误信息";
			String content = "不能打开数据库链接:" + environment;
			JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		DatabaseMetaData dmd = session.getConnection().getMetaData();
		String dbProductName = dmd.getDatabaseProductName();
		String dbDriverName = dmd.getDriverName() + " " + dmd.getDriverVersion();
		String dbName = session.getConnection().getSchema();
		String dbURL = dmd.getURL();
		String dbUserName = dmd.getUserName();
		String message = "数据库产品名称:" + dbProductName + "\n";
		message += "数据库驱动名称:" + dbDriverName + "\n";
		message += "数据库名称:" + dbName + "\n";
		message += "数据库连接字符串:" + dbURL + "\n";
		message += "数据库账号:" + dbUserName;
		JOptionPane.showMessageDialog(null, message, "信息", JOptionPane.INFORMATION_MESSAGE);
		
	  //选择映射器:获取映射器接口对象实例;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//调用接口映射器实例的方法,访问数据库:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//关闭数据库链接;
		session.close();
		
		int number_of_books = bookList.size();
		if(number_of_books <= 0)
		{
			Print.warn("没有查询到图书,你还没有添加任何图书信息!");
			String title = "提示信息";
			String content = "你还没有添加任何图书信息!";
			JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//打印图书列表:
		Print.info("共有 %d 图书", number_of_books);
		for(Book book: bookList)
		{
			Print.info("图书编号 = %d, 图书名称 = %s, 图书作者 = %s", book.getBookId(), book.getBookName(), book.getAuthor());
		}
		return bookList;
	}
	
	private void showBookList(List<Book> bookList)
	{
		int cols = this.columnNames.length;
    //rows
		int number_of_rows = bookList.size();
		Object[][] rows = new Object[number_of_rows][cols];
		
		int row = 0;
		int col = 0;
		
		for(Book book: bookList)
		{
			col = 0;
			rows[row][col++] = book.getBookId();
			rows[row][col++] = book.getBookName();
			rows[row][col++] = book.getAuthor();
			row++;
		}
		
		this.tableModel.setDataVector(rows, this.columnNames); //使用JTable的关键点5;
	}
	
	private class BtnMySQL1_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mHandler.sendMessage(MSG_LOAD_BOOKLIST, "db0", true);
//			try
//			{
//				List<Book> bookList = getBookList("db0");
//				showBookList(bookList);
//			}
//			catch(Exception e)
//			{
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			}
		}
	}
	private class BtnMySQL2_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mHandler.sendMessage(MSG_LOAD_BOOKLIST, "db1", true);
//			try
//			{
//				List<Book> bookList = getBookList("db1");
//				showBookList(bookList);
//			}
//			catch(Exception e)
//			{
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			}
		}
	}
	private class BtnSQLite_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mHandler.sendMessage(MSG_LOAD_BOOKLIST, "db3", true);
//			try
//			{
//				List<Book> bookList = getBookList("db3");
//				showBookList(bookList);
//			}
//			catch(Exception e)
//			{
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			}
		}
	}
}

class MyThread extends Thread
{
	private int counter = 0;
	JProgressBar progressBar;
	JButton button;
	
	public void setProgressBar(JProgressBar progressBar)
	{
		this.progressBar = progressBar;
	}
	
	public void setButton(JButton button)
	{
		this.button = button;
	}
	
	public MyThread(String name)
	{
		//this.setName(name);
		super(name);
	}
	
	public void sleep(int seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		}
		catch(InterruptedException ex)
		{}
	}
	
	public void joinSelf(long millis)
	{
		try
		{
			this.join(millis);
		}
		catch(InterruptedException ex)
		{}
	}
	
	@Override
	public void run()
	{
		//Thread Worker Code
		this.button.setEnabled(false);
		this.progressBar.setIndeterminate(false);
		int minValue = this.progressBar.getMinimum();
		int maxValue = this.progressBar.getMaximum();
		for(int i = minValue; i < maxValue; i++)
		{
			this.sleep(1);
			int curValue = this.progressBar.getValue();
			curValue++;
			this.progressBar.setValue(curValue);
		}
		this.button.setEnabled(true);
	}
}
