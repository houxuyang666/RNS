package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.entity.VehicleinfoVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Vehicleinfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-30 09:22:20
 */
public interface VehicleinfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param vehicleinfoId 主键
     * @return 实例对象
     */
    Vehicleinfo queryById(Integer vehicleinfoId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vehicleinfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vehicleinfo 实例对象
     * @return 对象列表
     */
    List<Vehicleinfo> queryAll(Vehicleinfo vehicleinfo);

    /**
     * 新增数据
     *
     * @param vehicleinfo 实例对象
     * @return 影响行数
     */
    int insert(Vehicleinfo vehicleinfo);

    /**
     * 修改数据
     *
     * @param vehicleinfo 实例对象
     * @return 影响行数
     */
    int update(Vehicleinfo vehicleinfo);

    /**
     * 通过主键删除数据
     *
     * @param vehicleinfoId 主键
     * @return 影响行数
     */
    int deleteById(Integer vehicleinfoId);

    List<VehicleinfoVO> queryAllvehicleinfo(@Param("vehicleNumber") String vehicleNumber,@Param("companyName") String companyName);

    Vehicleinfo queryByvehicleNumber(String vehicleNumber);
}