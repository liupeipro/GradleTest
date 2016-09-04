package com.unionpay.extension
/**
 * Created by AndroidDev0 on 2016/8/30/030.
 */
public class NdkBuildTaksExtension {

    final String name

    String buildTaskName

    Closure doCallFirst
    Closure doCallLast


    NdkBuildTaksExtension(String name) {
        this.name = name
        this.buildTaskName = getBuildTaskName()
    }


    void doCallFirst(Closure closure) {
        doCallFirst = closure
    }

    void doCallLast(Closure closure) {
        doCallLast = closure

    }

    def getBuildTaskName() {
        return "ndkBuild${captureStringUpper(this.name)}"
    }

    /**
     * 将字符串name 转化为首字母大写
     * @param name
     * @return
     */
    String captureStringUpper(String name) {
        //旧方法
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        //新方法：进行字母的ascii编码前移
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }


    @Override
    public String toString() {
        return "NdkBuildTaksExtension{" +
                "name='" + name + '\'' +
                ", buildTaskName='" + buildTaskName + '\'' +
                ", doCallFirst=" + doCallFirst +
                ", doCallLast=" + doCallLast +
                '}';
    }
}


