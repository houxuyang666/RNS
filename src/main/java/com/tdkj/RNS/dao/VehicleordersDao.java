package com.tdkj.RNS.dao;


import com.tdkj.RNS.entity.Vehicleorders;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Vehicleorders)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-30 09:22:22
 */
public interface VehicleordersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Vehicleorders queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vehicleorders> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vehicleorders 实例对象
     * @return 对象列表
     */
    List<Vehicleorders> queryAll(Vehicleorders vehicleorders);

    /**
     * 新增数据
     *
     * @param vehicleorders 实例对象
     * @return 影响行数
     */
    int insert(Vehicleorders vehicleorders);

    /**
     * 修改数据
     *
     * @param vehicleorders 实例对象
     * @return 影响行数
     */
    int update(Vehicleorders vehicleorders);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

}