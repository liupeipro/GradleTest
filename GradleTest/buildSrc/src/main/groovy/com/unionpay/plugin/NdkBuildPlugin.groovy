package com.unionpay.plugin


import com.unionpay.extension.NdkBuildExtension
import com.unionpay.extension.NdkBuildType
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * ndk 编译插件
 */
public class NdkBuildPlugin implements Plugin<Project> {

    //插件名
    static final String PLUGIN_NAME = "ndk"
    //project对象
    private Project project
    //ndkBuildExtension对象
    NdkBuildExtension ndkBuildExtension
    //ndkBuildType集合
    Map<String, NdkBuildType> ndkBuildTypeMaps = [:]


    @Override
    void apply(Project project) {
        this.project = project
        println " ****** NdkBuildPlugin is call"
        applyExtension()
    }

    /**
     * 导入扩展属性
     */
    void applyExtension() {
        //从project里获取关于NdkBuildType的对象集合
        NamedDomainObjectContainer<NdkBuildType> types = project.container(NdkBuildType)
        //ndk
        ndkBuildExtension = project.extensions.create(PLUGIN_NAME, NdkBuildExtension, types)

        //当对象创建时做处理
        types.whenObjectAdded(new Action<NdkBuildType>() {
            @Override
            void execute(NdkBuildType buildType) {
                if (buildType != null && ndkBuildExtension != null) {
                    addNdkBuildType(ndkBuildExtension, buildType)
                } else {
                    println "在project的ext属性里未找到 $PLUGIN_NAME 对象"
                }
            }
        })
        //当对象删除时做处理
//        types.whenObjectRemoved(new UnsupportedAction("Removing hello types is not supported."));


//        project.task('testNdk') << {
//            println "*** testNdk"
////            def extNdk = project.extensions.getByName(PLUGIN_NAME)
//            println "1 - extNdk = $ndkBuildExtension"
//            println "0 - ndkBuildTypeMaps = $ndkBuildTypeMaps"
//
//        }
    }

    void addNdkBuildType(NdkBuildExtension ndkBuildExtension, NdkBuildType type) {
        String name = type.name
        ndkBuildTypeMaps.put(name, type)
        addNdkBuildTask(ndkBuildExtension, type)
    }

    /**
     * 创建对应的ndkbuild task
     * @param type
     */
    void addNdkBuildTask(NdkBuildExtension ext, NdkBuildType type) {
        String taskName = type.buildTaskName

        def taskType = project.task(taskName) << {
            println "$taskName is call "
            // NDKBUildCmd所在路径
            def ndkCmdPath = convertPath(ext.ndkBuildCmdPath)
            // jni源码路径
            def jniSourcePath = convertPath(ext.jniSourcePath)
            // 获取ndk 编译中的临时输出目录
            def ndkOutPath = convertPath(ext.buildTempPath + "/obj")
            // 获取ndk 编译完成后的输出目录,输出so
//            def ndkAppDirOutPath = convertPath(ext.jniOutputsPath)
            def ndkAppDirOutPath = convertPath(type.jniOutputsPath + "/\$(TARGET_ARCH_ABI)")
            // android.mk 路径
            def appBuildScriptPath = convertPath(ext.jniSourcePath + "/Android.mk")
            // application.mk 路径
            def applicationMkPath = convertPath(ext.jniSourcePath + "/Application.mk")
            // 获取NDK工作目录(和jni源码路径一样)
            def ndkProjectPath = convertPath(ext.jniSourcePath)


            println "$taskName calling...ndkCmdPath = $ndkCmdPath ; jniSourcePath = $jniSourcePath ; " +
                    "ndkOutPath = $ndkOutPath ; ndkAppDirOutPath = $ndkAppDirOutPath ; " +
                    "appBuildScriptPath = $appBuildScriptPath ; applicationMkPath = $applicationMkPath ; ndkProjectPath = $ndkProjectPath "




            if (checkFileExist(project, ndkCmdPath, "ndkBuild/ndkBuild.cmd ")
                    && checkFileExist(project, jniSourcePath, " jni源码 ")
                    /*&& checkFileExist(project, ndkOutPath, " 编译时的临时输出目录 ")
                    && checkFileExist(project, ndkAppDirOutPath, "编译成功后的输出目录 ")*/
                    && checkFileExist(project, appBuildScriptPath, "Android.mk ")
                    && checkFileExist(project, applicationMkPath, "Application.mk ")) {

                project.exec {
                    //调用命令进行编译
                    commandLine ndkCmdPath, '-C', jniSourcePath,
                            "NDK_OUT=$ndkOutPath",
                            "NDK_APP_DST_DIR=$ndkAppDirOutPath",
                            "APP_BUILD_SCRIPT=$appBuildScriptPath",
                            "NDK_APPLICATION_MK=$applicationMkPath",
                            "NDK_PROJECT_PATH=$ndkProjectPath"
                }
            } else {
                //清除临时目录 & 输出目录
                def tempBuildPath = convertPath(ext.buildTempPath)
                def outputPath = convertPath(type.jniOutputsPath)

                println "delete path : $tempBuildPath 、 $outputPath"
                project.delete tempBuildPath, outputPath

                throw new GradleException("*** ndk build 失败 ***")
            }

        }

        taskType.doFirst {
            println "doFirst call"

            //清除临时目录 & 输出目录
            def tempBuildPath = convertPath(ext.buildTempPath)
            def outputPath = convertPath(type.jniOutputsPath)

            println "delete path : $tempBuildPath 、 $outputPath"
            project.delete tempBuildPath, outputPath

            if (type.callFirst != null) {
                //判断闭包是否为空。。。
                type.callFirst.call()
            }
        }

        taskType.doLast {
            if (type.callLast != null) {
                //判断闭包是否为空。。。
                type.callLast.call()
            }

        }

    }

    /**
     * 路径转换，切换不同操作系统环境下的斜杠
     * @param path 路径
     * @return 转换后的路径
     */
    static String convertPath(path) {
        if (Os.isFamily(Os.FAMILY_WINDOWS)) {
            path = path.replace("/", "\\")
        } else {
            //默认认为是linux /macox

        }
        return path
    }

    /**
     * 检查对应文件是否存在(只针对文件)
     * @return
     */
    static boolean checkFileExist(Project project, String filePath, String msg) {
        log "-- 检查 $msg 是否存在 --"
        def file = project.file(filePath)
        if (file != null && file.exists()) {
            return true
        } else {
            log " 无法找到该文件，$msg 不存在 "
        }
        return false
    }

    static void log(String msg) {
        println "** $msg **"
    }


}