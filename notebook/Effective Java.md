# Effective Java（中文第3版）

学习一门语言:

1. 语言的结构(语法)
2. 如何命名你想要表达的事物(词汇)
3. 如何以惯用,高效的方式表达事物(用法)

# 设计模式原则

## 里氏替换原则(Liskov substitution principle)

一个类型的任何重要属性,也将适用于它的子类型, 因此为该类型编写的任何方法, 在它的子类型上也应该同样运行地很好[Liskov87].

## Chapter2: 创建和销毁对象

### 1:静态工厂方法代替构造器完成对象的创建

#### 优点:

1. 对于重复一样的对象,不用反复new, 提升性能
2. 静态工厂方法名可自定义, 可读性强
3. 可以返回任何继承了返回值类型的子类对象
4. 创建的对象,可以根据入参而变化, 从而创建不同的对象
   1. 建议将静态方法背后的大部分实现代码放在包私有的类下.(java8之后)
   2. 静态方法的定义放在接口上.
5. 方法返回的类

#### 缺点

1. 类如果不含public或protected修饰的构造器,就不能被子类化.
   - 因为构造器没有被public或protected修饰, 子类将没法访问父类的构造器完成父类的初始化操作
2. 静态工厂方法完成对象的创建,很难被使用者感知到

#### 常见的惯用名称

1. from-类型转换方法. 返回该类型的一个相对应的实例. 例如:

   ```java
   Date date = Date.from(instant);
   ```

2. of-聚合方法, 带有多个参数, 返回该类型的一个实例,将多个参数合并起来. 例如:

   ```java
   Set<Rank> faceCards = EnumSet.of(JACK,QUEEN,KING);
   ```

3. valueOf-比from和of更繁琐的一种替代方法.例如:

   ```java
   BigInteger bt = BigInteger.valueOf(Integer.MAX_VALUE);
   ```

4. instance/getInstance- 返回的实例是通过入参来描述的,但不一定和入参具有同样的值

5. create/newInstance-返回的实例一定是一个新的实例.

6. get*Type*

7. new*Type*

8. *type*, get*Type*和new*Type*的简版

### 2.遇到多个构造器参数时,要考虑使用*构建器*,也就是建造者模式

静态工厂和构造器都有一个共同的局限性:

- 它们都不能很好地扩展到大量可选参数

```java
package org.example;

import java.io.Serializable;

/**
 * 创建和销毁对象-第二条:
 * 涉及:遇到多个构造器参数时,要考虑使用构建器,也就是建造者模式
 * 可以使用{@link lombok.Builder}一键配置
 *
 * @author mawenhao 2022/10/27
 */
public class NutritionFacts implements Serializable {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servingSize;
        this.calories = builder.servingSize;
        this.fat = builder.servingSize;
        this.sodium = builder.servingSize;
        this.carbohydrate = builder.servingSize;
    }

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            this.calories = val;
            return this;
        }

        public Builder fat(int val) {
            this.fat = val;
            return this;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            this.carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(240, 8).calories(100)
                                                           .sodium(35)
                                                           .carbohydrate(27)
                                                           .build();
        System.out.println(nutritionFacts);
    }
}
```

如果使用多个构造器,来适配用户初始化需要的参数, 代码会很难编写, 且可读性差.所以这时,推荐使用Builder建造者模式.

#### 使用JavaBeans模式的缺陷

构造过程中,JavaBean可能处于不一致的状态. 调用方能够反复设置同一个字段,将前一次设置的值给覆盖掉.

1. JavaBeans模式来创建对象,并不安全.
2. 使得类做成不可变的可能性不复存在(可以通过setter方法改变字段值)

#### 使用Builder建造者模式的好处
- 既有重叠构造器模式那样的安全性
- 也能保证像JavaBeans模式那么好的可读性
- 十分灵活,就算后续新增可选参数, 也不会影响现有代码

#### Builder模式创建对象的流程:
1. 使用方,调用必填参数的构造器(或静态工厂), 得到一个*builder*对象.
2. 对*builder*对象调用类型*setter*的方法,来设置每个相关的可选参数
3. 最后调用无参的*build()*方法,来生成**通常不可变**的对象

#### 使用Builder模式的劣势

1. 为了创建对象, 必须先创建它的构建器. 所以性能开销会大一些

### 3.私有构造器或枚举类强化Singleton属性

1. field方式
2. static-factory方式
3. enum方式

### 4.通过私有化构造器, 强化一个类的不被实例化的能力

通常,这样的类, 只用来编写包含静态方法和静态域的类

#### 副作用

类不能被子类化

### 5.优先考虑依赖注入, 来引用资源

静态工具类和Singleton类不适合于需要引用底层资源的类.

总而言之:

