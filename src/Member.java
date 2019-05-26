import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Member {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member window = new Member();
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
	public Member() {
		
	
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMember = new JLabel("Member");
		lblMember.setBounds(167, 16, 69, 20);
		frame.getContentPane().add(lblMember);
		
		JButton btnFind = new JButton("Database");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				m_datab.main(null);
			}
		});
		btnFind.setBounds(48, 68, 115, 29);
		frame.getContentPane().add(btnFind);
		
		JButton btnBorrowed = new JButton("Borrowed");
		btnBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				isu.main(null);
			}
		});
		btnBorrowed.setBounds(234, 68, 115, 29);
		frame.getContentPane().add(btnBorrowed);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnLogOut.setBounds(138, 166, 115, 29);
		frame.getContentPane().add(btnLogOut);
	}

}
