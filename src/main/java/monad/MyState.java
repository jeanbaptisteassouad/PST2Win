package monad;
import java.nio.file.Path;
import java.util.function.Function;

import Fs;

public class MyState<T> extends StateMonad<AppState,T> {
  // Use to get state class
  private static AppState init = new AppState();
  
  // Pure
  public MyState(T t) {
    super(init,t);
  }

  // To cast StateMonad<AppState,T> to MyState<T>
  private MyState(StateMonad<AppState,T> s) {
    super(s);
  }

  private static MyState<AppState> get() {
    return new MyState<>(new MyState<>(init)._get());
  }
  
  private static MyState<Void> put(AppState s) {
    return new MyState<>(new MyState<>(init)._put(s));
  }
  
  public <U> MyState<U> bind(Function<T, MyState<U>> g) {
    Function<T, StateMonad<AppState,U>> h = a -> (StateMonad<AppState,U>)g.apply(a);
    return new MyState<>(_bind(h));
  }
  
  public <U> MyState<U> map(Function<T, U> g) {
    return new MyState<>(_map(g));
  }
  
  
  // Specialised get and put functions
  
  public static MyState<Integer> getCount() {
    return get().map(s -> s.count);
  }
  
  public static MyState<Void> countPlusPlus() {
    return
        get().bind(s -> 
        put(new AppState(s.count + 1,s.dir_path)));
  }
  
  public static MyState<Path> pwd() {
    return get().map(s -> s.dir_path);
  }
  
  public static MyState<Void> cd(String str) {
    return
        get().bind(s -> 
        put(new AppState(s.count,Fs.appendString2Path(s.dir_path, str))));
  }
  
  public static MyState<Void> cdPointPoint() {
    return
        get().bind(s -> 
        put(new AppState(s.count,s.dir_path.getParent())));
  }
  
  
}
