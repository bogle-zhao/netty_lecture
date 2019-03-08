package com.shengsiyuan.netty.seondexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author bogle
 * @version 1.0 2018/9/25 下午10:29
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + ", " + msg);
        channel.writeAndFlush("from server: " + UUID.randomUUID());
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
