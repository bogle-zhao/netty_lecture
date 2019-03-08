package com.shengsiyuan.netty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author bogle
 * @version 1.0 2018/10/11 下午10:03
 */
public class IdleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 用户事件被触发时的调用
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = "";
            switch (event.state()) {
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + " 超时事件：" + eventType);
            ctx.channel().close();
        }
    }
}
