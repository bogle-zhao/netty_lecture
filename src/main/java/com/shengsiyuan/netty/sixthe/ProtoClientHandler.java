package com.shengsiyuan.netty.sixthe;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午8:46
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
            .setName("张三")
            .setAge(20)
            .setAddress("北京")
            .build();
        ctx.pipeline().writeAndFlush(person);

    }
}
