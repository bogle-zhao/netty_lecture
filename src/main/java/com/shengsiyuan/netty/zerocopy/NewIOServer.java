package com.shengsiyuan.netty.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws Exception{
        InetSocketAddress isa = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        //time WAIT 启用/禁用 一个超时socket占用端口的
        serverSocket.setReuseAddress(true);
        serverSocket.bind(isa);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int length = 0;
            while ((-1 != length)) {
                length = socketChannel.read(byteBuffer);
                byteBuffer.rewind();
            }
        }
    }
}
