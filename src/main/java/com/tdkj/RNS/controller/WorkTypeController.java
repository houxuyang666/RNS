package com.tdkj.RNS.controller;

import com.tdkj.RNS.entity.Worktype;
import com.tdkj.RNS.service.WorkTypeService;
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
public class WorkTypeController {

    @Autowired
    private WorkTypeService workTypeService;


    @RequestMapping("/JobSetup")
    public String selectJobSetup(Model model) {
    //前端需要传过来 分页数据 该功能sql 默认为0-10

        List<Worktype> worktypeList = workTypeService.select();
        for (Worktype worktype : worktypeList) {
            System.out.println("WorktypeName"+worktype.getWorktypeName());
        }
        model.addAttribute("worktypeList",worktypeList);
        System.out.println("JobSetup");
        return "/worktype/JobSetup";
    }
}
