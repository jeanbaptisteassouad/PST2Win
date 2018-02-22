import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.pff.PSTMessage;

public class Eml {
  
  public static void convert2Eml(PSTMessage pst_message, Path path) throws AddressException, MessagingException, FileNotFoundException, IOException {
    String to = "to@swagland.com";
    // String to = pst_message.getRecipientsString();
    // Debug.log(to);
    String from = "from@swagland.com";
    // String from = pst_message.getSenderEmailAddress();
    // Debug.log(from);
    String subject = pst_message.getSubject();
    // Debug.log(subject);
    String body = pst_message.getBody();
    // List<File> attachments;
    
    // Debug.log(pst_message.getMessageClass());
    // Debug.log(pst_message.getClass());
    
    Properties props = new Properties();
    props.put("mail.store.protocol", "Yolo");
    props.put("mail.transport.protocol", "Swag");
    props.put("mail.host", "localhost");
    props.put("mail.user", "user.name");
    props.put("mail.protocol.host", "mail.host");
    props.put("mail.protocol.user", "mail.user");
    props.put("mail.from", "username@host");
    props.put("mail.debug", "false");
    
    Session session = Session.getInstance(props);
    Message message = new MimeMessage(session);
    
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    message.setSubject(subject);
    // create the message part 
    MimeBodyPart content = new MimeBodyPart();
    // fill message
    content.setText(body);
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(content);
    // // add attachments
    // for(File file : attachments) {
    //     MimeBodyPart attachment = new MimeBodyPart();
    //     DataSource source = new FileDataSource(file);
    //     attachment.setDataHandler(new DataHandler(source));
    //     attachment.setFileName(file.getName());
    //     multipart.addBodyPart(attachment);
    // }
    // integration
    message.setContent(multipart);
    // store file
    message.writeTo(new FileOutputStream(path.toFile()));
  }
}
