package IO;

import java.io.File;
import java.io.IOException;

public class IODemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("./Test.java/");
        System.out.println(file.exists());//判断文件是否存在
        System.out.println(file.isFile());//判断是否是文件
        System.out.println(file.isDirectory());//判断是否是目录

         boolean ret = file.createNewFile();//在当前目录创建文件
        System.out.println("ret = " + ret);

        System.out.println(file.exists());//判断文件是否存在
        System.out.println(file.isFile());//判断是否是文件
        System.out.println(file.isDirectory());//判断是否是目录
    }
}
