package com.yy.educommons.model;

import lombok.Data;

/**
 * @author ycr
 * @date 2020/10/14
 */
@Data
public class ApiException extends RuntimeException {

    private ApiStatus code;

    private String message;

    public ApiException(ApiStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
