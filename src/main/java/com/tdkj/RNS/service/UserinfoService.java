package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Userinfo;
import com.tdkj.RNS.entity.Userinfo;

import java.util.List;

/**
 * 用户表(Userinfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-22 15:04:43
 */
public interface UserinfoService {

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Userinfo> queryAllByLimit(int offset, int limit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Userinfo selectByPrimaryKey(Integer id);
    /**
     * 修改数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(Userinfo userinfo);

    void insert(Userinfo userinfo);
}