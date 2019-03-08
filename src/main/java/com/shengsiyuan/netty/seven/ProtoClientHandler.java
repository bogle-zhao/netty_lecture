package com.shengsiyuan.netty.seven;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午8:46
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Random random = new Random();
        MyDataInfo.MyMessage myMessage = null;
        int randomInt = random.nextInt(3);

        if (randomInt == 0) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setPerson(MyDataInfo.Person.newBuilder()
                               .setName("张三")
                               .setAge(20)
                               .setAddress("北京")
                               .build())
                .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                .build();

        } else if (randomInt == 1) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("dog")
                            .setAge(2)
                            .build())
                .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                .build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("cat")
                            .setCity("上海")
                            .build())
                .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                .build();
        }


        ctx.pipeline().writeAndFlush(myMessage);

    }

    @Override protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }
}
