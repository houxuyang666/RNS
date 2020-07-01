package com.tdkj.RNS.controller;

import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2020-06-18 16:59:57
 */
@Controller
@RequestMapping("system")
public class SystemController {

    @RequestMapping("/gosetting")
    public String gomenu() {
        return "page/setting";
    }

}