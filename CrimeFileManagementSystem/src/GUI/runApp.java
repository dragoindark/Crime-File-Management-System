package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class runApp {
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
                    window.getJFrame().setVisible(true);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
