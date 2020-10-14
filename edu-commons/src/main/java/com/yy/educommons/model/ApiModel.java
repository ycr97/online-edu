package com.yy.educommons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ycr
 * @date 2020/10/13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiModel<D extends Serializable, E extends Serializable> implements Serializable {

    private static final long serialVersionUID = 3909206440170684275L;

    private Boolean status;

    private String message;

    private ApiStatus code;

    private D data;

    private E error;

    public static <D extends Serializable, E extends Serializable> ApiModel ok(D data, E status) {
        ApiModel apiModel = new ApiModel(true, "successful", ApiStatus.QUERY_DATA_SUCCESSFUL);
        apiModel.setData(data);
        return apiModel;
    }

    public static void main(String[] args) {
        new ApiModel<String, NullError>();
        ApiModel.ok("", ApiStatus.QUERY_DATA_SUCCESSFUL);
    }

    public ApiModel(Boolean status, String message, ApiStatus code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
