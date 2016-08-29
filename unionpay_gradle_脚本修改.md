# Gradle 脚本修改

#### 测试工作


#### 命令
	编译dev环境的jnilibs,输出log信息，并打印出错日志
	gradlew -i buildDevLibs --stacktrace

	


#### 脚本内容

##### 异常
	每个类都有每个类的异常。抛出后会把全局的结果设为false,认为本次编译失败。
	每个Task初始化时会把一些值设为默认的。、



### 迁移内容：

	1. 某某文件迁移到某目录
	2. drawable 改为 mipmap 
	3. 构建工具升级为24.0.0
	4. ....之类的
    


# Gradle脚本部署

### 环境变量和配置

aapt 环境变量，配置成默认build-tools下。
ndk目录
sdk目录
gradle .gradle
gradle m2respo***



#### NDK 强制编译机制
    如果jni源码有改动，就需要重新编译Jni。


#### 遗留：
1. 怎么让task有返回值，让sh知道该任务的成功与失败？
2. 混淆
