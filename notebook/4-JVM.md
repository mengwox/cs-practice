# 4.JVM

## 4.1 初识JVM

## 4.2 运行时数据区

### 4.2.1 官网解读

见JVM 1.8版本官网介绍(https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)

### 4.2.2 方法区(Run-time Data Area)

1. The pc Register程序计数器(pc = program counter)

   - The pc Register,程序技术器,pc = program counter
     - 在Oracle官网介绍中,是这样介绍的:
       - Java虚拟机支持多线程并行执行,每一个java虚拟机的线程都有它们自己的一个独立的程序技术器.在任何时间任何地点,每一个程序技术器都随着当前java虚拟机的线程的创建而创建,销毁而销毁.
       - 每一个java虚拟机线程都在当前线程下执行一个简单的当前方法,如果这个方法是native(本地方法)的,那么这个java虚拟机的程序计数器的value就是undefined(也就是空);如果这个方法不是native,则这个java虚拟机的pc的value就是当前java虚拟机需要被执行的方法的字节码指令地址值.
       - java虚拟机程序计数器,能存储的数据类型范围足够广,才能够存储非native方法的指令指针值和特定运行平台的native方法指针

   ```markdown
   The Java Virtual Machine can support many threads of execution at once (JLS §17). Each Java Virtual Machine thread has its own pc (program counter) register. At any point, each Java Virtual Machine thread is executing the code of a single method, namely the current method (§2.6) for that thread. If that method is not native, the pc register contains the address of the Java Virtual Machine instruction currently being executed. If the method currently being executed by the thread is native, the value of the Java Virtual Machine's pc register is undefined. The Java Virtual Machine's pc register is wide enough to hold a returnAddress or a native pointer on the specific platform.
   ```

2. Java Virtual Machine Stacks虚拟机栈

   - 每一个Java虚拟机线程,都有一个私有的java虚拟机栈,随着线程的生命周期,创建或销毁

   - 一个java虚拟机栈,类似于传统语言(比如:C语言)中的stack:它存储了局部变量,部分返回结果.因为java虚拟机栈从来不会操作除(push栈帧,pop栈帧)以外的操作,所以栈帧可能会被分配在heap堆上.

   - 栈帧,一个java方法被执行了,就创建了一个栈帧

     ```markdown
     栈帧介绍:
     	- 每一个栈帧对应一个被调用的方法,可以理解为一个方法的运行空间
     	- 每个栈帧中包括Local Variables(局部变量表),Operand Stacks(操作数栈),Dynamic Linking(动态链接),Normal Method Invocation Completion(正常方法调用返回地址),Abrupt Method Invocation Completion(突然方法调用返回地址)
     ```

     

     - 每一个被线程所执行的方法,被称为该栈中的栈帧,即为每个方法对应的栈帧
     - 调用一个方法,就会向栈中压入一个栈帧;一个方法调用完毕后,就会把该栈帧从栈中弹出

   - java虚拟机栈所占用的内存,不需要连续.这个规格,允许java虚拟机栈的内存大小是固定的,或者可以随着计算而动态扩容或收缩.如果java虚拟机栈的总内存是固定的,那么每一个java虚拟机栈在创建时,都是可以独立设置内存的.
     
     - java虚拟机栈,为程序员和使用者,提供了通过设置参数,来控制每个Java虚拟机栈的内存大小,以及在总内存可动态伸缩时的最大内存和最小内存.
     
   - 有2个异常条件和java虚拟机栈有关:

     - 如果在一个线程中计算需要比允许的Java虚拟机堆栈的最大内存还要大时，Java虚拟机抛出一个StackOverflowError。

     - 如果Java虚拟机栈进行动态扩容时,因为可用内存不足,Java虚拟机抛出一个OutOfMemoryError。

   ```markdown
   Each Java Virtual Machine thread has a private Java Virtual Machine stack, created at the same time as the thread. A Java Virtual Machine stack stores frames (§2.6). A Java Virtual Machine stack is analogous to the stack of a conventional language such as C: it holds local variables and partial results, and plays a part in method invocation and return. Because the Java Virtual Machine stack is never manipulated directly except to push and pop frames, frames may be heap allocated. The memory for a Java Virtual Machine stack does not need to be contiguous.
   
   In the First Edition of The Java® Virtual Machine Specification, the Java Virtual Machine stack was known as the Java stack.
   
   This specification permits Java Virtual Machine stacks either to be of a fixed size or to dynamically expand and contract as required by the computation. If the Java Virtual Machine stacks are of a fixed size, the size of each Java Virtual Machine stack may be chosen independently when that stack is created.
   
   A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of Java Virtual Machine stacks, as well as, in the case of dynamically expanding or contracting Java Virtual Machine stacks, control over the maximum and minimum sizes.
   
   The following exceptional conditions are associated with Java Virtual Machine stacks:
   
   If the computation in a thread requires a larger Java Virtual Machine stack than is permitted, the Java Virtual Machine throws a StackOverflowError.
   
   If Java Virtual Machine stacks can be dynamically expanded, and expansion is attempted but insufficient memory can be made available to effect the expansion, or if insufficient memory can be made available to create the initial Java Virtual Machine stack for a new thread, the Java Virtual Machine throws an OutOfMemoryError.
   ```

