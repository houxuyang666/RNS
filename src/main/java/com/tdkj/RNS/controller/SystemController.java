package com.tdkj.RNS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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