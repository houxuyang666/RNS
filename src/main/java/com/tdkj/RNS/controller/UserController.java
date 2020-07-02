package com.tdkj.RNS.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.annotation.Limit;
import com.tdkj.RNS.common.RnsJson;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.*;
import com.tdkj.RNS.exception.RnsException;
import com.tdkj.RNS.service.*;
import com.tdkj.RNS.utils.Md5Util;
import com.tdkj.RNS.utils.RedisUtil;
import com.tdkj.RNS.utils.ShiroUtils;
import com.tdkj.RNS.utils.ValidateCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.Transient;
import java.io.IOException;
import java.util.*;

@Slf4j
@Api("UserController")
@Controller
@Validated
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController implements RnsResultType, RnsResultCode {

    private final ValidateCodeUtil validateCodeUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private CompanyService companyService;


    @RequestMapping("/register")
    public String add(Model model) {
        /*进入注册用户页面*/
        List<Company> companylist =companyService.queryAllCompany();
        model.addAttribute("companylist", companylist);
        return "register";
    }
    /**
     * @Author houxuyang
     * @Description //注册用户
     * @Date 15:06 2020/5/26
     * @Param [username, password]
     * @return java.lang.String
     **/
    @Transactional
    @ResponseBody
    @RequestMapping("/register/user")
    public RnsResponse adduser(String username, String password,String name,Integer sex,Integer age,String tel,Integer companyId) {

        log.info("注册用户");
        log.info(password);
        User olduser = userService.findByName(username);
        if (olduser != null) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"用户名已存在");
        }
        Userinfo userinfo =new Userinfo();
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setAge(age);
        userinfo.setTel(tel);
        userinfo.setCompanyId(companyId);
        userinfo.setCreateTime(new Date());
        log.info(userinfo.toString());
        userinfoService.insert(userinfo);
        System.out.println(userinfo.getId());
        User user = new User();
        //uuid设置盐
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUsername(username);
        user.setPassword(Md5Util.Md5Password(uuid,password));
        user.setStatus(0);
        user.setSalt(uuid);
        user.setRid(1);
        user.setCreateTime(new Date());
        user.setUserinfoId(userinfo.getId());
        userService.insert(user);
        Log log = ShiroUtils.setLog(username,"注册");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,"注册成功");
    }


    @RequestMapping("/goupdapsd")
    public String updatepassword(Model model) {
        log.info("updatepassword");
        return "page/setpassword";
    }

    /**
     * @Author houxuyang
     * @Description //修改密码
     * @Date 15:50 2020/5/26
     * @Param [id, oldpassword, newpassword]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/updatepsd")
    public RnsResponse updatepsd(String oldpassword,String newpassword) {
        log.info("修改密码");
        //根据用户id查询出来用户信息
        User user =userService.queryById(ShiroUtils.getPrincipal().getId());
        //将输入的原密码进行加密后 与数据库密码进行对比
        String dbpassword = Md5Util.Md5Password(user.getSalt(), oldpassword);
        if (!dbpassword.equals(user.getPassword())){
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"原密码错误");
        }
        //密码正确后进入  将新密码进行加密
        newpassword=Md5Util.Md5Password(user.getSalt(), newpassword);
        user.setPassword(newpassword);
        user.setModifyTime(new Date());
        userService.update(user);
        log.info("密码修改成功");
        /*添加日志*/
        Log log = ShiroUtils.setLog("修改密码");
        logService.insert(log);
        //前端未确认返回code
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }


    /**
     * @Author houxuyang
     * @Description //重置某个用户密码
     * @Date 15:50 2020/5/26
     * @Param [id, oldpassword, newpassword]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/setpsd")
    public RnsResponse setpsd(Integer id) {
        log.info("重置密码");
        if("admin".equals(ShiroUtils.getPrincipal().getUsername())){
            User user =userService.queryById(id);
            user.setPassword(Md5Util.Md5Password(user.getSalt(),"123456"));
            user.setModifyTime(new Date());
            userService.update(user);
            log.info("密码修改成功");
            Log log = ShiroUtils.setLog("重置"+user.getUsername()+"密码");
            logService.insert(log);
            return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
        }
        return RnsResponse.setResult(HTTP_RNS_CODE_500,UPDATE_FAULT);
    }

    @RequestMapping("/goselectuser")
    public String goselectuser(Model model) {
        log.info("goselectuser");
        return "page/userlist";
    }

    @ResponseBody
    @RequestMapping("/selectuser")
    public RnsResponse selectuser() {
        log.info("-------------selectuser");
        PageHelper.startPage(1,10);
        List<User> userList=userService.selectUser();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        Log log = ShiroUtils.setLog("查看用户");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,pageInfo);
    }






}
