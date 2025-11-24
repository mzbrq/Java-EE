import java.util.PriorityQueue;

//描述任务的类
class MyTimerTask implements Comparable<MyTimerTask>{
    //毫秒级时间戳
    private long time;
    //执行的代码
    Runnable runnable;

    //获取时间
    public long getTime() {
        return time;
    }

    //构造方法
    public MyTimerTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.time = System.currentTimeMillis() + delay;
    }

    public void run() {
        runnable.run();
    }

    //任务存储在优先级队列，需要指定比较规则
    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }

}

//表示计时器
class MyTimer {
    //扫描队列，执行任务的线程
    private Thread t;

    //存储任务的优先级队列
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    //创建锁对象
    Object locker = new Object();

    //schedule类，安排任务
    public void schedule(Runnable runnable, long delay) {
        synchronized (locker) {
            //创建任务
            MyTimerTask task = new MyTimerTask(runnable, delay);
            //入队列
            queue.offer(task);
            locker.notify();
        }

    }

    //实现定时器的构造方法
    public MyTimer() {
        t = new Thread(()-> {
            //循环查看队头元素
            while (true) {
                synchronized (locker) {
                    try {
                        //判断队列是否为空
                        while (queue.isEmpty()) {
                            //continue;会反复需要，导致
                            //使用wait()阻塞等待，其他线程也能加锁，
                            locker.wait();
                        }

                        //查看队头元素
                        MyTimerTask task = queue.peek();

                        //获取当前时间与任务执行时间比较
                        long curTime = System.currentTimeMillis();
                        if (curTime >= task.getTime()) {
                            //执行任务, 并删除队头任务
                            queue.poll();
                            task.run();
                        } else {
                            //不能使用continue 反复循环,会造成 "忙等"
                            //使用 有限制时间的wait()
                            locker.wait(task.getTime()-curTime);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //启动线程
        t.start();
    }

}


public class Test {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();

        //主线程添加任务
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("3000ms,执行");
            }
        }, 3000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2000ms,执行");
            }
        }, 2000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("1000ms,执行");
            }
        }, 1000);


        System.out.println("hello main");


        //lambda表达式
        myTimer.schedule(()-> {
            System.out.println("4000ms,执行");
        }, 4000);

    }
}
