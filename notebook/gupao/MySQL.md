# MySQL

[MySQL官方文档]: https://dev.mysql.com/doc/refman/5.7/en/	"5.7 RELEASE"

#### MySQL一条SELECT语句的执行流程图

![image-20210401181258456](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210401181258456.png)

#### MySQL一条Update语句的执行流程图

## MySQL InnoDB存储引擎架构
#### Buffer Pool 缓冲池

Buffer Pool,是InnoDB用来缓存磁盘I/O查询出来的数据库数据存放的地方.通过对经常查询的数据进行缓存,大大提高请求响应速度.

```markdown
-- 查看系统变量:
show variables like '%innodb_buffer_pool%';
-- 查看服务器状态:
show status like '%innodb_buffer_pool%';
```

InnoDB使用LRU算法来管理缓冲池Buffer Pool.(链表实现,不是传统的LRU,分成了young和old,完成冷热数据替换)

##### MySQL  InnoDB 页

InnoDB设定了一个存储引擎从磁盘读取数据到内存的最小的单位,这个就叫做`页`.

操作系统内,也有页的概念.一般默认是4KB; 而InnoDB 页 的默认值为16KB.如果要修改这个值的大小, 需要清空数据库所有数据并重新初始化MySQL服务.

- 修改数据的时候, 是先将修改后的数据写入到Buffer Pool,而不是直接写到磁盘.当内存的数据页与磁盘数据不一致的时候, 我们把它叫做`脏页`. 而InnoDB后台有专门的线程,每隔一段时间,就会把Buffer Pool的数据写入到磁盘. 这样的一个操作,我们称之为:`刷脏`.

Buffer Pool就是用来在内存中,缓存页的数据的缓存区,这样设计的作用,就是为了提高读写效率,减少磁盘I/O次数.

#### Redo Log

问: 因为刷脏不是实时的,如果在InnoDB刷脏的动作之前,数据库崩溃了或者重启了,那么Buffer Pool的数据由于是存在内存中的,数据就丢失了,那么InnoDB是怎么解决这样的问题呢?

```markdown
- 内存的数据必须要有个持久化的补救措施.MySQL InnoDB就是通过Redo Log来实现的.
InnoDB会将所有对页的数据的修改操作,专门写入到Redo Log的日志文件.
如果数据库崩溃或重启了,在下一次数据库启动成功后,将会从Redo Log日志文件中,进行数据恢复操作(实现crash-safe,也叫崩溃恢复).
事务ACID的D(持久性),就是用Redo Log来实现的.
Redo Log,不是记录数据页更新之后的状态,而是记录的是"在哪个数据页上做了哪些修改".属于物理日志
```

问:同样是进行磁盘I/O写数据到文件中,为什么InnoDB要写入日志文件,而不是直接写入到DB File中呢?

```markdown
这涉及到了磁盘的寻址操作.
数据的存储是随机I/O,而写入日志文件则是顺序I/O. 顺序IO的效率更高.
所以,在确保内存数据的持久化安全的情况下,使用顺序I/O,可以延迟刷盘时机,进而提高系统的吞吐量.
	- redo log位于/var/lib/mysql目录下的ib_file0和ib_file1,默认2个文件,每个48MB大小
	- show variables like 'innodb_log%';即可查看innodb的日志文件相关参数
```

#### Undo Log

undo log和redo log都是和数据修改有关的日志,两者结合起来,就保证了事务的原子性和持久性,所以两者统称为:事务日志

```markdown
undo log(撤销日志,或回滚日志), 记录了事务发生之前的数据状态, 分为:
	- insert undo log
	- update undo log
如果修改数据时,出现了异常,需要进行事务的回滚,就可以通过undo log来实现回滚操作(保证原子性)
undo log,记录的是反向的操作.比如:
	- insert会记录delete.
	- update会记录update之前的值.
和redo log记录在哪个物理页做了什么操作不同,所以undo log属于逻辑日志
	- show variables like '%undo%';
```



#### Change Buffer 写缓冲

Change Buffer,是Buffer Pool的一部分.

Change Buffer,需要进行update/insert/delete操作的数据,先行缓存存放的地方,最后通过刷盘操作,将缓存的数据写入实际存储数据的磁盘中.

#### Redo Log Buffer

将需要记录redo log和undo log的日志数据也缓存起来,减少磁盘I/O的次数.

Log Buffer写入日志文件的时机, 由一个参数控制,默认是1.

​	- show variables like 'innodb_flush_log_at_trx_commit';

#### Adaptive Hash Index

相关参数: 开启`innodb_adaptive_hash_index` 关闭`--skip-innodb-adaptive-hash-index`

