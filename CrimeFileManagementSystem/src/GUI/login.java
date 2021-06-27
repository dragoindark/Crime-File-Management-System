package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class login{
    private JFrame jFrame;
    private JTextField textField;
    private JPasswordField passwordField;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
                    window.jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    window.jFrame.setVisible(true);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public JFrame getJFrame() {
        return this.jFrame;
    }

    public login() {
        initialize();
    }
    private void initialize() {
        jFrame = new JFrame();
        jFrame.setTitle("CRIME FILE MANAGEMENT SYSTEM");
        jFrame.getContentPane().setBackground(new Color(169, 169, 169));
        jFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel = new JPanel();
        jFrame.getContentPane().add(panel);
        panel.setLayout(null);


        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Login Type", "User", "Admin"}));
        comboBox.setBounds(194, 260, 139, 22);
        panel.add(comboBox);

        textField = new JTextField();
        textField.setBounds(270, 197, 110, 20);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(194, 200, 53, 14);
        panel.add(lblNewLabel);

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setBounds(193, 228, 69, 14);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(270, 225, 110, 20);
        panel.add(passwordField);

        JLabel lblNewLabel_1 = new JLabel("CRIME FILE MANAGEMENT SYSTEM");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBounds(146, 11, 326, 43);
        panel.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check if information exists in database
                String uname = textField.getText();
				String pword = String.valueOf(passwordField.getPassword());
				String option = comboBox.getSelectedItem().toString();
				if(uname.equals("")||pword.equals("")||option.equals("Select Login Type")) {
					JOptionPane.showMessageDialog(btnNewButton, "Some fields are empty","Error",1);
				}
				else {
					//DB Connection here
					try {
						if(option.equalsIgnoreCase("Admin")&&option.equalsIgnoreCase("admin")) {
							adminPage page = new adminPage();
							page.getJFrame().setVisible(true);
							getJFrame().setVisible(false);
							getJFrame().dispose();
						}
						else if(option.equalsIgnoreCase("User")&&option.equalsIgnoreCase("user")) {
							userPage page = new userPage();
							page.getJFrame().setVisible(true);
							getJFrame().setVisible(false);
							getJFrame().dispose();

						}
						
					}
					catch(Exception a) {
						a.printStackTrace();
						
					}
				}
            }
        });
        btnNewButton.setBounds(416, 273, 89, 23);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    registerPage register = new registerPage();
                    register.getJFrame().setVisible(true);
                }
                catch(Exception f) {
                    f.printStackTrace();
                }

            }
        });
        btnNewButton_1.setBounds(416, 307, 89, 23);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(146, 51, 326, 135);
        panel.add(lblNewLabel_2);
        
        Image img = new ImageIcon(this.getClass().getResource("image.jpg")).getImage();
        lblNewLabel_2.setIcon(new ImageIcon(img));
        
        
 
        jFrame.setBackground(new Color(0, 255, 255));
        jFrame.setBounds(100,100,605,442);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
