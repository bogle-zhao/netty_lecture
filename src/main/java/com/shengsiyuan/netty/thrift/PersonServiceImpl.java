package com.shengsiyuan.netty.thrift;

import org.apache.thrift.TException;
import thirft.generated.DataException;
import thirft.generated.Person;
import thirft.generated.PersonService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bogle
 * @version 1.0 2018/11/28 下午9:51
 */
public class PersonServiceImpl implements PersonService.Iface {

    private static Map<String, Person> map = new HashMap<>();

    static {
        Person p1 = new Person();
        p1.setUsername("zhangsan");
        p1.setAge(20);
        p1.setMarried(false);
        map.put(p1.getUsername(), p1);
    }

    @Override public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("got getPersonByUserName client param:" + username);

        return map.get(username);
    }

    @Override public void savePerson(Person person) throws DataException, TException {
        System.out.println("got savePerson client ");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
        map.put(person.getUsername(), person);
    }
}
