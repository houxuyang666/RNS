package com.tdkj.RNS.controller;

import com.tdkj.RNS.entity.Dept;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Worktype;
import com.tdkj.RNS.service.DeptService;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:53
 */
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private LogService logService;

    @RequestMapping("/adddept")
    public String adddept(String deptname,String deptdesc) {
        Dept dept =new Dept();
        dept.setDeptName(deptname);
        dept.setDeptDesc(deptdesc);
        dept.setCreateTime(new Date());
        deptService.insertSelective(dept);
        Log log = ShiroUtils.setLog("添加部门");
        logService.insert(log);
        return "success";
    }
}
