# java注解

## 注解的概念

注解：说明程序的，给计算机看的。

注释：说明代码的，给开发人员看的。

> 定义：
>
> ​	注解,Annotation，也叫元数据，是一种代码级别的说明。它是<font color ='red'>JDK1.5</font>及之后版本引入的一个特性。与类、接口、枚举是在同一个层次。它可以声明在包、类、字段、方法、局部变量、方法参数等前面，用来对这些元素进行说明，注释。
>
> 作用分类：
>
> 1. 编写文档：通过代码里标识的注解生成javaDoc文档。
> 2. 代码分析：通过代码里标识的注解对代码进行分析，使用反射
> 3. 编译检查：通过代码里标识的注解让编译器在编译阶段对代码进行编译检查，例如：@Override注解

## JDK中预定义的注解

> @Override: 检查被注解修饰的方法是否继承自父类接口
>
> @Deprecated:标识被注解修饰的方法已过时，不推荐使用（但是并不标识该方法无法使用，向下兼容）
>
> @SuppressWarnings:压制警告

## 揭开注解的面纱

自定义一个注解，然后通过java编译成class文件，再反编译查看，可以看到注解继承自jdk的一个注解，Annotation

```java
public interface MyAnnotation extends java.lang.annotation.Annotation{
}
```

## 注解的定义格式

```java
public @interface 注解名称{
    String[] values();//属性
}
```

属性：在接口中定义的抽象方法

> 其，返回类型必须是如下类型：
>
> 1. 基本数据类型
> 2. String类型
> 3. 枚举类型
> 4. 注解
> 5. 以上类型的数组

属性赋值的注意点：

> 1. 定义属性时，可以通过default给属性配置默认初始值，可以在使用注解时不赋值
> 2. 如果定义的属性只有一个且该属性的名称是`value`，那么在使用注解时，value可以省略，直接进行赋值
> 3. 数组赋值时，使用`{}`包裹，如果数组中只有一个值，那么`{}`可以省略

### 元注解

java中有4个元注解：

1. @Target：返回类型是ElementType. 常用值有：Type（类），Method方法，Field（属性）

   > @Target作用是表示注解可以作用在哪个地方

2. @Documented：描述注解可以被抽取到java Doc API文档中

3. @Retention：返回类型是RetentionPolicy。常用值有：SOURCE（代码阶段），CLASS（字节码时期），RUNTIME（运行时期）

   > @Retention作用是表示注解可以作用在哪个时期。SOURCE < CLASS < RUNTIME

4. @inherited：描述注解是否可以被子类继承

```java
package com.example.demo.annotation;

import java.lang.reflect.Method;

@MyAnnotation(className = "com.example.demo.annotation.Student2", methodName = "show")
public class AnnotationTest {
    public static void main(String[] args) {
        try {
            Class<AnnotationTest> clazz = AnnotationTest.class;
            MyAnnotation myAnnotation = clazz.getAnnotation(MyAnnotation.class);
            String className = myAnnotation.className();
            String methodName = myAnnotation.methodName();
            System.out.println(className);
            System.out.println(methodName);
            Class<?> annoClazz = Class.forName(className);
            Method method = annoClazz.getMethod(methodName);
            method.invoke(annoClazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

```java
package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String className();
    String methodName();
}
```

