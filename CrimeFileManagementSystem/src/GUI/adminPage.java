package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ListModel;

class adminPage {

	private JFrame jFrame;

	public JFrame getJFrame() {
		return this.jFrame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPage window = new adminPage();
					window.jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.setTitle("Admin Page");
		jFrame.setBounds(100, 100, 593, 442);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.PINK);
		panel.setBounds(211, 11, 338, 381);
		jFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnComplaint = new JButton("   Complaints   ");
		btnComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnComplaint.setBounds(66, 11, 180, 23);

		panel.add(btnComplaint);

		JButton btnAddCrimeReport = new JButton("Crime Reports");
		btnAddCrimeReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnAddCrimeReport.setBounds(66, 85, 180, 23);
		panel.add(btnAddCrimeReport);

		JButton btnMissingPerson = new JButton("User Feedbacks");
		btnMissingPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userFeedbacks update = new userFeedbacks();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnMissingPerson.setBounds(66, 150, 180, 23);
		panel.add(btnMissingPerson);
		
		JButton btnMessages = new JButton("Hot News");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotNews update = new hotNews();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	
		btnMessages.setBounds(66, 284, 180, 23);
		panel.add(btnMessages);

		JButton btnNewButton = new JButton("Missing People");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(66, 220, 180, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Name       Surname");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 162, 14);
		jFrame.getContentPane().add(lblNewLabel);

		JLabel lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMail.setBounds(10, 46, 44, 14);
		jFrame.getContentPane().add(lblMail);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPhone.setBounds(10, 80, 55, 14);
		jFrame.getContentPane().add(lblPhone);

		JLabel lblAbcgmailcom = new JLabel("abc@gmail.com");
		lblAbcgmailcom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAbcgmailcom.setBounds(64, 43, 117, 21);
		jFrame.getContentPane().add(lblAbcgmailcom);

		JLabel lblPhone_1 = new JLabel("05555555555");
		lblPhone_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPhone_1.setBounds(75, 83, 116, 14);
		jFrame.getContentPane().add(lblPhone_1);
		
		

		
	}
}
