import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPoolExecutor {
    //创建链表存放线程
    private List<Thread> threadList = new ArrayList<>();
    //创建一个阻塞队列，存放任务
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

    public MyThreadPoolExecutor(int n) {
        //循环创建 n 个线程
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(()-> {
                //把阻塞队列中的任务不停取出并执行
                while (true) {
                    try {
                        Runnable task = queue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            threadList.add(t);
        }
    }

    //提供 submit() 能够添加新任务
    public void submit(Runnable runnable) throws InterruptedException {
        queue.put(runnable);
    }
}
