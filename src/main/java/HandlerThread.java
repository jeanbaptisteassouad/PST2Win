
public class HandlerThread extends Thread {

	String filepath;
	
	public HandlerThread(String filepath) {
		this.filepath = filepath;
	}

	public void run() {
		
		
		try {
			Handler.handle(this.filepath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
