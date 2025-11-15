package MyThread;

class SingletonLazy {
    private volatile static SingletonLazy instance;
    private static Object locker = new Object();

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }

    private SingletonLazy() {}

}

public class MyThreadDemo1 {
    public static void main(String[] args) {
        SingletonLazy s1 = SingletonLazy.getInstance();
        SingletonLazy s2 = SingletonLazy.getInstance();

        System.out.println(s1 == s2);
    }
}
