# IO流

## 3.1 初步认识IO流

### 3.1.1 什么是IO流,以及IO流的作用

#### 什么是IO流

I/O流,实际上是将数据通过二进制字节流的形式,进行传输.input/output,也就是输入和输出.而流是一个抽象的概念,它实际上是数据的无结构化传递

#### IO流的作用

用来做数据的传递,inputstream完成数据的输入,outputstream完成数据的输出.

### 3.1.2 java中的IO流以及IO流的分类

#### 从本质上就是5类:

1. File
2. InoutStream
3. OutPutStream
4. Reader
5. Writer

#### IO流的分类:

- 字节流:操作的数据单元是8位的字节.Inputstream,Outputstream,作为抽象基类
- 字符流:操作的数据单元是字符.以Writer,Reader作为抽象基类

![image-20201215221939034](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20201215221939034.png)

![image-20201215222320921](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20201215222320921.png)

### 3.1.3 IO流应用实战

```java
package com.mawenhao.thread_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("E:\\demo.txt");
            //fileInputStream = new FileInputStream("E:/demo.txt");
            int i;
            while ((i = fileInputStream.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## 3.2 深入分析IO流

### 3.2.2 本地磁盘文件操作之File类

### 3.2.3 基于文件的字节输入输出流

```java
public static void main(Stringp[] args) throws IOException{
    File file = new File("E:/data/log.png");
    FileInputStream inputStream = null;
    FileOutputStream outputStream = null;
    try{
        inputStream = new FileInputStream(file);
        outputStream = new FileOutStream();
        int i = 0;
        byte[] bytes = new byte[1024];//这里使用bytes数组,进行数据缓冲,每1024个字节进行一次输出
        while((i = inputStream.read(bytes))){
            outputStream.write(bytes,0,i);//这里,使用0,i,可以避免输出文件的硬盘内存的多余占用,如果文件只有512B大小,输出后的文件也会只是512B的大小
        }
    }catch(Exception e){
        log.error(e.getMsg());
    }finally{
        if(outputStream != null){
            try{
                outputStream.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        if(inputStream != null){
            try{
                inputStream.close();
            }catch(Exception e){
                log.error(e);
            }
        }
    }
}
```

### 3.2.4 基于内存的字节输入输出流

```java
public static void main(String[] args) {
    ByteArrayInputStream byteArrayInputStream = null;
    ByteArrayOutputStream byteArrayOutputStream = null;
    String str = "hello world";
    try{
        byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        byte[] bytes = new bytes[1024];
		while((i = byteArrayInputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(new String(bytes,0,i).toUpperCase().getBytes());
        }
        System.out.println(byteArrayOutputStream.toString());
    }catch(Exception e){
        log.error(e);        
    }finally{
        if(byteArrayOutputStream != null){
            try{
                byteArrayOutputStream.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        if(byteArrayInputStream != null){
            try{
                byteArrayOutputStream.close();
            }catch(Exception e){
                log.error(e);
            }
        }
    }
}
```

### 3.2.5 基于缓冲流的字节输入输出流

这种基于缓冲流的IO读写操作,比普通的FileIO类的操作还要快

```java
public static void main(String[] args){
    try(
    	FileInputStream fileInputStream = new FileInputStream("E:/data/mic.zip");
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream("E:/data/mic_cp.zip");
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
    ){
        int i = 0;
        byte[] bytes = new byte[1024];
        while( (i = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, i);
        }
    }
}
```

### 3.2.6 详解Flush()方法

```java
//这种情况下,能正常输出
public static void main(String[] args) throws IOException{
    try(
    	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("E:/data/mic.txt"));
    ){
        outputStream.write("hello world".getBytes());
    }
}

//这种情况下,文件能创建成功,但是无法正常输出
public static void main(String[] args) throws IOException{
    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("E:/data/mic.txt"));
    outputStream.write("hello world".getBytes());
}

//这种情况下,文件能创建成功,可以正常输出
public static void main(String[] args) throws IOException{
    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("E:/data/mic.txt"));
    outputStream.write("hello world".getBytes());
    outputStream.flush();//刷盘动作
}
//这种情况下,文件能创建成功,可以正常输出
public static void main(String[] args) throws IOException{
    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("E:/data/mic.txt"));
    outputStream.write("hello world".getBytes());
    outputStream.close();//close方法,源码内包含了刷盘动作
}
```

### 3.2.7 基于文件的字节输入输出流

## 3.3网络IO

### 3.3.1 初步理解网络IO



### 3.3.2 Socket和ServerSocket



### 3.3.3 分析Socket



### 3.3.4 Socket网络IO的原理分析



### 3.3.5 基于Socket手写RPC框架



## 3.4 深入分析NIO

什么是NIO?

- NIO,New IO,是一种新的IO,不同于传统IO,NIO是为了服务器能够承载更多的请求,而在传统IO上进行了优化,它解决了BIO的阻塞问题

### 3.4.1彻底搞懂阻塞(BIO)和非阻塞(NIO)

同步,异步;阻塞和非阻塞的区别:

同步请求:客户端发起一个请求,这个请求需要同步等待结果,而在等待结果之前,客户端一直处于阻塞状态

异步请求:客户端发起一个请求,这个请求不需要同步等待结果,在等待结果返回之前,客户端处于非阻塞状态,可以做其他的事情.服务端异步返回请求.

同步和异步,阻塞和非阻塞是看待的事情的角度不一样

### 3.4.2 深入分析5种IO模型

各种各样的IO模型,都是为了提升服务端并行处理数量

1. 阻塞IO![image-20201222230246212](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222230246212.png)

2. 非阻塞IO![image-20201222230359908](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222230359908.png)

3. IO复用(多路复用), linux的select/poll, epoll模型(内核轮询)![image-20201222230514405](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222230514405.png)

   select/poll,受限于单个进程所能打开的fd(),默认1024

   在JDK1.6之后,进行了优化,有了epoll:

   	- 对单个进程所打开的连接数没有限制.
   	- 利用每个fd上的callback回调函数来实现异步回调
   	- mmap(减少内存复制),有点像AIO

4. 信号驱动![image-20201222231537236](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222231537236.png)

5. 异步IO![image-20201222231436884](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222231436884.png)

### 3.4.3 NIO的概述及应用

NIO,是JDK1.4提出来的,是非阻塞IO, 实际上是New IO

![image-20201222231848682](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20201222231848682.png)

#### NIO的核心组件

- Channel通道 : Java NIO数据来源,可以是网络,也可以是本地磁盘
- Buffer缓冲区 : 数据读写的中转区
- Selectors选择器 : 异步IO的核心类,可以实现异步非阻塞IO,一个selectors可以管理多个通道Channel

```java
package com.mawenhao.io.iodemo.version1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FirstNIODemo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("D:/test.txt"));
            FileChannel fcin = fis.getChannel();
            FileOutputStream fos = new FileOutputStream(new File("D:/test_copy.txt"));
            FileChannel fcout = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(3);//设置缓冲区的容量大小,capacity = 3;(该属性是父类Buffer的)
            while (true){
                if(fcin.read(byteBuffer) == -1){
                    break;
                }
                byteBuffer.flip();
                fcout.write(byteBuffer);
                byteBuffer.clear();//清除缓存(必备,如果没有,position会一直是3),经测试,实际上是将position置为0
            }
            fcout.close();
//            fos.close();//这里不需要close了,经测试,在channel的close方法里已经close
            fcin.close();
//            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.4.4 NIO详解Channel和Buffer

### 3.4.5 剖析缓冲区的内部细节

### 3.4.6 剖析零拷贝的原理

### 3.4.7 SocketChannel和ServerSocketChannel

### 3.4.8 深入分析Selector选择器



