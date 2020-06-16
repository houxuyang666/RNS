package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.dao.PermissionDao;
import com.tdkj.RNS.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(PermissionVO)表服务实现类
 *
 * @author makejava
 * @since 2020-06-04 14:32:47
 */
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer pid) {
        return this.permissionDao.queryById(pid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Permission> queryAllByLimit(int offset, int limit) {
        return this.permissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getPid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pid) {
        return this.permissionDao.deleteById(pid) > 0;
    }

    /**
     * @Author houxuyang
     * @Description //根据用户名称查询当前用户权限
     * @Date 9:27 2020/6/16
     * @Param [username]
     * @return java.util.List<com.tdkj.RNS.entity.PermissionVO>
     **/
    @Override
    public List<Permission> findByUsernameGetPermission(String username) {
        return permissionDao.findByUsernameGetPermission(username);
    }

    /**
     * @Author houxuyang
     * @Description //查询所有权限
     * @Date 9:27 2020/6/16
     * @Param []
     * @return java.lang.Boolean
     **/
    @Override
    public List<Permission> select() {
        return permissionDao.select();
    }
}