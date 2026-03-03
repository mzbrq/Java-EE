package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IODemo12 {
    public static void main(String[] args) throws IOException {
        //追加写文件
        try(OutputStream outputStream = new FileOutputStream("./test.txt", true)) {
            byte[] buffer = new byte[] {97, 98, 99, 100, 101, 102};
            outputStream.write(buffer);
        }

    }
}
