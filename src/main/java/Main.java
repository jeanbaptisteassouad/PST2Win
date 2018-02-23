

import java.io.FileNotFoundException;
import java.io.IOException;

import com.pff.*;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;



public class Main {
	public static void main(String[] args) throws FileNotFoundException, PSTException, IOException, AddressException, MessagingException {
    String file_path = "/Users/jean-baptiste/Desktop/testPst/backup.pst";
    new Handler(file_path);
  }

}


