package com.shengsiyuan.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author bogle
 * @version 1.0 2019/3/16 下午1:48
 */
public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer ibf = IntBuffer.allocate(10);//分配缓冲区为10
        for (int i = 0; i < ibf.capacity(); ++i) {
            int randomNumber = new SecureRandom().nextInt(20);
            ibf.put(randomNumber);
        }
        ibf.flip();//翻转，将写流转换为读流
        while (ibf.hasRemaining()) {
            System.out.println(ibf.get());
        }
    }
}

/*
java.io最核心的一个概念是流(Stream),面向流的编程，java中，一个流要么是输入流，要么是输出流，不可能同时既是输入流，又是输出流
java.nio中有三个核心概念：Selector,Channel与Buffer。在java.nio中，我们是面向块(block)或是缓冲区(buffer)编程区的。Buffer本身就是一块内存，底层实现是，它实际上是一个数组，数据的读，写都是通过buffer来实现的。

除了数组之外，Buffer还提供了对于数据的结构化访问方式，并且可以追踪到系统的读写过程。
Java中的8种原生数据类型都有各自对应的Buffer类型，如IntBuffer,LongBuffer,ByteBuffer以及CharBuffer等等

Channel指的可以想其写入数据或是从中读取数据的对象，它类似于java.io中的Stream。

所有数据的读写都是通过Buffer来进行的，永远不会出现直接像Channel写入数据的情况，或是直接从Channel读取数据的情况

与Stream不同的是，Channel是双向的，一个流只可能是InputStream或是OutputStream，Channel打开后则可以进行读取，写入或是读写

由于Channel是双向的，因此它能更好反应出底层操作系统的真是情况，底层操作系统的通道就是双向的。

Buffer属性信息：
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;

 */
