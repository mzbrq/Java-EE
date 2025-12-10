import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    private static int ret;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int n = 0;
                for (int i = 1; i <= 1000; i++) {
                    n+=i;
                }
                return n;
            }
        };

        //创建 futureTask 类，传入callable，在传入Thread的构造方法
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();

        //使用get()方法，获得计算结果
        System.out.println(futureTask.get());

        /*Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 0;
                for (int i = 1; i <= 1000; i++) {
                    n += i;
                }

                ret = n;
            }
        });

        t1.start();
        t1.join();

        //main线程访问不到 t1线程的变量
        //n.sout

        //创建成员变量，让 计算结果能够让main线程访问到

        System.out.println(ret);*/
    }
}
