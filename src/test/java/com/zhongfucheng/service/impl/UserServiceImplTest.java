package com.zhongfucheng.service.impl;

import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    public void findUserByMobileNo() {

        List<User> user = userService.findUserByMobileNo("13719193845");
        System.out.println(user);

    }

    @Test
    public void findUserByUserNickName() {

        List<User> user = userService.findUserByUserNickName("zhognfucheng");

        System.out.println(user);

    }

/*    @Test
    public void registerUser() {

        userService.registerUser(new User("ouzicheng", "1234432", "123", new Date(), 1, null,null));


    }*/

    @Test
    public void userLogin() {
        //用户注册
        User user = userService.userLogin("13719193845", "123123");
        System.out.println(user);

    }
}