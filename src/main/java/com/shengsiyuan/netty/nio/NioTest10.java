package com.shengsiyuan.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *
 * MappedByteBuffer 文档
 *
 * MappedByteBuffer 是一个直接内存，它的内容是一个文件的内存映射区域
 * MappedByteBuffer的实现是FileChannel.map
 *
 * 将文件映射到内存当中
 * @author bogle
 * @version 1.0 2019/3/18 下午5:36
 */
public class NioTest10 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

//        FileChannel.MapMode mode：模式，读，写，读写
//        long position:起始位置
//        long size：映射大小
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');
        randomAccessFile.close();


    }
}
