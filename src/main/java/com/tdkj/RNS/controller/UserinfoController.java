package com.tdkj.RNS.controller;

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Company;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Userinfo;
import com.tdkj.RNS.service.CompanyService;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.UserinfoService;
import com.tdkj.RNS.utils.ShiroUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
//@PropertySource("classpath:application-dev.properties")//此处路径需要按需修改
@RequestMapping("userinfo")
public class UserinfoController implements RnsResultType, RnsResultCode {

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private LogService logService;

    @Autowired
    private CompanyService companyService;

//    @Value("${file.uploadFolder}")
//    private String uploadFolder;
//
//    @Value("${file.uploadtempFolder}")
//    private String uploadtempFolder;

    @RequestMapping("/gouserinfo")
    public String Userinfo(Model model) throws Exception {
            return "page/userinfo";
    }

    @ResponseBody
    @RequestMapping("/userinfo")
    public RnsResponse userinfo() {
        /*进入用户修改页面*/
        Userinfo userinfo;
        try {
            userinfo=userinfoService.queryById(ShiroUtils.getPrincipal().getUserinfoId());
            List<Company> companylist =companyService.queryAllCompany();
            return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS,userinfo,companylist);
        }catch (NullPointerException E){

        }
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }





    @ResponseBody
    @RequestMapping("/setUserinfo")
    public RnsResponse setUserinfo(String name,Integer sex,Integer age,String tel,Integer companyId) {
        Integer id =ShiroUtils.getPrincipal().getId();
        Userinfo userinfo =new Userinfo();
        userinfo.setId(id);
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setAge(age);
        userinfo.setTel(tel);
        userinfo.setCompanyId(companyId);
        Userinfo oldUserinfo =userinfoService.queryById(id);
            if (null==oldUserinfo){
                userinfo.setCreateTime(new Date());
                userinfoService.insert(userinfo);
                Log log = ShiroUtils.setLog("新增个人信息");
                logService.insert(log);
                return RnsResponse.setResult(HTTP_RNS_CODE_200,SAVE_SUCCESS);
            }else{
                userinfo.setModifyTime(new Date());
                userinfoService.update(userinfo);
                Log log = ShiroUtils.setLog("修改个人信息");
                logService.insert(log);
            }
        log.info(userinfo.toString());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }

    /*@ResponseBody
    @RequestMapping("/setUserinfo")
    public RnsResponse setUserinfo(String name,Integer sex,Integer age,String phone,
                              String idNumber,String address,
                              @RequestParam("photo") MultipartFile photofile,
                              @RequestParam("idUpPhoto") MultipartFile idUpPhotofile,
                              @RequestParam("idDownPhoto") MultipartFile idDownPhotofile,
                              @RequestParam("qualificationsPhoto") MultipartFile qualificationsPhotofile,Integer deptid) {
        Integer id =ShiroUtils.getPrincipal().getId();
        Userinfo userinfo =new Userinfo();
        userinfo.setId(id);
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setAge(age);

        //设置文件保存路径
        File path = new File(uploadFolder);
        if(!path.exists()){
            path.mkdirs();
        }
        MultipartFile photofiles = photofile;
        MultipartFile idUpPhotofiles = idUpPhotofile;
        MultipartFile idDownPhotoofiles = idDownPhotofile;
        MultipartFile qualificationsPhotofiles = qualificationsPhotofile;
        String photofilesuffix =photofile.getOriginalFilename().substring(photofile.getOriginalFilename().lastIndexOf(".")+1);
        String idUpPhotofilesuffix =idUpPhotofile.getOriginalFilename().substring(idUpPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        String idDownPhotofilesuffix =idDownPhotofile.getOriginalFilename().substring(idDownPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        String qualificationsPhotofilesuffix =qualificationsPhotofile.getOriginalFilename().substring(qualificationsPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        //保存文件
        try {
            //转存文件
            photofiles.transferTo(new File(path+"/"+id+"."+photofilesuffix));
            idUpPhotofiles.transferTo(new File(path+"/"+idNumber+"up"+"."+idUpPhotofilesuffix));
            idDownPhotoofiles.transferTo(new File(path+"/"+idNumber+"down"+"."+idDownPhotofilesuffix));
            qualificationsPhotofiles.transferTo(new File(path+"/"+id+"qualifications"+"."+qualificationsPhotofilesuffix));
            userinfo.setPhoto(id+"."+photofilesuffix);
            userinfo.setIdUpPhoto(idNumber+"up"+"."+idUpPhotofilesuffix);
            userinfo.setIdDownPhoto(idNumber+"down"+"."+idDownPhotofilesuffix);
            userinfo.setQualificationsPhoto(id+"qualifications"+"."+qualificationsPhotofilesuffix);
            userinfo.setCreateTime(new Date());
            Userinfo oldUserinfo =userinfoService.selectByPrimaryKey(id);
            if (null==oldUserinfo){
                userinfoService.insert(userinfo);
                Log log = ShiroUtils.setLog("新增个人信息");
                logService.insert(log);
            }else{
                userinfo.setModifyTime(new Date());
                int a=userinfoService.updateByPrimaryKey(userinfo);
                Log log = ShiroUtils.setLog("修改个人信息");
                logService.insert(log);
            }

        } catch (IOException e) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,UPDATE_FAULT);
        }
        log.info(userinfo.toString());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }*/
}
