# 8 Spring

## 8.1 Spring框架初探

### 8.1.1 Spring架构全览

#### Spring 简化开发的4个策略:

1. 基于POJO的轻量级和最小侵入性编程
2. 通过依赖注入DI和面向接口松耦合
3. 基于AOP的切面和惯性进行声明式编程
4. 通过AOP和模板减少样板式代码

#### Spring5架构图

![image-20210226085110720](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210226085110720.png)

#### Spring之核心模块

| 模块名称               | 主要功能                                  |
| ---------------------- | ----------------------------------------- |
| spring-core            | 依赖注入IOC与DI的最基本实现               |
| spring-beans           | Bean工厂与Bean的装配                      |
| spring-context         | 定义基础的Spring的Context上下文,即IOC容器 |
| spring-context-support | 对Spring IOC的扩展支持,以及IOC子容器      |
| spring-context-indexer | Spring的类管理组件和Classpath扫描         |
| spring-expression      | Spring表达式语言                          |

#### Spring之切面编程

| 模块名称          | 主要功能                                      |
| ----------------- | --------------------------------------------- |
| spring-aop        | 面向切面编程的应用模块,整合ASM,CGLib,JDKProxy |
| spring-aspect     | 集成AspectJ,AOP应用框架                       |
| spring-instrument | 动态Class Loading模块                         |

#### Spring之数据访问与集成

| 模块名称    | 主要功能                                                     |
| ----------- | ------------------------------------------------------------ |
| spring-jdbc | Spring提供的JDBC抽象框架的主要实现模块,用于简化Spring JDBC操作 |
| spring-tx   | Spring JDBC事务控制实现模块                                  |
| spring-orm  | 主要集成Hibernate,Java Persistence API(JPA) 和 Java Data Object(JDO) |
| spring-oxm  | 将java对象映射成xml数据,或者将xml数据映射成java对象          |
| spring-jms  | Java Messaging Service能够发送和接受信息                     |

#### Spring之Web组件

| 模块名称         | 主要功能                                                     |
| ---------------- | ------------------------------------------------------------ |
| spring-web       | 提供了最基础的web支持,主要建立在核心容器上,通过Servlet或者listeners来初始化IOC容器 |
| spring-webmvc    | 实现了Spring MVC(model-view-controller)的web应用             |
| spring-websocket | 主要是与Web前端的全双工通讯的协议                            |
| spring-webflux   | 一个新的非堵塞函数式Reactive Web框架,可以用来建立异步的,非阻塞的,事件驱动的服务 |

#### Spring之通信报文

| 模块名称         | 主要功能                                                     |
| ---------------- | ------------------------------------------------------------ |
| spring-messaging | 从Spring4开始新加入的一个模块,主要职责是为Spring框架集成一些基础的报文传送应用 |

#### Spring之集成测试

| 模块名称    | 主要功能 |
| ----------- | -------- |
| spring-test | 测试支持 |

#### Spring之集成兼容

| 模块名称             | 主要功能                                                    |
| -------------------- | ----------------------------------------------------------- |
| spring-framework-bom | Bill of Materials. 解决Spring的不同模块所依赖版本不同的问题 |

#### Spring各模块之间的依赖关系

![image-20210226085204140](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210226085204140.png)

### 8.1.2 Spring源码版本升级命名规则

#### 语义化版本命名通行规则

![image-20210226085225260](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210226085225260.png)

#### 商业软件中的常见版本修饰词

![image-20210226085243002](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210226085243002.png)

#### Spring版本命名规则

![image-20210226085302169](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210226085302169.png)

## 8.2 Spring5框架高级应用

### 8.2.1 Spring IOC介绍

IOC,Inversion Of Controller, 控制反转,本质上是一种代码编程思想,在Spring中体现为,对象创建的控制权交由Spring容器进行统一管理,需要对象的时候,直接从Spring容器中拿.

### 8.2.2 基于XML配置文件方式的使用

##### 使用bean.id和bean.name

```markdown
通过xml的bean.id和name来获取bean

ApplicationContext的使用

applicationContext.xml中bean.id与bean.name的区别:

1. 根据ID,id="user1,user2,user3",是唯一标识,不能被拆分成user1,user2,user3 3个
2. 根据name,name="u1,u2,u3",非唯一标识,可以被"," ";" " "拆分为u1,u2,u3 3个name
```

##### 使用Class类型

```markdown
通过applicationContext,传入bean.class类型,来获取bean
```

##### 相关面试题

1. BeanFactory和ApplicationContext的区别,和FactoryBean的区别?

   ```markdown
   
   ```

##### 工厂注入的2种方式:静态工厂注入和动态工厂注入

##### 其他注入类型

### 8.2.3 基于Java配置类的方式实现IOC

SpringBoot流行后的主流JavaBean配置

### 8.2.4 总结

1. 基于xml文件的方式配置applicationContext
2. 基于javaBean配置类的方式配置applicationContext

使用Controller-Service-Dao三层架构,实现以上2种IOC注入方式,并进行单元测试,并了解`@Component,@Controller,@Service,@Repository`的作用和区别,掌握如何限制指定目录下的注解,include和exclude



##### 面试题:

1. `@Component,@Controller,@Service,@Repository`的作用和区别?

2. `@Autowired`和`@Resource`的区别?

   ```markdown
   @Autowired,是springframework提供的注解,而@Resource是jdk提供的注解
   
   @Autowired源码内,默认只能根据类型进行查找匹配注入的,有布尔值required,没有name属性;如果想使用@Autowired进行名称的方式注入,需搭配@Qualifier一起使用,@Qualifier有字符串类型值value.
   
   @Resource源码类,默认也是根据类型进行查找匹配注入的,但是它既有name,也有type属性,所以它既可以根据名称注入,也可以根据类型注入.
   ```

3. @Value中使用${}和#{}有什么区别?