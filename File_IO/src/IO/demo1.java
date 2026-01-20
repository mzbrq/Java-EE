package IO;

import java.io.File;
import java.io.IOException;

public class demo1 {
    public static void main(String[] args) throws IOException {
        //File file = new File("D:/code_java/Java-EE/MyTimer/src/Test.java/");
        File file = new File("./Test.java/");
        System.out.println(file.exists());
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getCanonicalPath());
    }
}
