package web.spring.proxy.cglib;


public class CGLIBTest {
    public static void main(String[] args) {
        CGLIBProxy proxy = new CGLIBProxy();
        CGLIBUserServiceImpl impl = (CGLIBUserServiceImpl) proxy.getProxy(CGLIBUserServiceImpl.class);
        impl.sayHello();
    }
}
