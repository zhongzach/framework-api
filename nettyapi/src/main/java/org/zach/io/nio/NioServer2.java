package org.zach.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/29 10:57 上午
 */
public class NioServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9000));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        // 给服务端Socket绑定接入时间
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start success...");

        for (; ; ) {
            // 阻塞，等待事件被触发
            selector.select();
            // 获取触发的socket key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 判断当前key是由于什么事件被触发的
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功...");
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = client.read(byteBuffer);
                    if (len > 0) {
                        System.out.println("接收到服务端信息：" + new String(byteBuffer.array()));
                    } else if (len == -1) { // 如果客户端断开连接，关闭Socket
                        System.out.println("客户端断开连接");
                        client.close();
                    }
                }
                // 将本次处理的Key移除，防止重复处理
                iterator.remove();
            }
        }

    }

}
