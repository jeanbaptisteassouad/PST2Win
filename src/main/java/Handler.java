
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.pff.PSTAttachment;
import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;


public class Handler extends State {

  public Handler(String file_path)
      throws FileNotFoundException, PSTException, IOException, AddressException, MessagingException {
    Path root_dir_path = Fs.rootPath(file_path);
    mkWorkingDir(root_dir_path);

    PSTFolder root = (new PSTFile(file_path)).getRootFolder();

    Debug.print("Début de l'extraction ...");
    Folder f = handleFolder(root, root_dir_path);
    Debug.print("Extraction terminée, écriture du bordereau de récolement ...");
    writeProofing(f,root_dir_path);
    Debug.print("Terminé !");
    Debug.print("--------------------------------");
  }
  
  private static void mkWorkingDir(Path root_dir_path) {
    Fs.mkdir(root_dir_path);
  }
  
  private static void writeProofing(Folder f, Path root_dir_path) throws IOException {
    f.write_CSV(root_dir_path.toString()+"/recolement.csv");
  }


  //**************//
  // TravelFolder //
  //**************//

  private Folder handleFolder(PSTFolder folder, Path parent_dir_path)
      throws IOException, PSTException, AddressException, MessagingException {
    countPlusPlus();
    int folder_count = getCount();
    
    String folder_name = folder2Name(folder,folder_count);

    Debug.print(folder_name);

    Path dir_path = folder2Dir(folder,parent_dir_path,folder_name);

    ArrayList<Item> list = new ArrayList<>();
    list.addAll(loopMessages(folder, dir_path));
    list.addAll(loopSubFolders(folder, dir_path));

    return folder2Proofing(folder,folder_name,folder_count,list);
  }

  private static String folder2Name(PSTFolder folder,int folder_count) {
    return "[R"+folder_count+"]"+folder.getDisplayName();
  }

  private static Path folder2Dir(PSTFolder folder, Path parent_dir_path, String folder_name) {
    Path dir_path = Fs.appendString2Path(parent_dir_path, folder_name);
    Fs.mkdir(dir_path);
    return dir_path;
  }

  private static Folder folder2Proofing(PSTFolder folder, String folder_name, int folder_count, ArrayList<Item> list) {
    return new Folder(folder_count,folder_name,list);
  }

  private ArrayList<Item> loopMessages(PSTFolder folder, Path parent_dir_path)
      throws IOException, PSTException, AddressException, MessagingException {

    ArrayList<Item> list = new ArrayList<>();

    if (folder.getEmailCount() > 0) {
      PSTMessage message = (PSTMessage)folder.getNextChild();
      while (message != null) {
        list.add(handleMessage(message, parent_dir_path));
        message = (PSTMessage)folder.getNextChild();
      }
    }
    return list;
  }

  private ArrayList<Item> loopSubFolders(PSTFolder folder, Path parent_dir_path)
      throws IOException, PSTException, AddressException, MessagingException {

    ArrayList<Item> list = new ArrayList<>();

    if (folder.hasSubfolders() && folder.getSubFolderCount() > 0) {
      Vector<PSTFolder> children = folder.getSubFolders();
      for (PSTFolder child : children) {
        list.add(handleFolder(child, parent_dir_path));
      }
    }
    return list;
  }
  

  //***************//
  // TravelMessage //
  //***************//

  private Message handleMessage(PSTMessage message, Path parent_dir_path)
      throws IOException, PSTException, AddressException, MessagingException {
    countPlusPlus();
    int message_count = getCount();
    
    String message_name = message2Name(message,message_count);

    Debug.print(message_name);

    Path dir_path = message2Dir(message,parent_dir_path,message_name);

    ArrayList<Item> list = loopAttachments(message, dir_path);
    
    message2Files(message,dir_path,message_name);
    return message2Proofing(message,message_name,message_count,list);
  }

  private static String message2Name(PSTMessage message,int message_count) {
    return "[M"+message_count+"]"+message.getSubject();
  }

  private static Path message2Dir(PSTMessage message, Path parent_dir_path, String message_name) {
    Path dir_path = Fs.appendString2Path(parent_dir_path, message_name);
    Fs.mkdir(dir_path);
    return dir_path;
  }

  private static void message2Files(PSTMessage message, Path dir_path, String message_name)
      throws IOException, AddressException, MessagingException {
    Path txt_file_path = Fs.appendString2Path(dir_path, message_name+".txt");
    Fs.writeString(txt_file_path, message.getBody());
    Path html_file_path = Fs.appendString2Path(dir_path, message_name+".html");
    Fs.writeString(html_file_path, message.getBodyHTML());
    if (message.getClass() == PSTMessage.class) {
      Path eml_file_path = Fs.appendString2Path(dir_path, message_name+".eml");
      Eml.convert2Eml(message,eml_file_path);
    }
  }

  private static Message message2Proofing(PSTMessage message,String message_name, int message_count, ArrayList<Item> list) {
    Person sender = new Person(message.getSenderEmailAddress(),message.getSenderName());
    long size = message.getMessageSize();
    Date time = message.getClientSubmitTime();
    return new Message(message_count,message_name,sender,new ArrayList<>(),size,time, list);
  }

  private ArrayList<Item> loopAttachments(PSTMessage message, Path parent_dir_path)
      throws IOException, PSTException {
    int num_attach = message.getNumberOfAttachments();

    ArrayList<Item> list = new ArrayList<>();

    for (int i = 0; i<num_attach ; i++) {
      PSTAttachment attach = message.getAttachment(i);

      list.add(handleAttachment(attach, parent_dir_path));
    }
    return list;
  }


  //******************//
  // TravelAttachment //
  //******************//

  private Attachment handleAttachment(PSTAttachment attach, Path parent_dir_path)
      throws IOException, PSTException {
    countPlusPlus();
    int attach_count = getCount();
    
    String attach_name = attachment2Name(attach,attach_count);

    Debug.print(attach_name);

    Path dir_path = attachment2Dir(attach,parent_dir_path,attach_name);

    attachment2Files(attach, dir_path, attach_name);

    return attachment2Proofing(attach,attach_name,attach_count);
  }

  private static String attachment2Name(PSTAttachment attach,int attach_count) {
    return "[P"+attach_count+"]"+attach.getLongFilename();
  }

  private static Path attachment2Dir(PSTAttachment attach, Path parent_dir_path, String attach_name) {
    Path dir_path = Fs.appendString2Path(parent_dir_path, attach_name);
    Fs.mkdir(dir_path);
    return dir_path;
  }

  private static void attachment2Files(PSTAttachment attach, Path dir_path, String attach_name)
      throws IOException, PSTException {
    Path file_path = Fs.appendString2Path(dir_path, attach_name);
    Fs.writeStream(file_path, attach.getFileInputStream());
  }

  private static Attachment attachment2Proofing(PSTAttachment attach, String attach_name, int attach_count) {
    return new Attachment(attach_count,attach_name,attach.getAttachSize());
  }


}


