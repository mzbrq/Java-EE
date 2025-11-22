package MyThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(100);
        bq.put("aaa");
        bq.put("bbb");

        String s1 = bq.take();
        System.out.println(s1);

        String s2 = bq.take();
        System.out.println(s2);

//        String s3 = bq.take();
//        System.out.println(s3);
    }
}
