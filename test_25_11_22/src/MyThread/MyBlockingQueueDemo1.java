package MyThread;

import static java.lang.System.exit;

public class MyBlockingQueueDemo1 {
    //默认元素为String类型
    private String[] array;
    private int size;
    private int head;
    private int tail;
    Object locker = new Object();

    public MyBlockingQueueDemo1(int capacity) {
        array = new String[capacity];
    }

    //入队列
    public void put(String elem) throws InterruptedException {
        synchronized (locker) {
            //判断队列满
            if (size >= array.length) {
                locker.wait();
            }

            //进行入队列
            array[tail] = elem;
            tail++;
            if (tail >= array.length) {
                tail = 0;
            }
            size++;

            //入队列成功，队列不为空
            locker.notify();
        }
    }

    //出队列
    public String take() throws InterruptedException {
        String ret = null;
        synchronized (locker) {
            //判断队列是否为空
            if (size == 0) {
                locker.wait();
            }

            //出队列
            ret = array[head];
            head++;
            if (head >= array.length) {
                head = 0;
            }

            size--;

            //出队列成功，队列不满
            locker.notify();
        }
        return ret;
    }

    public static void main(String[] args) {
        /*MyBlockingQueueDemo1 bq = new MyBlockingQueueDemo1(5);

        bq.put("aaa");
        bq.put("bbb");
        bq.put("ccc");
        bq.put("ddd");

        String s1 = bq.take();
        System.out.println(s1);

        String s2 = bq.take();
        System.out.println(s2);

        String s3 = bq.take();
        System.out.println(s3);

        String s4 = bq.take();
        System.out.println(s4);

//        String s5 = bq.take();
//        System.out.println(s5);

        bq.put("aaa");
        bq.put("bbb");
        bq.put("ccc");
        bq.put("ddd");
        bq.put("ddd");*/

    }

}
