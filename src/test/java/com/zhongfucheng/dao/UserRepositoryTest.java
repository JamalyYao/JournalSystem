package com.zhongfucheng.dao;

import com.zhongfucheng.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserByMobileNo() {

        List<User> user  = userRepository.findUserByMobileNo("13719193845");

        System.out.println(user);
    }

    @Test
    public void findUserByUserNickName() {

        List<User> user = userRepository.findUserByUserNickName("zhognfucheng");

        System.out.println(user);
    }


    @Test
    public void registerUser() {

        userRepository.save(new User("zhognfucheng", "13719193845", "123", new Date(), 1, null,null));

    }

    @Test
    public void findUserByMobileNoAndPassword() {

        User user = userRepository.findUserByMobileNoAndPassword("13719193845", "123123");

        System.out.println(user);
    }
}