
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import java.nio.file.Paths;


import com.pff.*;

import java.util.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, PSTException, IOException {
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

    Attachment attach_test_1 = new Attachment(8, "img.jpg", 250000);
    Attachment attach_test_2 = new Attachment(9, "blabla.txt", 1);
    Attachment attach_test_3 = new Attachment(10, "musique.mp3", 42000);

    Attachment attach_test_4 = new Attachment(5, "doc.pdf", 17000);

    Person manu = new Person("emmanuel.gre@gmail.com", "Emmanuel Gautier");
    Person JB = new Person("jean.baptiste@assouad.com", "JB Assouad");
    Person chloe = new Person("chloe.moser@sg.social.gouv.fr", "Chloé Moser");
    Person anne = new Person("anne.lambert@sg.social.gouv.fr", "Anne Lambert");

    ArrayList<Person> receivers_M1 = new ArrayList<Person>();
    receivers_M1.add(JB);

    ArrayList<Person> receivers_M3 = new ArrayList<Person>();
    receivers_M3.add(anne);
    receivers_M3.add(chloe);

    ArrayList<Item> attachments_M1 = new ArrayList<Item>();
    attachments_M1.add(attach_test_4);

    ArrayList<Item> attachments_M3 = new ArrayList<Item>();
    attachments_M3.add(attach_test_1);
    attachments_M3.add(attach_test_2);
    attachments_M3.add(attach_test_3);

    Message message_test_1 = new Message(4, "Hello", manu, receivers_M1 , 9, null, attachments_M1);
    Message message_test_2 = new Message(6, "Bonjour", JB, null , 14, null, null);
    Message message_test_3 = new Message(7, "Coucou", null, receivers_M3 , 9, null, attachments_M3);

    ArrayList<Item> messages_F3 = new ArrayList<Item>();
    messages_F3.add(message_test_1);
    messages_F3.add(message_test_2);
    messages_F3.add(message_test_3);        

    Folder folder_test_2 = new Folder(2, "Éléments supprimés", null);
    Folder folder_test_3 = new Folder(3, "Boîte de réception", messages_F3);

    ArrayList<Item> subfolders_F1 = new ArrayList<Item>();
    subfolders_F1.add(folder_test_2);
    subfolders_F1.add(folder_test_3);

    Folder folder_test_1 = new Folder(1, "Début du fichier", subfolders_F1);

    try {
      folder_test_1.write_CSV("/Users/Emmanuel/Documents/Travail/Perso/Data Science/EIG/Sprints/01_PST/test_recolement.csv");
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    String file_path = "/Users/jean-baptiste/Desktop/testPst/backup.pst";
    Handler.handle(file_path);  
  }


}


