package com.zhongfucheng.controller;

import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.enums.ResultEnum;
import com.zhongfucheng.service.UserService;
import com.zhongfucheng.utils.ResultUtil;
import com.zhongfucheng.utils.WebUtils;
import com.zhongfucheng.utils.note.SendSmsDemo;
import com.zhongfucheng.utils.vcode.Captcha;
import com.zhongfucheng.utils.vcode.GifCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@RestController
public class UserController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param user
     */
    @PostMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Result register(@Valid User user, String captcha, BindingResult bindingResult, HttpSession session) {

        // 传过来的参数错误
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors != null && allErrors.size() > 0) {
            logger.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(ResultEnum.PARAM_ERROR);
        }

        //判断验证码是否正确
        if (WebUtils.validateCaptcha(captcha, "validateCode", session)) {

            //判断完就把验证码删除了
            session.removeAttribute("validateCode");

            // 注册
            User user1 = userService.registerUser(user);
            if (user1 != null) {
                return ResultUtil.success(null);
            } else {
                return ResultUtil.error(ResultEnum.UNKONW_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.CAPTCHA_ERROR);
        }


    }


    /**
     * 发送手机验证码(设置验证码到session中)
     */
    @GetMapping("/user/mobileCode")
    public void mobileCode(HttpSession session, String mobileNo ) {
        //生成4位随机数
        String fourRandom = WebUtils.getFourRandom();
        SendSmsDemo.sendSMS(mobileNo, "3", new String[]{fourRandom, "3"}, session);

    }
    /**
     * 获取用户
     *
     * @param
     */
    @GetMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Result getUser( HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResultUtil.success(user);
        } else {
            return ResultUtil.error(ResultEnum.USER_NOEXIST);
        }

    }

    /**
     * 检查手机是否已被使用（由Juqery-validation的remote调用，返回true或者false）
     */
    @GetMapping("/user/mobileNo")
    public void validateMobileNo(String mobileNo, PrintWriter writer) {
        List<User> users = userService.findUserByMobileNo(mobileNo);
        if (users != null && users.size() > 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }

    }

    /**
     * 检查昵称是否已被使用（由Juqery-validation的remote调用，返回true或者false）
     */
    @GetMapping("/user/userNickName")
    public void validateUserNickName(String userNickName, PrintWriter writer) {
        List<User> users = userService.findUserByUserNickName(userNickName);
        if (users != null && users.size() > 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }
    }

    /**
     * 获取GIF验证码
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("/user/gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        // gif格式动画验证码 宽，高，位数。
        Captcha captcha = new GifCaptcha(146, 42, 4);

        ServletOutputStream out = response.getOutputStream();
        captcha.out(out);
        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
    }


    /**
     * 用户登陆
     *
     * @param mobileNo
     * @param password
     * @param inputCaptcha
     * @param rememberMe
     * @param session
     * @return
     */
    @PostMapping(value = "/session", produces = {"application/json;charset=UTF-8"})
    public Result login(String mobileNo, String password, String inputCaptcha, String rememberMe, HttpSession session) {


        //判断验证码是否正确
        if (WebUtils.validateCaptcha(inputCaptcha, "captcha", session)) {

            //判断有没有该用户
            User user = userService.userLogin(mobileNo, password);
            if (user != null) {
                session.setAttribute("user", user);

                return ResultUtil.success(user);

            } else {
                return ResultUtil.error(ResultEnum.LOGIN_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.CAPTCHA_ERROR);
        }

    }


}
