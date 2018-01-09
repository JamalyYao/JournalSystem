package com.zhongfucheng.handle;

import com.zhongfucheng.dto.Result;
import com.zhongfucheng.enums.ResultEnum;
import com.zhongfucheng.exception.UserException;
import com.zhongfucheng.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof UserException) {
            UserException userException = (UserException) e;
            return ResultUtil.error(userException.getResultEnum());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }

    }
}
