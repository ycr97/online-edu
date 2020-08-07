package com.yy.generator;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycr
 * @date 2020/8/4
 */
public class Test {

    @org.junit.Test
    public void test(){
        List<Person> people = new ArrayList<>();
        Chinese chinese = new Chinese();
        chinese.setAddress("chengdu");
        chinese.setName("yy");
        chinese.setAge(17);
        people.add(chinese);
        for (Person person : people) {
            System.out.println(person);
        }
    }

}
@Data
@ToString
class Person{
    private String name;
    private int age;

}

@Data
@ToString
class Chinese extends Person{

    private String address;
}