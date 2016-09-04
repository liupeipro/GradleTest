package com.unionpay.plugin

import com.unionpay.extension.HelloExtension
import com.unionpay.extension.HelloType
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project

public class HelloPlugin implements Plugin<Project> {

    private Project project

    Map<String, HelloType> helloTypeMaps = [:]


    @Override
    void apply(Project project) {
        this.project = project
        println " ****** HelloPlugin is call"

        applyExtension()
//        addTasks()
    }

    void applyExtension() {
        //NamedDomainObjectContainer导入
        NamedDomainObjectContainer<HelloType> helloTypes = project.container(HelloType)
        helloTypes.whenObjectAdded(new Action<HelloType>() {
            @Override
            void execute(HelloType helloType) {
                addHelloTypes(helloType)
            }
        })

        helloTypes.whenObjectRemoved(new UnsupportedAction("Removing hello types is not supported."));

        //extension 导入
        //1- project.extension.add(获取单独的字段可以)
//        project.extensions.add('hello', HelloExtension)

        //2-project.extension.create(获取单独的字段可以)
        project.extensions.create('hello', HelloExtension, helloTypes)

        //3-project.extension.name = hello .没试成功
//        def tHelloExt = project.extensions.getByType(HelloExtension)
//        project.extensions.hello = tHelloExt

        //namedDomain
        project.task('testHello') << {

            def extHello = project.extensions.hello
            println "0 - extHello = $extHello"

            def extHello1 = project.extensions.getByName('hello')
            println "1 - extHello = $extHello1"


            println "0 - helloTypeMaps = $helloTypeMaps"

        }
    }


    void addHelloTypes(HelloType type) {
        String name = type.name


        helloTypeMaps.put(name, type)
    }

    private static class UnsupportedAction
            implements Action<Object> {
        private final String message;

        UnsupportedAction(String message) {
            this.message = message;
        }

        public void execute(Object o) {
            throw new UnsupportedOperationException(this.message);
        }
    }

}