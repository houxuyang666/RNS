package com.tdkj.RNS.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

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
    private Object Data;
    private long total;

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


    public static RnsResponse setResult(long total, Object data) {
        RnsResponse rnsResponse =new RnsResponse();
        rnsResponse.setTotal(total);
        rnsResponse.setData(data);
        return rnsResponse;
    }

}
