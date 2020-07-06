package com.yy.exception;

public enum CustomExceptionType {

    USER_INPUT_ERROR(40000, "用户输入异常"),
    SYSTEM_ERROR(50000, "系统异常"),
    OTHER_ERROR(99999, "其他未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    /*异常编码*/
    private int code;
    /*异常描述*/
    private String typeDesc;


    public int getCode() {
        return code;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
