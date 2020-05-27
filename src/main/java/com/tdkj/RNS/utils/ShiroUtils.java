package com.tdkj.RNS.utils;

import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.User;
import org.apache.shiro.SecurityUtils;

import java.util.Date;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/26 17:11
 */
public class ShiroUtils {

    /**
     * @Author houxuyang
     * @Description //获取当前用户的session
     * @Date 16:48 2020/5/26
     * @Param []
     * @return User
     **/
    public static User getPrincipal(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 
     * @param operation
     * @return
     */
    public static Log setLog(String operation){
        Log log = new Log();
        log.setOperatedate(new Date());
        log.setOperateor(ShiroUtils.getPrincipal().getUsername());
        log.setOperateresult("正常");
        log.setOperatetype(operation);
        log.setIp(IpUtils.getIp());
        return log;
    }

    public static Log setLog(String operation,String username){
        Log log = new Log();
        log.setOperatedate(new Date());
        log.setOperateor(ShiroUtils.getPrincipal().getUsername());
        log.setOperateresult("正常");
        log.setOperatetype(operation+":"+username);
        log.setIp(IpUtils.getIp());
        return log;
    }
}
