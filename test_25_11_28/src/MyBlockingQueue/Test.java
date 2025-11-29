package MyBlockingQueue;

public class Test {
    public static void main(String[] args) {
        MyBlockingQueueDemo1 queueDemo1 = new MyBlockingQueueDemo1(5);
        /*queueDemo1.put("aaa");
        queueDemo1.put("bbb");
        queueDemo1.put("ccc");

        String s1 = queueDemo1.take();
        System.out.println(s1);

        String s2 = queueDemo1.take();
        System.out.println(s2);

        String s3 = queueDemo1.take();
        System.out.println(s3);*/


        //模拟实现 生产者消费者模型

        //生产者
        Thread t1 = new Thread(()-> {
            int n = 0;

            while (true) {
                try {
                    queueDemo1.put(n + "");
                    System.out.println("生产元素：" + n);
                    //Thread.sleep(500);
                    n++;
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(()-> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    String ret = queueDemo1.take();
                    System.out.println("消费元素：" + ret);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();


    }
}
