package Demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class TcpEchoClient {
    private Socket socket;

    public TcpEchoClient(String serverIP, int serverPort) throws IOException {
        //实例化Socket 指定服务器 IP 和 Port
        //TCP 是有连接的，会保存对端信息，不需要额外变量保存 IP 和 Port
        this.socket = new Socket(serverIP, serverPort);
    }

    //客户端启动逻辑，以及 获取发送请求 和 接收处理响应 的逻辑
    private void start() throws IOException {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Scanner scannerConsole = new Scanner(System.in);
            Scanner scannerNetwork = new Scanner(inputStream);
            PrintWriter writer = new PrintWriter(outputStream)) {
            System.out.printf("[%s:%d] TCP客户端启动！！！\n", socket.getInetAddress(), socket.getPort());
            while(true) {
                //1. 从控制台读取请求
                System.out.print("->");
                if (!scannerConsole.hasNext()) {
                    break;
                }
                String request = scannerConsole.next();

                //2. 把请求发送给服务器,使用 Println() 发送结尾带有 空白符 的请求，服务器使用 next() 读取
                writer.println(request);
                    //冲刷PrintWriter内置的缓存区，确保请求被传输
                writer.flush();

                //3. 等待读取服务器返回的响应，服务器返回结束标志为空白符的响应，客户端使用next()读取
                String response = scannerNetwork.next();

                //4. 把响应显示到控制台
                System.out.println(response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            socket.close();
        }

    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient("127.0.0.1", 9090);
        tcpEchoClient.start();
    }
}
