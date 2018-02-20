
import java.io.FileNotFoundException;
import java.io.IOException;

import com.pff.*;

public class Main {
	public static void main(String[] args) {
		String filename = "";
		try {
			PSTFile pstFile = new PSTFile(filename);
			System.out.println(pstFile.getMessageStore().getDisplayName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PSTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Hello World!"); // Display the string.
    }
}
