package com.shengsiyuan.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thirft.generated.Person;
import thirft.generated.PersonService;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午10:01
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUserName("zhangsan");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            Person p2 = new Person();
            p2.setUsername("李四");
            p2.setAge(30);
            p2.setMarried(false);
            client.savePerson(p2);


            Person p3 = client.getPersonByUserName("李四");
            System.out.println(p3.getUsername());
            System.out.println(p3.getAge());
            System.out.println(p3.isMarried());


        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }

    }
}
