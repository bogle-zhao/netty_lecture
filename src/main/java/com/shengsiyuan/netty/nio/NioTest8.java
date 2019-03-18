package com.shengsiyuan.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer,做参数很有用，
 * 可以将一个读写buffer转换为一个只读buffer，
 * 不能将只读buffer转换为读写buffer
 * @author bogle
 * @version 1.0 2019/3/17 下午10:19
 */
public class NioTest8 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());//可读写的buffer：class java.nio.HeapByteBuffer
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();//生成只读buffer：class java.nio.HeapByteBufferR
        System.out.println(readonlyBuffer.getClass());

    }
}
