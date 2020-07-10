package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Vehicleorders;
import com.tdkj.RNS.entity.VehicleordersVO;

import java.util.List;

/**
 * (Vehicleorders)表服务接口
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
public interface VehicleordersService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Vehicleorders queryById(String orderId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vehicleorders> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vehicleorders 实例对象
     * @return 实例对象
     */
    Vehicleorders insert(Vehicleorders vehicleorders);

    /**
     * 修改数据
     *
     * @param vehicleorders 实例对象
     * @return 实例对象
     */
    Vehicleorders update(Vehicleorders vehicleorders);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);
    /**
     * @Author houxuyang
     * @Description //查询车辆订单
     * @Date 15:51 2020/7/8
     * @Param []
     * @return java.util.List<com.tdkj.RNS.entity.VehicleordersVO>
     **/
    List<VehicleordersVO> selecvehicleorders(Integer userId);

    List<VehicleordersVO> selecALLvehicleorders();
}