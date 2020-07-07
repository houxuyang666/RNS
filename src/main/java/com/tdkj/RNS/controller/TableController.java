package com.tdkj.RNS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/7/7 9:41
 */
@Slf4j
@Controller
@RequestMapping("table")
public class TableController {

    @RequestMapping("/goaddcompany")
    public String goaddcompany()  {
        log.info("goaddcompany");
        return "page/table/addcompany";
    }

    @RequestMapping("/goupdatecompany")
    public String goupdatecompany()  {
        log.info("goupdatecompany");
        return "page/table/updatecompany";
    }


    @RequestMapping("/goaddmenu")
    public String goaddmenu()  {
        log.info("goaddmenu");
        return "page/table/addmenu";
    }

    @RequestMapping("/goupdatemenu")
    public String goupdatemenu()  {
        log.info("goupdatemenu");
        return "page/table/updatemenu";
    }


    @RequestMapping("/goaddvehicleinfo")
    public String goaddvehicleinfo()  {
        log.info("goaddvehicleinfo");
        return "page/table/addvehicleinfo";
    }

    @RequestMapping("/goupdatevehicleinfo")
    public String goupdatevehicleinfo()  {
        log.info("goupdatevehicleinfo");
        return "page/table/updatevehicleinfo";
    }
}
