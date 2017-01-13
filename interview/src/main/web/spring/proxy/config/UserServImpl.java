package web.spring.proxy.config;

import java.util.List;

public class UserServImpl implements IUserServ{
    public int deleteUserById(User user) {
        System.out.println("******执行删除方法******");
        return 0;
    }
    public List<User> findAllUser() {
        System.out.println("*******执行查询方法*******");
        return null;
    }
    public int saveUser(User user) {
        System.out.println("*******执行添加方法********");
        return 0;
    }
}
