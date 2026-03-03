package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IODemo8 {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = new FileInputStream("./test.txt")) {
            //每次次读取一个字节
            while (true) {
                int n = inputStream.read();
                if (n == -1) {
                    break;
                }
                System.out.printf("%x ", n);
            }
        }
    }
}
