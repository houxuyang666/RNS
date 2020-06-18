package com.tdkj.RNS.controller;

import com.alibaba.fastjson.JSONArray;
import com.tdkj.RNS.annotation.Limit;
import com.tdkj.RNS.common.RnsJson;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.entity.Userinfo;
import com.tdkj.RNS.exception.RnsException;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.MenuService;
import com.tdkj.RNS.service.UserService;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Slf4j
@Api("UserController")
@Controller
@Validated
@RequiredArgsConstructor
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


    @RequestMapping("/add")
    public String add() {
        /*进入添加用户页面*/
        return "user/add";
    }
    /**
     * @Author houxuyang
     * @Description //添加用户
     * @Date 15:06 2020/5/26
     * @Param [username, password]
     * @return java.lang.String
     **/
    @RequestMapping("/adduser")
    public String adduser(String username, String password, Integer roleid, Model model) {
        log.info("添加用户");
        User user = userService.findByName(username);
        if (user != null) {
            model.addAttribute("msg", "用户名已存在");
            return "user/add";
        }
        user = new User();
        //uuid设置盐
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUsername(username);
        user.setPassword(Md5Util.Md5Password(uuid,password));
        user.setStatus(0);
        user.setSalt(uuid);
        user.setRid(roleid);
        user.setCreateTime(new Date());
        Userinfo userinfo =new Userinfo();
        userService.insert(user);
        /*添加日志*/
        //如用户添加 删除用户 等等  都是谁 操作了谁 所以重写一个方法
        Log log = ShiroUtils.setLog("添加用户", username);
        logService.insert(log);
        model.addAttribute("msg","添加成功");
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        System.out.println("update");
        return "user/update";
    }


    //离子页面
    @RequestMapping("/basic_info")
    public String basic_info() {
        return "user/basic_info";
    }


    /**
     * @Author houxuyang
     * @Description //修改密码
     * @Date 15:50 2020/5/26
     * @Param [id, oldpassword, newpassword]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/updatepassword")
    public RnsResponse updatepassword(Integer id,String oldpassword,String newpassword) {
        log.info("修改密码");
        //根据用户id查询出来用户信息
        User user =userService.queryById(id);
        //将输入的原密码进行加密后 与数据库密码进行对比
        String dbpassword = Md5Util.Md5Password(user.getSalt(), oldpassword);
        if (!dbpassword.equals(user.getPassword())){
            return RnsResponse.setResult(HTTP_RNS_CODE_500,UPDATE_FAULT);
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

    @RequestMapping("/tologin")
    public String toLogin() {
        log.info("------------------------------tologin");
       return "login-1";
    }

    @RequestMapping("/noAuth")
    public String noAuth() {
        System.out.println("noAuth");
        return "noAuth";
    }


    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }

    /**
     * @Author houxuyang
     * @Description //查询目录
     * @Date 15:26 2020/6/18
     * @Param []
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/indexinit")
    public RnsResponse indexinit() {
        List<Menu> menuList =menuService.queryAllByLimit(0,20);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS, RnsJson.toJson(menuList));
    }


    /*编写shiro 登录认证逻辑*/
    @ApiOperation("登录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "username", value = "账号", required = true, dataType = "String")
            , @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String")
    }
    )
    @ResponseBody
    @RequestMapping("/login")
    @PostMapping
    public RnsResponse login(String username, String password, boolean rememberMe,String verifyCode,
                        HttpServletRequest request) throws Exception {
        log.info("-----login");
        log.info("username" + ":" + username);
        log.info("password" + ":" + password);
        log.info("rememberMe" + ":" + rememberMe);
        /*使用Shiro编写认证操作
         *1.获取subjec
         * */
        Subject subject = SecurityUtils.getSubject();
        try {
            HttpSession session = request.getSession();
            validateCodeUtil.check(session.getId(), verifyCode);
            User user = userService.findByName(username);
            if (1 == user.getStatus()) {
                //model.addAttribute("msg", "账户已被冻结，请联系管理员");
                return RnsResponse.setResult(HTTP_RNS_CODE_401,"账户已被冻结，请联系管理员");
            }
            password = Md5Util.Md5Password(user.getSalt(), password);
            /*2.封装用户数据*/ //记住我
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            /*3.执行登录操作*/
            //会将用户信息传给 UserRealm的doGetAuthenticationInfo方法的authenticationToken参数 用于与数据库验证
            subject.login(token);
            /*设置session*/
            Log log = ShiroUtils.setLog("登录"); //将操作传过去生成对象后 插入DB
            logService.insert(log);
            Session session1 = subject.getSession();
            session1.setAttribute("user", user);
            return RnsResponse.setResult(HTTP_RNS_CODE_200,"/index");
        } catch(RnsException e){
            return RnsResponse.setResult(HTTP_RNS_CODE_401,"验证码错误！");
        }catch (UnknownAccountException e){
            //UnknownAccountException 指的是用户名不存在 但是为了防止恶意扫描账号 提示为用户名或密码不正确
            return RnsResponse.setResult(HTTP_RNS_CODE_401,"用户名或密码错误！");
        }catch (IncorrectCredentialsException e){
            return RnsResponse.setResult(HTTP_RNS_CODE_401,"用户名或密码错误");
        }catch (NullPointerException e){
            //由于在此需要获取用户的盐值及用户名等 会出现null错误 所以添加try
            return RnsResponse.setResult(HTTP_RNS_CODE_401,"用户名或密码错误");
        }
    }

    @RequestMapping("/selectuser")
    public RnsResponse selectuser() {
        log.info("-------------selectuser");
        List<User> userList =userService.selectUser();
        Object json = JSONArray.toJSON(userList);
        //log.info(json.toString());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,json.toString());
    }

    @GetMapping("images/captcha")
    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, RnsException {
        validateCodeUtil.create(request, response);
        log.info("测试生成验证码");
    }

}
