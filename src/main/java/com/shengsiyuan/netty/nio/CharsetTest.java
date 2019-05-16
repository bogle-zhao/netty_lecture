package com.shengsiyuan.netty.nio;

import io.netty.buffer.ByteBufUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTest {

    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest_in.txt";
        String outFile = "NioTest_out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset charset = Charset.forName("utf-8");
        //decoder:将字节数组转换为字符串
        CharsetDecoder decoder = charset.newDecoder();
        //encoder:将字符串转换为自己数组
        CharsetEncoder encoder = charset.newEncoder();
        //将内存映射的文件解码为CharBuffer
        CharBuffer charBuffer = decoder.decode(inputData);

        ByteBuffer outputData = encoder.encode(charBuffer);

        outFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

        System.out.println("===================================");
        Charset.availableCharsets().forEach((k, v) -> {
            System.out.println(k + " , " + v);
        });

    }
}
