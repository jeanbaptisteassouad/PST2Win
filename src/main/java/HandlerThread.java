import java.nio.channels.ClosedByInterruptException;

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
				Handler handler = new Handler(this.filepath);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(window != null)
					window.set_stop_to_extract();
			}

	}

}
