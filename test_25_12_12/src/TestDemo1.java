import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestDemo1 {
    public static void main(String[] args) throws InterruptedException {
        //有10个线程
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int id = i;
            Thread t = new Thread(()-> {
                //随机 1 ~ 5ms
                Random random = new Random();
                int time = (random.nextInt(5)+1) * 1000;
                System.out.println("线程" + id + "开始下载");

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + id + "下载完成");
                //使用 countDown() 告知 CountDownLatch 执行结束
                countDownLatch.countDown();
            });

            t.start();
        }

        countDownLatch.await();
        System.out.println("全部下载完成");

    }
}
