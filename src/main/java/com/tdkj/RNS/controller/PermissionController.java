package com.tdkj.RNS.controller;

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * 权限表(PermissionVO)表控制层
 *
 * @author makejava
 * @since 2020-06-04 14:32:48
 */
@Controller
@Slf4j
public class PermissionController implements RnsResultType, RnsResultCode {
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
  /*  @RequestMapping("selectOne")
    public Permission selectOne(Integer id) {
        return this.permissionService.queryById(id);
    }*/

    /**
     * @Author houxuyang
     * @Description //查询所有权限
     * @Date 9:16 2020/6/16
     * @Param []
     * @return java.lang.Boolean
     **/
    @RequestMapping("/selectpermission")
    public  RnsResponse selectpermission(Integer id) {
        //获取系统所有的权限
        List<Permission> permissionList =permissionService.select();
        log.info("获取系统所有的权限");
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,permissionList);
    }

    /**
     * @Author houxuyang
     * @Description //添加权限
     * @Date 8:50 2020/6/16
     * @Param [permissionName, permissionUrl]
     * @return com.tdkj.RNS.entity.Permission
     **/
    @RequestMapping("/insertpermission")
    public Permission insertpermission(String permissionName,String permissionUrl) {
        Permission permission =new Permission();
        permission.setPermissionName(permissionName);
        permission.setPermissionUrl(permissionUrl);
        return this.permissionService.insert(permission);
    }

    /**
     * @Author houxuyang
     * @Description //删除权限
     * @Date 9:15 2020/6/16
     * @Param [id]
     * @return java.lang.Boolean
     **/
    @RequestMapping("/deletepermission")
    public Boolean deletepermission(Integer id) {
        return this.permissionService.deleteById(id);
    }





}