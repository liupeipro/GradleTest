# Gradle 基础
## 简介
Gradle是一种自动化构建工具，它抛弃了基于xml的构建，基于DSL(特定领域语言)语法来声明项目配置。

## Project 和 Task
projects 和 tasks是Gradle中最重要的两个概念。任何一个Gradle构建都是由一个或多个 projects 组成.每个project包括许多可构建组成部分，每个project的产物可以是一个jar包、一个web应用,一个jar包，也可以是zip包.每个project都由多个tasks组成.每个task都代表了构建执行过程中的一个子操作.如编译,打包,生成javadoc,发布到某个仓库等操作。

#### Project
在Gradle中每一个待构建的工程都是一个Project。
#### Task
任务是最小的工作单元，指具体执行工作的统称，一个Task表示一个逻辑上独立的执行过程。比如：编译java源码、检查、拷贝、打包。 构建一个Project需要执行一系列的Task，一个apk的构建包含 java源码编译、资源文件编译、Lint检查、打包生成最终的apk文件等等。

## 生命周期
Gradle有自己的生命周期，包括**初始化、配置和执行**三个阶段。

**初始化阶段：**会去读取根工程中setting.gradle中的include信息，决定哪些项目要参与构建，
  并创建project实例，比如下面有三个工程： include ':app', ':lib1', ':lib2

**配置阶段：**gradle会读取所有工程下的build.gradle中的内容来配置project和task。比如设置Project和创建、配置task及相关信息，处理Task之间的依赖关系等等。

**执行阶段：**根据gradle命令传递过来的task名称，执行相关依赖任务

## 文件目录结构

    目录结构：
    ./build.gradle
    ./gradle.properties
    ./gradlew
    ./gradlew.bat
    ./local.properties
    ./setting.gradle
    ./XXX.iml
    ./app/build.gradle
    ./app/app.iml
    ./app/proguard-rules.pro

    ./builld.gradle 和 ./app/build.grade

    gradle项目自动编译的时候要读取的配置文件。比如指定项目的依赖包等。build.grade有两个，一个是全局的，一个是在模块里面。全局的build.grade主要设置的是声明仓库源，gradle的版本号说明等。

    ./app/proguard-rules.pro
    混淆文件
    
    ./gradle.properties
    grade的运行环境配置，比如使用多少内存之类的。定义了供build.gradle使用的常量，比如keyStore的存储路径、密码等。
    
    ./gradlew 和 ./gradlew.bat
    自动完成 gradle 环境的脚本，在linux和mac下直接运行gradlew会自动完成gradle环境的搭建。
    
    ./local.properties
	定义了一些本地属性，比如sdk、ndk的路径
    
    ./setting.gradle
    描述了哪些项目参与构建，如果项目中包含了不止一个Module时，必须在它里面进行包含(include)。可以做一些初始化的工作，在这里设置一些函数，这些函数会在gradle构建整个工程时执行。
    
    ./XXX.iml 和 ./app/app.iml
    iml是Intellij模块文件。Intellij是一款JAVA的IDE。Android Studio是基于开源的Intellij IDEA开发出来的IDE。
    所以Android Studio有的IDE功能是需要有.iml才能使用的。比如我们删除了iml文件，可能就在Android Studio中看不到一些目录了。

## Task

### 定义task

##### 调用Project的task()方法创建Task 。也是最常用的方式。

    task hello << {
      println "hello 1"
    }

doLast 被替换成了 <<. 它们有一样的功能, 但看上去更加简洁了, 

这里的“<<”表示追加的意思，即向hello中加入执行代码。Gradle提供了一套的DSL，它在底层依然是执行groovy，比如task关键字，就是一个groovy的方法，大括号之间的内容表示传递给task()方法的一个闭包。
我们还可以使用doLast来达到同样的效果：

    task hello {
        doLast{
            println "hello 1"
        }
    }

doLast是在最后面加入执行过程，还有doFirst，它是在最前面加入执行过程

    task hello{
        doFirst{
            println "hello 1"
        }
    }

上面的task关键字实际上调用了project里的方法来定义task，该方法属于Project，Project里存在多个重载的task()方法


##### 通过TaskContainer的create()方法创建Task

    task.create(name:'hello2') << {
        println "hello 2"
    }




#### 执行task

gradle -q hello

