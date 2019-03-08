package com.shengsiyuan.netty.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.shengsiyuan.protobuf.DataInfo;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午8:28
 */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
            .setName("张三")
            .setAge(20)
            .setAddress("北京")
            .build();

        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
