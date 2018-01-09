package com.zhongfucheng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ozc on 2018/1/9.
 *
 * @author ozc
 * @version 1.0
 * <p>
 * 用户信息表
 */

@Entity
public class UserInfo implements Serializable {


    /**
     * 主键.
     */
    @Id
    @GeneratedValue
    private Integer id;




}