自适应哈希索引使InnoDB在具有适当的工作负载组合和足够的缓冲池内存的系统上执行更像内存数据库，而不会牺牲事务特性或可靠性

#### 总结

Buffer Pool和Change Buffer的设计,都是为了尽可能减少磁盘I/O的次数,如果没有缓存,每次CRUD都要将数据通过磁盘I/O交互,而磁盘I/O的速度远远不如内存I/O.

#### 官方文档InnoDB架构图

![image-20210401181316954](C:\Users\Mengwox\AppData\Roaming\Typora\typora-user-images\image-20210401181316954.png)

## MySQL 常用存储引擎对比

### Archive(2个文件)

```markdown
这些紧凑的未索引的表,用于存储和检索大量很少引用的历史, 存档或安全审计信息.
- 特点: 不支持索引, 不支持update/delete
```

### CSV(3个文件)

```markdown
- 在磁盘空间内,有3个文件,后缀名分别为frm,csv,csm
- 它,实际上是带有逗号分隔值的文本文件.CSV表允许以CSV格式导入或转储数据.
- 因为CSV表没有索引,所以通常在正常操作期间,将数据存储在InnoDB表中,并且只在导入或导出阶段使用CSV表
- 特点:不允许空行,不支持索引.格式通用,可以直接编辑,适合在不同数据库之间导入导出(也就是数据迁移)
```

### Memory(1个文件)

```markdown
- 在磁盘空间内,只有一个后缀名为frm的文件,该文件是用来记录表结构的
- 优点:将数据放在内存里面,读写的速度很快
- 缺点:但是如果数据库崩溃或者重启,内存上的数据会全部清空,只适合做为临时数据存储的一种方式
```

将所有的数据存储在RAM上,以便在需要快速查找非关键数据的环境中快速访问.

### MyISAM存储引擎(3个文件)

```markdown
- 一个MyISAM表,在磁盘空间有3个文件,MYI文件,MYD文件,以及用来存储表结构的FRM文件.
MYI文件,保存了索引和数据库数据的地址值(该地址值指向MYD文件的数据库数据)
MYD文件,保存了数据库数据

适合只读之类的数据存储,有较高的插入和查询速度,存储了表的行数(count速度会更快)
支持表锁,不支持事务
```

### InnoDB存储引擎(2个文件)

```markdown
- InnoDB表,在磁盘空间有2个文件,frm文件存储表结构,ibd文件存储实际的数据.
- 特点:
    支持事务,支持外键
    支持行锁,支持表锁
    支持读写并发,写不阻塞读(MVCC, Multi Version Concurrency Control)
    特殊的索引存放方式,可以减少I/O,提升查询效率
- 适合:经常更新的数据存储,且存在并发读写或有事务处理的数据
```

文件中,存放了索引及数据库数据.其中,必然有一个聚集索引(Cluster Index)的B+Tree,其叶子节点存放了一条数据的所有字段数据.

### InnoDB的索引

#### 聚集索引:

1. 当表有主键Primary Key时,InnoDB就会选用该主键作为聚集索引,生成B+Tree
2. 当表没有PK,但是有Unique Key时,且Unique Key这列的数据没有null数据,InnoDB将会选择这个Unique Key作为聚集索引
3. 当表既没有PK,也没有Unique Key时,InnoDB将会给这个表加一个名为`_rowid`隐藏的列,然后将该列作为聚集索引

#### 索引的最左匹配原则:

想要复合索引生效,那么就不能跳字段使用

当有一个复合索引,例如:a, b, c 构建了一个复合索引.

```markdown
select * from table where a = '1' and c = '1';//使用到了复合索引,但是索引只生效到a='1'
select * from table where a = '1' and b = '1' and c = '1';//完美使用到了复合索引.where后面的条件,索引都生效
select * from table where c = '1' and a = '1';//该条和第一条实际上是一样的,因为优化器会对SQL进行优化,索引生效到a=1
select * from table where b = '1' and c = '1';//该条没法用到索引,因为索引的最左匹配原则,必须从第一个a开始
```

### MySQL体系结构总结:

按架构进行分层:

1. 连接层, 跟客户端对接
2. 服务层Server, 真正执行SQL语句操作
3. 存储引擎层, 与硬件(如:磁盘)存储的数据交互

## MySQL事务

### 事务并发三大问题:其实都是数据库读一致性问题导致,由数据库提供一定的事务隔离机制来解决

#### 1.脏读

一个事务读取到了其他事务未提交的数据,造成读不一致.

#### 2. 不可重复读

一个事务读取到了其他事务已提交的数据,造成读不一致,针对update/delete

#### 3.幻读

一个事务读取到了其他事务插入的数据,造成读不一致.只针对Insert语句

#### 事务的4种事务隔离级别

