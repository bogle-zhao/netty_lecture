package com.shengsiyuan.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author bogle
 * @version 1.0 2019/3/16 下午1:48
 */
public class NioTest4 {

    public static void main(String[] args) {
        IntBuffer ibf = IntBuffer.allocate(10);//分配缓冲区为10
        System.out.println("capacity:" + ibf.capacity());
        for (int i = 0; i < 5; ++i) {
            int randomNumber = new SecureRandom().nextInt(20);
            ibf.put(randomNumber);
        }
        System.out.println("before flip limit:" + ibf.limit());
        ibf.flip();//翻转，将写流转换为读流
        System.out.println("after flip limit: " + ibf.limit());

        System.out.println("enter while loop");

        while (ibf.hasRemaining()) {
            System.out.println("position:" + ibf.position() +
                               ", limit:" + ibf.limit() +
                               ", capacity:" + ibf.capacity()
                               );
            ibf.get();
//            System.out.println(ibf.get());
        }
    }
}
















































