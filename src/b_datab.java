import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class b_datab extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame frame;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					b_datab frame = new b_datab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/sys";
	String username = "root";
	String password = "Bh0smarina";
	
	
	Connection conn = null;
	public void refreshTable()
	{
		
		try {
			String query = "Select * from data";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
	}
	
	public b_datab() throws SQLException{
		conn = DriverManager.getConnection(url,username,password);
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewDatabase = new JButton("View Database");
		btnViewDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Select * from data";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
				
			}
		);
		btnViewDatabase.setBounds(250, 157, 115, 29);
		contentPane.add(btnViewDatabase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(216, 17, 197, 124);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(59, 17, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(10, 20, 69, 20);
		contentPane.add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(59, 46, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setBounds(10, 49, 69, 20);
		contentPane.add(lblBook);
		
		JLabel lblNewLabel = new JLabel("issued");
		lblNewLabel.setBounds(10, 79, 69, 20);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(59, 76, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "insert into data (b_id,b_name,issued) values (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Book Added");
					
				}catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(69, 109, 115, 29);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update data set b_id='"+textField.getText()+"' , b_name='"+textField_1.getText()+"' , issued='"+textField_2.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Book Updated");
					
					pst.close();
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(69, 157, 115, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "delete from data where b_id = '"+textField.getText()+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Book Delete");
					
				}
				catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(69, 199, 115, 29);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				Librarian.main(null);
			}
		});
		btnBack.setBounds(250, 202, 115, 29);
		contentPane.add(btnBack);
		refreshTable();
	}

}
