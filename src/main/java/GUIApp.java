import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class GUIApp implements ActionListener, Runnable {
	
	/** The main window. */
	GUIWindow mainWindow;

	/** The parameters */
	String sourcePath = "";

	public GUIApp(String sourcePath) {
		this.mainWindow = new GUIWindow(this);
		this.sourcePath = sourcePath;
	}

	public void run() {
		// TODO Auto-generated method stub

	}
	
	public void insertOptions() {
		this.mainWindow.containerField.setText(this.sourcePath);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
