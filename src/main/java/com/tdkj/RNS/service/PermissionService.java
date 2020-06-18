package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限表(PermissionVO)表服务接口
 *
 * @author makejava
 * @since 2020-06-04 14:32:47
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    Permission queryById(Integer pid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pid);
    /**
     * @Author houxuyang
     * @Description //根据姓名获取权限
     * @Date 15:25 2020/6/4
     * @Param [username]
     * @return java.util.List<com.tdkj.RNS.entity.PermissionVO>
     **/
    List<Permission> findByUsernameGetPermission(String username);

    /**
     * @Author houxuyang
     * @Description //查询所有权限
     * @Date 9:26 2020/6/16
     * @Param []
     * @return java.lang.Boolean
     **/
    List<Permission> select();


}