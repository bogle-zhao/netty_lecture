package com.shengsiyuan.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author bogle
 * @version 1.0 2019/3/17 下午3:01
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(500000000L);
        buffer.putDouble(14.3446546);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
