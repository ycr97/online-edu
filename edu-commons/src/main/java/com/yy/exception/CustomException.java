package com.yy.exception;

/**
 * @author ycr
 * @date 2020/6/3
 */
public class CustomException extends RuntimeException{

    /*错误异常编码*/
    private int code;

    /*异常信息*/
    private String message;

    protected CustomException(){}

    public CustomException(CustomExceptionType type, String message) {
        this.code = type.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
