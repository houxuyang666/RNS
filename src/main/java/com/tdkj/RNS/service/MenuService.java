package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.entity.MenuTree;

import java.util.List;

/**
 * (Menu)表服务接口
 *
 * @author makejava
 * @since 2020-06-18 16:59:56
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Integer menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer menuId);

    /**
     * @Author houxuyang
     * @Description //通过名称查询用户权限
     * @Date 17:06 2020/6/18
     * @Param [username]
     * @return java.util.List<com.tdkj.RNS.entity.Menu>
     **/
    List<Menu> findByUsernameGetPerms(String username);
    /**
     * @Author houxuyang
     * @Description //通过名称查询菜单
     * @Date 17:16 2020/6/18
     * @Param [username]
     * @return java.util.List<com.tdkj.RNS.entity.Menu>
     **/
    MenuTree<Menu> findByUsernameGetMenu(String username);

    MenuTree<Menu> findMenus();

    int insertroleAndmenu(Integer roleId, Integer menuId);

    List<Menu> queryAllMenu();

    MenuTree<Menu> findMenusAllMenu();
}