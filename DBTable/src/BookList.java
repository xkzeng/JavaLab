import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import com.learn.Student;
import java.util.Vector;

public class BookList extends JDialog {

	private final JScrollPane contentPanel = new JScrollPane();
	private JTable tblBook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookList dialog = new BookList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookList() {
		setBounds(100, 100, 657, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Student s1 = new Student("001", "张三", 23);
		Student s2 = new Student("002", "李四", 24);
		Student s3 = new Student("003", "王五", 22);

		Vector<Object> row1 = new Vector<Object>();
		row1.add(s1.getId());
		row1.add(s1.getName());
		row1.add(s1.getAge());

		Vector<Object> row2 = new Vector<Object>();
		row2.add(s2.getId());
		row2.add(s2.getName());
		row2.add(s2.getAge());

		Vector<Vector<Object>> studentData = new Vector<Vector<Object>>();
		studentData.add(row1);
		studentData.add(row2);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("编号");
		columnNames.add("姓名");
		columnNames.add("年龄");
		
		tblBook = new JTable(studentData, columnNames);
		tblBook.setBounds(473, 259, -441, -174);
		contentPanel.setViewportView(tblBook);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
