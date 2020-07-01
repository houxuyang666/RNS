package com.tdkj.RNS.controller;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 9:14
 */

import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.service.VehicleinfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Vehicleinfo)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Slf4j
@Controller
@RequestMapping("vehicleinfo")
public class VehicleinfoController {
    /**
     * 服务对象
     */
    @Resource
    private VehicleinfoService vehicleinfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Vehicleinfo selectOne(Integer id) {
        return this.vehicleinfoService.queryById(id);
    }

}