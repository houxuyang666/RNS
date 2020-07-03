package com.tdkj.RNS.controller;

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2020-06-18 16:59:57
 */
@Slf4j
@Controller
@RequestMapping("menu")
public class MenuController  implements RnsResultCode, RnsResultType {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;


    @RequestMapping("/gomenu")
    public String gomenu() {
        log.info("menu");
        return "page/menu";
    }

    @ResponseBody
    @RequestMapping("/addmenu")
    public RnsResponse addmenu(Integer parentId,String title, String href, String perms,String icon,String target) {
        Menu menu =new Menu();
        menu.setParentId(parentId);
        menu.setTitle(title);
        menu.setHref(href);
        menu.setPerms(perms);
        menu.setIcon(icon);
        menu.setTarget(target);
        menu.setCreateTime(new Date());
        menuService.insert(menu);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,ADD_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/updatemenu")
    public RnsResponse updatemenu(Integer menuId,Integer parentId,String title, String href, String perms,String icon,String target) {
        Menu menu =new Menu();
        menu.setMenuId(menuId);
        menu.setParentId(parentId);
        menu.setTitle(title);
        menu.setHref(href);
        menu.setPerms(perms);
        menu.setIcon(icon);
        menu.setTarget(target);
        menu.setModifyTime(new Date());
        menuService.update(menu);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/delemenu")
    public RnsResponse delemenu(Integer menuId) {
        menuService.deleteById(menuId);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,REMOVE_SUCCESS);
    }
}