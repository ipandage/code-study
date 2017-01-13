package web.spring.proxy.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActionTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("file:E:/gitWorkspace/code-study/interview/src/main/web/spring/proxy/config/applicationContext.xml");
        IUserServ iuserServ = (IUserServ)ac.getBean("userServProxy");
        iuserServ.deleteUserById(new User());
        iuserServ.findAllUser();
    }
}
