# 官网解读

见[JVM 1.8版本官网介绍](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)

# 类(.class)加载机制

## 概要

理解为 `.class`文件到JVM的这个过程.

- 当类文件不是一个array class时:
  - 通过使用ClassLoader
    - 有2类ClassLoader
      - bootstrap class loader (JVM提供的ClassLoader)
      - user-defined class loders (自定义ClassLoader), 必须是抽象类ClassLoader的子类
  - 加载二进制引用(binary representation) 相当于分配内存地址值
  - 加载一个二进制引用(binary representation)
  - 完成类文件的创建(Creation)
- 当类文件是一个array class时, 将会直接被JVM创建(Creation)

Loading, Linking And Initializing

- Loading
  - 找到类文件所在位置
  - 将类文件的信息交给JVM
  - 将类文件所对应的对象Class加载到JVM
- Linking
  - 验证verification: 确保二进制引用在结构正确
  - 准备preparation: 为类创建static fields
    - 不做**显式**初始化操作(因为这是Initializing的部分)
    - 只根据field type, 做**默认值**的初始化操作
    - 比如int i = 10; 在该步, i只会初始化为int默认值**0**
  - 解析resolution: 将部分JVM指令的符号引用, 解析为运行时常量池(Run-Time Constant Pool)的直接引用
- Initializing: 完成class或interface里的显式初始化方法(initialization method)
  - 比如int i = 10; 在该步, i将会由**int默认值0变更为显式值10**

## Loading

装载, 通过一个特殊的名称(全限定路径), 找到class或interface的二进制表示, 并通过二进制表示创建一个class或interface的过程

```markdown
Loading, is the process of finding the binary representation of a class or interface type with
a particular name and _creating_ a class or interface from that binary representation.
```

### 找到.class文件的位置

```text
// name为.class文件的全路径名; 找到.class文件的位置
ClassLoader.find(name)
```

如下图, ClassLoader按功能模块划分, 使用双亲委派机制, 完成了.class文件装载到JVM中:

1. son classLoader装载时, 优先让parent ClassLoader装载
2. 当parent完成某个name的装载时, son将不会在对同样的name进行装载.

![ClassLoader](md-images/classLoader.jpg)

### 类文件信息到JVM

## Linking

## Initialization

# JVM Run-Time Data Area

![JRT Data Area](md-images/JRT-DataArea.jpg)

## 程序计数器 PC Register

**The pc Register(pc, program counter)**

- 线程**私有**
- PC Register记录当前线程执行的method
  - if method is native(本地方法), PC Register.value=undefined(也就是空)
  - if method isn't native, PC Register.value=被执行方法的字节码指令地址值.
- PC Register存储的数据类型范围足够广, 广到能够存储非native方法的指令指针值和特定运行平台的native方法指针

## JVM虚拟机栈

- 每一个Java虚拟机线程,都有一个私有的java虚拟机栈,随着线程的生命周期,创建或销毁
- 一个java虚拟机栈,类似于传统语言(比如:C语言)中的stack:它存储了局部变量,部分返回结果.因为java虚拟机栈从来不会操作除(
  push栈帧,pop栈帧)以外的操作,所以栈帧可能会被分配在heap堆上.
- 栈帧,一个java方法被执行了,就创建了一个栈帧
- 每一个栈帧对应一个被调用的方法,可以理解为一个方法的运行空间
- 每个栈帧中包括:
  - Local Variables(局部变量)
  - Operand Stacks(操作数栈)
  - Dynamic Linking(动态链接)
  - Normal Method Invocation Completion(正常方法调用返回地址)
  - Abrupt Method Invocation Completion(突然方法调用返回地址)
- 每一个被线程所执行的方法,被称为该栈中的栈帧,即为每个方法对应的栈帧
- 调用一个方法,就会向栈中压入一个栈帧;一个方法调用完毕后,就会把该栈帧从栈中弹出
- java虚拟机栈所占用的内存,不需要连续.这个规格,允许java虚拟机栈的内存大小是固定的,或者可以随着计算而动态扩容或收缩.如果java虚拟机栈的总内存是固定的,那么每一个java虚拟机栈在创建时,都是可以独立设置内存的.
  - java虚拟机栈,为程序员和使用者,提供了通过设置参数,来控制每个Java虚拟机栈的内存大小,以及在总内存可动态伸缩时的最大内存和最小内存.
- 有2个异常条件和java虚拟机栈有关:
  - 如果在一个线程中计算需要比允许的Java虚拟机堆栈的最大内存还要大时,Java虚拟机抛出一个StackOverflowError。
  - 如果Java虚拟机栈进行动态扩容时,因为可用内存不足,Java虚拟机抛出一个OutOfMemoryError。

## Heap堆

- 线程公有
- 功能: 用来分配所有的class实例和数组的内存的运行时数据区, 是Java虚拟机所管理的内存中最大的一块
- 为对象存储的堆, 被GC进行回收. 对象永远不会被显式地释放掉(GC回收)
- Heap可以是一个固定内存大小, 也可以根据计算进行动态的扩容和收缩.
- Heap在内存上的分配是不连续的
- 可通过参数,来控制Heap的初始化size;如果heap是能够动态扩容的,还可以控制heap的最大内存和最小内存
- 可能引发的异常:
  - 如果计算需要的heap堆内存超出了当前内存自动管理系统分配的Heap内存最大值,java虚拟机将抛出一个OutOfMemoryError

