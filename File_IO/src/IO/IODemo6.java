package IO;

import java.io.File;

public class IODemo6 {
    public static void main(String[] args) {
        File src = new File("./Test1.java");
        File dest = new File("./Test2.java");

        //重名名文件
        boolean ret = src.renameTo(dest);
        System.out.println(ret);
    }
}