3. Heap堆

   - Heap堆,随着java虚拟机的生命周期,创建或销毁. 
   - 堆,是用来分配所有的class实例和数组的内存的运行时数据区,是Java虚拟机所管理的内存中最大的一块
   - 为对象存储的堆,被GC进行回收.对象永远不会被显式地释放掉(GC回收).java虚拟机没有指定特定的内存自动管理存储系统,具体的内存自动管理系统,将根据实现者的系统要求而进行指定.heap堆可能是一个固定内存大小,或者根据计算进行动态的扩容和收缩.heap堆在内存上的分配是不连续的
   - java虚拟机的实现,为使用者和程序员提供了参数,来控制Heap的初始化size;如果heap是能够动态扩容的,还可以控制heap的最大内存和最小内存
   - Heap堆可能引发的异常:
     - 如果计算需要的heap堆内存超出了当前内存自动管理系统分配的Heap内存最大值,java虚拟机将抛出一个OutOfMemoryError

   ```markdown
   The Java Virtual Machine has a *heap* that is shared among all Java Virtual Machine threads. The heap is the run-time data area from which memory for all class instances and arrays is allocated.
   
   The heap is created on virtual machine start-up. Heap storage for objects is reclaimed by an automatic storage management system (known as a *garbage collector*); objects are never explicitly deallocated. The Java Virtual Machine assumes no particular type of automatic storage management system, and the storage management technique may be chosen according to the implementor's system requirements. The heap may be of a fixed size or may be expanded as required by the computation and may be contracted if a larger heap becomes unnecessary. The memory for the heap does not need to be contiguous.
   
   A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of the heap, as well as, if the heap can be dynamically expanded or contracted, control over the maximum and minimum heap size.
   
   The following exceptional condition is associated with the heap:
   
   - If a computation requires more heap than can be made available by the automatic storage management system, the Java Virtual Machine throws an `OutOfMemoryError`.
   ```

4. Method Area方法区

   - 方法区,被所有的java虚拟机线程共享,随着java虚拟机的生命周期,创建或销毁.
   - 方法区,类型于传统语言的编译代码存储区,或者操作系统中的text部分. 它存储每个类的结构，比如运行时常量池、字段和方法数据，以及方法和构造函数的代码，包括在类和实例初始化以及接口初始化中使用的特殊方法。
   - 方法区,随着java虚拟机的启动而创建.尽管方法在逻辑上heap堆的一部分,但是简单的方法区实现可以选择不进行垃圾收集或压缩它.有一个别名叫Non-Heap(非堆),目的是为了和Java堆 区分开来
   - java虚拟机为使用者提供了参数,来控制方法区的初始化内存大小,方法区的最大内存和最小内存
   - 方法区可能引发的异常有:
     - 如果方法区的内存无法再满足一个新的分配请求,java虚拟机将抛出OutOfMemoryError异常
   - 方法区在JDK8中是Metaspace[元数据],在JDK6或7中是Perm Space[永久代]
   - 方法区是线程不安全的

   ```markdown
   The Java Virtual Machine has a *method area* that is shared among all Java Virtual Machine threads. The method area is analogous to the storage area for compiled code of a conventional language or analogous to the "text" segment in an operating system process. It stores per-class structures such as the run-time constant pool, field and method data, and the code for methods and constructors, including the special methods ([§2.9](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.9)) used in class and instance initialization and interface initialization.
   
   The method area is created on virtual machine start-up. Although the method area is logically part of the heap, simple implementations may choose not to either garbage collect or compact it. This specification does not mandate the location of the method area or the policies used to manage compiled code. The method area may be of a fixed size or may be expanded as required by the computation and may be contracted if a larger method area becomes unnecessary. The memory for the method area does not need to be contiguous.
   
   A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of the method area, as well as, in the case of a varying-size method area, control over the maximum and minimum method area size.
   
   The following exceptional condition is associated with the method area:
   
   - If memory in the method area cannot be made available to satisfy an allocation request, the Java Virtual Machine throws an `OutOfMemoryError`.
   ```

