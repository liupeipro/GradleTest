package com.unionpay.extension

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

/**
 * NdkBuild 扩展属性，
 */
public class NdkBuildExtension {

    /**
     * ndkbuild cmd 路径，</br>
     * 建议输入 ‘/’来区分路径，默认为linux路径，如果系统为windows，则会将斜杠转换成反斜杠
     */
    String ndkBuildCmdPath

    /**
     * jni源码 根目录
     */
    String jniSourcePath

    /**
     * build时临时输出目录
     */
    String buildTempPath


    NamedDomainObjectContainer<NdkBuildType> ndkBuildTypes

    NdkBuildExtension(NamedDomainObjectContainer<NdkBuildType> ndkBuildTypes) {
        this.ndkBuildTypes = ndkBuildTypes
    }

    void ndkBuildTypes(Action<? super NamedDomainObjectContainer<NdkBuildType>> action) {
//        this.checkWritability();
        action.execute(this.ndkBuildTypes);
    }

    Collection<NdkBuildType> getNdkBuildTypes() {
        return this.ndkBuildTypes
    }


    @Override
    public String toString() {
        return "NdkBuildExtension{" +
                "ndkBuildCmdPath='" + ndkBuildCmdPath + '\'' +
                ", jniSourcePath='" + jniSourcePath + '\'' +
                ", buildTempPath='" + buildTempPath + '\'' +
                ", ndkBuildTypes=" + ndkBuildTypes +
                '}';
    }
}


