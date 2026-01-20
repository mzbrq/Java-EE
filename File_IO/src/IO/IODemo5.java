package IO;

import java.io.File;

public class IODemo5 {
    public static void main(String[] args) {
        File file = new File("./aaa");
        File file1 = new File("./bbb/ccc/ddd");
        //创建单级目录
        boolean ret = file.mkdir();
        System.out.println("ret + " + ret);

        boolean ret1 = file1.mkdirs();
        System.out.println("ret1 + " + ret1);


    }
}
