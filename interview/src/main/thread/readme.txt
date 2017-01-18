线程同步
线程同步目的是防止多个线程访问一个资源对资源的破坏

死锁是什么？
死锁是线程间相互等待锁造成的，一旦发生死锁程序会死掉

线程同步方式
1.有关键字 synchronized 关键字修饰
2.同步代码块
3.使用特殊域变量(volatile)实现线程同步
4.使用重入锁实现线程同步
5.使用局部变量实现线程同步

线程池
什么情况下使用线程池？
1.单个任务处理时间比较短
2.处理的任务量非常大

使用线程池的好处？
1.减少在创建和销毁线程上说花的时间及系统开销
2.不使用线程池，有可能导致系统创建大量线程而导致消耗完系统内存以及“过度切换”

自带线程池种类
newFixedThreadPool 指定工作线程数量 线程数量大于线程池初始的数量，则将提交的任务放入池队列中
newCachedThreadPool 可缓存的线程池
newSingleThreadExecutor 单线程花的Executor
newScheduleThreadPool 定长的线程池，支持周期性的执行任务

线程池工作原理
http://www.cnblogs.com/dolphin0520/p/3932921.html#fillback=0100307b617b7b7b623139623362666639617b617b7b240000&anchor=testanchor
概念：
java.uitl.concurrent.ThreadPoolExcutor 是线程池中最核心的类
ThreadPoolExcutor 继承了 AbstractExecutorService
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
        BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);

构造器中的参数
corePoolSize 线程池大小，默认情况下线程池中没有任何线程，而是等待有任务来采取创建线程去执行任务，
            默认情况下，创建了线程池后，线程池中的线程数量为0，当有任务来的时候就创建一个线程去执行任务，
            当线程池中的线程数目达到 corePoolSize ，就会把到达的任务放到缓存队列中
maximumPoolSize 最大线程数，表示在线程池中最大能创建多少个数
keepAliveTime 表示线程没有任务执行时，最多保持多久时间会终止，默认情况下线程数量大于corePoolSize时才起作用
            知道线程池中的线程数量不大于corePoolSize
unit  keepAliveTime 时间单位
workQueue 阻塞队列，用于存储待执行的任务
threadFactory 线程工厂 负责创建线程
handler 表示拒绝处理任务时策略

ThreadPoolExecutor（类） 继承 AbstractExecutorService（类） 实现 ExecutorService（接口）继承 Executor（接口）
Executor 是一个顶层接口 只声明了一个方法 execute(Runnable) 返回值void 参数为Runnable类型，用来执行传递进来的任务的
然后ExecutorService接口继承了Executor接口，并声明了一些方法：submit、invokeAll、invokeAny以及shutDown等
抽象类AbstractExecutorService实现了ExecutorService接口，基本实现了ExecutorService中声明的所有方法
然后ThreadPoolExecutor继承了类AbstractExecutorService

在ThreadPoolExecutor类中有几个非常重要的方法： execute（） submit（） shutdown（） shutdownNow（）
execute 是 Executor 声明的方法，ThreadPoolExecutor进行了具体的实现，这个方法是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行
submit 是 ExecutorService 中声明的方法，AbstractExecutorService 已经有了具体的实现，ThreadPoolExecutor 并没有重写，这个方法也是向线程池提交任务的，但是跟execute方法
        不同，它能够返回线程执行的结果，submit（）实现里还是调用了execute（）方法，只不过利用了Future来获取执行结果
shutdown shutdownNow 用来关闭线程池

java doc 中，并不提倡，直接用ThreadPoolExecutor,而是使用Executors类中提供的几个静态方法来创建线程池!!

Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程

Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；

Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue

任务缓存及排队策略
ArrayBlockingQueue 给予数组的先进先出队列 队列创建时必须指定大小
LinkedBlockingQueue 给予链表的先进先出队列 如果创建时没有指定大小，则默认为Integer.MAX_VALUE
SynchronousQueue 比较特殊 他不会保存提交的任务，而是将直接创建一个线程来执行任务
SynchronousQueue 内部没有数据缓存空间 ，可以这样来理解：生产者和消费者互相等待对方，握手，然后一起离开


如何合理配置线程池大小？
一般根据任务类型配置线程池大小
如果是cpu 密集型，就尽量压榨cpu ，参考值可以设为 NCPU+1
如果io密集型 ，参考值可以设置为2*NCPU

