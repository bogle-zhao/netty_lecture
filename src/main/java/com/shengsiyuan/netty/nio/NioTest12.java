package com.shengsiyuan.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scattering与Gathering
 * Scattering: 分散，放开，在读的时候，我们可以传一个buffer的数组，让每一个buffer中读入数据，一个buffer读满了之后，接着读入到先一个buffer中，简单理解就是将来自于channel中的数据顺序的多到多个buffer中，
 * Gathering: 收集，合并到一起，和Scattering相反，我们可以将一个buffer数组的每个buffer数据写入到channel中，第一个buffer写完了之后，接着写第二个buffer中的数据，依次执行
 * 运用场景
 * 定义每一个协议的长度，比如第一个header的长度，第二个header长度....，body长度，我们就可以按照协议定义每一个buffer的长度，那么我们在读写的时候就知道每一个buffer内容是代表什么内容
 * @author bogle
 * @version 1.0 2019/3/18 下午10:05
 */
public class NioTest12 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;

                System.out.println("bytesRead: " + bytesRead);

                Arrays.asList(buffers).stream()
                    .map(buffer -> "posistion:" + buffer.position() + ", limit: " + buffer.limit())
                    .forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWritten = 0;

            while (bytesWritten < messageLength) {
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("byteRead: " + bytesRead + ", byteWritten: " + bytesWritten);
        }
    }

}
