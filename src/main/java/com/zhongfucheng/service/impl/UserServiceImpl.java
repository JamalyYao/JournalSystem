package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.UserRepository;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUserByMobileNo(String mobileNo) {
        return userRepository.findUserByMobileNo(mobileNo);
    }

    @Override
    public User findUserByLoginToken(String loginToken) {

        return userRepository.findUserByLoginToken(loginToken);
    }




    @Override
    public List<User> findUserByUserNickName(String userNickName)   {
        return userRepository.findUserByUserNickName(userNickName);
    }

    @Override
    public User userRegister(User user)   {

        user.setValidity(1);
        return userRepository.save(user);

    }

    @Override
    public User userLogin(String mobileNo, String password)   {
        return userRepository.findUserByMobileNoAndPassword(mobileNo, password);
    }

    @Override
    public User userUpload(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id)   {
        return userRepository.findOne(id);
    }


}
