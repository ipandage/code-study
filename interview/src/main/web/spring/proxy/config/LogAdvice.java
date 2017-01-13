package web.spring.proxy.config;

public class LogAdvice {
    public void beforeLog(){
        System.out.println("开始执行");
    }
    public void afterLog(){
        System.out.println("执行完毕");
    }
}
