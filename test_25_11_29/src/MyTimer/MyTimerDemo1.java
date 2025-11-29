package MyTimer;

import java.util.PriorityQueue;

public class MyTimerDemo1 {
    //负责扫描 和 执行任务的线程
    Thread t;
    //储存任务的优先级队列
    PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    private final Object locker = new Object();


    //安排任务进入任务队列
    public void schedule(Runnable runnable, long delay) {
        synchronized (locker) {
            MyTimerTask task = new MyTimerTask(runnable, delay);
            queue.offer(task);
            locker.notify();
        }
    }

    //构造方法
    public MyTimerDemo1() {
        t = new Thread(()->{
            //线程扫描堆顶
            while (true) {
                synchronized (locker) {
                    try {
                        while (queue.isEmpty()) {
                            locker.wait();
                        }

                        MyTimerTask task = queue.peek();
                        if (task.getTime() <= System.currentTimeMillis()) {
                            //到达执行时间，执行代码
                            queue.poll();
                            task.run();
                        } else {
                            locker.wait(task.getTime()-System.currentTimeMillis());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }
}