5. Run-Time Constant Pool运行时常量池(运行时常量池被JVM分配在方法区内,所以可以算是方法区的一部分)

   - 运行时常量池,是类文件中constant_pool表的每个类或接口的运行时代表.它包含了几种常量,从编译期的已知数值到运行时期的方法和字段的引用. 运行时常量池提供了一个类似于`传统编程语言的符号表`的功能, 尽管它所包含的数据比一个典型的符号表范围要更广.
   - 每一个运行时常量池,被分配在方法区上,运行时常量池随着对应的类或接口在Java虚拟机上的生命周期,创建或销毁
   - 运行时常量池可能引发的异常有:
     - 当创建一个类或接口时, 如果构造一个运行时常量池的内存超出了java虚拟机的方法区能够分配的内存时,jvm将抛出一个OutOfMemoryError的异常
   - 参考Chapter 5装载,链接,初始化中更多的对于运行时常量池的介绍

   ```markdown
   A run-time constant pool is a per-class or per-interface run-time representation of the constant_pool table in a class file (§4.4). It contains several kinds of constants, ranging from numeric literals known at compile-time to method and field references that must be resolved at run-time. The run-time constant pool serves a function similar to that of a symbol table for a conventional programming language, although it contains a wider range of data than a typical symbol table.
   
   Each run-time constant pool is allocated from the Java Virtual Machine's method area (§2.5.4). The run-time constant pool for a class or interface is constructed when the class or interface is created (§5.3) by the Java Virtual Machine.
   
   The following exceptional condition is associated with the construction of the run-time constant pool for a class or interface:
   
   When creating a class or interface, if the construction of the run-time constant pool requires more memory than can be made available in the method area of the Java Virtual Machine, the Java Virtual Machine throws an OutOfMemoryError.
   
   See §5 (Loading, Linking, and Initializing) for information about the construction of the run-time constant pool.
   ```

6. Native Method Stacks本地方法栈

   - java虚拟机的具体实现,可能用传统的栈,(俗称C栈),来支持native方法(一种不同于java编程语言的语言写的方法).native方法栈也可以被Java虚拟机指令集(比如C语言)的解释器的实现所使用. 不能加载本地方法且本身不依赖于传统栈的Java虚拟机不需要提供本地方法栈.如果提供的话,本地方法栈按惯例,将在每个线程的创建而被分配到对应的线程.