## Method Area方法区

- 线程公有
- 类似于传统语言的编译代码存储区,或者操作系统中的text部分.
- 存储每个类的结构,比如运行时常量池、字段和方法数据,以及方法和构造函数的代码,包括在类和实例初始化以及接口初始化中使用的特殊方法.
- 尽管方法在逻辑上heap堆的一部分,但是简单的方法区实现可以选择不进行垃圾收集或压缩它.
- 有一个别名叫Non-Heap(非堆),目的是为了和Heap区分开来
- 可以通过参数,来控制初始化内存大小,最大内存和最小内存
- 方法区可能引发的异常有:
  - 如果方法区的内存无法再满足一个新的分配请求,java虚拟机将抛出OutOfMemoryError异常
- 方法区在JDK8中是Metaspace[元数据],在JDK6或7中是Perm Space[永久代]
- 方法区是线程不安全的

## Run-Time Constant Pool运行时常量池

运行时常量池被JVM分配在方法区内,所以可以算是方法区的一部分

- 运行时常量池,是类文件中constant_pool表的每个类或接口的运行时代表.它包含了几种常量,从编译期的已知数值到运行时期的方法和字段的引用.
  运行时常量池提供了一个类似于`传统编程语言的符号表`的功能, 尽管它所包含的数据比一个典型的符号表范围要更广.
- 每一个运行时常量池,被分配在方法区上,运行时常量池随着对应的类或接口在Java虚拟机上的生命周期,创建或销毁
- 运行时常量池可能引发的异常有:
  - 当创建一个类或接口时, 如果构造一个运行时常量池的内存超出了java虚拟机的方法区能够分配的内存时,jvm将抛出一个OutOfMemoryError的异常
- 参考Chapter 5装载,链接,初始化中更多的对于运行时常量池的介绍

## Native Method Stacks本地方法栈

- 又称C栈,是native方法, native方法栈也可以被Java虚拟机指令集(比如C语言)的解释器的实现所使用.
  不能加载本地方法且本身不依赖于传统栈的Java虚拟机不需要提供本地方法栈.如果提供的话,本地方法栈按惯例,将在每个线程的创建而被分配到对应的线程.

- 这个规格,允许本地方法栈,既可以是固定内存大小,也可根据计算的需要进行动态扩容和收缩.如果本地方法栈是固定大小,那么每个本地方法栈在创建时,可以自定义所分配的内存大小.
  - Java虚拟机实现不但为程序员和使用者提供了控制本地方法栈初始化内存分配大小的参数设置,
    而且还提供了参数来设置动态扩容和收缩的本地方法栈的最大和最小方法栈内存.
  - 本地方法栈可能引起的异常有:
    - StackOverFlow: 当一个线程的计算需要的栈帧数超出栈的深度时
    - OOM: 如果本地方法栈是可以动态扩容的,当本地方法栈尝试进行扩容但是内存不足而失败了,或者一个新的线程创建初始化本地方法栈时因为内存不足而失败

## 总结

| 比较                | 线程私有/公有 | 会OOM   | 会StackOverFlow |
| ------------------- | ------------- | ------- | --------------- |
| Method Area         | 公有          | Yes     | No              |
| Heap                | 公有          | Yes     | No              |
| JVM Stack           | 私有          | Yes     | Yes             |
| Native Method Stack | 私有          | Yes     | Yes             |
| PC Register         | 私有          | Unknown | Unknown         |

# JMM (JVM内存模型)

3种GC:

- Full GC = Old区和Young区都进行GC
- Minor GC = Young GC
- Major GC = Old GC

Young区分为Eden区和Survivor区(Survivor区又分为S0和S1),默认比例:

Eden : S0 : S1 = 8 : 1 : 1

```markdown
使用jvisualvm工具,实验得到的内存空间占用比为：
Old:Eden:S0:S1 = 6:3:1:1,这个占比问题,默认占比见IntelliJ Platform
Old:Eden:S0:S1 = 20.44:8:1:1
```

Survivor区：分为两块S0和S1,也可以叫做From和To.

在同一个时间节点上,S0和S1只能有一个区有数据,另外一个是空的。

Old区：一般都是年龄比较大的对象,或者相对超过了某个阈值的对象

---
结合工具进行验证：

1. 使用官网提供的自带工具,命令行jvisualvm,链接：https://visualvm.github.io/
2. 堆内存溢出：设置参数,例如：-Xmx20M -Xms20M
3. 方法区内存溢出：-XX：MetaspaceSize=50M -XX:MaxMetaspaceSize=50M
4. 虚拟机栈溢出：-Xss128K

# 垃圾回收

## 如何确定一个对象是垃圾？

1. 引用计数法
   
   ```markdown
   引用计数法可能存在的问题：
   一旦存在相互持有引用,将会导致对象永远没法被GC回收掉。
   ```

