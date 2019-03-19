package com.shengsiyuan.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author bogle
 * @version 1.0 2019/3/18 下午10:02
 */
public class NioTest11 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest11.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileLock fileLock = fileChannel.lock(3, 6, true);
        System.out.println("valid:" + fileLock.isShared());
        System.out.println("lock type: " + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();
    }
}
