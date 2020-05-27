package com.tdkj.RNS.controller;

import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.UserService;
import com.tdkj.RNS.utils.Md5Util;
import com.tdkj.RNS.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @RequestMapping("/add")
    public String add() {
        System.out.println("add");
        return "/user/add";
    }
    /**
     * @Author houxuyang
     * @Description //添加用户
     * @Date 15:06 2020/5/26
     * @Param [username, password]
     * @return java.lang.String
     **/
    @RequestMapping("/adduser")
    public String adduser(String username,String password,Integer roleid,Model model) {
        User user = userService.findByName(username);
        if (user != null) {
            model.addAttribute("msg","用户名已存在");
            return "/user/add";
        }
        user = new User();
        //uuid设置盐
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUsername(username);
        user.setPassword(Md5Util.Md5Password(uuid,password));
        user.setStatus(0);
        user.setSalt(uuid);
        user.setRid(roleid);
        userService.insert(user);
        /*添加日志*/
        Log log = ShiroUtils.setLog("添加用户",username);
        logService.insert(log);
        model.addAttribute("msg","添加成功");
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {

        System.out.println("update");
        return "/user/update";
    }

    @RequestMapping("/Notice")
    public String Notice() {

        System.out.println("Notice");
        return "/user/Notice";
    }

    @RequestMapping("/JobSetup")
    public String JobSetup() {
        System.out.println("JobSetup");
        return "/user/JobSetup";
    }


    @RequestMapping("/basic_info")
    public String basic_info() {
        System.out.println("basic_info");
        return "/user/basic_info";
    }

    @RequestMapping("/AttendanceInfo")
    public String AttendanceInfo() {
        System.out.println("AttendanceInfo");
        return "/user/AttendanceInfo";
    }

    @RequestMapping("/CompanyInfo")
    public String CompanyInfo() {
        System.out.println("CompanyInfo");
        return "/user/CompanyInfo";
    }

    @RequestMapping("/MapInfo")
    public String MapInfo() {
        System.out.println("MapInfo");
        return "/user/MapInfo";
    }

    @RequestMapping("/ProjectInfo")
    public String ProjectInfo() {
        System.out.println("ProjectInfo");
        return "/user/ProjectInfo";
    }

    @RequestMapping("/WageInfo")
    public String WageInfo() {
        System.out.println("WageInfo");
        return "/user/WageInfo";
    }

    @RequestMapping("/WorkerIDInfo")
    public String WorkerIDInfo() {
        System.out.println("WorkerIDInfo");
        return "/user/WorkerIDInfo";
    }


    /**
     * @Author houxuyang
     * @Description //修改密码
     * @Date 15:50 2020/5/26
     * @Param [id, oldpassword, newpassword]
     * @return java.lang.String
     **/
    @RequestMapping("/updatepassword")
    public String updatepassword(Integer id,String oldpassword,String newpassword) {
        //根据用户id查询出来用户信息
        User user =userService.selectByPrimaryKey(id);
        System.out.println("oldpassword---------"+oldpassword);
        System.out.println("newpassword---------"+newpassword);

        //将输入的原密码进行加密后 与数据库密码进行对比
        String dbpassword = Md5Util.Md5Password(user.getSalt(), oldpassword);
        if (dbpassword.equals(user.getPassword())){
            //密码正确后进入  将新密码进行加密
            newpassword=Md5Util.Md5Password(user.getSalt(), newpassword);
            user.setPassword(newpassword);
            userService.findByidUpdate(user);
            /*添加日志*/
            Log log = ShiroUtils.setLog("修改密码");
            logService.insert(log);
            return "updateSuccess";
        }
        return "updateError";
    }

    @RequestMapping("/select")
    public String select() {
        System.out.println("select");
        return null;
    }
    @RequestMapping("/delete")
    public String delete() {
        System.out.println("delete");
        return null;
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        System.out.println("tologin");
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth() {
        System.out.println("noAuth");
        return "noAuth";
    }

    @RequestMapping("/index")
    public String index() {
        return "/user/index";
    }

    /*编写shiro 登录认证逻辑*/
    @RequestMapping("/login")
    public String login(String username,String password,Model model) {
        /*使用Shiro编写认证操作
        *1.获取subjec
        * */
        if ("".equals(username)||"".equals(password)){
            model.addAttribute("msg","请输入用户名或密码！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            User user =userService.findByName(username);
            if (1==user.getStatus()){
                model.addAttribute("msg","账户已被冻结，请联系管理员");
                return "login";
            }
            password = Md5Util.Md5Password(user.getSalt(), password);
            /*2.封装用户数据*/
            UsernamePasswordToken token =new UsernamePasswordToken(username,password);
            /*3.执行登录操作*/
            //会将用户信息传给 UserRealm的doGetAuthenticationInfo方法的authenticationToken参数 用于与数据库验证
            subject.login(token);
            /*设置session*/
            Log log = ShiroUtils.setLog("登录"); //将操作传过去生成对象后 插入DB
            logService.insert(log);
            Session session =subject.getSession();
            session.setAttribute("user",user);
            //登录成功 重定向
            return "redirect:/index";
        }catch (UnknownAccountException e){
            //UnknownAccountException 指的是用户名不存在 但是为了防止恶意扫描账号 提示为用户名或密码不正确
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }catch (NullPointerException e){
            //由于在此需要获取用户的盐值及用户名等 会出现null错误 所以添加try
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }




}
