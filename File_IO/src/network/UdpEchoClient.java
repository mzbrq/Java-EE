package network;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    private DatagramSocket socket;
    private String serverIP;
    private int serverPort;

    //保存服务器的IP 和 Port
    public UdpEchoClient(String serverIP, int serverPort) throws SocketException {
        this.socket = new DatagramSocket();//这里不用手动指定port，让客户端主机自己分配，避免冲突
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    //实现客户端启动逻辑
    private void start() throws IOException {
        System.out.println("====== 客户端启动!!! ======");

        while(true) {
            System.out.print("->");
            Scanner scanner = new Scanner(System.in);

            //1. 读取控制台请求信息并解析
            if (!scanner.hasNext()) {
                break;
            }
            String request = scanner.nextLine();
            //2. 把请求发送给服务器:指定服务器 IP 和 Port
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(this.serverIP), this.serverPort);
            socket.send(requestPacket);
            //3. 等待服务器返回响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);
            //4. 把响应打印到控制台上
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);
        }

    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpClient = new UdpEchoClient("127.0.0.1", 9090);
        udpClient.start();
    }

}
