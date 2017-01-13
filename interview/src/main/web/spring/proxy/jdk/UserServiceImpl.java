package web.spring.proxy.jdk;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("--------------------add---------------");
    }
}
