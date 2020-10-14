package com.yy.educommons;

import com.yy.educommons.model.ApiModel;
import com.yy.educommons.model.NullError;
import com.yy.exception.CustomException;
import com.yy.exception.CustomExceptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author ycr
 * @date 2020/5/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultCommon {

    private Boolean isOk;

    private Integer code;

    private String message;

    private Object data;


    /**
     * 请求出现异常时的响应数据封装
     *
     * @param e 异常对象
     * @return ResultCommon
     */
    public static ResultCommon error(CustomException e) {
        ResultCommon result = new ResultCommon();
        result.setIsOk(false);
        result.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()) {
            result.setMessage(e.getMessage());
        } else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            result.setMessage(e.getMessage() + ",请将该异常信息发送给管理员!");
        } else if (e.getCode() == CustomExceptionType.OTHER_ERROR.getCode()) {
            result.setMessage("系统出现未知异常，请联系管理员!");
        }
        //TODO 可以将异常信息持久化
        return result;
    }

    /**
     * 请求出现异常时的响应数据封装
     *
     * @param type     错误类型
     * @param errorMsg 错误信息
     * @return ResultCommon
     */
    public static ResultCommon error(CustomExceptionType type, String errorMsg) {
        ResultCommon result = new ResultCommon();
        result.setIsOk(false);
        result.setMessage(errorMsg);
        result.setCode(type.getCode());
        return result;
    }

    /**
     * 请求成功的数据响应封装
     *
     * @return ResultCommon
     */
    public static ResultCommon success() {
        ResultCommon result = new ResultCommon();
        result.setIsOk(true);
        result.setCode(20000);
        result.setMessage("success");
        return result;
    }

    /**
     * 请求成功的数据响应封装
     *
     * @param data 返回的数据
     * @return ResultCommon
     */
    public static ResultCommon success(Object data) {
        ResultCommon result = new ResultCommon();
        result.data = data;
        result.setCode(20000);
        result.setMessage("success");
        result.setIsOk(true);
        return result;
    }

    public static ResultCommon fail() {
        ResultCommon result = new ResultCommon();
        result.setIsOk(false);
        result.setCode(20001);
        result.setMessage("fail");
        return result;
    }
}
