package com.tdkj.RNS.common;

import com.alibaba.fastjson.JSONArray;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/18 15:23
 */
public class RnsJson {
    private Object Data;

    public static Object toJson(Object Data){
        Object json = JSONArray.toJSON(Data);
        return json;
    }




}
