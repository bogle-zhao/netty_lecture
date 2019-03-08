package com.shengsiyuan.netty.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thirft.generated.PersonService;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午9:50
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

//        TProtocol (org.apache.thrift.protocol)
//          TJSONProtocol (org.apache.thrift.protocol)          //json格式
//          TSimpleJSONProtocol (org.apache.thrift.protocol)    //提供json只写协议，生成的文件很容易通过脚步语言解析
//          TCompactProtocol (org.apache.thrift.protocol)   //压缩格式
//          TProtocolDecorator (org.apache.thrift.protocol) //
//          TBinaryProtocol (org.apache.thrift.protocol)    // 二进制格式协议
//          TDebugProtocol(org.apache.thrift.protocol)  //使用易懂的可读文本格式，以便于debug

        arg.protocolFactory(new TCompactProtocol.Factory());// 协议，压缩协议

//        TTransport (org.apache.thrift.transport)
//          TFileTransport (org.apache.thrift.transport)                //以文件形式进行传输，
//          AutoExpandingBufferReadTransport (org.apache.thrift.transport)
//          TSaslTransport (org.apache.thrift.transport)
//                TSaslServerTransport (org.apache.thrift.transport)
//                TSaslClientTransport (org.apache.thrift.transport)
//          AutoExpandingBufferWriteTransport (org.apache.thrift.transport)
//          TFastFramedTransport (org.apache.thrift.transport)
//          TSimpleFileTransport (org.apache.thrift.transport)
//          TFramedTransport (org.apache.thrift.transport)              //以frame为单位进行传输，非阻塞式服务中使用
//          TMemoryInputTransport (org.apache.thrift.transport)
//          THttpClient (org.apache.thrift.transport)
//          TNonblockingTransport (org.apache.thrift.transport)
//          TByteBuffer (org.apache.thrift.transport)
//          TIOStreamTransport (org.apache.thrift.transport)
//                TZlibTransport (org.apache.thrift.transport)          使用zlib进行压缩，与其他传输方式联合使用，当前无java实现
//                TSocket (org.apache.thrift.transport)                 //阻塞式socket
//          TMemoryBuffer (org.apache.thrift.transport)                 //将内存用于I/O，Java实现时内部实际使用了简单的ByteArrayOutputStream
        arg.transportFactory(new TFramedTransport.Factory());//传输
        arg.processorFactory(new TProcessorFactory(processor));

//        TServer (org.apache.thrift.server)
//            AbstractNonblockingServer (org.apache.thrift.server)
//                TNonblockingServer (org.apache.thrift.server)         多线程服务模型，使用非阻塞式IO（需要使用TFramedTransport数据传输方式）
//                    THsHaServer (org.apache.thrift.server)            THsHa引用了线程池去处理，其模型把读写任务放到线程池去处理；Half-sync/Half-async的处理模式，Half-async是在处理IO事件上(accept/read/write io),Half-sync用于Handler对RPC的同步处理
//                TThreadedSelectorServer (org.apache.thrift.server)
//                TThreadPoolServer (org.apache.thrift.server)          多线程服务模型，使用标准的阻塞式io
//                TSimpleServer (org.apache.thrift.server)              简单的单线程服务模型，常用于测试

        TServer server = new THsHaServer(arg);//THsHaServer 半同步，半异步
        System.out.println("server started");
        server.serve();
    }
}
