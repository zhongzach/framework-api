package org.zach.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.zach.netty.base.NettyClientHandler;

import java.util.Scanner;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/2/1 12:15 下午
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //加入处理器
                            channel.pipeline().addLast("encoder", new StringEncoder());
                            channel.pipeline().addLast("decoder",new StringDecoder());
                            channel.pipeline().addLast(new ChatClientHandler());
                        }
                    });
            System.out.println("netty client start");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();
            System.out.println("========== " + channel.localAddress() + " ==========");
            Scanner scanner = new Scanner(System.in);
            for (;;) {
                if (scanner.hasNextLine()) {
                    String msg = scanner.nextLine();
                    channel.writeAndFlush(msg);
                }
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
