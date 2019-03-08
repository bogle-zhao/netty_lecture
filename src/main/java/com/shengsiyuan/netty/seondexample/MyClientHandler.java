package com.shengsiyuan.netty.seondexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author bogle
 * @version 1.0 2018/9/25 下午10:47
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + ", client output" + msg);
        ctx.writeAndFlush("from client: " + LocalDateTime.now());
    }

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端的问候!");
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
