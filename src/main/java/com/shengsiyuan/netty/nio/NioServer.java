package com.shengsiyuan.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeySet.iterator();
            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    String key = "【" + UUID.randomUUID().toString() + "】";
                    clientMap.put(key, client);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(readBuffer);
                    if (count > 0) {
                        readBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String receiveMessage = String.valueOf(charset.decode(readBuffer).array());
                        System.out.println(socketChannel + ":" + receiveMessage);
                        String senderKey = null;
                        for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                            if (entry.getValue() == socketChannel) {
                                senderKey = entry.getKey();
                            }
                        }
                        for(Map.Entry<String,SocketChannel> entry : clientMap.entrySet()) {
                            SocketChannel value = entry.getValue();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((senderKey + " : " + receiveMessage).getBytes());
                            writeBuffer.flip();
                            value.write(writeBuffer);
                        }
                    }
                }
                iter.remove();
            }
        }


    }
}
