package com.weiyu.learning.gson;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.ToString;

/**
 * @author: weiyu
 * @date: 2018/3/1
 */
@Data
public class People {


    private String name;
    private int age;
    private String shortName;

    //一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
    private transient String gender;


    @Expose
    private String engName;
    @Expose(serialize = false, deserialize = false)
    private String address;
}
