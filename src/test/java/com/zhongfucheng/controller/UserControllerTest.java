package com.zhongfucheng.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;


/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mvc;


    @Test
    public void register() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user").param("userNickName", "zhong234").param("mobileNo", "13719193843").param("password", "123123").param("registerTime", new Date().toString()).param("validity", "1").param("headPortrait", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("abc"));

    }


    @Test
    public void validateMobileNo() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/user/mobileNo").param("mobileNo", "13719193845"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void validateUserNickName() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/user/userNickName").param("userNickName", "ouzichen"))
                .andDo(MockMvcResultHandlers.print());


    }
}