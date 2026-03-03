package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IODemo9 {
    public static void main(String[] args) throws IOException {
        try(InputStream inputStream = new FileInputStream("./test.txt")) {
            while(true) {
                byte[] buffer = new byte[1024];
                int n = inputStream.read(buffer);
                if (n == -1) {
                    break;
                }

                for (int i = 0; i < n; i++) {
                    System.out.printf("%x ", buffer[i]);
                }
            }
        }
    }
}
