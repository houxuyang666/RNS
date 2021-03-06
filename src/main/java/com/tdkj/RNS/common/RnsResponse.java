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
    private Object Data2;//数据 Data2
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

    public static RnsResponse setResult(Integer ResponseCode, String message,String url, Object Data) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setData(Data);
        rnsResponse.setUrl(url);
        return rnsResponse;
    }

    public static RnsResponse setResult(Integer ResponseCode, String message, Object Data,Object Data2) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setResponseCode(ResponseCode);
        rnsResponse.setMessage(message);
        rnsResponse.setData(Data);
        rnsResponse.setData2(Data2);
        return rnsResponse;
    }


}
