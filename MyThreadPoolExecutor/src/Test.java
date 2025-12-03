
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //创建固定四个线程的线程池
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(4);

        //创建任务并加入任务队列中，让线程池中的四个线程执行
        for (int i = 0; i < 1000; i++) {
            int n = i;
            executor.submit(() -> System.out.println("执行任务" + n + "，当前线程为：" + Thread.currentThread().getName()));
        }
    }
}
