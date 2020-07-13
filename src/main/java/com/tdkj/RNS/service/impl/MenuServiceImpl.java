package com.tdkj.RNS.service.impl;

import com.alibaba.fastjson.JSON;
import com.tdkj.RNS.entity.Menu;
import com.tdkj.RNS.dao.MenuDao;
import com.tdkj.RNS.entity.MenuTree;
import com.tdkj.RNS.service.MenuService;
import com.tdkj.RNS.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2020-06-18 16:59:57
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Integer menuId) {
        return this.menuDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Menu> queryAllByLimit(int offset, int limit) {
        return this.menuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
        this.menuDao.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu update(Menu menu) {
        this.menuDao.update(menu);
        return this.queryById(1);
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer menuId) {
        return this.menuDao.deleteById(menuId) > 0;
    }

    @Override
    public List<Menu> findByUsernameGetPerms(String username) {
        return this.menuDao.findByUsernameGetPerms(username);
    }


    @Override
    public MenuTree<Menu> findByUsernameGetMenu(String username) {
        List<Menu> menus = this.menuDao.findByUsernameGetMenu(username);
        List<MenuTree<Menu>> trees = this.convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public MenuTree<Menu> findMenus() {
        List<Menu> menus = this.menuDao.findMenus();
        System.out.println("1"+menus);
        List<MenuTree<Menu>> trees = this.convertMenus(menus);
        System.out.println("2"+trees);
        System.out.println(TreeUtil.buildMenuTree(trees));
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public int insertroleAndmenu(Integer roleId, Integer menuId) {
        return menuDao.insertroleAndmenu(roleId,menuId);
    }


    @Override
    public MenuTree<Menu> findMenusAllMenu() {
        List<Menu> menus = this.menuDao.queryAllMenu();
        List<MenuTree<Menu>> trees = this.convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public List<Menu> queryAllMenu() {
        return menuDao.queryAllMenu();
    }


    /**
 *
 * @author Chang
 * @description
 * @date 2020/7/1 8:29
 * @return
 */
    private List<MenuTree<Menu>> convertMenus(List<Menu> menus) {
        List<MenuTree<Menu>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<Menu> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getTitle());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getHref());
            tree.setData(menu);
            trees.add(tree);
        });


        return trees;
    }
}