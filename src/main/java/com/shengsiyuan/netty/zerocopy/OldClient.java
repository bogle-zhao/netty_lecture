package com.shengsiyuan.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);
        String fileNames = "G:\\学习\\netty高并发-张龙\\12_Google Protobuf详解.mp4";
        InputStream is = new FileInputStream(fileNames);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        long length = 0;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((length = is.read(bytes)) >= 0) {
            total += length;
            dos.write(bytes);
        }
        System.out.println("发送总字节数：" + total + ", 耗时：" + (System.currentTimeMillis() - startTime));
        dos.close();
        socket.close();
        is.close();
    }
}
