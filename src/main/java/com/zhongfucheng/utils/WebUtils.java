package com.zhongfucheng.utils;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by ozc on 2018/1/7.
 *
 * @author ozc
 * @version 1.0
 * <p>
 * Web层工具类
 */
public class WebUtils {


    /**
     * 判断验证码是否正确
     * @param inputCaptcha 页面输入的验证码
     * @param sessionAttr 存在session域的key名称
     * @param session
     * @return
     */
    public static boolean validateCaptcha(String inputCaptcha, String sessionAttr, HttpSession session) {

        String captchaVal = (String) session.getAttribute(sessionAttr);
        if (inputCaptcha != null && captchaVal != null && inputCaptcha.equalsIgnoreCase(captchaVal)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取得到4位的随机数
     * @return
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }
}
