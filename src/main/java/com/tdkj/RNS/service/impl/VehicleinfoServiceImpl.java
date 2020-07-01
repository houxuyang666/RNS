package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.dao.VehicleinfoDao;
import com.tdkj.RNS.entity.Vehicleinfo;
import com.tdkj.RNS.service.VehicleinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Vehicleinfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-30 09:11:13
 */
@Service("vehicleinfoService")
public class VehicleinfoServiceImpl implements VehicleinfoService {
    @Resource
    private VehicleinfoDao vehicleinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param vehicleinfoId 主键
     * @return 实例对象
     */
    @Override
    public Vehicleinfo queryById(Integer vehicleinfoId) {
        return this.vehicleinfoDao.queryById(vehicleinfoId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Vehicleinfo> queryAllByLimit(int offset, int limit) {
        return this.vehicleinfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vehicleinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Vehicleinfo insert(Vehicleinfo vehicleinfo) {
        this.vehicleinfoDao.insert(vehicleinfo);
        return vehicleinfo;
    }

    /**
     * 修改数据
     *
     * @param vehicleinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Vehicleinfo update(Vehicleinfo vehicleinfo) {
        this.vehicleinfoDao.update(vehicleinfo);
        return this.queryById(vehicleinfo.getVehicleinfoId());
    }

    /**
     * 通过主键删除数据
     *
     * @param vehicleinfoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer vehicleinfoId) {
        return this.vehicleinfoDao.deleteById(vehicleinfoId) > 0;
    }
}