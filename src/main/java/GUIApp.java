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
		try {
			mainWindow = new GUIWindow(this);
			insertOptions();
			redirectSystemStreams();
			mainWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void redirectSystemStreams() {
		// TODO Auto-generated method stub
		
	}

	public void insertOptions() {
		this.mainWindow.containerField.setText(this.sourcePath);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("container")) {
			String filename = selectPath(mainWindow.containerField.getText(), false);
			if (filename != null)
				mainWindow.containerField.setText(filename);
		}
		else if (command.equals("extract"))
			doAction(EXTRACT_ACTION);
		else if (command.equals("empty"))
			doAction(EMPTY_LOG);

	}
	
	private String selectPath(String folder, boolean dirBool) {
		File file;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser
				.setFileSelectionMode((dirBool ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_AND_DIRECTORIES));
		fileChooser.setFileHidingEnabled(false);
		file = new File(folder);
		if (file.exists())
			fileChooser.setSelectedFile(file);
		int returnVal = fileChooser.showOpenDialog(this.mainWindow);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			return (file.getAbsolutePath());
		} else
			return null;
	}
	
	/** The Constant EXTRACT_ACTION. */
	static final int EXTRACT_ACTION = 1;

	/** The Constant EMPTY_LOG. */
	static final int EMPTY_LOG = 2;
	
	private void doAction(int actionNumber) {

		if (actionNumber == EMPTY_LOG) {
			mainWindow.consoleTextArea.setText("");
		} else {
			// Code pour extraire
		}
	}

}
