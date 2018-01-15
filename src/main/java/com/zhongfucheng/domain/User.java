package com.zhongfucheng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ozc on 2018/1/3.
 *
 * @author ozc
 * @version 1.0
 */

@Entity
public class User  implements Serializable {


    /** 主键. */
    @Id
    @GeneratedValue
    private Integer userId;

    /** 用户名. */
    @NotNull(message = "昵称不能为空")
    @Size(min=2,max=18,message="昵称最低2位、最高18位")
    private String userNickName = "";

    /** 手机号码. */
    @NotNull(message = "手机号码不能为空")
    @Size(min=11,max=11,message="手机号码只能是11位")
    private String mobileNo = "";

    /** 密码. */
    @NotNull(message = "密码不能为空")
    @Size(min=6,max=18,message="密码只能是6到18位")
    private String password = "";

    /** 注册时间. */
    private Date registerTime = new Date();

    /**
     * 是否有效 0 有效 1 无效.
     */
    private Integer validity = 0;

    private String email = "";
    /**
     * 头像.
     */
    private String headPortrait = "";



    public User() {

    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", validity=" + validity +
                ", email='" + email + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
