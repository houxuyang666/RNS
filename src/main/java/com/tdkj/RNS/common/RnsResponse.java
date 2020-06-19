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
    private String Url;

    private Object Data;//数据 Data
    private Long total;

    public static RnsResponse setResult(Integer ResponseCode, String message, Object data) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setData(data);
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
        rnsResponse.setData(rows);
        rnsResponse.setTotal(total);
        return rnsResponse;
    }

    public static RnsResponse setResult(Integer ResponseCode, String message,String url, Object rows) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setData(rows);
        rnsResponse.setUrl(url);
        return rnsResponse;
    }


}
