import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class tab_s {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tab_s window = new tab_s();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/sys";
	String username = "root";
	String password = "Bh0smarina";
	
	
	Connection conn = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public void refreshTable()
	{
		try {
			String query = "Select * from memb";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
	}
	
	public tab_s() throws SQLException {
		
	

	/**
	 * Initialize the contents of the frame.
	 */
		conn = DriverManager.getConnection(url,username,password);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnViewMembers = new JButton("View Members");
		btnViewMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Select * from memb";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnViewMembers.setBounds(264, 158, 115, 29);
		frame.getContentPane().add(btnViewMembers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 13, 189, 129);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(15, 11, 69, 20);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(63, 8, 146, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(15, 52, 69, 20);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(63, 47, 146, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBooks = new JLabel("books");
		lblBooks.setBounds(15, 88, 69, 20);
		frame.getContentPane().add(lblBooks);
		
		textField_2 = new JTextField();
		textField_2.setBounds(63, 84, 146, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into memb (m_id,m_name,no_book) values (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Member Added");
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(73, 113, 115, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update memb set m_id='"+textField.getText()+"' , m_name='"+textField_1.getText()+"' , no_book='"+textField_2.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Member Updated");
					
					pst.close();
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(73, 158, 115, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from memb where m_id = '"+textField.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Member Delete");
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(73, 203, 115, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Librarian.main(null);
			}
		});
		btnBack.setBounds(264, 199, 115, 29);
		frame.getContentPane().add(btnBack);
		refreshTable();
	}
	

}
