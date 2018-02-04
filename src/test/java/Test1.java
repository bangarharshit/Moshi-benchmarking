import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test1 {
  @BeforeEach
  public void setup() {
    System.out.println(RxJavaPlugins.getComputationSchedulerHandler());
    RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
      @Override public Scheduler apply(Scheduler scheduler) throws Exception {
        return Schedulers.trampoline();
      }
    });
  }
  @Test
  public void test() {

  }

  @Test
  public void test2() {

  }

  @Test
  public void test3() {

  }
}

