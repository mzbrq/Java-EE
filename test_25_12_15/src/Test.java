public class Test {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.MySchedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("3000ms 后，执行");
            }
        }, 3000);

        myTimer.MySchedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2000ms 后，执行");
            }
        }, 2000);

        myTimer.MySchedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("1000ms 后，执行");
            }
        }, 1000);


    }
}