-q. 代表安静模式. 它不会生成 Gradle 的日志信息 (log messages), 只能看到 tasks 的输出. 它使得的输出更加清晰. 


gradle -i hello

gradle -i hello --stacktrace



#### 在task里添加自定义属性
一个Task除了执行操作之外，还可以包含多个Property，其中有Gradle为每个Task默认定义的Property，比如description、logger等。另外每个特定的Task还可以拥有特定的Property，比如 Copy的 from,into等。例如,新增一个叫做 myProperty 的属性,用 ext.myProperty 的方式给他一个初始值.这样便增加了一个自定义属性
    

    task myTask {
        ext.myProperty = "myValue"
    }
    
    task printTaskProperties << {
        println myTask.myProperty
    }

#### 为task加入特定类型
前面定义的task属于通用的Task类型，而在有些时候，我们希望创建一些具有特定功能的Task，比如Copy和Delete等.
##### 常用的task类型
###### Copy

    task copyDocs(type: Copy) {
        from 'src/main/doc'
        into 'build/target/doc'
    }

###### Delete

    task makePretty(type: Delete) {
      delete 'uglyFolder', 'uglyFile'
    }



### 多任务通信
#### 依赖
你可以按如下方式创建任务间的依赖关系
    
    Task之间是可以存在依赖关系，比如A依赖B，那么在执行TaskA时，Gradle会先执行B，再执行A。我们可以在定义一个Task的同时声明它的依赖关系：

    
    task testB() << {
        println "test B"
    }
    
    task testA(dependsOn: testB) << {
        println "test A"
    }

    结果：

    gradlew -q testA
    test B
    test A
    
    gradlew -q testB
    test B

也可以在定义Task之后再声明依赖：
    
    task testB() << {
        println "test B"
    }
    
    task testA() << {
        println "test A"
    }
    
    testA.dependsOn testB
    
结果也是一样的。

添加依赖task也可以不必首先声明被依赖的task.(延迟依赖),需要给依赖的task加单引号，不然编译不通过

    task testB(dependsOn：'testA') << {
        println "test B"
    }
    
    task testA() << {
        println "test A"
    }
    
#### 增加任务行为
    比如 

    task hello << {
        println 'Hello Earth'
    }
    hello.doFirst {
        println 'Hello Venus'
    }
    hello.doLast {
        println 'Hello Mars'
    }
    hello << {
        println 'Hello Jupiter'
    }
 
doFirst 和 doLast 可以进行多次调用. 他们分别被添加在任务的开头和结尾.当任务开始执行时这些动作会按照既定顺序进行.其中 << 操作符 是 doLast 的简写方式.

#### task执行排序
##### dependsOn
task的执行时依赖别的task的方法就是使用dependsOn方法

    task A << {println 'Hello from A'}
    task B << {println 'Hello from B'}
    B.dependsOn A

也可以dependsOn 多个，但需要使用mustRunAfter了


##### mustRunAfter
mustRunAfter并不会添加依赖，如果两个task同时存在，它只是告诉Gradle执行的优先级。例如：B.mustRunAfter C，如果B和C同时存在，gradle会优先执行C，然后再是B。单独执行B，并不会优先执行C，它和dependsOn不同。

##### finalizedBy
finalizeBy就是在task执行完之后要执行的task。
    
    taskA << {
        println "taskA"
    }

    taskB <<{
        println "taskB"
    }

    taskA.finalizedBy taskB

A执行完后会接着执行B。




#### 
### 


#### Log输出
是在gradle的执行阶段打印出来的

    task hello {
        doLast {
            println "hello"
        }
    }

是在gradle的配置阶段打印出来的

    task hello {
        println "hello"
    }



## 自定义属性
#### 在build.gradle文件中定义Property
定义在build.gradle中的ext块中。ext准确的说是Gradle领域对象的一个属性，我们可以将自定义的属性添加到ext对象上，Build.gradle中的其它代码片段可以使用
在build.gradle文件中向Project添加额外的Property时，我们并不能直接定义，而是应该通过ext来定义。如果要添加一个名为property1的Property

    ext.property1 = "this is property1"

另外，我们也可以通过闭包的方式：

    ext {
       property2 = "this is property2"
    }

