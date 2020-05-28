package com.tdkj.RNS.controller;

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Worktype;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.WorkTypeService;
import com.tdkj.RNS.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:53
 */
@Controller
public class WorkTypeController  implements RnsResultCode, RnsResultType {

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private LogService logService;

    @RequestMapping("/selectJobSetup")
    public RnsResponse selectJobSetup(Model model) {
    //前端需要传过来 分页数据 该功能sql 默认为0-10
        List<Worktype> worktypeList = workTypeService.select();
        Log log = ShiroUtils.setLog("查看工种");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,worktypeList);
    }
}
