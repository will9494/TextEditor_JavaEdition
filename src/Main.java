import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import gui.Ventana;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try{
					  JFrame.setDefaultLookAndFeelDecorated(true);
					  JDialog.setDefaultLookAndFeelDecorated(true);
					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					}
					catch (Exception e){}
					Ventana start = new Ventana();

					start.frmEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
