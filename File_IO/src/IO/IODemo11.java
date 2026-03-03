package IO;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IODemo11 {
    public static void main(String[] args) throws IOException {
        //清空文件，再写入
        try(OutputStream outputStream = new FileOutputStream("./test.txt")) {
            byte[] buffer = new byte[] {97, 98, 99, 100, 101, 102};
            outputStream.write(buffer);

        }
    }
}
