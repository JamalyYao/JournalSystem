package com.zhongfucheng.utils;

import com.zhongfucheng.dto.Result;
import com.zhongfucheng.enums.ResultEnum;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 *
 * 用于快速封装Result对象。
 */

public class ResultUtil {

    //成功
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    //失败
    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
    public static Result success() {
        return success(null);
    }


}
