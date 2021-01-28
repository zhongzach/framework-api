package org.zach.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/28 5:19 下午
 */
public class NioServer {

    public static List<SocketChannel> clientList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("nio服务器启动成功...");

        for (;;) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                System.out.println("客户端连接成功...");
                socketChannel.configureBlocking(false);
                clientList.add(socketChannel);
            }

            Iterator<SocketChannel> iter = clientList.iterator();
            while (iter.hasNext()) {
                SocketChannel client = iter.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int len = client.read(byteBuffer);
                if (len > 0) {
                    System.out.println("接收到客户端信息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    iter.remove();
                }
            }
        }
    }

}
