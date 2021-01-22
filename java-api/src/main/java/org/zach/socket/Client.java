package org.zach.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/22 5:40 下午
 */
public class Client {

    private static final String HOST = "localhost";

    private static final int PORT = 7777;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(HOST, PORT);

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("socket 连接... ".getBytes(StandardCharsets.UTF_8));

        outputStream.flush();

        outputStream.close();

        socket.close();


    }

}
