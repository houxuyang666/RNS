package com.tdkj.RNS.controller;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/6/30 9:15
 */

import com.tdkj.RNS.common.RnsResponse;
import com.tdkj.RNS.common.RnsResultCode;
import com.tdkj.RNS.common.RnsResultType;
import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.entity.Vehicleorders;
import com.tdkj.RNS.service.VehicleinfoService;
import com.tdkj.RNS.service.VehicleordersService;
import com.tdkj.RNS.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (Vehicleorders)表控制层
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Slf4j
@Controller
@PropertySource("classpath:application-dev.properties")//此处路径需要按需修改
@RequestMapping("vehicleorders")
public class VehicleordersController implements RnsResultCode, RnsResultType {
    /**
     * 服务对象
     */
    @Resource
    private VehicleordersService vehicleordersService;
    @Resource
    private VehicleinfoService vehicleinfoService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.uploadtempFolder}")
    private String uploadtempFolder;

    @Transactional
    @ResponseBody
    @RequestMapping("/addvehicleorders")
    public RnsResponse addvehicleorders(Integer vehicleId,String vehicleDriver, String beganAddress, String endAddress, String orderDesc) {
        Vehicleorders vehicleorders =new Vehicleorders();
        //生成4为随机数 第二个参数为是否要字母 第三个参数是否要数字
        String code= RandomStringUtils.random(4, false, true);
        vehicleorders.setOrderId(DateUtil.getDateFormat(new Date(),"yyyyMMddHHmmss")+code);

        Vehicleinfo vehicleinfo=vehicleinfoService.queryById(vehicleId);
        if (vehicleinfo.getVehicleStatus()==0||vehicleinfo.getVehicleStatus()==2){
            return RnsResponse.setResult(HTTP_RNS_CODE_500,"该车辆正在使用，请选择未使用车辆");
        }
        //如果车辆被申请使用则修改该车辆的状态
        vehicleinfo.setVehicleStatus(2);

        vehicleorders.setVehicleId(vehicleId);
        vehicleorders.setVehicleDriver(vehicleDriver);
        vehicleorders.setBeganAddress(beganAddress);
        vehicleorders.setEndAddress(endAddress);
        //状态改为0 进行中
        vehicleorders.setOrderStatus(0);
        vehicleorders.setOrderDesc(orderDesc);

        //订单申请时将车辆状态改为已申请
        vehicleinfoService.update(vehicleinfo);
        vehicleordersService.insert(vehicleorders);
        return RnsResponse.setResult(HTTP_RNS_CODE_200,"订单已生成");
    }

    /*@ResponseBody
    @RequestMapping("/setUserinfo")
    public RnsResponse setUserinfo(String name,Integer sex,Integer age,String phone,
                              String idNumber,String address,
                              @RequestParam("photo") MultipartFile photofile,
                              @RequestParam("idUpPhoto") MultipartFile idUpPhotofile,
                              @RequestParam("idDownPhoto") MultipartFile idDownPhotofile,
                              @RequestParam("qualificationsPhoto") MultipartFile qualificationsPhotofile,Integer deptid) {
        Integer id =ShiroUtils.getPrincipal().getId();
        Userinfo userinfo =new Userinfo();
        userinfo.setId(id);
        userinfo.setName(name);
        userinfo.setSex(sex);
        userinfo.setAge(age);

        //设置文件保存路径
        File path = new File(uploadFolder);
        if(!path.exists()){
            path.mkdirs();
        }
        MultipartFile photofiles = photofile;
        MultipartFile idUpPhotofiles = idUpPhotofile;
        MultipartFile idDownPhotoofiles = idDownPhotofile;
        MultipartFile qualificationsPhotofiles = qualificationsPhotofile;
        String photofilesuffix =photofile.getOriginalFilename().substring(photofile.getOriginalFilename().lastIndexOf(".")+1);
        String idUpPhotofilesuffix =idUpPhotofile.getOriginalFilename().substring(idUpPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        String idDownPhotofilesuffix =idDownPhotofile.getOriginalFilename().substring(idDownPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        String qualificationsPhotofilesuffix =qualificationsPhotofile.getOriginalFilename().substring(qualificationsPhotofile.getOriginalFilename().lastIndexOf(".")+1);
        //保存文件
        try {
            //转存文件
            photofiles.transferTo(new File(path+"/"+id+"."+photofilesuffix));
            idUpPhotofiles.transferTo(new File(path+"/"+idNumber+"up"+"."+idUpPhotofilesuffix));
            idDownPhotoofiles.transferTo(new File(path+"/"+idNumber+"down"+"."+idDownPhotofilesuffix));
            qualificationsPhotofiles.transferTo(new File(path+"/"+id+"qualifications"+"."+qualificationsPhotofilesuffix));
            userinfo.setPhoto(id+"."+photofilesuffix);
            userinfo.setIdUpPhoto(idNumber+"up"+"."+idUpPhotofilesuffix);
            userinfo.setIdDownPhoto(idNumber+"down"+"."+idDownPhotofilesuffix);
            userinfo.setQualificationsPhoto(id+"qualifications"+"."+qualificationsPhotofilesuffix);
            userinfo.setCreateTime(new Date());
            Userinfo oldUserinfo =userinfoService.selectByPrimaryKey(id);
            if (null==oldUserinfo){
                userinfoService.insert(userinfo);
                Log log = ShiroUtils.setLog("新增个人信息");
                logService.insert(log);
            }else{
                userinfo.setModifyTime(new Date());
                int a=userinfoService.updateByPrimaryKey(userinfo);
                Log log = ShiroUtils.setLog("修改个人信息");
                logService.insert(log);
            }

        } catch (IOException e) {
            return RnsResponse.setResult(HTTP_RNS_CODE_500,UPDATE_FAULT);
        }
        log.info(userinfo.toString());
        return RnsResponse.setResult(HTTP_RNS_CODE_200,UPDATE_SUCCESS);
    }*/
}
