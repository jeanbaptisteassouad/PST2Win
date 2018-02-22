package monad;
import java.nio.file.Path;

public class AppState {
  public int count;
  public Path dir_path;
  
  public AppState(int count_,Path dir_path_) {
    count = count_;
    dir_path = dir_path_;
  }

  public AppState() {
  }
}
