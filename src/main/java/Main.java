
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.pff.*;

public class Main {
	public static void main(String[] args) {
		String filename = "/Users/Emmanuel/Documents/Travail/Perso/Data Science/EIG/Sprints/01_PST/EG_16022018.pst";
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
        System.out.println(PSTItem.write_CSVhead()); // Display the string.
        
        PSTAttachment attach_test = new PSTAttachment(3, "img.jpeg", 243, null);
        System.out.println(attach_test.write_CSVline());
        
        ArrayList<PSTAttachment> PJs_test = new ArrayList<PSTAttachment>();
        PJs_test.add(attach_test);
        
        PSTFolder folder_test = new PSTFolder(1, "répertoire_test", null, null);
        System.out.println(folder_test.write_CSVline());
        
        Person manu = new Person("emmanuel.gre@gmail.com", "Emmanuel Gautier");
        
        Person JB = new Person("jean.baptiste@assouad.com", "JB Assouad");
        Person chloe = new Person("chloe.moser@sg.social.gouv.fr", "Chloé Moser");
        Person anne = new Person("anne.lambert@sg.social.gouv.fr", "Anne Lambert");
        
        ArrayList<Person> dest_list = new ArrayList<Person>();
        dest_list.add(JB);
        dest_list.add(chloe);
        dest_list.add(anne);
        
        PSTMessage message_test = new PSTMessage(2, "Hello", manu, dest_list, 12, null, PJs_test, folder_test);
        System.out.println(message_test.write_CSVline());
        
        
    }
}
