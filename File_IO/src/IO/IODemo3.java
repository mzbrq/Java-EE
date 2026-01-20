package IO;

import java.io.File;

public class IODemo3 {
    public static void main(String[] args) throws InterruptedException {
        File file = new File("./Test.java/");

        //删除文件
        /*boolean ret = file.delete();
        System.out.println(ret);*/

        //代码执行结束退出时，删除文件
        file.deleteOnExit();

        //休眠 5 秒
        Thread.sleep(5000);

    }
}
