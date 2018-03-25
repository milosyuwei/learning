package com.weiyu.learning.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: weiyu
 * @date: 2018/3/1
 */
public class App {



    public static void main(String[] args) throws Exception {
        People p = new People();
        p.setAge(20);
        p.setName("People");
        p.setShortName("a");
        p.setGender("F");
        p.setEngName("hello");
        p.setAddress("shanghai");
        //Gson gson = new Gson();

        //当使用Expose注解的时候需要使用GsonBuilder
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        System.out.println(gson.toJson(p));

        System.exit(0);
    }
}
