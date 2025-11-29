package MyTimer;

public class MyTimerTask implements Comparable<MyTimerTask>{

    //规定执行时间
    private long time;
    //需要执行的代码
    private Runnable runnable;

    public long getTime() {
        return time;
    }

    public MyTimerTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        //计算代码执行的绝对时间
        this.time = System.currentTimeMillis() + delay;
    }

    //指定比较规则
    @Override
    public int compareTo(MyTimerTask o) {
        //时间短的优先执行
        return (int) (this.time-o.time);
    }

    public void run() {
        runnable.run();
    }

}
