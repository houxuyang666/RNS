package com.tdkj.RNS.controller;

import com.tdkj.RNS.annotation.Limit;
import com.tdkj.RNS.common.RnsJson;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.entity.MenuTree;
import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.exception.RnsException;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.MenuService;
import com.tdkj.RNS.service.UserService;
import com.tdkj.RNS.service.UserinfoService;
import com.tdkj.RNS.utils.Md5Util;
import com.tdkj.RNS.utils.RedisUtil;
import com.tdkj.RNS.utils.ShiroUtils;
import com.tdkj.RNS.utils.ValidateCodeUtil;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 11:43
 */

@Slf4j
@Controller
@Validated
@RequiredArgsConstructor
public class LoginController implements RnsResultType, RnsResultCode{
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

    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }


    @RequestMapping("/welcome")
    public String welcome() {
        return "page/welcome";
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
        MenuTree<Menu> menuList =menuService.findByUsernameGetMenu(ShiroUtils.getPrincipal().getUsername());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS, RnsJson.toJson(menuList));
    }

    @ResponseBody
    @GetMapping("tree")
    public RnsResponse getMenuTree() {
        MenuTree<Menu> allMenus = menuService.findMenus();
        //System.out.println(allMenus);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS, RnsJson.toJson(allMenus));
    }

/*    *//*编写shiro 登录认证逻辑*//*
    @ApiOperation("登录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "username", value = "账号", required = true, dataType = "String")
            , @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String")
    }
    )*/
    @ResponseBody
    @RequestMapping("/login")
    @PostMapping
    public RnsResponse login(String username, String password, boolean rememberMe, String verifyCode,
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
            //token.setRememberMe(true);
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

    @GetMapping("images/captcha")
    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, RnsException {
        validateCodeUtil.create(request, response);
        log.info("测试生成验证码");
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        log.info("------------------------------tologin");
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth() {
        System.out.println("noAuth");
        return "noAuth";
    }
}
