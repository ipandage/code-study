http://dubbo.io/User+Guide-zh.htm

dubbo 的高可用是怎么做到的?
1.监控中心宕掉不影响使用，只会丢失部分采集数据
2.数据库宕掉后，注册中心仍能通过缓存提供服务列表查询，但不能注册新服务
3.注册中心对等集群，任意一台宕掉，将自动切换到另外一台
4.服务提供者无状态，任意一台宕掉，不影响使用
5.服务提供者全部宕掉，服务消费者应用将无法使用，并无限次数重连等待服务提供者恢复

基本概念解释
Dubbo是一种分布式服务框架，webserivce也是一种服务框架，但是webserivce需要结合F5实现负载均衡。
因此dubbo除了可以提供服务之外，还可以实现软负载均衡，还提供了监控中心和调用中心，这两个是可选的，需要单独配置

架构
Consumer服务消费者
Provider 服务提供者
Container 服务容器
Register 注册中心
Monitor 监控中心

消费者invoke服务者，过程是同步的
Provider对于Consumer是透明的，上一次调用服务的位置（ip）和下一次调用服务的位置，是不确定的，实现了软负载

服务提供者先启动start，然后register服务
消费者subscribe服务，如果没订阅到服务，它会不断的尝试订阅，新的服务注册到注册中心以后，注册中心会将这些服务通过notify到消费者

Monitor是一个监控，Consumer Provider异步方式发送消息到Monitor，Consumer和Provider会将信息存放在本地磁盘，平均1min发送一次

dubbo 原理
1.初始化过程
解析服务
1）基于dubbo.jar 内的Meta-inf/spring.handlers配置，spring在遇到dubbo命名空间时，会回调DubboNamespaceHandler类
2）所有的dubbo标签，都统一调用DubboBeanDefinitionParser进行解析，进行一对一的映射，将XML标签解析为Bean对象

暴露服务
1.只暴露服务端口
在没有注册中心的情况，一般适用于开发环境，服务的调用和提供再一个ip上，只要打开服务的端口即可
当配置 or ServiceConfig解析出的URL的格式为：
Dubbo：//service-host/com.xxx.TxxService?version=1.0.0
基于扩展点的Adaptiver机制，通过URL的“dubbo：//”协议头识别，直接调用DubboProtocol的export（）方法，打开服务端口

2.像注册中心暴露服务
和上一种的区别，需要将服务的ip和端口暴露给服务中心
ServiceConfig解析出的url格式为：
registry://registry-host/com.alibaba.dubbo.registry.RegistryService?export=URL.encode(“dubbo://service-host/com.xxx.TxxService?version=1.0.0”)
基于扩展点的Adaptive机制，通过URL的“registry：//”协议头识别，调用RegistryProtocol的export方法，将export参数中的提供者URL先注册到注册中心，
再重新传给Protocol扩展点进行暴露：
Dubbo：//service-host/com.xxx.TxxService?version=1.0.0

引用服务
1.直接引用
在没有注册中心的，直连提供者情况下，
ReferenceConfig解析出的URL格式为：
Dubbo：//service-host/com.xxx.TxxService?version=1.0.0
基于扩展点的Adaptive机制，通过url的“dubbo：//”协议头识别，直接调用DubboProtocol的refer方法，返回提供者引用。

2.从注册中心发现引用服务
此时，ReferenceConfig解析出的URL的格式为：
registry://registry-host/com.alibaba.dubbo.registry.RegistryService?refer=URL.encode(“consumer://consumer-host/com.foo.FooService?version=1.0.0”)

基于扩展点的Apaptive机制，通过URL的“registry：//”协议头识别，就会调用RegistryProtocol的refer方法，基于refer参数总的条件，查询提供者URL，如：
Dubbo：//service-host/com.xxx.TxxService?version=1.0.0

基于扩展点的Adaptive机制，通过提供者URL的“dubbo：//”协议头识别，就会调用DubboProtocol的refer（）方法，得到提供者引用。
然后RegistryProtocol将多个提供者引用，通过Cluster扩展点，伪装成单个提供这引用返回。



