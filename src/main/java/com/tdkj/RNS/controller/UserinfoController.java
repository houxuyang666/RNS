package com.tdkj.RNS.controller;

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Company;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.UserCompanyVO;
import com.tdkj.RNS.entity.Userinfo;
import com.tdkj.RNS.service.CompanyService;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.UserinfoService;
import com.tdkj.RNS.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("userinfo")
public class UserinfoController implements RnsResultType, RnsResultCode {

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private LogService logService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/gouserinfo")
    public String gouserinfo()  {
        log.info("gouserinfo");
            return "page/userinfo";
    }

    @ResponseBody
    @RequestMapping("/userinfo")
    public RnsResponse userinfo(String companyName,String vehicleOfficerName) {
        /*进入用户修改页面*/
        UserCompanyVO userCompanyVO;
        try {
            userCompanyVO =userinfoService.queryById(ShiroUtils.getPrincipal().getUserinfoId());
            List<Company> companylist =companyService.queryAllCompany(companyName,vehicleOfficerName);
            return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS,userCompanyVO,companylist);
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
        UserCompanyVO oldUserinfo =userinfoService.queryById(id);
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
}
