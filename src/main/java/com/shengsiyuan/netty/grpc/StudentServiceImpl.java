package com.shengsiyuan.netty.grpc;

import io.grpc.stub.StreamObserver;

/**
 * @author bogle
 * @version 1.0 2019/3/14 下午2:12
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {


    /**
     *
     * @param request   客户端请求参数
     * @param responseObserver  像客户端返回结果
     */
    @Override public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受到客户端信息:" + request.getUsername());

        responseObserver.onNext(
            MyResponse.newBuilder()
                .setRealname("张三")
                .build()
        );
        responseObserver.onCompleted();
    }
}
