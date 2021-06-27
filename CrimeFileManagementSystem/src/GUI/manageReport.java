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

public class manageReport extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
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
					manageReport frame = new manageReport();
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
	public manageReport() {
		
		
		//deneme.fillJTable(table);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(15, 67, 95, 35);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblContent = new JLabel("Content");
		lblContent.setBounds(15, 138, 95, 35);
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblContent);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(15, 292, 95, 35);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblLocation);
		
		textField = new JTextField();
		textField.setBounds(143, 70, 146, 35);
		textField.setColumns(10);
		contentPane.add(textField);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(143, 146, 144, 128);
		contentPane.add(textArea);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 292, 146, 35);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JButton buttonDeleteReport = new JButton("Delete");
		buttonDeleteReport.setBounds(47, 373, 89, 23);
		contentPane.add(buttonDeleteReport);
		
		JButton buttonAddReport = new JButton("Add");
		buttonAddReport.setBounds(47, 411, 89, 23);
		contentPane.add(buttonAddReport);
		
		JButton buttonEditReport = new JButton("Edit");
		buttonEditReport.setBounds(198, 373, 89, 23);
		contentPane.add(buttonEditReport);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 67, 416, 349);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex= table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				textField.setText(model.getValueAt(rowIndex, 0).toString());
				textArea.setText(model.getValueAt(rowIndex, 1).toString());
				textField_1.setText(model.getValueAt(rowIndex, 2).toString());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"asdfadfas", "asdfadaf", "asdfadfs"},
				{"asdfasdf", "asdfasdf", "asdfasdf"},
				{"asdfasdfa", "asdfasfads", "asdfasdf"},
			},
			new String[] {
				"Name", "Content", "Location"
			}
		));
		
		JLabel lblManage = new JLabel("Manage .....");
		lblManage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManage.setBounds(294, 11, 245, 35);
		contentPane.add(lblManage);
		
		JButton btnNewButton = new JButton("Reply");
		btnNewButton.setBounds(198, 411, 89, 23);
		contentPane.add(btnNewButton);
	}

}
