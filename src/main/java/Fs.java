import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ClosedByInterruptException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Fs {
  public static void mkdir(Path p) {
    new File(p.toString()).mkdir();
  }

  public static Path rootPath(String s) {
    Path p = Paths.get(s);
    return p.getParent().resolve(p.getFileName().toString().replaceAll(".pst", ""));
  }

  public static Path appendStr2DirPath(Path a, String s) {
    return a.resolve(escape(s,40));
  }

  public static Path appendStr2FilePath(Path a, String s) {
    String ext = "";
    String s_without_ext = s;
    
    int i = s.lastIndexOf('.');
    if(i >= 0) {
      s_without_ext = s.substring(0, i).replaceAll("\\.","");
      ext = s.substring(i, s.length());
    }

    String ans = escape(s_without_ext,30) + "." + escape(ext,4);
    
    return a.resolve(ans);
  }

  private static String escape(String s, int thr) {
    s = s.replaceAll("[ /]","_");

    s = convert2ASCII(s);
    s = escapeDot(s);
    s = removeInvalidWinPathChar(s);
    s = truncateStrLength(s,thr);

    return s;
  }

  private static String convert2ASCII(String s) {
    s = replaceAllInsensitive(s,"[éèê]","e");
    s = replaceAllInsensitive(s,"[àâ]","a");
    s = replaceAllInsensitive(s,"[ç]","c");
    s = replaceAllInsensitive(s,"[ù]","u");
    s = replaceAllInsensitive(s,"[î]","i");
    s = s.replaceAll("[«»]","\"");

    byte[] byte_array = s.getBytes(StandardCharsets.US_ASCII);
    s = new String(byte_array, StandardCharsets.US_ASCII);
    return s;
  }

  private static String escapeDot(String s) {
    return s.replaceAll("\\.","");
  }

  private static String removeInvalidWinPathChar(String s) {
    return s.replaceAll("[\\\\/:\\*?\"<>|]","");
  }

  private static String truncateStrLength(String s, int thr) {
    if(s.length() > thr)
      s = s.substring(0,thr) + "_";

    return s;
  }

  private static String replaceAllInsensitive(String s,String minRegex, String minReplace) {
    s = s.replaceAll(minRegex,minReplace);
    s = s.replaceAll(minRegex.toUpperCase(),minReplace.toUpperCase());
    return s;
  }



  public static void writeString(Path p, String s) throws IOException {
    byte[] strToBytes = s.getBytes();
    try {
      Files.write(p, strToBytes);
    } catch(ClosedByInterruptException e) {
      throw e;
    } catch (IOException e) {
      System.out.println("Erreur à l'écriture du fichier");
      System.out.println(p);
      e.printStackTrace();
    }
  }

  public static void writeStream(Path p, InputStream inStream) throws IOException {
    OutputStream outStream = new FileOutputStream(p.toFile());

    int octets = 1024 * 1024;
    byte[] buffer = new byte[octets];

    int data = inStream.read(buffer);
    while(data != -1) {
      outStream.write(buffer);

      data = inStream.read(buffer);
    }
 
    inStream.close();
    outStream.flush();
    outStream.close();
  }
}
