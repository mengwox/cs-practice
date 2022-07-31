# sicp课程相关链接
[SICP课程github链接](https://github.com/DeathKing/Learning-SICP)
[bilibili中文字幕课程]()

## 目的
本目录,记录学习SICP课程的一些笔记和基于Java实现的练习, 后续会补充一些基于原课程记录的Lisp练习

本课程使用编程语言`Lisp`

# Lesson 1: Lisp概览
## For English:

basic technique for controlling complexity:
1. black box abstraction, 抽象
2. conventional interfaces, 按照约定来实现相应的接口 
   1. object-oriented programming, 面向对象编程
   2. operations on aggregates, called streams
3. metalinguistic abstraction, 元语言抽象making new languages

when you study a cs language,
there are some questions that u need to look for result:
1. what kind of primitive elements in this language?
2. how to combination those primitive elements?
3. how to define a produce?
4. what's the means of abstraction?

## 中文描述:

控制系统复杂度的3种基础技术
1. 抽象
2. 按照约定,实现相应的接口
   1. 面向对象编程
   2. 流操作
3. 元语言抽象, 自己编写一门新的计算机语言,来适配当前系统复杂度

在学习一门计算机语言时, 你要提一些问题:
1. 这门语言,有哪些基础元素?
2. 这门语言,如何定义程序?
3. 这门语言,如何组合基础元素, 构造成combination或者produce?
4. 这门语言,如何表达抽象?

# lesson 2: 计算过程
For English:

substitution model:

kinds of expressions:
- numbers
- symbol
- lambda expressions
- definitions
- conditionals
- combinations

# lesson 3: 高阶过程
procedure的高阶用法. 即procedure as a argument

general pattern of sigma. two ways to implementation:
- iteration
- recursion

# lesson 4: combination data 组合数据

# lesson 5: Henderson Escher Example
与其将一个任务,分解成二叉树结构的子任务.
不如, 将一个任务,按领域/层次 划分成多个layer. 这样设计更健壮.

真正的设计过程, 与其说是在设计程序, 不如说是在设计基于原本语言的一门新语言.
really of The design process, is not so much implementing programs as implementing languages.

# lesson 6: Symbolic Differentiation: Quotation