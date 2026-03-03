package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IODemo10 {
    public static void main(String[] args) throws IOException {
        try(InputStream inputStream = new FileInputStream("./test.txt")) {
            while(true) {
                byte[] buffer = new byte[1024];
                int n = inputStream.read(buffer);
                if (n == -1) {
                    //文件读取完毕
                    break;
                }

                String s = new String(buffer, 0, n);
                System.out.println(s);
            }
        }
    }
}
