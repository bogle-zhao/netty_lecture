package com.shengsiyuan.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * selector doc
 * selector是一个可以通过channel进行双向通信的对象
 * selector是可以调用Selector类的open方法来创建对象，这个方法会使用系统默认的java.nio.channels.spi.SelectorProvider selector provider来创建对象，可以提供自定义的java.nio.channels.spi.SelectorProvider#openSelector openSelector 选择器创建，一个selector会一直保持着打开状态，直到调用closed方法
 * <p>
 * 一个可选择的channel注册到selector上，是通过SelectionKey来表现的，一个selector维护的三种selection集合
 * <p>
 * 1. ke set ： 包含了selector注册到channel上的所有的key，他是调用keys返回的
 * 2. selected-key: 感兴趣的key集合，
 * 3. cancelled-key：原来关注的key，然后取消的key集合，取消的时候并不是理解关闭，是在下一次selector的时候才关闭
 * <p>
 * 所有这三种sets在新创建的时候都是empty的
 * <p>
 * 我们可以通过channel来注册selector，同时会将这个key添加到集合中，被取消的key，会将这个key移除，keys不能被修改
 */
public class NioTest12_1 {

    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//是否以阻塞方式运行
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口：" + ports[i]);
        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers:" +numbers);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionnKeys:" + selectionKeys);
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();
                if(selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    iter.remove();
                    System.out.println("获得客户端连接：" + socketChannel);
                } else if(selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    int bytesRead = 0;
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0) {
                            break;
                        }

                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        bytesRead += read;
                        System.out.println("读取：" + bytesRead + ",来自于：" + socketChannel);
                        iter.remove();
                    }
                }
            }

        }
    }
}
