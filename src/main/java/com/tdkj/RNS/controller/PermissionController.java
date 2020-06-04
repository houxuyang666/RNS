package com.tdkj.RNS.controller;

import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 权限表(Permission)表控制层
 *
 * @author makejava
 * @since 2020-06-04 14:32:48
 */
@Controller
@RequestMapping("Permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(Integer id) {
        return this.permissionService.queryById(id);
    }

}