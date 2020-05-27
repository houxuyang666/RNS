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
     * @Author houxuyang
     * @Description //用于插入log
     * @Date 14:30 2020/5/27
     * @Param [operation]
     * @return com.tdkj.RNS.entity.Log
     **/
    public static Log setLog(String operation){
        Log log = new Log();
        log.setOperatedate(new Date());
        log.setOperateor(ShiroUtils.getPrincipal().getUsername());
        log.setOperateresult("正常");
        log.setOperatetype(operation);
        log.setIp(IpUtils.getIp());
        return log;
    }

    /**
     * @Author houxuyang
     * @Description //重载日志方法
     * @Date 14:31 2020/5/27
     * @Param [operation, username]
     * @return com.tdkj.RNS.entity.Log
     **/
    public static Log setLog(String operation,String resources){
        //如用户添加 删除用户 等等  都是谁 操作了谁 所以重写一个方法
        Log log = new Log();
        log.setOperatedate(new Date());
        log.setOperateor(ShiroUtils.getPrincipal().getUsername());
        log.setOperateresult("正常");
        log.setOperatetype(operation+":"+resources);
        log.setIp(IpUtils.getIp());
        return log;
    }
}
