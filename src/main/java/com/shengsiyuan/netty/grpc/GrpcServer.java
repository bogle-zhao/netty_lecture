package com.shengsiyuan.netty.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author bogle
 * @version 1.0 2019/3/14 下午2:24
 */
public class GrpcServer {

    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder
            .forPort(8899)
            .addService(new StudentServiceImpl())
            .build()
            .start();
        System.out.println("servert started!");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭jvm");
            GrpcServer.this.stop();
        }));

        System.out.println("执行到这里了!");
    }

    private void stop() {
        if (server != null) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != server) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcService = new GrpcServer();
        grpcService.start();
        grpcService.awaitTermination();// 因为grpc服务器已启动就会推出，不会等待
    }

}
