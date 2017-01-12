细说JDK动态代理的实现原理
http://blog.csdn.net/mhmyqn/article/details/48474815

Proxy --> newProxyInstance()

ProxyClassFactory --> apply -- > ProxyGenerator.generateProxyClass 生成代理类字节码
