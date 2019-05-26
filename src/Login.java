import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setBounds(123, 26, 266, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(58, 84, 69, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(58, 130, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String driver = "com.mysql.cj.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/sys";
					String username = "root";
					String password = "Bh0smarina";
					Class.forName(driver);
					
					Connection conn = DriverManager.getConnection(url,username,password);
					String sql = "Select * from login where user=? and password = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();

					
				String user = textField.getText();
				String passwor = passwordField.getText();
				
				
			if(rs.next())
			{
				if(user.contains("admin") && passwor.contains("admin"))
				{				
					Librarian.main(null);
				}
				else
				{
					Member.main(null);
				}
			}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Login Details",null, JOptionPane.ERROR_MESSAGE);
					textField.setText(null);
					passwordField.setText(null);
				}
			conn.close();
			}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				}
		});
		btnNewButton.setBounds(151, 184, 146, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(151, 81, 146, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 126, 146, 29);
		frame.getContentPane().add(passwordField);
	}
}
