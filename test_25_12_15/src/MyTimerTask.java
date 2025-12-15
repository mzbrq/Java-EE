public class MyTimerTask implements Comparable<MyTimerTask>{
    private long time;
    Runnable runnable;

    public long getTime() {
        return this.time;
    }

    MyTimerTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.time = (long) (System.currentTimeMillis() + delay);
    }

    public void run() {
        this.runnable.run();
    }


    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }
}
