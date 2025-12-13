import java.util.concurrent.Semaphore;

public class TestDemo2 {
    public static void main(String[] args) throws InterruptedException {
        //设置可用资源只有一个
        Semaphore semaphore = new Semaphore(1);

        //可用资源为0，再执行P操作，就会进入阻塞，直到 执行V操作

        semaphore.acquire();
        System.out.println("执行P操作");
        semaphore.release();

        semaphore.acquire();
        System.out.println("执行P操作");

        //此时没有可用资源，执行P操作，会进入阻塞
        semaphore.acquire();
        System.out.println("执行P操作");

    }
}
