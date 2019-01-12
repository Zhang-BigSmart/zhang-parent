package com.zhang.practice.spring;

import com.zhang.practice.spring.transaction.User;
import com.zhang.practice.spring.transaction.UserRepository;
import com.zhang.practice.spring.transaction.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName TransactionTest
 * @Description:
 * @Author: zhangzh
 * @Date 2018/9/22 18:06
 */
public class TransactionTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setName("susan");
        user.setAge(18);
        userService.save(user);
    }
}
