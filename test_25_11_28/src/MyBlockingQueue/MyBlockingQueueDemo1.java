package MyBlockingQueue;

public class MyBlockingQueueDemo1 {
    private String[] queue;
    private int size;
    private int head;
    private int tail;
    private Object locker = new Object();


    public MyBlockingQueueDemo1(int capacity) {
        this.queue = new String[capacity];
    }

    public MyBlockingQueueDemo1() {
        this.queue = new String[100];
    }
    //入队列
    public void put(String elem) throws InterruptedException {
        synchronized (locker) {
            //判满
            while (this.size >= this.queue.length) {
                //队列满了，不能入队列，进入阻塞
                locker.wait();
            }

            //执行入队列
            this.queue[this.tail] = elem;
            this.tail++;

            if (this.tail >= this.queue.length) {
                this.tail = 0;
            }

            locker.notify();

            this.size++;
        }
    }


    //出队列
    public String take() throws InterruptedException {
        String ret = null;

        synchronized (locker) {
            //判空
            while (this.size == 0) {
                //队列为空，不能出队列，进入阻塞
                locker.wait();
            }

            //执行入队列
            ret = this.queue[this.head];
            this.head++;

            if (this.head >= this.queue.length) {
                this.head = 0;
            }

            locker.notify();

            this.size--;
        }
        return ret;
    }


}