- 不要用Singleton和静态工具类, 来实现依赖一个或多个底层资源的类, 且该资源的行为会影响到该类的行为
- 也不要直接用这个类来创建这些资源. 应该将这些资源或者工厂传给构造器(或者静态工厂, 构建器Builder), 通过他们来创建类.
- 这个实践就被叫做依赖注入(DI, Dependency Injection). 它极大地提升了类的灵活性,可重用性,和可测试性

### 6.避免创建不必要的对象

- 一般来说, 对于一些不变的或功能相同的对象, 建议直接复用该对象,而不是每次使用都重新创建一个功能一样的对象.

- 要优先使用基本类型,而不是装箱基本类型, 要当心无意识的自动装箱

1. map的keySet方法,会在第一次调用的时候, new KeySet(), 创建一个keys的视图.创建多个keySet视图对象的多个实例.没有必要,也没有好处(鸡肋)
2. java的自动拆装箱机制,使得基本类型和它的包装类型之间的差别变得模糊起来,但是没有完全消除. 使用基本类型,性能更好
3. 使用保护性拷贝的时候, 创建重复对象比重用对象更合适

### 7.消除过期的对象引用

支持GC的语言, 内存泄漏是很隐蔽的. 被称之为"无意识的对象保持"(unintentional object retention)

- 清空对象引用,应该是一种例外, 而不是一种规范行为
- 一般来说, 只要类是自己管理内存, 就应该警惕内存泄漏问题. 元素一旦被释放, 则该元素中包含的任何对象引用都应该被清空.
- 另一个常见的内存泄漏: 缓存
- 内存泄漏的第三个常见来源: 监听器和其他回调

### 8.避免使用终结方法(finalizer)和清除方法(cleaner)

- 永远不应该依赖finalizer或者cleaner方法来更新重要的持久状态
- 为了防止非final类收到finalizer attack(终结攻击), 要编写一个空的final的finalize方法
  - 如果类的对象中封装的资源(eg: 文件或者线程)确实需要终止, 应该让类实现*AutoCloseable*接口, 并要求其客户端在每个实例不再需要的时候调用close()方法.
  - 一般是使用try-with-resources确保终止
  - 实例必须记录下自己是否已经被关闭了: close()方法必须在一个私有域中记录下"该对象已不再有效"

- 静态的嵌套类不包含外围实例的引用, 非静态的嵌套类则包含外围实例的引用

```java
package org.chapter2.item8;

import java.lang.ref.Cleaner;

/**
 * 8.避免使用终结方法(finalizer)和清除方法(cleaner)
 *
 * @author mawenhao 2022/10/29
 */
public class Room implements AutoCloseable{
    private static final Cleaner cleaner = Cleaner.create();

    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        State state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }

    public static void main(String[] args) throws Exception {
        Room room = new Room(10);
        room = null;
        System.gc();
        while (true){

        }
    }
}
```

### 9.try-with-resources优先于try-finally

- 客户端经常会忽略资源的关闭

## Chapter3: 对所有对象(Object)都通用的方法

### 10.覆盖equals时请遵守通用约定

覆盖equals方法看起来似乎很简单,但是很容易出问题,且后果非常严重.

最容易避免这类问题的方法就是不覆盖equals方法.

在这种情况下, 类的每个实例只与它自身相等.

- 类的每个实例本质上都是唯一的
- 类没有必要提供**逻辑相等(logical equality)**的测试功能
- 超类已经覆盖了equals, 超类的行为对于这个类也是适合的
- 类是private,或default(包级私有), 可以确定它的equals方法永远不会被调用

#### Object equals规范

equals方法实现了**等价关系(equivalence relation)**

- 自反性(reflexive): 对于任何非null的引用值x, x.equals(x)必须返回true
- 对称性(symmetric): 对于任何非null的引用值x和y, 当且仅当y.equals(x)返回true时, x.equals(y)也必须返回true
- 传递性(transitive): 对于任何非null的引用值x y z, 如果x.equals(y)返回true,并且y.equals(z)也返回true, 那么x.equals(z)也必须返回true
- 一致性(consistent): 对于任何非null的引用值x y, 只要equals的比较操作在对象中所用的信息没有被修改, 多次调用x.equals(y)就会一致地返回true, 或者一致地返回false
- 对于任何非null的引用值x, x.equals(null)必须返回false

#### 面向对象语言中关于等价关系的基本问题

- 我们无法在扩展可实例化的类的同时, 既增加新的值组件, 同时又保留equals约定, <font color="red">除非愿意放弃面向对象的抽象所带来的优势</font>
- java.sql.Timestamp对java.util.Date进行了扩展, 并增加了nanoseconds域. 其equals违反了对称性, 是个错误的例子
- 不要使equals方法依赖于不可靠的资源. 例如: java.net.URL的equals方法依赖于URL中主机IP地址的比较.IP地址变化,equals方法的返回值也有可能改变. 这是个错误的例子, 可惜因为兼容性要求, 该行为无法被改变
- equals方法应该对内存中的对象执行确定性的计算
- equals方法一定要显式判断null,来防止空指针异常

