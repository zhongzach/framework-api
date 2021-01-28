package org.zach.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/22 5:40 下午
 */
public class Client {

    private static final String HOST = "localhost";

    private static final int PORT = 9000;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(HOST, PORT);
        System.out.println("请输入发送的消息：");
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            if (scanner.hasNext()) {
                String msg = scanner.next();
                System.out.println(msg);
                clientSocket.getOutputStream().write(msg.getBytes());
                clientSocket.getOutputStream().flush();
            }
        }
    }
}
