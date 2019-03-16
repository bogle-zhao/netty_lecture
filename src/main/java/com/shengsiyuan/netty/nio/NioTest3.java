package com.shengsiyuan.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author bogle
 * @version 1.0 2019/3/16 下午2:27
 */
public class NioTest3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);//分配内存

        byte[] messages = "hello word welcom, nihao".getBytes();

        for (int i = 0; i < messages.length; ++i) {
            byteBuffer.put(messages[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();

    }
}