#### 实现高质量equals的诀窍

1. 使用**==**操作符检查`参数是否为这个对象的引用`
2. 使用instanceof操作符检查`参数是否为正确的类型`
3. 把参数转换成正确的类型, 强转在instanceof之后,确保一定成功
4. 对于该类中的每个`关键significant`域, 检查参数中的域是否与该对象中对应的域相匹配
   1. 对于非float也非double的基本类型域, 可以使用==操作符进行比较
   2. 对应对象引用域, 可以使用equals方法比较
   3. 对应float域, 可以使用Float.compare(float,float)方法, 每次比较都要自动装箱,性能会有所下降
   4. 对应double域, 可以使用Double.compare(double,double)方法, 每次比较都要自动装箱,性能会有所下降
   5. 对与数组域, 则要把每一个元素参照上面的原则进行比较.如果每个元素都很重要, 就可以使用Arrays.equals方法,将会比较2个数组内的每一个元素是否完全一致
   6. 有些对象引用域包含null可能是合法的,为了避免空指针, 应该使用Objects.equals(Object,Object)方法来检查等同性
   7. 对与有些类, 域的比较要复杂的多. 如果是这样, 可能希望报错该域的一个`范式(canonical form)`.这样equals方法就可以根据这些范式,进行低开销的精准比较,而不是高开销的非精准比较.详见第17条. 
      1. 这种方法, 对于不可变类最为合适. 如果对象有变化, 就必须使其范式保持最新
   8. 域的比较顺序可能会影响equals方法的性能. 所以,应该<font color="red">最先比较最有可能不一致的域, 或者说开销最低的域</font>. 
   9. 不应该比较哪些不属于对象逻辑状态的域, 比如: 同步操作的Lock域.
   10. 也不应该比较衍生域(derived field), 因为这些域可以由`关键域(significant field)`计算获得
   11. 编写完equals方法之后, 应该问自己3个问题: 是否是对称的, 传递的, 一致的, 自反性, 非空性? 并编写单元测试来验证这些特性.

#### 实现equals的告诫

1. 重写equals时,总要一起重写hashCode
2. 不要试图让equals方法过于智能
3. 不要将equals声明中的Object对象替换为其他的类型, 这样写出来的方法不是重写override,而是重载overload
4. 建议使用Google开源的AutoValue框架, 自动生成equals和hashCode方法

### 11.重写equals方法时,一定也要重写hashCode方法

#### hashCode通用约定

来自Object规范:

1. 只要对象的equals方法的比较操作所用到信息没有改变,那么对同一个对象多次调用得到的hashCode值必须始终如一
2. 如果两个对象equals比较相等, 那么两个对象hashCode值也一定相等
3. 如果两个对象equals比较不相等, 那么两个对象的hashCode值不一定不相等. 但是建议不相等, 这样可以提高在散列表hash table中的性能
4. 将不相等的散列码分配给不相等的实例

这样做的好处是,对象存储容器的散列表性能达到最佳,否则,散列表的性能可能退化成链表

### 12.始终要覆盖toString

Object默认toString方法,并不会将对象中各个属性值打印出来.完全没有可读性.建议覆盖toString,将需要查看的属性在toString中打印出来

### 13 谨慎覆盖clone

- 浅拷贝: 只clone了原始对象,对于对象内的非基本类型参数, 只引用, 不拷贝,是一样的对象

- 深拷贝: 不但clone了原对象, 对于对象内的非基本类型参数, 也进行了拷贝,是不一样的对象

实际上, clone方法就是另一个构造器, 必须确保它不会伤害到原始的对象, 并确保正常的创建被克隆对象中的约束条件.(即, 实现深度拷贝,而不是浅拷贝)

final域 与 clone 不相互兼容

#### 最佳实现

1. 拷贝构造器
2. 拷贝工厂
3. 只对于数组的深拷贝, 可以利用clone方法拷贝

### 14. 考虑实现Comparable接口

compareTo方法通用约定:

1. sgn(x.compareTo(y)) == -sgn(y.compareTo(x)), 自反性
2. x.compareTo(y) > 0 && y.compareTo(z) > 0, 则x.compareTo(z) > 0, 传递性
3. x.compareTo(y) == 0, 则对于任意的z, 都满足x.compareTo(z) == y.compareTo(z), 对称性
4. 建议: (x.compareTo(y) == 0) == (x.equals(y)), 并非绝对必要, 但是建议满足一致

要点:

1. 尽量避免使用`<`和`>`操作符
2. 基本类型,应该使用装箱基本类型的类中的静态compare方法
3. 或者, 使用Comparator接口中使用比较器构造方法

## Chapter4: 类和接口

