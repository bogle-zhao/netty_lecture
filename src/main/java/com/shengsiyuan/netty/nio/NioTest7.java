package com.shengsiyuan.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author bogle
 * @version 1.0 2019/3/17 下午3:08
 */
public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer slice = buffer.slice(); // slice 新生成的buffer和原来的buffer是引用，修改slice的buffer会影响到原生的buffer

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());;
        }
    }
}
