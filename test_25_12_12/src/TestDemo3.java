import java.util.concurrent.Semaphore;

public class TestDemo3 {
    private static int count;

    public static void main(String[] args) throws InterruptedException {
        //设置可用资源为：1
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                try {
                    //进行 acquire
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count++;

                //进行release
                semaphore.release();
            }
        });

        Thread t2 = new Thread(()-> {
            for (int i = 0; i < 50000; i++) {
                try {
                    //进行 acquire
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count++;

                //进行release
                semaphore.release();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }
}