2. 可达性分析
   
   ```markdown
   由GC Root出发,开始寻找,看看某个对象是否可达
   GC Root:类加载器,Thread类,本地变量表,static成员,常用引用,本地方法栈中的变量等。
   ```

## 分代收集算法

这些垃圾回收的算法,在本质上都是为了在GC后确保内存地址的连续性

1. 标记清除
2. 复制
3. 标记整理

Young区：复制算法

Old区：标记清除算法,标记整理算法

### 垃圾收集器

1. Serial
2. Serial Old
3. ParNew
4. Parallel
5. Parallel Old
6. CMS
7. G1

![JVM8垃圾收集器](./md-images/jvm-collectors.jpg)

#### Serial

应用程序线程在Serial GC线程执行的过程中,会处于阻塞状态,暂停执行。

且,Serial GC线程是单线程执行

#### ParNew

应用程序线程在ParNew GC线程执行过程中,处于阻塞暂停状态。

ParNew GC线程是多线程执行的。

#### Parallel

相比ParNew,Parallel更关注吞吐量。

#### 吞吐量和停顿时间

- 停顿时间: 垃圾收集器进行垃圾回收时, 终端所需要等待的时间
- 吞吐量: 用户代码执行时间 / (用户代码执行时间 + 垃圾回收时间)

#### CMS（Concurrent Mark Sweep）

CMS更关注GC停顿时间,分为4个阶段：其中,初始标记和重新标记仍然是“Stop The World”

- 初始标记
- 并发标记
- 重新标记
- 并发清除

#### G1

G1更关注GC停顿时间,用户可以设置一个预期的停顿时间

### 垃圾回收器分类总结：

1. 串行回收器：Serial和Serial Old。

     - 只能有一个垃圾回收器执行,执行过程中用户线程暂停。

     - 适用场景：适合用于内存较小的嵌入式设备


2. 并行回收器【吞吐量优先】：ParNew, Parallel Scavenge, Parallel Old

     - 多条垃圾回收器线程并行工作,但执行过程中用户线程仍然处于暂停状态。
     

     - 适用于科学计算、后台处理等若干交互场景。

3. 并发回收器【停顿时间优先】：CMS,G1

     - 用户线程和垃圾回收线程同时执行（但不一定是并行的,可能是交替执行的）,垃圾回收线程在执行的时候不会停顿用户线程的运行。
     

     - 适用于相对有时间要求的场景,比如web端。


### 如何选择垃圾回收器

- 优先调整堆的大小,让服务器自己选择垃圾回收器
- 如果内存小于100M,使用串行回收器
- 如果CPU是单核的,且没有停顿时间要求,使用串行或JVM自己选择垃圾回收器
- 如果允许停顿时间超过1秒,选择并行或JVM自己选择
- 如果响应时间最重要,且不能超过1秒,使用并发垃圾回收器

### 如何开启垃圾回收器

- 启动参数添加, 例如: `-XX:UseG1GC`

### JVM参数

1. 标准参数

     - 不会随着java版本更新而变化

     - -X参数


2. 非标准参数

     - -XX参数
     

     - Boolean类型
     

     - 非Boolean类型
       
        ```markdown
        -XX:[+/-] 用于增加或删除,例如：-XX:+UseG1GC,表示告诉JVM,使用G1GC进行垃圾回收
        -XX:<name>=<value> 例如：-XX:InitialHeapSize=100M
        ```



3. 其他参数

     - 相当于-XX参数的简写
       
        ```text
        //告诉JVM,初始化heap堆内存大小为100M
        -Xms100M = -XX:InitialHeapSize=100M
        -Xmx100M = -XX:MaxHeapSize=100M
        -Xss100
        ```


### JVM命令

1. jps：查看当前java进程

2. jinfo：查看特定java进程目前的参数设置的情况, 并且可以对参数进行实时修改

3. jstat：查看java进程统计性能

4. jstack：查看当前java进程的堆栈信息

5. jmap：打印出堆转存储快照
   
   ```markdown
   jmap -heap PID
   dump出堆内存相关信息：jmap dump:format=b,file=heap.hprof 【PID】
   ```

### 常用工具

1. jconsole,jvm自带
2. jvisualvm,jvm自带
3. arthas
4. mat/perfma：内存相关信息
5. gceasy.io/gcviewer
6. gcviewer, 分析GC日志工具

# 性能优化

### JVM全局理解图

### OOM

通过MAT分析dump文件,定位OOM

### GC优化

通过不断调整,观察GC日志的吞吐量和停顿时间,寻找最佳值

-XX:+PrintGCDetails, 打印GC详情信息

-XX:+PrintGCTimeStamps, 打印GC时间点

-XX:+PrintGCDateStamps, 打印GC时间点

-XX:+UseG1GC, 使用G1垃圾收集器

-Xloggc:gc.log gceasy.io, 指定GC日志存储位置

主要就是调整各种参数,垃圾收集器--->查看吞吐量和停顿时间的变量

最终要达到的目的：高吞吐量,低停顿时间

### 性能优化指南

​							
