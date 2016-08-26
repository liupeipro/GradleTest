# Gradle 基础



## 简介

	Gradle是一种自动化构建工具，它抛弃了基于xml的构建，采用一种基于Groovy的特定领域语言(DSL)来声明项目配置。

## 基本概念

	Gradle本身的的对象包括Project和Task，Project为Task提供了执行上下文，

0. 基本类型

    gradle基于groovy, gradle提供的基本类型也都实现了script.并且提供了大量的方法和属性

    Build script -> Project (每个project的build.gradle也就是一个project)
    Init script -> Gradle
    Settings script -> Settings (对应setting.gradle)

    * Project 每个project的build.gradle都会转换成一个project
    * Gradle
    * Settings 每一个settings.gradle都会转换成一个Settings对象


1. Project(项目)
 	
	项目是指构建的具体产物(jar、apk...)或实施产物(比如将应用部署到生产环境)。一个项目保护一个或多个任务。在Gradle中每一个待构建的工程就是一个Project。
2. Task(任务)
	
	任务是最小的工作单元，指具体执行的工作的统称，一个Task表示一个逻辑上独立的执行过程。比如：编译java源码、检查、拷贝、打包。 构建一个Project需要执行一系列的Task，一个apk的构建包含 java源码编译、资源文件编译、Lint检查、打包生成最终的apk文件等等。
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


4. 生命周期
    1. 初始化创建Settings实例
    2. 解析settings.gradle 构造各个Project实例
    3. 解析每个Project对应的build.gradle，配置相应Project
	
	Gradle并不会一开始就顺序的执行build.gradle中的内容，而是分为几个阶段：配置阶段和执行阶段。
	
	配置阶段：gradle会读取所有build.gradle中的所有内容来配置project和task。比如设置Project和Task的Property，处理Task之间的依赖关系等等。

	执行阶段：根据配置的执行顺序来执行task。

5. Gradle配置文件
	
* gradle.properties
	定义了供build.gradle使用的常量，比如keyStore的存储路径、密码等。
	
* local.properties
	定义了一些本地属性，比如sdk、ndk的路径，gradle代理设置

* setting.gradle
	描述了哪些项目参与构建，如果项目中包含了不止一个Module时，必须在它里面进行包含(include)。可以做一些初始化的工作，在这里设置一些函数，这些函数会在gradle构建整个工程时执行。

    >     //定义一个名为initMinshengGradleEnvironment的函数。该函数内部完成一些初始化操作
    >     //比如创建特定的目录，设置特定的参数等
    >     def initMinshengGradleEnvironment(){
    > 		println"initialize Minsheng Gradle Environment ....."
    > 		......//干一些special的私活....
    > 		println"initialize Minsheng Gradle Environment completes..."
    > 	}
    > 	//settings.gradle加载的时候，会执行initMinshengGradleEnvironment
    > 	initMinshengGradleEnvironment()
    > 	//include也是一个函数：
    > 	include 'CPosSystemSdk' , 'CPosDeviceSdk' , 'CPos
    >     SdkDemo','CPosDeviceServerApk','CPosSystemSdkWizarPosImpl'
    
* gradlew 
	gradlew 为Linux下的shell脚本。gradlew是gradlewrapper的缩写，它对gradle的命令进行了包装。

* gradlew.bat
	windows下的批处理文件


6. 常用命令
gradle projects 查看工程信息
gradle clean 是执行清理任务，和make clean类似。
gradle properites 用来查看所有属性信息。
gradle tasks 查看Project中所有的Task。会列出每个任务的描述，通过描述，我们大概能知道这些任务是干什么的.....。然后gradle task-name执行它就好。




## ****
1. 自定义属性

* 在gradle.properties里添加***配置 属性
* 在local.properties里添加***配置 属性
* 使用ext来声明属性

2. 创建Task

2.1 第一种： 调用Project的task()方法创建Task 。也是最常用的方式。

    task hello << {
      println "hello 1"
    }

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

上面的task 关键字实际上是一个方法调用，该方法属于Project，Project里存在多个重载的task()方法


第二种：通过TaskContainer的create()方法创建Task

    task.create(name:'hello2') << {
        println "hello 2"
    }

第三种：

    **** 

3. 声明Task之间的依赖
    
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

配置Task
    
    一个Task除了执行操作之外，还可以包含多个Property，其中有Gradle为每个Task默认定义的Property，比如description、logger等。另外每个特定的Task还可以拥有特定的Property，比如 Copy的 from,into等。

.添加依赖时，无需所依赖的任务存在，意思可以先添加依赖，然后再定义所依赖的任务

3. Android Plugin

    每个元素的含义和作用。

4. 仓库
	4.1 远程仓库
	
* mavenCentral() ，表示依赖是从Central Maven2仓库中获取
* jcenter()，表示依赖是从Bintary's JCenter Maven仓库中获取，其中jcenter的速度要快于mavenCentral
	

	4.2 本地仓库

5. 依赖管理
	5.1 jar的引用
	5.2 aar的 引用
	5.3 Module引用	
		
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

    
多任务间的关系管理
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
###### Project下每个文件的作用？
怎么添加依赖？

