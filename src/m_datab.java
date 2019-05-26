import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class m_datab extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					m_datab frame = new m_datab();
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
	private JTextField textField_3;
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

	public m_datab() throws SQLException {
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
			
		});
		btnViewDatabase.setBounds(249, 149, 154, 29);
		contentPane.add(btnViewDatabase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String b_id_ = (table.getModel().getValueAt(row, 0)).toString();
					
					String query = "Select * from data where b_id='"+b_id_+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						textField.setText(rs.getString("b_id"));
						textField_1.setText(rs.getString("b_name"));
						textField_2.setText(rs.getString("issued"));
					}
					pst.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setBounds(239, 10, 183, 123);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Member.main(null);
			}
		});
		btnBack.setBounds(268, 194, 115, 29);
		contentPane.add(btnBack);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(10, 20, 69, 20);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(73, 17, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(10, 67, 69, 20);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 64, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIssued = new JLabel("issued");
		lblIssued.setBounds(10, 113, 69, 20);
		contentPane.add(lblIssued);
		
		textField_2 = new JTextField();
		textField_2.setBounds(73, 110, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("issue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into isu (id,b_name) values (?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2, textField_1.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Book Issued");
					
				}catch(Exception f)
				{
					f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(83, 155, 115, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(10, 203, 69, 20);
		contentPane.add(lblSearch);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query6 = "Select * from data where b_name=?";
					PreparedStatement pst = conn.prepareStatement(query6);
					pst.setString(1,textField_3.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				}
				catch(Exception f1)
				{
					f1.printStackTrace();
				}
				refreshTable();
			}
		});
		textField_3.setBounds(73, 200, 146, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		refreshTable();
	}

}