- 这个规格,允许本地方法栈,既可以是固定内存大小,也可根据计算的需要进行动态扩容和收缩.如果本地方法栈是固定大小,那么每个本地方法栈在创建时,可以自定义所分配的内存大小.
   - Java虚拟机实现不但为程序员和使用者提供了控制本地方法栈初始化内存分配大小的参数设置, 而且还提供了参数来设置动态扩容和收缩的本地方法栈的最大和最小方法栈内存.
   - 本地方法栈可能引起的异常有:
     - 如果一个线程的计算需要的本地方法栈超出了限制,java虚拟机将抛出StackOverflowError
     - 如果本地方法栈是可以动态扩容的,当本地方法栈尝试进行扩容但是因为内存不足而失败了,或者一个新的线程创建初始化本地方法栈时因为内存不足而失败, java虚拟机将抛出OutOfMemoryError
   
   ```markdown
   An implementation of the Java Virtual Machine may use conventional stacks, colloquially called "C stacks," to support native methods (methods written in a language other than the Java programming language). Native method stacks may also be used by the implementation of an interpreter for the Java Virtual Machine's instruction set in a language such as C. Java Virtual Machine implementations that cannot load native methods and that do not themselves rely on conventional stacks need not supply native method stacks. If supplied, native method stacks are typically allocated per thread when each thread is created.
   
   This specification permits native method stacks either to be of a fixed size or to dynamically expand and contract as required by the computation. If the native method stacks are of a fixed size, the size of each native method stack may be chosen independently when that stack is created.
   
   A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of the native method stacks, as well as, in the case of varying-size native method stacks, control over the maximum and minimum method stack sizes.
   
   The following exceptional conditions are associated with native method stacks:
   
   If the computation in a thread requires a larger native method stack than is permitted, the Java Virtual Machine throws a StackOverflowError.
   
   If native method stacks can be dynamically expanded and native method stack expansion is attempted but insufficient memory can be made available, or if insufficient memory can be made available to create the initial native method stack for a new thread, the Java Virtual Machine throws an OutOfMemoryError.
   ```

![image-20201226150906572](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201226150906572.png)

#### 总结:

1. 生命周期

   ```markdown
   方法区,堆,运行时常量池的生命周期和JVM一样.
   虚拟机栈,本地方法栈,程序计数器的生命周期和JVM的线程一样.
   ```

2. 运行时数据区的各个区域可能会报什么错?

   ```markdown
   StackOverflowError,栈溢出异常,只有虚拟机栈和本地方法栈
   OutOfMemoryError,内存溢出异常,6个区都有
   ```


## 4.3 JVM内存模型

![image-20201227110234047](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201227110234047.png)

![image-20201227110821606](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201227110821606.png)

3种GC:

- Full GC = Old区和Young区都进行GC

- Minor GC = Young GC

- Old GC = Major GC

Young区分为Eden区和Survivor区(Survivor区又分为S0和S1), Eden : S0 : S1 = 8 : 1 : 1

```markdown
使用jvisualvm工具，实验得到的内存空间占用比为：
Old:Eden:S0:S1 = 6:3:1:1,这个占比问题，默认占比见IntelliJ Platform
Old:Eden:S0:S1 = 20.44:8:1:1
```

Survivor区：分为两块S0和S1,也可以叫做From和To.在同一个时间节点上，S0和S1只能有一个区有数据，另外一个是空的。

Old区：一般都是年龄比较大的对象，或者相对超过了某个阈值的对象

========================================================

结合工具进行验证：

1. 使用官网提供的自带工具，命令行jvisualvm，链接：https://visualvm.github.io/
2. 堆内存溢出：设置参数，例如：-Xmx20M -Xms20M
3. 方法区内存溢出：-XX：MetaspaceSize=50M -XX:MaxMetaspaceSize=50M
4. 虚拟机栈溢出：-Xss128K

## 4.4 垃圾回收

### 4.4.1 如何确定一个对象是垃圾？

1. 引用计数法

   ```markdown
   引用计数法可能存在的问题：
   一旦存在相互持有引用，将会导致对象永远没法被GC回收掉。
   ```

2. 可达性分析

   ```markdown
   由GC Root出发，开始寻找，看看某个对象是否可达
   GC Root:类加载器，Thread类，本地变量表，static成员，常用引用，本地方法栈中的变量等。
   ```


### 4.4.2 分代收集算法

这些垃圾回收的算法，在本质上都是为了在GC后确保内存地址的连续性

1. 标记清除![image-20201229211313197](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211313197.png)![image-20201229211323205](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211323205.png)
2. 复制![image-20201229211332681](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211332681.png)![image-20201229211339757](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211339757.png)
3. 标记整理![image-20201229211347492](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211347492.png)![image-20201229211355787](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211355787.png)

Young区：复制算法

Old区：标记清除算法，标记整理算法

#### 垃圾收集器

1. Serial
2. Serial Old
3. ParNew
4. Parallel
5. Parallel Old
6. CMS
7. G1

