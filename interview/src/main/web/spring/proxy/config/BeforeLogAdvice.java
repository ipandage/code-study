package web.spring.proxy.config;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeLogAdvice implements MethodBeforeAdvice {
    /**
     * Method method:调用目标对象的方法
     * Object[] args:发送给目标对象的参数列表
     * Object target:目标对象
     */
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        beforeLog();
    }
    private void beforeLog(){
        System.out.println("开始执行");
    }
}
