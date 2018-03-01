import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;

import javax.mail.MessagingException;

import com.pff.PSTException;

public class HandlerThread extends Thread {

	String filepath;
	GUIWindow window;
	
	public HandlerThread(String filepath) {
		this.filepath = filepath;
		this.window = null;
	}
	
	public HandlerThread(String filepath, GUIWindow window) {
		this.filepath = filepath;
		this.window = window;
	}
	
	public void run(){
			//if(window != null)
			//	window.set_extract_to_stop();
			
			try {
			new Handler(this.filepath);
			}
			catch (MessagingException e) {
				e.printStackTrace();
			}
			catch (ClosedByInterruptException e) {
				Debug.print("--- Interrompu ---");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (PSTException e) {
				e.printStackTrace();
			}
			finally {
			if(window != null)
				window.set_stop_to_extract();
			}
			
		

	}

}
