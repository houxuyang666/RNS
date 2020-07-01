package com.tdkj.RNS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.entity.Company;
import com.tdkj.RNS.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Controller
@RequestMapping("company")
public class CompanyController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    /**
     * 查询多条数据
     *
     * @param
     * @return 多条数据
     */
    @ResponseBody
    @GetMapping("selectcompany")
    public PageInfo selectcompany() {
        PageHelper.startPage(1,10);
        List<Company> companyList=companyService.queryAllCompany();
        PageInfo<Company> pageInfo = new PageInfo<Company>(companyList);
        return pageInfo;
    }


}