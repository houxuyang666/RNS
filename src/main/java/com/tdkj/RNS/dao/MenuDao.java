package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Menu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-18 16:59:56
 */
public interface MenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Integer menuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Integer menuId);

    /**
     * @Author houxuyang
     * @Description //根据名称查询权限
     * @Date 17:07 2020/6/18
     * @Param [username]
     * @return java.util.List<com.tdkj.RNS.entity.Menu>
     **/
    List<Menu> findByUsernameGetPerms(String username);

    List<Menu> findByUsernameGetMenu(String username);
}