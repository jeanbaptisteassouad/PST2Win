
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


public class Handler {

  public static void handle(String file_path)
      throws FileNotFoundException, PSTException, IOException, AddressException, MessagingException {
    Path root_dir_path = Fs.rootPath(file_path);
    mkWorkingDir(root_dir_path);

    PSTFolder root = (new PSTFile(file_path)).getRootFolder();

    Pair<Integer,Folder> pair = TravelFolder.handleFolder(root, root_dir_path, 0);
    writeProofing(pair.b,root_dir_path);
  }
  
  private static void mkWorkingDir(Path root_dir_path) {
    Fs.mkdir(root_dir_path);
  }
  
  private static void writeProofing(Folder f, Path root_dir_path) throws IOException {
    f.write_CSV(root_dir_path.toString()+"/recolement.csv");
  }

  private static class TravelFolder {
    private static Pair<Integer,Folder> handleFolder(PSTFolder folder, Path parent_dir_path, int count)
        throws IOException, PSTException, AddressException, MessagingException {
      count += 1;
      int folder_count = count;
      String folder_name = folder2Name(folder,count);

      Debug.log(folder_name);

      Path dir_path = folder2Dir(folder,parent_dir_path,folder_name);

      Pair<Integer,ArrayList<Item>> pair1 = loopMessages(folder, dir_path, count);
      count = pair1.a;
      Pair<Integer,ArrayList<Item>> pair2 = loopSubFolders(folder, dir_path, count);
      count = pair2.a;

      ArrayList<Item> list = new ArrayList<>();
      list.addAll(pair1.b);
      list.addAll(pair2.b);

      return new Pair<>(count,folder2Proofing(folder,folder_name,folder_count,list));
    }

    private static String folder2Name(PSTFolder folder,int count) {
      return "[R"+count+"]"+folder.getDisplayName();
    }

    private static Path folder2Dir(PSTFolder folder, Path parent_dir_path, String folder_name) {
      Path dir_path = Fs.appendString2Path(parent_dir_path, folder_name);
      Fs.mkdir(dir_path);
      return dir_path;
    }

    private static Folder folder2Proofing(PSTFolder folder, String folder_name, int folder_count, ArrayList<Item> list) {
      return new Folder(folder_count,folder_name,list);
    }

    private static Pair<Integer,ArrayList<Item>> loopMessages(PSTFolder folder, Path parent_dir_path, int count)
        throws IOException, PSTException, AddressException, MessagingException {

      ArrayList<Item> list = new ArrayList<>();

      if (folder.getEmailCount() > 0) {
        PSTMessage message = (PSTMessage)folder.getNextChild();
        while (message != null) {
          Pair<Integer,Message> pair = TravelMessage.handleMessage(message, parent_dir_path, count);
          count = pair.a;
          list.add(pair.b);
          message = (PSTMessage)folder.getNextChild();
        }
      }
      return new Pair<>(count,list);
    }

    private static Pair<Integer,ArrayList<Item>> loopSubFolders(PSTFolder folder, Path parent_dir_path, int count)
        throws IOException, PSTException, AddressException, MessagingException {

      ArrayList<Item> list = new ArrayList<>();

      if (folder.hasSubfolders() && folder.getSubFolderCount() > 0) {
        Vector<PSTFolder> children = folder.getSubFolders();
        for (PSTFolder child : children) {
          Pair<Integer,Folder> pair = handleFolder(child, parent_dir_path, count);
          count = pair.a;
          list.add(pair.b);
        }
      }
      return new Pair<>(count,list);
    }
  }


  private static class TravelMessage {
    private static Pair<Integer,Message> handleMessage(PSTMessage message, Path parent_dir_path, int count)
        throws IOException, PSTException, AddressException, MessagingException {
      count += 1;
      int message_count = count;
      String message_name = message2Name(message,count);

      Debug.log(message_name);

      Path dir_path = message2Dir(message,parent_dir_path,message_name);

      Pair<Integer,ArrayList<Item>> pair = loopAttachments(message, dir_path, count);
      count = pair.a;
      
      message2Files(message,dir_path,message_name);
      return new Pair<>(count,message2Proofing(message,message_name,message_count,pair.b));
    }

    private static String message2Name(PSTMessage message,int count) {
      return "[M"+count+"]"+message.getSubject();
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
      int size = (int) message.getMessageSize();
      Date time = message.getClientSubmitTime();
      return new Message(message_count,message_name,sender,new ArrayList<>(),size,time, list);
    }

    private static Pair<Integer,ArrayList<Item>> loopAttachments(PSTMessage message, Path parent_dir_path, int count)
        throws IOException, PSTException {
      int num_attach = message.getNumberOfAttachments();

      ArrayList<Item> list = new ArrayList<>();

      for (int i = 0; i<num_attach ; i++) {
        PSTAttachment attach = message.getAttachment(i);

        Pair<Integer,Attachment> pair = TravelAttachment.handleAttachment(attach, parent_dir_path, count);
        count = pair.a;
        list.add(pair.b);
      }
      return new Pair<>(count,list);
    }
  }


  private static class TravelAttachment {
    private static Pair<Integer,Attachment> handleAttachment(PSTAttachment attach, Path parent_dir_path, int count)
        throws IOException, PSTException {
      count += 1;
      int attach_count = count;
      String attach_name = attachment2Name(attach,count);

      Debug.log(attach_name);

      Path dir_path = attachment2Dir(attach,parent_dir_path,attach_name);

      attachment2Files(attach, dir_path, attach_name);

      return new Pair<>(count,attachment2Proofing(attach,attach_name,attach_count));
    }

    private static String attachment2Name(PSTAttachment attach,int count) {
      return "[P"+count+"]"+attach.getLongFilename();
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


}


