软件设计基本原则
1.高内聚，低耦合
2.面向抽象编程
3.多用组合少用继承
4.“开-闭”原则

未使用设计模式可能出现的弊端
1.系统可读性差
2.系统健壮性差
3.系统复用性不高
4.系统维护性差

设计模式中最重要的六大原则 http://www.uml.org.cn/sjms/201211023.asp
1.开闭原则
2.单一职责原则
3.依赖导致原则
4.接口隔离原则
5.里氏替换原则
6.迪米特法则
单一职责原则告诉我们实现类要职责单一；
里氏替换原则告诉我们不要破坏继承体系；
依赖倒置原则告诉我们要面向接口编程；
接口隔离原则告诉我们在设计接口的时候要精简单一；
迪米特法则告诉我们要降低耦合。
而开闭原则是总纲，他告诉我们要对扩展开放，对修改关闭。

23种设计模式
创建型模式

工厂方法模式
抽象工厂模式
建造者模式
原型模式
单例模式

结构型模式

适配器模式
    在Java jdk中，适配器模式使用场景很多，如集合包中Java.util.Arrays#asList()、io包中java.io.InputStreamReader(InputStream)、java.io.OutputStreamWriter(OutputStream) 等。
    spring AdvisorAdapter

桥接模式
组合模式
装饰者模式
    定义：装饰模式是在不必改变原类文件和使用继承的情况下，动态的扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
    java.io 中大量使用
    是动态扩展一个类的功能的最佳体现

外观模式
享元模式
代理模式
    spring aop

行为型模式

责任链模式
    springmvc 拦截器链

迭代器模式
中介者模式
备忘录模式
观察者模式
    spring ApplicationListener

状态模式
策略模式
    促销打折

模板方法模式
    spring JdbcTemplate

访问者模式

jdk 中的设计模式 http://www.cnblogs.com/zhousysu/p/5483862.html
spring 中用到的设计模式 http://blog.csdn.net/fg2006/article/details/6435410