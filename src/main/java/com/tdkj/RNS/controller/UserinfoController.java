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
import com.tdkj.RNS.service.UserinfoService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Api("UserController")
@Controller
@Validated
@RequiredArgsConstructor
@PropertySource("classpath:application-dev.properties")//此处路径需要按需修改
public class UserinfoController implements RnsResultType, RnsResultCode {

    private final ValidateCodeUtil validateCodeUtil;

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private LogService logService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;


    @RequestMapping("/Userinfo")
    public String Userinfo(Model model) {
        /*进入用户修改页面*/
        Userinfo userinfo=userinfoService.selectByPrimaryKey(ShiroUtils.getPrincipal().getId());
        model.addAttribute("userinfo",userinfo);
        model.addAttribute("userinfophoto",uploadFolder+userinfo.getIdUpPhoto());
        log.info(uploadFolder+userinfo.getIdUpPhoto());
        log.info(ShiroUtils.getPrincipal().getId().toString());
        return "page/userinfo";
    }

    @ResponseBody
    @RequestMapping("/setUserinfo")
    public RnsResponse setUserinfo(Integer id,String name,Integer sex,Integer age,String phone,
                              String idNumber,String address,
                              @RequestParam("photo") MultipartFile photofile,
                              @RequestParam("idUpPhoto") MultipartFile idUpPhotofile,
                              @RequestParam("idDownPhoto") MultipartFile idDownPhotofile,
                              @RequestParam("qualificationsPhoto") MultipartFile qualificationsPhotofile,Integer deptid) {
        Userinfo userinfo =new Userinfo();
        userinfo.setId(id);
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setAge(age);
        userinfo.setPhone(phone);
        userinfo.setIdNumber(idNumber);
        userinfo.setAddress(address);
        userinfo.setDeptid(deptid);
        //设置文件保存路径
        File path = new File(uploadFolder);
        if(!path.exists()){
            path.mkdirs();
        }
        MultipartFile photofiles = photofile;
        MultipartFile idUpPhotofiles = idUpPhotofile;
        MultipartFile idDownPhotoofiles = idDownPhotofile;
        MultipartFile qualificationsPhotofiles = qualificationsPhotofile;
        //保存文件
        try {
            //转存文件
            photofiles.transferTo(new File(path+"/"+name+".png"));
            idUpPhotofiles.transferTo(new File(path+"/"+idNumber+"up"+".png"));
            idDownPhotoofiles.transferTo(new File(path+"/"+idNumber+"down"+".png"));
            qualificationsPhotofiles.transferTo(new File(path+"/"+name+"qualifications"+".png"));
            userinfo.setPhoto(name+".png");
            userinfo.setIdUpPhoto(idNumber+"up"+".png");
            userinfo.setIdDownPhoto(idNumber+"down"+".png");
            userinfo.setQualificationsPhoto(name+"从业资格证"+".png");
            userinfo.setCreateTime(new Date());
            Userinfo oldUserinfo =userinfoService.selectByPrimaryKey(id);
            if (null==oldUserinfo){
                userinfoService.insert(userinfo);
                Log log = ShiroUtils.setLog("新增个人信息");
                logService.insert(log);
            }else{
                int a=userinfoService.updateByPrimaryKey(userinfo);
                Log log = ShiroUtils.setLog("修改个人信息");
                logService.insert(log);
            }

        } catch (IOException e) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,UPDATE_FAULT);
        }
        log.info(userinfo.toString());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }
}
