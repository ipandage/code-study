事物控制
一个连接发出 multi ，会进入事物上下文，后续的命令不会立即执行，会放到一个队列里
收到exec命令后，redis会顺序的执行队列中的所有命令，并将所有命令的结果打包一起返回

如何取消一个事物 discard

乐观锁复杂事物控制 watch
为数据库表添加一个version字段，读取出数据的时候version连同读出，之后更新时候对此版本号加1
将提交数据的版本号与数据库表对应记录的版本号做对比，如果提交的数据版本号大于数据库表当前
的版本号，则予以跟新，否则认为数据过期

数据持久化
需要将内存中的数据同步到磁盘
支持两种持久化方式 Snapshotting（快照）默认方式，Append-only file（aof）

为什么redis在down的情况下会丢失数据？Aof通过什么方式保证数据不丢失？
因为快照方式是在一定间隔时间做一次
使用aof做持久化时，redis会将每个收到的命令都通过write函数追加到appendonly.aof
redis down掉重启之后，会将磁盘的数据load到内存

aof 也可能丢失部分数据，以为os会在内核中缓存write做的修改
可以告诉redis我们想要通过fsync函数强制os写入磁盘
appendfsync always 收到命令就立即写入磁盘 最慢，但是保证完全的持久化
appendfsync everysec 每秒钟写入磁盘一次 性能和持久化这种方案
appendfsync no 完全依赖os 持久化没保证

aof文件增大如何处理？
使用bgrwrite命令压缩aof文件

redis是个单线程的程序，为什么会这么快呢？
1.绝大部分请求是纯粹的内存操作
2.采用单线程，避免了不必要的上下文切换和竞争条件
3.非阻塞io 内部实现采用epoll


