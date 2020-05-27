package com.tdkj.RNS.configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.tdkj.RNS.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{
    /*
     * @Author houxuyang
     * @Description //创建ShiroFilterFactoryBean 该类用于设置资源拦截器 和资源授权
     * @Date 10:50 2020/5/22
     * @Param [securityManager]
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*shiro内置过滤器，可以实现权限相关的拦截器
        *   常用的过滤器：
        *           anon：无需认证可以访问
        *           authc：必须认证才能访问
        *           user：如果使用rememberMe的功能可以直接访问
        *           perms：该资源必须得到资源权限才能访问
        *           role：该资源必须得到角色权限才能访问
        * */
        /*创建一个map存储功能和他它的权限 第一个参数为controller的 RequestMapping*/
        Map<String ,String> filterMap =new LinkedHashMap<String,String>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/

        /*也可以配合使用 让某个请求可以被访问 ，但是必须在通配符上面*/
        //filterMap.put("/index","anon");
        filterMap.put("/login","anon");
        /* 第二个参数配置为logout  不用配置cotroller就能直接退出 并清除session*/
        filterMap.put("/logout","logout");

        /*Swagger 开放白名单*/

        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");


        /*授权拦截器  访问add 需要perms[user:add] 该授权*/
        filterMap.put("/adduser","perms[user:adduser]");
        filterMap.put("/updatepassword","perms[user:updatepassword]");
        filterMap.put("/select","perms[user:select]");
        filterMap.put("/delete","perms[user:delete]");

        /*授权拦截器 结束*/

        /*放行静态资源-开始*/
        filterMap.put("/css/**","anon");
        filterMap.put("/fonts/**","anon");
        filterMap.put("/icons/**","anon");
        filterMap.put("/images/**","anon");
        filterMap.put("/images/guide/**","anon");
        filterMap.put("/images/main/**","anon");
        filterMap.put("/images/timeline/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/js/lizibg/**","anon");
        filterMap.put("/json/**","anon");
        filterMap.put("/uimaker/**","anon");
        filterMap.put("/uimaker/css/**","anon");
        filterMap.put("/uimaker/css/login/**","anon");
        filterMap.put("/uimaker/icons/**","anon");
        filterMap.put("/uimaker/images/**","anon");
        filterMap.put("/uimaker/images/new/**","anon");
        filterMap.put("/uimaker/imgs.login","anon");
        filterMap.put("/uimaker/js/**","anon");
        /*放行静态资源-结束*/
        /*用通配符  */
        filterMap.put("/**","authc");

        /*修改被拦截之后跳转的页面  参数为controller的 RequestMapping*/
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        /*设置未授权跳转的页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        /*将存好的权限交给shiroFilterFactoryBean 处理*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /*
     * @Author houxuyang
     * @Description //创建DefaultWebSecurityManager 默认的web安全管理器 获取Realm进行管理
     * @Date 10:50 2020/5/22
     * @Param [userRealm]
     * @return org.apache.shiro.web.mgt.DefaultWebSecurityManager
     **/
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        /*创建一个默认的web安全管理器 ，将自定义Realm 交给shiro管理*/
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
     * @Author houxuyang
     * @Description //创建Realm  new了一个自定义Realm
     * @Date 10:50 2020/5/22
     * @Param []
     * @return UserRealm
     **/
    @Bean
    public UserRealm getUserRealm(){
        /*UserRealm用于验证用户 以及授权用户 等逻辑*/
        return new UserRealm();
    }


    /*
     * @Author houxuyang
     * @Description //配置ShiroDialect ，用于thymeleaf和shiro标签配合使用
     * @Date 10:49 2020/5/22
     * @Param []
     * @return at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     **/
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }




}
