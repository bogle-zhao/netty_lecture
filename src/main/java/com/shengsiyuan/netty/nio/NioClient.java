package com.shengsiyuan.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeySet.iterator();
            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();
                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) { // 是否处于连接过程中，如果是，处理连接为完成链接
                        client.finishConnect();// 完成连接
                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put(((LocalDateTime.now()) + "连接成功").getBytes());
                        writeBuffer.flip();
                        client.write(writeBuffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(() -> {
                            while (true) {
                                writeBuffer.clear();
                                InputStreamReader input = new InputStreamReader(System.in);
                                BufferedReader br = new BufferedReader(input);
                                String sendMsg = br.readLine();
                                writeBuffer.put(sendMsg.getBytes());
                                writeBuffer.flip();
                                client.write(writeBuffer);
                            }
                        });
                    }

                    client.register(selector,SelectionKey.OP_READ);
                } else if(selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel)selectionKey.channel();
                    ByteBuffer readByffer = ByteBuffer.allocate(1024);
                    int count = client.read(readByffer);
                    if (count > 0) {
                        String receivedMsg = new String(readByffer.array(),0,count);
                        System.out.println(receivedMsg);
                    }
                }

                iter.remove();
            }
        }
    }
}
