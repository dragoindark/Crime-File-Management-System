package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class message {

	private JFrame jFrame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					message window = new message();
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getJFrame() {
		return this.jFrame;
	}
	/**
	 * Create the application.
	 */
	public message() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.setTitle("Message");
		jFrame.setBounds(100, 100, 450, 300);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(48, 27, 118, 35);
		jFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessage.setBounds(48, 91, 118, 35);
		jFrame.getContentPane().add(lblMessage);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(185, 100, 201, 109);
		jFrame.getContentPane().add(textArea);
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setBounds(184, 27, 202, 35);
		jFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(317, 220, 89, 23);
		jFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reply");
		btnNewButton_1.setBounds(195, 220, 89, 23);
		jFrame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(77, 220, 89, 23);
		jFrame.getContentPane().add(btnNewButton_2);
	}
}
