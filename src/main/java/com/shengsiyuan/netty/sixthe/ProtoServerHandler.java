package com.shengsiyuan.netty.sixthe;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午8:40
 */
public class ProtoServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
