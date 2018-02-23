package monad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

import com.pff.PSTAttachment;
import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;
import com.pff.PSTObject;

import Fs;

public class HandlerMonad {


  public static void handle(String file_path)
      throws FileNotFoundException, PSTException, IOException {
    Path root_dir_path = Fs.rootPath(file_path);
    PSTFolder root = (new PSTFile(file_path)).getRootFolder();

    Folder.handleFolder(root, root_dir_path, 0);
  }

  private static class Folder {
    private static MyState<Void> handleFolder(PSTFolder folder)
        throws IOException, PSTException {
      return 
        MyState.countPlusPlus().bind(unused ->
        MyState.getCount().bind(count ->
        MyState.cd("[R"+count+"]"+folder.getDisplayName()).bind(unused2 ->
        MyState.pwd().bind(dir_path ->
        {
          Fs.mkdir(dir_path);
          return
              loopMessages(folder).bind(unused3 ->
              loopSubFolders(folder).bind(unused4 ->
              MyState.cdPointPoint()));
        }))));
    }

    private static MyState<Void> loopMessages(PSTFolder folder)
        throws IOException, PSTException {
          MyState<Void> ans = new MyState<>(null);
          if (folder.getEmailCount() > 0) {
            PSTMessage message = (PSTMessage)folder.getNextChild();
            while (message != null) {
              ans = ans.bind(Message.handleMessage(message));
              message = (PSTMessage)folder.getNextChild();
            }
          }
          return ans;
        };
    }

    private static MyState<Void> loopSubFolders(PSTFolder folder)
        throws IOException, PSTException {
      MyState<Void> ans = new MyState<>(null);
      if (folder.hasSubfolders() && folder.getSubFolderCount() > 0) {
        Vector<PSTFolder> children = folder.getSubFolders();
        for (PSTFolder child : children) {
          ans = ans.bind(handleFolder(child));
        }
      }
      return ans;
    }
  }


  private static class Message {
    private static MyState<Void> handleMessage(PSTMessage message)
        throws IOException, PSTException {
      return 
          MyState.countPlusPlus().bind(unused ->
          MyState.getCount().bind(count ->
          MyState.cd("[M"+count+"]"+message.getSubject()).bind(unused2 ->
          MyState.pwd().bind(dir_path ->
          {
            Fs.mkdir(dir_path);
            return
                loopAttachments(message).bind(unused3 ->
                MyState.cdPointPoint());
          }))));
    }

    private static MyState<Void> loopAttachments(PSTMessage message)
        throws IOException, PSTException {
      MyState<Void> ans = new MyState<>(null);
      int num_attach = message.getNumberOfAttachments();
      for (int i = 0; i<num_attach ; i++) {
        PSTAttachment attach = message.getAttachment(i);
        ans = ans.bind(Attachment.handleAttachment(attach));
      }
      return ans;
    }
  }

  private static class Attachment {
    private static MyState<Void> handleAttachment(PSTAttachment attach)
        throws IOException, PSTException {
      return 
          MyState.countPlusPlus().bind(unused ->
          MyState.getCount().bind(count ->
          MyState.cd("[P"+count+"]"+attach.getLongFilename()).bind(unused2 ->
          MyState.pwd().bind(dir_path ->
          {
            Fs.mkdir(dir_path);
            return MyState.cdPointPoint();
          }))));
    }
  }


}


