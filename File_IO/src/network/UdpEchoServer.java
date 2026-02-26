package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoServer {
    private DatagramSocket socket;

    public UdpEchoServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    //服务器的启动逻辑
    public void start() throws IOException {
        System.out.println("====== 服务器启动!!! ======");

        //注意：服务器一般都是 7*24小时运行
        while(true) {
            //每次循环处理一个 请求-响应

            //1. 读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
                //byte[] --> String，便于后续逻辑处理
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            //2. 通过请求计算响应(回显服务器这里没有复杂的业务逻辑)
            String response = process(request);

            //3. 把响应返回给客户端：通过 String 响应，构造 DatagramPacket数据报并返回。把请求中客户端的IP和端口作为目的IP和目的端口
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);

            //4. 打印日志: 打印 客户端IP 和 客户端端口号，请求 和 响应
            System.out.printf("[%s:%d] request: %s, response: %s\n", requestPacket.getAddress().toString(),
                    requestPacket.getPort(), request, response);

        }

    }

    //计算响应的逻辑
    private String process(String request) {
        //直接返回request即可，没有复杂的业务逻辑
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpServer = new UdpEchoServer(9090);
        udpServer.start();
    }

}
