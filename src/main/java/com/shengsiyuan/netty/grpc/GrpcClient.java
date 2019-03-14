package com.shengsiyuan.netty.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author bogle
 * @version 1.0 2019/3/14 下午2:33
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
            .forAddress("localhost",8899)
            .usePlaintext(true)
            .build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
            .newBlockingStub(managedChannel);

        MyResponse myResponse = blockingStub
            .getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());
    }
}
