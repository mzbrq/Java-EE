package Demo1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    //构造 DatagramSocket 对象指定服务器IP和Port
    private DatagramSocket socket;
    private String ServerIP;
    private int ServerPort;


    public UdpEchoClient(String serverIP, int serverPort) throws SocketException {
        //客户端通常不用手动指定端口号，让系统自行分配
        this.socket = new DatagramSocket();
        this.ServerIP = serverIP;
        this.ServerPort = serverPort;
    }

    //客户端启动逻辑，以及构造发送请求，接收处理响应逻辑
    private void start() throws IOException {
        System.out.println("===== 客户端启动！！！ ======");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //1. 读取控制台输入的请求，并解析
            System.out.print("->");
            if (!scanner.hasNext()) {
                break;
            }
            String request = scanner.nextLine();
            //2. 构造数据报请求发送给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(this.ServerIP), this.ServerPort);
            this.socket.send(requestPacket);
            //3. 等待接收服务器返回的响应,并解析
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            this.socket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            //4. 把响应显示到控制台上
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        //IP"127.0.0.1" 是一个回环IP，表示本机，我们现在是在同一台主机上操作的
        UdpEchoClient UdpClient = new UdpEchoClient("127.0.0.1", 9090);
        UdpClient.start();
    }

}
