package com.zhongfucheng.service;

import com.zhongfucheng.domain.User;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface UserService {

    /**
     * 通过电话查询出用户
     *
     * @param mobileNo
     * @return
     */
    List<User> findUserByMobileNo(String mobileNo)  ;


    /**
     * 通过昵称判断该用户是否存在
     *
     * @param userNickName
     * @return
     */
    List<User> findUserByUserNickName(String userNickName)  ;


    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    User userRegister(User user)  ;


    /**
     * 用户登陆
     *
     * @param mobileNo
     * @param password
     * @return
     */
    User userLogin(String mobileNo, String password)  ;


    /**
     * 更新用户的数据
     *
     * @param user
     * @return
     */
    User userUpload(User user)  ;


    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User findUserById(Integer id)  ;


}
