import java.util.PriorityQueue;

public class MyTimer {
    private Thread thread;
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<>(100);
    private final Object locker = new Object();

    MyTimer() {
        this.thread = new Thread(()-> {
            while (true) {
                try {
                    synchronized (locker) {
                        while (this.queue.isEmpty()) {
                            locker.wait();
                        }

                        MyTimerTask task = this.queue.peek();
                        long curTime = System.currentTimeMillis();
                        if (curTime >= task.getTime()) {
                            //执行时间到
                            this.queue.poll();
                            task.run();
                        } else {
                            //等待执行时间到达 --> 绝对执行时间 - 当前时间 = 剩余时间
                            locker.wait(task.getTime() - curTime);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    //添加任务
    public void MySchedule(Runnable runnable, long delay) {
        synchronized (locker) {
            MyTimerTask task = new MyTimerTask(runnable, delay);
            this.queue.offer(task);
            locker.notify();
        }
    }
}
