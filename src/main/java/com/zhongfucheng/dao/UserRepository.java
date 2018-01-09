package com.zhongfucheng.dao;

import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {


    /**
     * 通过电话查询出用户
     *
     * @param mobileNo
     * @return
     */
    List<User> findUserByMobileNo(String mobileNo);


    /**
     * 通过昵称判断该用户是否存在
     *
     * @param userNickName
     * @return
     */
    List<User> findUserByUserNickName(String userNickName);


    /**
     * 查询是否有该用户名和密码
     *
     * @param mobileNo
     * @param password
     * @return
     */
    User findUserByMobileNoAndPassword(String mobileNo, String password);

}
