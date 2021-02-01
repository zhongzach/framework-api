package org.zach.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/2/1 9:30 上午
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端] " + channel.remoteAddress() + " 上线了 "
                + df.format(new Date()));
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress() + "连接当前服务器");
        System.out.println("当前在线人数：" + channelGroup.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端] " + channel.remoteAddress() + " 下线了 "
                + df.format(new Date()));
        System.out.println(channel.remoteAddress() + "与当前服务器断开连接");
        System.out.println("当前在线客户端人数：" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(c -> {
            if (c != channel) {
                c.writeAndFlush("[客户端] " + channel.remoteAddress() + " 发送了消息："
                        + msg);
            } else {
                c.writeAndFlush("[我] 发送了消息：" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
