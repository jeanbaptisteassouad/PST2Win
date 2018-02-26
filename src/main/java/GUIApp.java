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
	
	HandlerThread thread = null;

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
		else if (command.equals("stop"))
			doAction(STOP_EXTRACT);
		else if (command.equals("empty"))
			doAction(EMPTY_LOG);

	}
	
	private String selectPath(String folder, boolean dirBool) {
		File file;

		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser
				.setFileSelectionMode((dirBool ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_AND_DIRECTORIES));
		//fileChooser.setFileHidingEnabled(false);
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
	
	/** The Constant STOP_EXTRACT. */
	static final int STOP_EXTRACT = 3;
	
	private void doAction(int actionNumber) {
		
		if (actionNumber == EMPTY_LOG) {
			mainWindow.consoleTextArea.setText("");
		}
		else if (actionNumber == EXTRACT_ACTION){
			
			this.mainWindow.set_extract_to_stop();

			this.thread = new HandlerThread(mainWindow.containerField.getText());
			thread.start();

			//On remet le bouton Extraire
			
		}
		else if(actionNumber == STOP_EXTRACT) {
			this.thread.interrupt();
			Debug.print("-- Interrompu --");
			this.mainWindow.set_stop_to_extract();
		}
	}
	
	// used to update console text area
		private void updateTextArea(final String text) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					mainWindow.consoleTextArea.append(text);
				}
			});
		}

		// used to redirect out console stream to text area (no err redirection to
		// avoid tika and other tools errors...)
		private void redirectSystemStreams() {
			OutputStream out = new OutputStream() {
				@Override
				public void write(int b) throws IOException {
					updateTextArea(String.valueOf((char) b));
				}

				@Override
				public void write(byte[] b, int off, int len) throws IOException {
					updateTextArea(new String(b, off, len));
				}

				@Override
				public void write(byte[] b) throws IOException {
					write(b, 0, b.length);
				}
			};

			System.setOut(new PrintStream(out, true));
			// System.setErr(new PrintStream(out, true));
		}

}
