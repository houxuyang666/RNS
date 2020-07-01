package com.tdkj.RNS.controller;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 9:15
 */

import com.tdkj.RNS.entity.Vehicleorders;
import com.tdkj.RNS.service.VehicleordersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * (Vehicleorders)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Slf4j
@Controller
@RequestMapping("vehicleorders")
public class VehicleordersController {
    /**
     * 服务对象
     */
    @Resource
    private VehicleordersService vehicleordersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Vehicleorders selectOne(String id) {
        return this.vehicleordersService.queryById(id);
    }

}
