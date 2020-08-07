package com.yy.msmservice.service;

import java.util.Map;

/**
 * @author ycr
 * @date 2020/7/23
 */
public interface MsmService {
    boolean send(String phone, Map<String, Object> param);
}
