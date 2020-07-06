package com.tdkj.RNS.common;

import lombok.Data;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/7/6 11:36
 */
@Data
public class RnsResponseList {

    private Integer code;
    private String Message;
    private Object Data;//数据 Data

    public static RnsResponseList setResult(Integer code, String message, Object data) {
        RnsResponseList rnsResponseList =new RnsResponseList();
        rnsResponseList.setCode(code);
        rnsResponseList.setMessage(message);
        rnsResponseList.setData(data);
        return rnsResponseList;
    }


}
