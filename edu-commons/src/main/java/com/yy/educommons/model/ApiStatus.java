package com.yy.educommons.model;

import java.io.Serializable;

public enum ApiStatus implements Serializable {

    DELETE_DATA_ERROR("5003"),

    DELETE_DATA_SUCCESSFUL("2003"),

    QUERY_DATA_ERROR("5000"),

    QUERY_DATA_SUCCESSFUL("2000"),

    SAVA_DATA_ERROR("5001"),

    SAVA_DATA_SUCCESSFUL("2001"),

    UPDATE_DATA_ERROR("5002"),

    UPDATE_DATA_SUCCESSFUL("2002");

    ApiStatus(String code) {
    }
}
