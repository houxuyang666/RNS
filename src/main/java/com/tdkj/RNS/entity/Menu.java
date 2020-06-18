package com.tdkj.RNS.entity;

import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-06-18 14:53:51
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -34473616404247869L;
    /**
    * 菜单ID
    */
    private Integer menuId;
    /**
    * 父类ID
    */
    private Integer parentId;
    /**
    * 菜单名称
    */
    private String title;
    /**
    * 请求路径 url
    */
    private String href;
    /**
    * 图标样式
    */
    private String icon;
    /**
    * 跳转方式
    */
    private String target;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}