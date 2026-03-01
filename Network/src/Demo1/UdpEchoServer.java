package Demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoServer {
    //创建 DatagramSocket 对象，对数据进行发送和接收
    private final DatagramSocket socket;


    public UdpEchoServer(int port) throws SocketException {
        //手动指定服务器端口，让服务器启动立即绑定需要进行网络传输的进程
        this.socket = new DatagramSocket(port);
    }


    //服务器启动逻辑，以及处理请求逻辑
    private void start() throws IOException {
        System.out.println("===== 服务器启动！！！ =====");
        //每次循环都是 处理 请求-响应 的过程
        while(true) {
            //1. 接收请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            this.socket.receive(requestPacket);
            //2. 处理请求，计算响应
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            String response = process(request);
            //3. 把响应返回给客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            this.socket.send(responsePacket);
            //4. 打印日志:"[地址:端口号] 请求: , 响应: "
            System.out.printf("[%s:%d] req:%s, res:%s\n", requestPacket.getAddress().toString(),
                    requestPacket.getPort(),
                    request, response);
        }
    }

    //通过process()，计算响应(回显服务器，这里直接返回 request 即可)
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpServer = new UdpEchoServer(9090);
        udpServer.start();
    }

}