#### 在gradle.properties中定义Property
在该文件中定义需要的属性。这些属性在Gradle构建Gradle领域对象（即project对象实例）时会被自动加到project对象实例中作为其属性被直接调用。

    gradle.properties
    p1 = 'test1'

    调用时
    println "$p1"

#### 在gradle.properties创建系统属性。如果有systemProp.前缀的属性会被识别为系统属性。
    gradle.properties
    systemProp.guestName = 'Bowen'

    调用时：
    println "hello, " + System.properties['guestName']

#### 使用特殊前缀的系统属性或环境变量
    如果有环境变量以ORG_GRADLE_PROJECT.为前缀，那么该变量会被自动添加到Gradle领域对象中。同样，如果有系统属性以org.gradle.project.为前缀，那么也会被自动加入到Gradl领域对象中。这一特性的目的之一是为了隐藏一些敏感的信息。比如在执行Gradle脚本时需要传入密码信息


## 仓库配置
#### 远程仓库
Gradl使用多种类型的仓库，来获取应用中使用的库文件。

支持的类型有如下几种：
类型 	说明
Maven central repository 	这是Maven的中央仓库，无需配置，直接声明就可以使用。但不支持https协议访问
Maven JCenter repository 	JCenter中央仓库，实际也是是用的maven搭建的，但相比Maven仓库更友好，通过CDN分发，并且支持https访问。
Maven local repository 	Maven本地的仓库，可以通过本地配置文件进行配置
Maven repository 	常规的第三方Maven仓库，可设置访问Url
Ivy repository 	Ivy仓库，可以是本地仓库，也可以是远程仓库
Flat directory repository 	使用本地文件夹作为仓库

以下是几种仓库的使用方法：
Maven central repository

在build.gradle中配置

repositories {
    mavenCentral()
}

就可以直接使用了。
Maven JCenter repository

最常用也是Android Studio默认配置：

repositories {
    jcenter()
}

这时使用jcenter仓库是通过https访问的，如果想切换成http协议访问，需要修改配置：

repositories {
    jcenter {
        url "http://jcenter.bintray.com"
    }
}

Local Maven repository

可以使用Maven本地的仓库。默认情况下，本地仓库位于USER_HOME/.m2/repository（例如windows环境中，在C:\Users\NAME.m2.repository），同时可以通过USER_HOME/.m2/下的settings.xml配置文件修改默认路径位置。
若使用本地仓库在build.gradle中进行如下配置：

repositories {
    mavenLocal()
}


Maven repositories

第三方的配置也很简单，直接指明url即可：

repositories {
    maven {
        url "http://repo.mycompany.com/maven2"
    }
}

Ivy repository

配置如下：

repositories {
    ivy {
        url "http://repo.mycompany.com/repo"
    }
}

Flat directory repository

使用本地文件夹，这个也比较常用。直接在build.gradle中声明文件夹路径：

repositories {
    flatDir {
        dirs 'lib'
    }
    flatDir {
        dirs 'lib1', 'lib2'
    }
}

使用本地文件夹时，就不支持配置元数据格式的信息了（POM文件）。并且Gradle会优先使用服务器仓库中的库文件：例如同时声明了jcenter和flatDir，当flatDir中的库文件同样在jcenter中存在，gradle会优先使用jcenter的。



#### 引用jar、aar、module
	5.1 jar的引用
可以通过AndroidStudio IDE来引用jar
在jar右键点击 Add As Library
可以在 build.gradle的dependencies下指定 compile files('libs/libammsdk.jar')
	5.2 aar的 引用
在根目录的build.gradle里添加

    repositories{flatDir { dirs 'libs'    }}

这个是添加一个本地仓库，并把libs目录作为仓库的地址。
接着修改module下build.gradle里的dependencies，添加 compile(name:'名字', ext:'aar')


	5.3 Module引用	
也可以直接 
    compile project(':uplib') 
意思是把uplib作为module引入依赖。像Eclipse指定外部工程依赖一样。





---
结束

--- 

3. Plugin 
	
	plugin就是Groovy库，就是groovy代码。
	插件(‘com.android.application’、‘com.android.library’)的核心工作就是定义和执行Task。
	想要完成某个的构建流程，必须导入合适的插件，这些插件中定义并执行了构建Project所需的一系列Task。
	
	**插件的导入**：使用 apply plugin: 

	apply plugin:‘com.android.application’ 表示使用这个插件来构建Project，它负责定义和执行一系列Task，它有4个顶级任务：

    * assemble 构建项目的输出
    * check 进行检查教验工作
    * build 执行assemble任务和check任务
    * clean 清除项目的输出