1. Read Uncommited (RU 未提交读)

   ```markdown
   未解决任何并发问题
   事务未提交的数据对其他事务也是可见的,会出现脏读,不可重复读,幻读
   ```

2. Read Commited (RC 已提交读)

   ```markdown
   解决脏读问题
   一个事务开始之后,只能看到已提交的事务所做的修改,会出现不可重复读,幻读
   ```

3. Repeatable Read (RR 可重复读)

   ```markdown
   解决不可重复读问题
   在同一个事务中多次读取同样的数据结果是一样的,这种隔离级别会出现幻读的问题
   ```

4. Serializable(串行化)

   ```markdown
   最高的隔离级别,通过强制事务的串行执行,解决了所有的并发问题
   ```

### MySQL InnoDB是怎么做到RR级别却解决了幻读问题的?

有2种解决方案,可供选择:

1. 在读取数据前,对其加锁,阻塞其他事务对数据进行修改(LBCC, Lock Based Concurrency Control)
2. 生成一个数据请求时间点的一致性数据快照(Snapshot), 并用这个快照提供一定级别(语句级或事务级)的一致性读取(MVCC, Multi Version Concurrency Control)

#### MVCC

效果:建立了一个快照,同一个事务内,不管执行多少次查询,结果都是一样的

MVCC的规则:

1. 一个事务能够看到的数据版本
   1. 该事务第一次查询之前已提交的事务的修改
   2. 本次事务的修改
2. 一个事务不能够看到的数据版本
   1. 该事务第一次查询之后创建的事务(创建的事务ID比当前第一次查询的事务ID大)
   2. 该事务第一次查询之前活跃(未提交)的事务的修改

##### InnoDB是怎么设计的?

InnoDB为每行数据,都实现了3个隐藏字段

1. DB_ROW_ID, 6字节,行标识
2. DB_TRX_ID, 6字节,插入或更新行的最后一个事务的事务ID,自动递增(新增版本号)
3. DB_ROLL_PTR, 7字节, 回滚指针(删除版本号)

###### Read View(一致性视图) 存储内容

4个字段

1. m_ids{} ,列表,当前系统活跃的事务ID
2. min_trx_id ,m_ids列表的最小值事务ID
3. max_trx_id ,系统分配给下一个事务的ID
4. creator_trx_id , 生成readView事务的事务ID

具体设计如下图:



###### Read View判断逻辑

0. 从数据的最早版本开始判断(undo log)
1. 数据版本trx_id = creator_trx_id, 那么本事务可以修改,可以访问
2. 数据版本trx_id < min_trx_id, 说明本事务在生成Read View已经提交, 可以访问
3. 数据版本trx_id > max_trx_id, 说明本事务在Read View生成之后才创建的, 不能访问
4. 数据版本trx_id 在[min_trx_id, max_trx_id]之间, 看看能否在m_ids{}列表中找到;如果可以找到,则不可以;反之,则可以.
5. 如果当前版本不可见, 就找undo log链中的下一个版本,重复0-4,反复循环.

##### RR与RC的MVCC的区别

Repeatable Read(RR)和Read Commited(RC)都有设计MVCC,但是:

1. RR的Read View创建时机,只在事务的第一次查询
2. RC的Read View创建时机,却在事务的每一次查询

所以,RC存在着不可重复读和幻读的问题

#### MySQL 锁的基本类型

InnoDB支持行锁,表锁; MyISAM只支持表锁,具体内容可参考官网

1. 共享锁-行锁, Shared Locks, 简称S锁
2. 排他锁-行锁, Exclusive Locks, 简称X锁
3. 意向共享锁-表锁, Intention Shared Locks
4. 意向排他锁-表锁, Intention Exclusive Locksr

锁,实际上是锁聚集索引

#### 锁的算法

- Record Lock, 记录锁
- Gap Lock, 间隙锁
- Next-Key Lock,临键锁

临键锁,就是InnoDB使用RR级别的事务隔离能够做到解决幻读的关键

##### 死锁的3个条件

1. 互斥
2. 不可被剥夺
3. 形成等待环路

##### 如何查看锁信息(日志)

步骤:

1. show status like 'innodb_row_lock_%';

##### 死锁的避免

1. 操作多张表时,尽量以相同的顺序来访问(避免形成等待环路)
2. 批量操作单张表数据的时候,先对数据进行排序(避免形成等待环路)
3. 申请足够级别的锁,如果要操作数据,就申请排他锁Exclusive Locks(X锁)
4. 尽量使用索引访问数据, 避免没有where条件的操作, 避免锁表
5. 如果可以,将大的事务分解为一个个的小事务
6. 使用等值查询而不是范围查询来查询数据,命中记录Record, 避免间隙锁对并发的影响