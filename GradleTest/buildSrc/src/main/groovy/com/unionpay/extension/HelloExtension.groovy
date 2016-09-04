package com.unionpay.extension

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

/**
 * Created by Peil on 2016/9/4/004.
 */
public class HelloExtension {

    String title
    String msg

    NamedDomainObjectContainer<HelloType> helloTypes

    HelloExtension(NamedDomainObjectContainer<HelloType> helloTypes) {
        this.helloTypes = helloTypes
    }

    void helloTypes(Action<? super NamedDomainObjectContainer<HelloType>> action) {
//        this.checkWritability();
        action.execute(this.helloTypes);
    }

    Collection<HelloType> getHelloTypes() {
        return this.helloTypes
    }


    @Override
    public String toString() {
        return "HelloExtension{" +
                "title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", helloTypes=" + helloTypes +
                '}';
    }
}
