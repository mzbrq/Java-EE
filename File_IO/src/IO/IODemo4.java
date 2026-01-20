package IO;

import java.io.File;
import java.util.Arrays;

public class IODemo4 {
    public static void main(String[] args) {
        File file = new File(".");
        //列出目录中的文件
        String[] files = file.list();

        //数组的元素也是 File类型
        File[] files1 = file.listFiles();

        System.out.println(Arrays.toString(files));
        System.out.println(Arrays.toString(files1));

    }
}
