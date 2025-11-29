package MyTimer;

public class Test {
    public static void main(String[] args) {
        MyTimerDemo1 myTimerDemo1 = new MyTimerDemo1();

        myTimerDemo1.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("3000ms后执行");
            }
        }, 3000);

        myTimerDemo1.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2000ms后执行");
            }
        }, 2000);

        myTimerDemo1.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("1000ms后执行");
            }
        }, 1000);

        myTimerDemo1.schedule(()-> {
            System.out.println("5000ms后执行");
        }, 5000);
    }
}
