package com.shengsiyuan.netty.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

/**
 * @author bogle
 * @version 1.0 2019/3/14 下午2:33
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder
            .forAddress("localhost", 8899)
            .usePlaintext(true)
            .build();
        // 同步形式
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
            .newBlockingStub(managedChannel);

        // 异步形式
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        MyResponse myResponse = blockingStub
            .getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());

        System.out.println("-------------------------");

        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder()
                                                                               .setAge(20)
                                                                               .build()
        );

        while (iterator.hasNext()) {
            StudentResponse studentResponse = iterator.next();
            System.out.println("name: " + studentResponse.getName() + ", age:" + studentResponse.getAge() + ", city:" +
                               studentResponse.getCity());
        }

        System.out.println("-------------------------");

        StreamObserver<StudentResponseList> studentResponseListStreamObserver =
            new StreamObserver<StudentResponseList>() {
                @Override public void onNext(StudentResponseList value) {
                    value.getStudentResponseList().forEach(item -> {
                        System.out.println("name: " + item.getName() + ", age:" + item.getAge() + ", city:" +
                                           item.getCity());
                    });
                }

                @Override public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override public void onCompleted() {
                    System.out.println("client onCompleted!");
                }
            };

        // 只要客户端是以流式的形式像服务器发送数据的，一定就是异步的
        StreamObserver<StudentRequest> studentRequestStreamObserver =
            stub.getStudentsWrapperByAges(studentResponseListStreamObserver);

        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                                                .setAge(20)
                                                .build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                                                .setAge(30)
                                                .build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                                                .setAge(40)
                                                .build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder()
                                                .setAge(50)
                                                .build());
        studentRequestStreamObserver.onCompleted();

        Thread.sleep(5000);
    }
}
