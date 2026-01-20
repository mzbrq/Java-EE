package IO;

import java.io.File;

public class IODemo7 {
    public static void main(String[] args) {
        File src = new File("./Test2.java");
        File dest = new File("./aaa/Test2.java");

        //移动文件
        boolean ret = src.renameTo(dest);
        System.out.println("ret: " + ret);

    }
}
