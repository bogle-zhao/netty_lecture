package com.shengsiyuan.netty.grpc;

import io.grpc.stub.StreamObserver;

/**
 * @author bogle
 * @version 1.0 2019/3/14 下午2:12
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {


    /**
     * @param request          客户端请求参数
     * @param responseObserver 像客户端返回结果
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

    @Override public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接受到客户端信息：" + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder()
                                    .setName("张三")
                                    .setAge(20)
                                    .setCity("北京")
                                    .build());

        responseObserver.onNext(StudentResponse.newBuilder()
                                    .setName("李四")
                                    .setAge(30)
                                    .setCity("天津")
                                    .build());

        responseObserver.onNext(StudentResponse.newBuilder()
                                    .setName("王五")
                                    .setAge(40)
                                    .setCity("成都")
                                    .build());

        responseObserver.onNext(StudentResponse.newBuilder()
                                    .setName("赵六")
                                    .setAge(50)
                                    .setCity("深圳")
                                    .build());

        responseObserver.onCompleted();


    }

    @Override public StreamObserver<StudentRequest> getStudentsWrapperByAges(
        StreamObserver<StudentResponseList> responseObserver) {
        //这里整个都是客户端
        return new StreamObserver<StudentRequest>() {

            @Override public void onNext(StudentRequest value) {
                System.out.println("onNext: " + value.getAge());
            }

            @Override public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override public void onCompleted() {
                System.out.println("server onCompleted!");
                StudentResponse studentResponse = StudentResponse.newBuilder()
                    .setName("张三")
                    .setAge(20)
                    .setCity("西安")
                    .build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder()
                    .setName("李四")
                    .setAge(30)
                    .setCity("广州")
                    .build();

                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                    .addStudentResponse(studentResponse)
                    .addStudentResponse(studentResponse2)
                    .build();

                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }


        };

    }
}
