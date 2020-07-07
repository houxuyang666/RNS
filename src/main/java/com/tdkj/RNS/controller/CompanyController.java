package com.tdkj.RNS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResponseList;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Company;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.service.CompanyService;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Slf4j
@Controller
@RequestMapping("company")
public class CompanyController implements RnsResultType, RnsResultCode {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;
    @Autowired
    private LogService logService;


    @RequestMapping("/gocompany")
    public String gomenu() {
        log.info("company");
        return "page/companylist";
    }

    /**
     * 查询多条数据
     *
     * @param
     * @return 多条数据
     */
    @ResponseBody
    @RequestMapping("/selectcompany")
    public RnsResponseList companylist(Integer page,Integer limit) {
        PageHelper.startPage(page,limit,true);
    public RnsResponseList companylist(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<Company> companyList=companyService.queryAllCompany();
        PageInfo pageInfo=new PageInfo(companyList);
        Log log = ShiroUtils.setLog("查看公司");
        logService.insert(log);
        return RnsResponseList.setResult(0,FIND_SUCCESS,pageInfo);
    }

    /**
     * @Author houxuyang
     * @Description //添加公司
     * @Date 16:41 2020/7/1
     * @Param [companyName, companyAddress, vehicleOfficerName, vehicleOfficerTel]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/addcompany")
    public RnsResponse addcompany(String companyName,String companyAddress,String vehicleOfficerName,String vehicleOfficerTel) {
        Company company = companyService.queryByName(companyName);
        if (company != null) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"公司已存在");
        }
        company.setCompanyName(companyName);
        company.setCompanyAddress(companyAddress);
        company.setVehicleOfficerName(vehicleOfficerName);
        company.setVehicleOfficerTel(vehicleOfficerTel);
        company.setCreateTime(new Date());
        companyService.insert(company);
        Log log = ShiroUtils.setLog("添加公司"+companyName);
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,ADD_SUCCESS);
    }

    /**
     * @Author houxuyang
     * @Description //根据id修改公司信息
     * @Date 16:42 2020/7/1
     * @Param [companyId, companyName, companyAddress, vehicleOfficerName, vehicleOfficerTel]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/updatecompany")
    public RnsResponse updatecompany(Integer companyId,String companyName,String companyAddress,String vehicleOfficerName,String vehicleOfficerTel) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyName(companyName);
        company.setCompanyAddress(companyAddress);
        company.setVehicleOfficerName(vehicleOfficerName);
        company.setVehicleOfficerTel(vehicleOfficerTel);
        company.setModifyTime(new Date());
        companyService.update(company);
        Log log = ShiroUtils.setLog("修改公司"+companyName);
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/delcompany")
    public RnsResponse delcompany(Integer companyId) {
        companyService.deleteById(companyId);
        Log log = ShiroUtils.setLog("删除公司");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,REMOVE_SUCCESS);
    }

}