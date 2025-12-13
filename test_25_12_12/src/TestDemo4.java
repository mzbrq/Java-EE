import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo4 {
    private static AtomicInteger count = new AtomicInteger(0);//初始值:0
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                //count 自增: 通过 CAS 方式实现，具有原子性
                count.getAndIncrement();
            }
        });

        Thread t2 = new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                count.getAndIncrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);

    }
}
