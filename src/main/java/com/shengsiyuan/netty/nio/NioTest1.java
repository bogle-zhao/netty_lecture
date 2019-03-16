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
Java中的8种原生数据类型都有各自对应的Buffer类型，如IntBuffer,LongBuffer,ByteBuffer以及CharBuffer等等，但是美BooleanBuffer类型。所以只有7中原生类型的Buffer

Channel指的可以想其写入数据或是从中读取数据的对象，它类似于java.io中的Stream。

所有数据的读写都是通过Buffer来进行的，永远不会出现直接像Channel写入数据的情况，或是直接从Channel读取数据的情况

与Stream不同的是，Channel是双向的，一个流只可能是InputStream或是OutputStream，Channel打开后则可以进行读取，写入或是读写

由于Channel是双向的，因此它能更好反应出底层操作系统的真是情况，底层操作系统的通道就是双向的。


关于NIO Buffer中的3个重要状态属性的含义，limit，position，capacity
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;

 Buffer doc:
    Buffer是一个具体的原生某一种容器，比如IntBuffer，那么他是int的容器
    buffer是一种特定，原生的，线性，有限一种元素序列，除了它的内容之外，
    本质属性是：position，position，capacity
    capacity：它包含的元素的个数，一个capacity永远不是负数，永远不会变化

    limit：limit不应该是被读或写的第一个元素的索引，limit永远不会为负数，永远不会超过capacity

    position：下一个将要被读或写的一个元素的索引，永远不肯为负数，永远不可能超过limit

通过NIO读取文件设计3个步骤

1. 从FileInputStream获取到FileChannel对象
2. 创建Buffer。
3. 将数据从Channel读取到Buffer中。

绝对方法和相对方法含义：
1. 相对方法：limit值与position值会在操作时被考虑到
2. 而结对方法：完全忽略掉limit与position值。
 */
















































