import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

  public static Path appendString2Path(Path a, String s) {
    s = escape(s);
    Debug.log(s);
    return a.resolve(s);
  }

  private static String escape(String s) {
    s = s.replaceAll("[ /]","_");
    return s;
  }

  public static void writeString(Path p, String s) throws IOException {
    byte[] strToBytes = s.getBytes();
    Files.write(p, strToBytes);
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
