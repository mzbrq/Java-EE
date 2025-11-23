package MyThread;

public class Test {
    public static void main(String[] args) {
        MyBlockingQueueDemo1 queue = new MyBlockingQueueDemo1(1000);
        //生产者
        Thread t1 = new Thread(()-> {
            int n = 1;
            while (true) {
                try {
                    queue.put(n + "");
                    System.out.println("生产元素" + n);
                    n++;
                    //Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //消费者
        Thread t2 = new Thread(()-> {
            while(true) {
                try {
                    String ret = queue.take();
                    System.out.println("消费元素" + ret);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        t1.start();
        t2.start();
    }
}
