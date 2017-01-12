索引
1，创建索引
对于查询占主要的应用来说，索引显得尤为重要。很多时候性能问题很简单的就是因为我们忘了添加索引而造成的，或者说没有添加更为有效的索引导致。如果不加索引的话，那么查找任何哪怕只是一条特定的数据都会进行一次全表扫描，如果一张表的数据量很大而符合条件的结果又很少，那么不加索引会引起致命的性能下降。但是也不是什么情况都非得建索引不可，比如性别可能就只有两个值，建索引不仅没什么优势，还会影响到更新速度，这被称为过度索引。
2，复合索引
比如有一条语句是这样的：select * from users where area=’beijing’ and age=22;
如果我们是在area和age上分别创建单个索引的话，由于mysql查询每次只能使用一个索引，所以虽然这样已经相对不做索引时全表扫描提高了很多效率，但是如果在area、age两列上创建复合索引的话将带来更高的效率。如果我们创建了(area, age, salary)的复合索引，那么其实相当于创建了(area,age,salary)、(area,age)、(area)三个索引，这被称为最佳左前缀特性。因此我们在创建复合索引时应该将最常用作限制条件的列放在最左边，依次递减。
3，索引不会包含有NULL值的列
只要列中包含有NULL值都将不会被包含在索引中，复合索引中只要有一列含有NULL值，那么这一列对于此复合索引就是无效的。所以我们在数据库设计时不要让字段的默认值为NULL。
4，使用短索引
对串列进行索引，如果可能应该指定一个前缀长度。例如，如果有一个CHAR(255)的 列，如果在前10 个或20 个字符内，多数值是惟一的，那么就不要对整个列进行索引。短索引不仅可以提高查询速度而且可以节省磁盘空间和I/O操作。
5，排序的索引问题
mysql查询只使用一个索引，因此如果where子句中已经使用了索引的话，那么order by中的列是不会使用索引的。因此数据库默认排序可以符合要求的情况下不要使用排序操作；尽量不要包含多个列的排序，如果需要最好给这些列创建复合索引。
6，like语句操作
一般情况下不鼓励使用like操作，如果非使用不可，如何使用也是一个问题。like “%aaa%” 不会使用索引而like “aaa%”可以使用索引。
7，不要在列上进行运算
select * from users where YEAR(adddate)<2007;
将在每个行上进行运算，这将导致索引失效而进行全表扫描，因此我们可以改成
select * from users where adddate<‘2007-01-01’;
8，不使用NOT IN和操作
NOT IN和<>操作都不会使用索引将进行全表扫描。NOT IN可以NOT EXISTS代替，id3则可使用id>3 or id<3来代替 。

索引分类
1.普通索引
2.唯一索引
3.主键索引
4.组合索引
一个查询只能用索引的一部分，但只能是左侧索引

查询慢优化
1.使用explain
2.慢查询分析
在my.ini中：
long_query_time=1
log-slow-queries=d:\mysql5\logs\mysqlslow.log
把超过1秒的记录在慢查询日志中
可以用mysqlsla来分析之。也可以在mysqlreport中，有如
DMS分别分析了select ,update,insert,delete,replace等所占的百份比

explain 使用 需要关注哪几列
id  selecttype  table  type possible_keys  key key_len  ref rows  extra
type 本次查询表连接类型，能看到本次查询的大概效率 All是执行全表扫描效率最差
key 选择的索引，最终选择的索引，没有索引的话效率通常很差
key_len 索引的长度
rows 预计扫描的记录数，越小越好
extra 判断是否出现几种情况 Using filesort、Using temporary

MyISAM与InnoDb主要区别和应用场景

1.MyISAM是非事物安全型的，InnoDb是事物安全型的
2.MyISAM锁的粒度是表级，InnoDb是行级
3.MyISAM支持全文索引，InnoDb不支持
3.MyISAM相对简单，效率上高于InnoDb
3.MyISAM表是保存成文件的形式，快平台的数据转移中会省不少事
3.InnoDb比MyISAM更安全

应用场景
1.MyISAM管理非事物表。它提供高速存储和索引，以及提供全文搜索能力。如果程序中执行大量的select操作，MyISAM是非常好的选择
2.InnoDb用户事物处理应用程序，具有众多特性，包括ACID事物支持。如果应用中有大量的insert update操作，可以挺高多用户并发操作性能
