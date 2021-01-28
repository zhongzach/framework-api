package org.zach.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/28 4:41 下午
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);

        for (; ; ) {
            System.out.println("等待客户端连接...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接：" + clientSocket.getLocalAddress());
            handler(clientSocket);
        }

    }

    public static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("start read client msg...");
        int len = socket.getInputStream().read(bytes);
        System.out.println("read success...");
        if (len != -1) {
            System.out.println("接收到客户端数据:" + new String(bytes, 0, len));
        }
        socket.getOutputStream().write("server reading end...".getBytes());
        socket.getOutputStream().flush();
    }

}
