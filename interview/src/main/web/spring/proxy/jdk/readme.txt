目标对象实现的接口，用JDK来生成代理对象一定要实现一个接口

谁来调用InvocationHandler 的invoke方法？
反编译字节码文件

细说JDK动态代理的实现原理
http://blog.csdn.net/mhmyqn/article/details/48474815

Proxy --> newProxyInstance()

ProxyClassFactory --> apply -- > ProxyGenerator.generateProxyClass 生成代理类字节码
