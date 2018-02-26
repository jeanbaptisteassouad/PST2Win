

import java.io.FileNotFoundException;
import java.io.IOException;

import com.pff.*;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;



public class Main {
	public static void main(String[] args) {
        GUIApp app = new GUIApp("");
        app.run();
		
		System.out.println(Item.write_smart_size(0));
		System.out.println(Item.write_smart_size(9));
		System.out.println(Item.write_smart_size(1023));
		System.out.println(Item.write_smart_size(5782)); // début ko
		System.out.println(Item.write_smart_size(234001));
		System.out.println(Item.write_smart_size(6789003)); // début Mo
		System.out.println(Item.write_smart_size(43000000));
		System.out.println(Item.write_smart_size(1900210456)); // début Go
        
    }
}


