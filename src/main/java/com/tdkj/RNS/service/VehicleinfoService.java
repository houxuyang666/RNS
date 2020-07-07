package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.entity.VehicleinfoVO;

import java.util.List;

/**
 * (Vehicleinfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public interface VehicleinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param vehicleinfoId 主键
     * @return 实例对象
     */
    Vehicleinfo queryById(Integer vehicleinfoId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vehicleinfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vehicleinfo 实例对象
     * @return 实例对象
     */
    Vehicleinfo insert(Vehicleinfo vehicleinfo);

    /**
     * 修改数据
     *
     * @param vehicleinfo 实例对象
     * @return 实例对象
     */
    Vehicleinfo update(Vehicleinfo vehicleinfo);

    /**
     * 通过主键删除数据
     *
     * @param vehicleinfoId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer vehicleinfoId);

    /**
     * @Author houxuyang
     * @Description //查询所有车辆信息
     * @Date 17:03 2020/7/1
     * @Param []
     * @return java.util.List<com.tdkj.RNS.entity.Vehicleinfo>
     **/
    List<VehicleinfoVO> queryAllvehicleinfo(String vehicleNumber,String companyName);
    /**
     * @Author houxuyang
     * @Description //通过车牌号查询
     * @Date 17:03 2020/7/1
     * @Param []
     * @return java.util.List<com.tdkj.RNS.entity.Vehicleinfo>
     **/
    Vehicleinfo queryByvehicleNumber(String vehicleNumber);
}