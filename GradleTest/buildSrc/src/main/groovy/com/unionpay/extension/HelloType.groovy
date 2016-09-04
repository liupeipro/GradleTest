package com.unionpay.extension;

/**
 * Created by Peil on 2016/9/4/004.
 */
public class HelloType {

    final String name

    String content
    String type

    HelloType(String name){
        this.name = name
    }


    @Override
    public String toString() {
        return "HelloType{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
