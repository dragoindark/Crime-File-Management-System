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

class userPage {

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
					userPage window = new userPage();
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
	public userPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.setTitle("User Page");
		jFrame.setBounds(100, 100, 593, 442);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);

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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.PINK);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(191, 35, 333, 340);
		jFrame.getContentPane().add(panel);
		
		JButton btnComplaint = new JButton("   Complaint   ");
		btnComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnComplaint.setBounds(90, 11, 142, 23);
		panel.add(btnComplaint);
		
		JButton btnAddCrimeReport = new JButton("Crime Report");
		btnAddCrimeReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnAddCrimeReport.setBounds(90, 61, 142, 23);
		panel.add(btnAddCrimeReport);
		
		JButton btnMessages = new JButton("Messages");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userMessages update = new userMessages();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnMessages.setBounds(90, 157, 142, 23);
		panel.add(btnMessages);
		
		JButton btnNewButton = new JButton("Missing Person");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReport update = new manageReport();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(90, 107, 142, 23);
		panel.add(btnNewButton);
		
		JButton sendMessage = new JButton("Send Message");
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message update = new message();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		sendMessage.setBounds(90, 211, 142, 23);
		panel.add(sendMessage);
		
		JButton btnNewButton_1 = new JButton("User Updates");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatesOfUsers update = new updatesOfUsers();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(90, 263, 142, 23);
		panel.add(btnNewButton_1);



	}
}
