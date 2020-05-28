package com.tdkj.RNS.common;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @Description RNS系统,统一响应格式
 * @ClassName RnsResponse
 * @Author Chang
 * @date 2020.05.28 10:49
 */
public class RnsResponse extends HashMap<String, Object> {
    public RnsResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public RnsResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public RnsResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public RnsResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public RnsResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public RnsResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
