package com.tdkj.RNS.controller;

import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Worktype;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.WorkTypeService;
import com.tdkj.RNS.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:53
 */
@Slf4j
@Controller
@RequestMapping("WorkType")
public class WorkTypeController  implements RnsResultCode, RnsResultType {

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private LogService logService;


    @RequestMapping("/JobSetup")
    public String JobSetup() {
        log.info("------------跳转页面JobSetup");
        return "worktype/JobSetup";
    }

    @ResponseBody
    @RequestMapping("/selectJobSetup")
    public RnsResponse selectJobSetup(int page,int rows) {
        log.info("------------selectJobSetup");
        PageInfo<Worktype> pagelist = workTypeService.selectByLimit(page,rows);
        Log log = ShiroUtils.setLog("查看工种");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,pagelist.getList(),pagelist.getTotal());
    }

    @ResponseBody
    @RequestMapping("/addJobSetup")
    public RnsResponse addJobSetup(String worktypeName) {
        log.info("------------addJobSetup");
        Worktype worktype =new Worktype();
        worktype.setWorktypeName(worktypeName);
        worktype.setCreateTime(new Date());
        workTypeService.insertSelective(worktype);
        Log log = ShiroUtils.setLog("添加工种");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,ADD_SUCCESS);
    }
}
