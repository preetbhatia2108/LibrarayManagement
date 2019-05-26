import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Librarian {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librarian window = new Librarian();
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
	public Librarian() {
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
		
		JLabel li = new JLabel("Librarian");
		li.setBounds(170, 16, 69, 20);
		frame.getContentPane().add(li);
		
		JButton btnSearchBook = new JButton("View Database");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
							frame.dispose();
							b_datab bd;
							try {
								bd = new b_datab();
								bd.setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
			}
		});
		btnSearchBook.setBounds(33, 73, 150, 29);
		frame.getContentPane().add(btnSearchBook);
		
		JButton btnAdddeleteMember = new JButton("View Member");
		btnAdddeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tab_s.main(null);
			}
		});
		btnAdddeleteMember.setBounds(211, 73, 191, 29);
		frame.getContentPane().add(btnAdddeleteMember);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnLogOut.setBounds(135, 155, 115, 29);
		frame.getContentPane().add(btnLogOut);
	}
}
