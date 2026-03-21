package Demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {
    private ServerSocket serverSocket;

    public TcpEchoServer(int port) throws IOException {
        //手动指定端口号
        serverSocket = new ServerSocket(port);
    }

    //实现服务器启动逻辑
    private void start() throws IOException {
        System.out.println("===== Tcp回显服务器启动！！！ =====");

        while (true) {
            //1. 调用 accept() 与客户端正式建立连接通信，clientSocket保存对端的信息
            Socket clientSocket = this.serverSocket.accept();
            //2. 创建 processConnection()，处理一次连接中的，多次 请求-响应交互
                //需要让多个客户端能同时访问服务器，需要每个客户端都提供一个线程服务
            /*Thread t = new Thread(() -> {
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();*/
            //使用线程池：避免频繁创建销毁线程
            ExecutorService pool = Executors.newCachedThreadPool();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        processConnection(clientSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端启动！！！\n", clientSocket.getInetAddress(),
                clientSocket.getPort());
        //1. 循环读取客户端请求，并解析
        try(InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream()) {
            while (true) {
                //1. 读取请求，并解析. 注意: next()会读到空白符结束，所以需求 需要以空白符作为结束标志
                Scanner scanner = new Scanner(inputStream);
                if (!scanner.hasNext()) {
                    System.out.printf("[%s:%d] 客户端下线\n", clientSocket.getInetAddress(),
                            clientSocket.getPort());
                    break;
                }
                String request = scanner.next();

                //2. 调用 process() 计算响应
                String response = process(request);

                //3. 把响应返回给客户端
                    //方式一: 不利于给返回的响应结尾添加 \n
                //outputStream.write(response.getBytes(), 0, response.getBytes().length);
                    //方式二: 使用 PrintfWrite 包装 outputStream，调用 println(response)返回带有'\n'换行的响应
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(response);
                    //注意要使用 flush() 冲刷 PrintWriter内置缓存区，确保数据被传输
                printWriter.flush();

                //4. 打印日志
                System.out.printf("[%s:%d] req: %s, res: %s\n", clientSocket.getInetAddress(),
                        clientSocket.getPort(),
                        request, response);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            clientSocket.close();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        // 1. 创建服务器实例，监听 9090 端口
        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);
        // 2. 启动服务器主逻辑
        tcpEchoServer.start();
    }
}
