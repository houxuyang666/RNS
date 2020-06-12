package com.tdkj.RNS.common;

import lombok.Data;

/**
 * @Description RNS系统,统一响应格式
 * @ClassName RnsResponse
 * @Author Chang
 * @date 2020.05.28 10:49
 */
@Data
public class RnsResponse {

    private Integer ResponseCode;
    private String Message;

    private Object rows;//数据 Data
    private Long total;

    public static RnsResponse setResult(Integer ResponseCode, String message, Object rows) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setRows(rows);
        return rnsResponse;
    }

    public static RnsResponse setResult(Integer ResponseCode, String message) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        return rnsResponse;
    }


    public static RnsResponse setResult(Integer ResponseCode, String message, Object rows,Long total) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setRows(rows);
        rnsResponse.setTotal(total);
        return rnsResponse;
    }


}