![image-20201229211622174](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211622174.png)

#### Serial

应用程序线程在Serial GC线程执行的过程中，会处于阻塞状态，暂停执行。

且，Serial GC线程是单线程执行

![image-20201229211646809](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211646809.png)

#### ParNew

应用程序线程在ParNew GC线程执行过程中，处于阻塞暂停状态。

ParNew GC线程是多线程执行的。

![image-20201229211801791](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229211801791.png)

#### Parallel

相比ParNew，Parallel更关注吞吐量。

#### 吞吐量和停顿时间

![image-20201229213849700](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229213849700.png)

#### CMS（Concurrent Mark Sweep）

CMS更关注GC停顿时间，分为4个阶段：其中，初始标记和重新标记仍然是“Stop The World”

- 初始标记
- 并发标记
- 重新标记
- 并发清除

![image-20201229212100501](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229212100501.png)

#### G1

G1更关注GC停顿时间，用户可以设置一个预期的停顿时间

![image-20201229212152995](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229212152995.png)

#### 垃圾回收器分类总结：

1. 串行回收器：Serial和Serial Old。

   - 只能有一个垃圾回收器执行，执行过程中用户线程暂停。

   - 适用场景：适合用于内存较小的嵌入式设备

2. 并行回收器【吞吐量优先】：ParNew, Parallel Scavenge, Parallel Old

   - 多条垃圾回收器线程并行工作，但执行过程中用户线程仍然处于暂停状态。
   - 适用于科学计算、后台处理等若干交互场景。

3. 并发回收器【停顿时间优先】：CMS,G1

   - 用户线程和垃圾回收线程同时执行（但不一定是并行的，可能是交替执行的），垃圾回收线程在执行的时候不会停顿用户线程的运行。
   - 适用于相对有时间要求的场景，比如web端。

#### 如何选择垃圾回收器

- 优先调整堆的大小，让服务器自己选择垃圾回收器
- 如果内存小于100M，使用串行回收器
- 如果CPU是单核的，且没有停顿时间要求，使用串行或JVM自己选择垃圾回收器
- 如果允许停顿时间超过1秒，选择并行或JVM自己选择
- 如果响应时间最重要，且不能超过1秒，使用并发垃圾回收器

#### 如何开启垃圾回收器

![image-20201229214731780](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229214731780.png)

## 4.5 工欲善其事必先利其器

### 4.5.1 JVM参数

1. 标准参数

2. -X参数

3. -XX参数

   ```markdown
   -XX:[+/-] 用于增加或删除，例如：-XX:+UseG1GC,表示告诉JVM，使用G1GC进行垃圾回收
   -XX:<name>=<value> 例如：-XX:InitialHeapSize=100M
   ```

4. 其他参数

   ```markdow
   -Xms100M 等同于-XX:InitialHeapSize=100M(告诉JVM，初始化heap堆内存大小为100M)
   -Xmx100M -Xss100
   ```

   ![image-20201229215557016](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229215557016.png)

### 4.5.2 JVM命令

1. jps：查看当前的java进程

2. jinfo：查看某个java进程目前的参数设置的情况

3. jstat：查看java进程统计性能

4. jstack：查看当前java进程的堆栈信息

5. jmap：打印出堆转存储快照

   ```markdown
   jmap -heap PID
   dump出堆内存相关信息：jmap dump:format=b,file=heap.hprof 【PID】
   ```

### 4.5.3 常用工具

1. jconsole，jvm自带
2. jvisualvm，jvm自带
3. arthas
4. mat/perfma：内存相关信息
5. gceasy.io/gcviewer

## 4.6 性能优化

#### JVM全局理解图

![image-20201229220259231](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229220259231.png)

### 4.6.1 OOM

通过MAT分析dump文件，定位OOM

### 4.6.2 GC优化

通过不断调整，观察GC日志的吞吐量和停顿时间，寻找最佳值

 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+UseG1GC -Xloggc:gc.log gceasy.io 

主要就是调整各种参数，垃圾收集器--->查看吞吐量和停顿时间的变量

最终要达到的目的：高吞吐量，低停顿时间

### 4.6.3 性能优化指南

![image-20201229220425998](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201229220425998.png)

