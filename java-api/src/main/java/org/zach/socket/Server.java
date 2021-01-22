package org.zach.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/22 5:26 下午
 */
public class Server {

    private static final int PORT = 7777;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(PORT);

            socket = serverSocket.accept();

            inputStream = socket.getInputStream();

            byte[] bytes = new byte[1024];

            int len;

            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0 ,len , StandardCharsets.UTF_8));
            }
        } finally {
            inputStream.close();
            socket.close();
            serverSocket.close();
        }

    }

}
