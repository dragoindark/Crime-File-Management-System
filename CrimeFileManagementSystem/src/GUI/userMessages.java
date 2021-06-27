package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class userMessages extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public JFrame getJFrame() {
		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userMessages frame = new userMessages();
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
	public userMessages() {
		
		
		//deneme.fillJTable(table);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 81, 546, 357);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				message update = new message();
				update.getJFrame().setVisible(true);
				update.getJFrame().setLocationRelativeTo(null);
				update.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"asdfadfas", "asdfadaf"},
				{"asdfasdf", "asdfasdf"},
				{"asdfasdfa", "asdfasfads"},
			},
			new String[] {
				"Username", "Message"
			}
		));
		
		JLabel lblManage = new JLabel("User Messages");
		lblManage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManage.setBounds(294, 11, 245, 35);
		contentPane.add(lblManage);
	}

}
