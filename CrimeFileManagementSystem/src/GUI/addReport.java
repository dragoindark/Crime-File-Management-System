package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addReport {

	private JFrame jFrame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addReport window = new addReport();
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
	public addReport() {
		initialize();
	}
	public JFrame getJFrame() {
        return this.jFrame;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		jFrame.setTitle("Add Report");
		jFrame.setBounds(100, 100, 450, 300);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblNewLabel.setBounds(59, 27, 81, 34);
		jFrame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(145, 36, 182, 20);
		jFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblContent.setBounds(59, 74, 81, 34);
		jFrame.getContentPane().add(lblContent);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(145, 81, 182, 86);
		jFrame.getContentPane().add(textArea);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblLocation.setBounds(59, 175, 81, 34);
		jFrame.getContentPane().add(lblLocation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 184, 182, 20);
		jFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDelete = new JButton("Add");
		btnDelete.setBounds(328, 215, 89, 23);
		jFrame.getContentPane().add(btnDelete);
	}
}
