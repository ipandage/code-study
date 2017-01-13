package web.spring.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxy implements MethodInterceptor{
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){

        //设置父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    /**
     * 所有的方法都会被这个方法所拦截。该类实现了创建子类的方法与代理的方法。getProxy(SuperClass.class)方法通过入参即父类的字节码，通过扩展父类的class来创建代理对象。intercept()方法拦截所有目标类方法的调用，obj表示目标类的实例，method为目标类方法的反射对象，args为方法的动态入参，proxy为代理类实例。
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodproxy) throws Throwable {

        System.out.println("cglib实现的前置代理");

        //通过代理类调用父类中的方法
        Object result = methodproxy.invokeSuper(obj, args);

        System.out.println("cglib实现的后置代理");
        return result;
    }
}
