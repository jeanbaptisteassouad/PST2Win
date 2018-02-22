package monad;
import java.util.function.Function;

public class StateMonad<S,T> {
  private class Box<TT,SS> {
    public TT t;
    public SS s;
    public Box(TT t_,SS s_) {
      t = t_;
      s = s_;
    }
  }

  private Function<S, Box<T,S>> runState;

  private StateMonad(Function<S, Box<T,S>> runState_) {
    runState = runState_;
  }

  protected StateMonad(StateMonad<S,T> s) {
    runState = s.runState;
  }
  
  protected StateMonad(S s,T t) {
    runState = s_ -> new Box<>(t,s_);
  }
   
  protected StateMonad<S,S> _get() {
    Function<S, Box<S,S>> f = s -> new Box<>(s,s);
    return new StateMonad<>(f);
  }

  protected StateMonad<S,Void> _put(S s) {
    Function<S, Box<Void,S>> f = unused -> new Box<Void,S>(null,s);
    return new StateMonad<>(f);
  }

  protected <U> StateMonad<S,U> _bind(Function<T, StateMonad<S,U>> g) {
    Function<S, Box<T,S>> localRunState = runState;
    Function<S, StateMonad<S,U>.Box<U,S>> f = s -> {
      Box<T,S> box = localRunState.apply(s);
      StateMonad<S,U> next_state_monad = g.apply(box.t);
      S next_s = box.s;
      Function<S, StateMonad<S,U>.Box<U,S>> kkk = next_state_monad.runState;
      
      StateMonad<S,U>.Box<U,S> ans = kkk.apply(next_s);
      return ans;
    };
    return new StateMonad<>(f);
  }

  protected <U> StateMonad<S,U> _map(Function<T,U> g) {
    Function<S, Box<T,S>> localRunState = runState;
    Function<S, StateMonad<S,U>.Box<U,S>> f = s -> {
      Box<T,S> box = localRunState.apply(s);
      StateMonad<S,U> kkk = new StateMonad<S,U>(box.s,g.apply(box.t));
      return kkk.runState.apply(box.s);
    };
    return new StateMonad<>(f);
  }

  public T run(S s) {
    return runState.apply(s).t;
  }
}
