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

/* �Զ���� */
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
	private String[] columnNames = new String[] { "���", "����", "����"};
	private DefaultTableModel tableModel = null; //ʹ��JTable�Ĺؼ���1;
	
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
		setFont(new Font("������", Font.PLAIN, 15));
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
					Print.info("�������Ѿ�����<" + result + ">");
				}
				break;
				
				case MSG_LOAD_BOOKLIST:
				{
					Print.info("ͼ���б��Ѿ��������<" + result + ">");
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
		Print.info("�յ��������ȴ������Ϣ");
		MyThread stepper = new MyThread("�����������߳�");
		stepper.setProgressBar(param.bar);
		stepper.setButton(param.btn);
		stepper.start();
		Print.info("���ȴ��������������ת������̨�߳�");
	}
	
	private void onLoadBookList(Message msg)
	{
		Print.info("�յ�����ͼ���б��֪ͨ��Ϣ");
		String dbId = (String)msg._obj;
		try
		{
			List<Book> bookList = this.getBookList(dbId);
			this.showBookList(bookList);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "�������ڹر�...", "Message", JOptionPane.WARNING_MESSAGE);
			}
			
			@Override
			public void windowClosed(WindowEvent e)
			{
				JOptionPane.showMessageDialog(null, "�����ѹر�", "Message", JOptionPane.WARNING_MESSAGE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\soft\\others\\on_off_red.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setFont(new Font("������", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("\u8D26\u53F7:");
		lblUserName.setFont(new Font("������", Font.PLAIN, 15));
		lblUserName.setBounds(14, 13, 44, 18);
		getContentPane().add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("������", Font.PLAIN, 15));
		txtUserName.setBounds(58, 10, 145, 24);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801:");
		lblPassword.setFont(new Font("������", Font.PLAIN, 15));
		lblPassword.setBounds(226, 13, 38, 18);
		getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("������", Font.PLAIN, 15));
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(267, 10, 145, 24);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(32);
		
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String message = "�˺�: " + txtUserName.getText() + "\n" + "����: " + txtPassword.getText();
				JOptionPane.showMessageDialog(null, message, "��¼", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLogin.setFont(new Font("������", Font.PLAIN, 15));
		btnLogin.setBounds(14, 58, 71, 27);
		getContentPane().add(btnLogin);
		
		JButton btnRegist = new JButton("\u6CE8\u518C");
		btnRegist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String message = "�˺�: " + txtUserName.getText() + "\n" + "����: " + txtPassword.getText();
				JOptionPane.showMessageDialog(null, message, "ע��", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnRegist.setFont(new Font("������", Font.PLAIN, 15));
		btnRegist.setBounds(99, 58, 71, 27);
		getContentPane().add(btnRegist);
		
		JButton btnReset = new JButton("\u53D6\u6D88");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(null, "���Ҫȡ����?", "ȷ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "������,�����ˣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(result == JOptionPane.CANCEL_OPTION)
				{
					JOptionPane.showMessageDialog(null, "��ȡ���˲������㻹������ѡ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//dispose(); //OK
					//System.exit(0); //OK
					return;
				}
			}
		});
		btnReset.setFont(new Font("������", Font.PLAIN, 15));
		btnReset.setBounds(341, 58, 71, 27);
		getContentPane().add(btnReset);
		
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("������", Font.PLAIN, 15));
		progressBar.setIndeterminate(true);
		progressBar.setForeground(Color.BLUE);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(400,50));
		progressBar.setBounds(14, 98, 398, 24);
		getContentPane().add(progressBar);
		
		final JButton btnProgressBar = new JButton("\u8FDB\u5EA6\u6761");
		btnProgressBar.setFont(new Font("������", Font.PLAIN, 15));
		btnProgressBar.addActionListener(new ActionListener()
		{
			private void startProgressBarByButtonEvent()
			{
				Print.info("�յ����ȴ���������¼�");
				MyThread stepper = new MyThread("�����������߳�");
				stepper.setProgressBar(progressBar);
				stepper.setButton(btnProgressBar);
				stepper.start();
				Print.info("ֱ���ڰ�ť�¼������������ȴ���");
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
		txtSWINGMessage.setFont(new Font("������", Font.PLAIN, 15));
		txtSWINGMessage.setBounds(14, 135, 396, 98);
		//getContentPane().add(txtSWINGMessage);
		
		final TextArea txtAWTMessage = new TextArea();
		txtAWTMessage.setFont(new Font("������", Font.PLAIN, 15));
		txtAWTMessage.setBounds(14, 366, 398, 115);
		getContentPane().add(txtAWTMessage);
		
		Label lblFile = new Label("\u9009\u62E9\u6587\u4EF6:");
		lblFile.setFont(new Font("������", Font.PLAIN, 16));
		lblFile.setBounds(10, 252, 71, 25);
		getContentPane().add(lblFile);
		
		final TextField txtFile = new TextField();
		txtFile.setFont(new Font("������", Font.PLAIN, 15));
		txtFile.setBounds(14, 277, 321, 25);
		getContentPane().add(txtFile);
		
		final TextField txtFile2 = new TextField();
		txtFile2.setFont(new Font("������", Font.PLAIN, 15));
		txtFile2.setBounds(14, 318, 321, 25);
		getContentPane().add(txtFile2);
		
		Button btnFile = new Button("\u6587\u4EF6AWT");
		btnFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				FileDialog fileDialog = new FileDialog(curFrame, "���ļ�", FileDialog.LOAD);
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
		btnFile.setFont(new Font("������", Font.PLAIN, 15));
		btnFile.setBounds(341, 277, 71, 25);
		getContentPane().add(btnFile);
		
		Button button = new Button("\u6587\u4EF6SWI");
		button.setFont(new Font("������", Font.PLAIN, 15));
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//JFileChooser fileDialog = new JFileChooser();
				//fileDialog.setCurrentDirectory(new File("."));
				JFileChooser fileDialog = new JFileChooser(".", FileSystemView.getFileSystemView());
				fileDialog.setDialogTitle("ѡ��һ���ļ�");
				//fileDialog.setApproveButtonText("ȷ��");
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
					txtSWINGMessage.setText("��ȡ���ļ�ѡ����");
					txtAWTMessage.setText("��ȡ���ļ�ѡ����");
					txtFile.setText("��ȡ���ļ�ѡ����");
					txtFile2.setText("��ȡ���ļ�ѡ����");
					return;
				}
				
				if(result == JFileChooser.ERROR_OPTION)
				{
					txtSWINGMessage.setText("ѡ���ļ�������");
					txtAWTMessage.setText("ѡ���ļ�������");
					txtFile.setText("ѡ���ļ�������");
					txtFile2.setText("ѡ���ļ�������");
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
			this.btnMySQL1.setFont(new Font("������", Font.PLAIN, 15));
			this.btnMySQL1.setBounds(426, 9, 113, 27);
			getContentPane().add(this.btnMySQL1);
		}
		{
			this.btnMySQL2 = new JButton("MySQL2");
			this.btnMySQL2.addActionListener(new BtnMySQL2_ActionListener());
			this.btnMySQL2.setFont(new Font("������", Font.PLAIN, 15));
			this.btnMySQL2.setBounds(572, 9, 113, 27);
			getContentPane().add(this.btnMySQL2);
		}
		{
			this.btnSQLite = new JButton("SQLite");
			this.btnSQLite.addActionListener(new BtnSQLite_ActionListener());
			this.btnSQLite.setFont(new Font("������", Font.PLAIN, 15));
			this.btnSQLite.setBounds(716, 9, 113, 27);
			getContentPane().add(this.btnSQLite);
		}
		
		this.tableModel = new DefaultTableModel(); //ʹ��JTable�Ĺؼ���2;
		{
			this.tblBooks = new JTable();
			this.tblBooks.setBackground(SystemColor.control);
			this.tblBooks.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
			this.tblBooks.setToolTipText("\u56FE\u4E66\u8868\u683C");
			this.tblBooks.setFont(new Font("������", Font.PLAIN, 15));
			this.tblBooks.setBounds(426, 58, 403, 423);
			this.tblBooks.setRowHeight(24);
			this.tblBooks.setRowMargin(5);
			this.tblBooks.setModel(this.tableModel); //ʹ��JTable�Ĺؼ���3;
			//getContentPane().add(this.tblBooks);
		}
		{
			this.panelBook = new JScrollPane();
			this.panelBook.setBounds(426, 58, 403, 423);
			this.panelBook.setViewportView(this.tblBooks);  //ʹ��JTable�Ĺؼ���4; ��ʽ1 OK
			//this.panelBook.getViewport().add(this.tblBooks);//ʹ��JTable�Ĺؼ���4; ��ʽ2 OK
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
		this.setTitle("�ҵ�Ӧ�ó���");
		//this.setLocationRelativeTo(null);
		//this.setLocationByPlatform(true);
		this.setLocation(600, 300);
		this.setSize(850, 530);
		
	  //��������Ӧ��С:
		//this.pack();
		//���ڴ�С���ɸı�;
		this.setResizable(false);
		
		this.font = new Font("������", Font.PLAIN, 16);
		//this.setUiFont(font);
		UIManager.put("OptionPane.messageFont", font);
		UIManager.put("OptionPane.buttonFont", font);
		
		//������Ϣ����:
		//this.mHandler.runForEver();
		
		//��ʾ����:
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyBatisGUI app = new MyBatisGUI();
		app.init("�ҵ�Ӧ�ó���");
	}
	
	private SqlSession getSqlSession(String configFilePath, String environment, Properties props)
	{
		SqlSession sqlSession = null;
		
		try
		{
			//����MyBatis��ȫ��������Ϣ:
			Reader mybatisConfiguration = Resources.getResourceAsReader(configFilePath);
			
			//ʵ����SqlSessionFactoryBuilder;
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = builder.build(mybatisConfiguration, environment, props);
			
			//�����ݿ�����:
			sqlSession =  sqlSessionFactory.openSession();
		}
		catch(Exception e)
		{
			sqlSession = null;
			Print.exce("�����ݿ��쳣:%s", e.getMessage());
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
		
		//��MyBatis��ȫ�������ļ��ж�ȡ���ݿ�������Ϣ�������ݿ�����:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml", environment, props);
		if(session == null)
		{
			String title = "������Ϣ";
			String content = "���ܴ����ݿ�����:" + environment;
			JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		DatabaseMetaData dmd = session.getConnection().getMetaData();
		String dbProductName = dmd.getDatabaseProductName();
		String dbDriverName = dmd.getDriverName() + " " + dmd.getDriverVersion();
		String dbName = session.getConnection().getSchema();
		String dbURL = dmd.getURL();
		String dbUserName = dmd.getUserName();
		String message = "���ݿ��Ʒ����:" + dbProductName + "\n";
		message += "���ݿ���������:" + dbDriverName + "\n";
		message += "���ݿ�����:" + dbName + "\n";
		message += "���ݿ������ַ���:" + dbURL + "\n";
		message += "���ݿ��˺�:" + dbUserName;
		JOptionPane.showMessageDialog(null, message, "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		
	  //ѡ��ӳ����:��ȡӳ�����ӿڶ���ʵ��;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//���ýӿ�ӳ����ʵ���ķ���,�������ݿ�:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//�ر����ݿ�����;
		session.close();
		
		int number_of_books = bookList.size();
		if(number_of_books <= 0)
		{
			Print.warn("û�в�ѯ��ͼ��,�㻹û������κ�ͼ����Ϣ!");
			String title = "��ʾ��Ϣ";
			String content = "�㻹û������κ�ͼ����Ϣ!";
			JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//��ӡͼ���б�:
		Print.info("���� %d ͼ��", number_of_books);
		for(Book book: bookList)
		{
			Print.info("ͼ���� = %d, ͼ������ = %s, ͼ������ = %s", book.getBookId(), book.getBookName(), book.getAuthor());
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
		
		this.tableModel.setDataVector(rows, this.columnNames); //ʹ��JTable�Ĺؼ���5;
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
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
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
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
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
//				JOptionPane.showMessageDialog(null, "ERROR:" + e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
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
