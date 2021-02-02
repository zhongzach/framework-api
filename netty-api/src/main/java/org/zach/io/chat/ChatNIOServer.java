package org.zach.io.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/2/1 4:46 下午
 */
public class ChatNIOServer {

    public static List<SocketChannel> channels = new ArrayList<>();

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 设置成非阻塞
        serverSocketChannel.configureBlocking(false);
        // 创建IO多路复用器（可以理解成Reactor）
        Selector selector = Selector.open();
        // 首先将服务器本身的channel注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("聊天室启动成功...");

        for (; ; ) {
            selector.select(); // 等待响应事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 通知所有客户端，有新客户端上线
                    channels.forEach(ch -> {
                        try {
                            String msg = "[客户端] " + socketChannel.getRemoteAddress()
                                    + " 上线了 " + df.format(new Date());
                            ch.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    System.out.println(socketChannel.getRemoteAddress() + "上线了...");
                    // 通知完毕后将当前连接的客户端加入到集合中
                    channels.add(socketChannel);
                    System.out.println("当前在线人数：" + channels.size());
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = channel.read(byteBuffer);
                    if (len > 0) {
                        channels.forEach(ch -> {
                            String msg = "";
                            if (ch != channel) {
                                try {
                                    msg = "[客户端] " + channel.getRemoteAddress()
                                            + " 发送了消息: " + new String(byteBuffer.array());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                msg = "[自己] 发送了消息：" + new String(byteBuffer.array());
                            }
                            try {
                                ch.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else if (len == -1) {
                        System.out.println("客户端断开连接...");
                        channels.remove(channel);
                        channel.close();
                    }
                }
                iterator.remove();
            }


        }


    }

}
