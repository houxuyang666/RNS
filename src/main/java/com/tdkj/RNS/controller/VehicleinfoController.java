package com.tdkj.RNS.controller;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 9:14
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.VehicleinfoService;
import com.tdkj.RNS.utils.ShiroUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Vehicleinfo)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Slf4j
@Controller
@RequestMapping("vehicleinfo")
public class VehicleinfoController implements RnsResultType, RnsResultCode {
    /**
     * 服务对象
     */
    @Resource
    private VehicleinfoService vehicleinfoService;
    @Autowired
    private LogService logService;

    /**
     * 查询多条数据
     *
     * @param
     * @return 多条数据
     */
    @ResponseBody
    @RequestMapping("/vehicleinfolist")
    public RnsResponse vehicleinfolist() {
        PageHelper.startPage(1,10);
        List<Vehicleinfo> vehicleinfoList=vehicleinfoService.queryAllvehicleinfo();
        PageInfo<Vehicleinfo> pageInfo = new PageInfo<Vehicleinfo>(vehicleinfoList);
        //Object json = JSONArray.toJSON(pageInfo);
        Log log = ShiroUtils.setLog("查看车辆");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,FIND_SUCCESS,"page/vehicleinfolist",pageInfo);
    }
    /**
     * @Author houxuyang
     * @Description //新增车辆信息
     * @Date 17:28 2020/7/1
     * @Param [vehicleType, vehicleSeatsNumber, vehicleNumber, vehicleAffiliationCompany, vehicleAffiliationPersonal]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/addvehicleinfo")
    public RnsResponse addcompany(String vehicleType,Integer vehicleSeatsNumber,String vehicleNumber,Integer vehicleAffiliationCompany,Integer vehicleAffiliationPersonal) {
        Vehicleinfo vehicleinfo =vehicleinfoService.queryByvehicleNumber(vehicleNumber);
        if (vehicleinfo != null) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆已存在");
        }
        vehicleinfo.setVehicleType(vehicleType);
        vehicleinfo.setVehicleSeatsNumber(vehicleSeatsNumber);
        vehicleinfo.setVehicleStatus(1); //刚注册都为未用
        vehicleinfo.setVehicleAffiliationCompany(vehicleAffiliationCompany);
        vehicleinfo.setVehicleAffiliationPersonal(vehicleAffiliationPersonal);
        vehicleinfoService.insert(vehicleinfo);
        Log log = ShiroUtils.setLog("新增车辆");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,ADD_SUCCESS);
    }

    /**
     * @Author houxuyang
     * @Description //根据id修改车辆信息
     * @Date 17:28 2020/7/1
     * @Param [vehicleinfoId, vehicleType, vehicleSeatsNumber, vehicleNumber, vehicleAffiliationCompany, vehicleAffiliationPersonal]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/updatevehicleinfo")
    public RnsResponse updatevehicleinfo(Integer vehicleinfoId,String vehicleType,Integer vehicleSeatsNumber,String vehicleNumber,Integer vehicleAffiliationCompany,Integer vehicleAffiliationPersonal) {
        Vehicleinfo vehicleinfo =vehicleinfoService.queryById(vehicleinfoId);
        if (vehicleinfo != null) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆已存在");
        }else if("0".equals(vehicleinfo.getVehicleStatus())){
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆正在使用");
        }
        vehicleinfo.setVehicleType(vehicleType);
        vehicleinfo.setVehicleSeatsNumber(vehicleSeatsNumber);
        vehicleinfo.setVehicleStatus(1); //刚注册都为未用
        vehicleinfo.setVehicleAffiliationCompany(vehicleAffiliationCompany);
        vehicleinfo.setVehicleAffiliationPersonal(vehicleAffiliationPersonal);
        vehicleinfoService.insert(vehicleinfo);
        Log log = ShiroUtils.setLog("更新车辆信息："+vehicleNumber);
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }
    /**
     * @Author houxuyang
     * @Description //删除车辆
     * @Date 17:33 2020/7/1
     * @Param [vehicleinfoId]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/delvehicleinfo")
    public RnsResponse delvehicleinfo(Integer vehicleinfoId) {
        vehicleinfoService.deleteById(vehicleinfoId);
        Log log = ShiroUtils.setLog("删除车辆");
        logService.insert(log);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,REMOVE_SUCCESS);
    }
}