6. 常用命令
gradle projects 查看工程信息
gradle clean 是执行清理任务，和make clean类似。
gradle properites 用来查看所有属性信息。
gradle tasks 查看Project中所有的Task。会列出每个任务的描述，通过描述，我们大概能知道这些任务是干什么的.....。然后gradle task-name执行它就好。

##### Hello world

你可以通过在命令行运行gradle命令来执行构建, gradle命令会从当前目录下寻找 build.gradle 文件来执行构建.我们称 build.gradle 文件为构建脚本. 严格来说这其实是一个构建配置脚本,后面你会了解到这个构建脚本定义了一个project和一些默认的task


    task hello {
        doLast {
            println 'Hello world!'
        }
    }

    然后在该文件所在目录执行 gradle -q hello

    可以用一种更简洁的方式来定义 hello 任务.
   
    task hello << {
        println 'Hello world!'
    }


3. Android Plugin

    每个元素的含义和作用。


		
6. Task

* 常用Task

Copy 复制任务把文件从A复制到B
    
    下面是一个复制文档的任务，把文档从 'src/main/doc' 这个地方复制到 'build/target/doc' 过滤条件为只复制.md结尾的文件，并且不复制空文件夹。
    
        task copyDocs(type: Copy) { 
             includeEmptyDirs = false
             from( 'src/main/doc') {
                    include '**.md'
                    into ('build/target/doc')
             }
        }
    
Delete  删除文件，目录

    删除文件夹 "uglyFolder" 和 "uglyFile" ，用起来更简单。

        task makePretty(type: Delete) { 
               delete 'uglyFolder', 'uglyFile'
        }

    
Zip 把制定文件压缩成 .zip 包
    
    这段代码把"build/sdk"下面的文件复制到'build/hehe'里面，然后把"misc"里面的"config.json"文件复制到根目录下面。注意"from"来的文件默认是打包脚本所在目录。"into" 的根目录是 "destinationDir" 属性设置的地方。
       
        task dabao( type:Zip){    
            destinationDir = file('build')    
            archiveName =  'abc.zip'    
        
            from('build/sdk') {        
                  into( 'hehe' )   
            }    
            from('misc'){        
                  include('config.json')        
                  into('.')    
            }
        }

    
多任务间的通信
1 增加依赖
    taskA.dependsOn taskB


	dependsOn

    依赖多个task
    task allTask(dependsOn: ['clean', 'assembleRelease', 'copyTask']){
        clean.mustRunAfter copyTask
        copyTask.mustRunAfter assembleRelease
    }

	finalizedBy
	mustRunBy
gradle脚本的拆分与引用
引用：通过apply from: 
> apply from: rootProject.file('basegradle/commonProject.gradle')//引入
> commonProject

多脚本之间的task调用
* b.execute()
* 

异常
 StopExecutionException 如果跳过任务的规则不能与断言同时表达，您可以使用StopExecutionException。如果一个操作（action）抛出了此异常，那么这个操作（action）接下来的行为和这个任务的其他 操作（action）都会被跳过。构建会继续执行下一个任务

    // 暂时忽略这个异常，继续执行，执行完成后再抛异常 build failed
    throw new GradleException("异常： exception : $msg")
    //throw new GradleException("Exception：未找到 ndkCmd 文件 ")
    //会忽略这个异常、继续执行.build success
    //throw new StopActionException("Exception：未找到 ndkCmd 文件 ")
    // 暂时忽略这个异常，继续执行，执行完成后再抛异常 build failed
    // throw new BuildCancelledException("Exception：未找到 ndkCmd 文件 ")
    //暂时忽略这个异常，继续执行，执行完成后再抛异常 build failed
    //throw new GradleScriptException("Exception：未找到 ndkCmd 文件 ")
    //暂时忽略这个异常，继续执行，执行完成后再抛异常 build failed
//     throw new TaskExecutionException(content)































































## 附录
###### 网址
	https://gradle.org/

## 问题
###### 什么是DSL？
###### Groovy基础
###### gradlewrapper是什么？有什么用？
###### gradle在初始化阶段会执行读取哪些文件，每个文件的作用。