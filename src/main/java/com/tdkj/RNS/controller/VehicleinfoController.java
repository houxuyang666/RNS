package com.tdkj.RNS.controller;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 9:14
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResponseList;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.*;
import com.tdkj.RNS.service.LogService;
import com.tdkj.RNS.service.UserinfoService;
import com.tdkj.RNS.service.VehicleinfoService;
import com.tdkj.RNS.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
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
    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping("/govehicle")
    public String govehicle() {
        log.info("vehiclelist");
        return "page/vehiclelist";
    }

    /**
     * 查询多条数据
     *
     * @param
     * @return 多条数据
     */
    @ResponseBody
    @RequestMapping("/selectvehicleinfo")
    public RnsResponseList vehicleinfolist(Integer page,Integer limit,String vehicleNumber,String companyName) {
        PageHelper.startPage(page,limit,true);
        List<VehicleinfoVO> vehicleinfoVOList=vehicleinfoService.queryAllvehicleinfo(vehicleNumber,companyName);
        PageInfo<VehicleinfoVO> pageInfo=new PageInfo<>(vehicleinfoVOList);
        List<UserinfoVO> userinfoVOList=userinfoService.Alldriver();
        Log log = ShiroUtils.setLog("查看车辆");
        logService.insert(log);
        return RnsResponseList.setResult(0,FIND_SUCCESS,pageInfo,userinfoVOList);
    }
    /**
     * @Author houxuyang
      *
     * @Description //新增车辆信息
     * @Date 17:28 2020/7/1
     * @Param [vehicleType, vehicleSeatsNumber, vehicleNumber, vehicleAffiliationCompany, vehicleAffiliationPersonal]
     * @return com.tdkj.RNS.common.RnsResponse
     **/
    @ResponseBody
    @RequestMapping("/addvehicleinfo")
    public RnsResponse addcompany(String vehicleType,Integer vehicleSeatsNumber,String vehicleNumber,Integer vehicleAffiliationCompany,Integer vehicleAffiliationPersonal) {
        Vehicleinfo vehicleinfo1 =vehicleinfoService.queryByvehicleNumber(vehicleNumber);
        if (vehicleinfo1 != null) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆已存在");
        }
        Vehicleinfo vehicleinfo=new Vehicleinfo();
        vehicleinfo.setVehicleType(vehicleType);
        vehicleinfo.setVehicleSeatsNumber(vehicleSeatsNumber);
        vehicleinfo.setVehicleNumber(vehicleNumber);
        vehicleinfo.setVehicleStatus(1); //刚注册都为未用
        vehicleinfo.setVehicleAffiliationCompany(vehicleAffiliationCompany);
        vehicleinfo.setVehicleAffiliationPersonal(vehicleAffiliationPersonal);
        vehicleinfo.setCreateTime(new Date());
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
//        if (vehicleinfo != null) {
//            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆已存在");
//        }else
        System.out.println(vehicleinfo.getVehicleStatus()+"---1111111111111111111111111111111111");
        if(0==vehicleinfo.getVehicleStatus()){
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"车辆正在使用");
        }
        vehicleinfo.setVehicleType(vehicleType);
        vehicleinfo.setVehicleSeatsNumber(vehicleSeatsNumber);
        vehicleinfo.setVehicleNumber(vehicleNumber);
        vehicleinfo.setVehicleStatus(1); //刚注册都为未用
        vehicleinfo.setVehicleAffiliationCompany(vehicleAffiliationCompany);
        vehicleinfo.setVehicleAffiliationPersonal(vehicleAffiliationPersonal);
        vehicleinfo.setVehicleNumber(vehicleNumber);
        vehicleinfo.setModifyTime(new Date());
        vehicleinfoService.update(vehicleinfo);
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