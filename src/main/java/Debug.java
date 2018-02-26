


public class Debug {
  public static void log(Object o) {
    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
    String file_name = stack[2].getFileName();
    String methode_name = stack[2].getMethodName();
    int line_number = stack[2].getLineNumber();
    String class_name = o.getClass().getName();
    
    String ans = file_name+" | "+methode_name+" | "+line_number+" : ("+class_name+") ";
    ans += o.toString();
    System.out.println(ans);
  }
  
  public static void print(Object o) { 
    String ans = "";
    ans += o.toString();
    System.out.println(ans);
  }
}
