package com.tdkj.RNS.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-06-18 16:59:54
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -28891821337214503L;
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
    * 权限
    */
    private String perms;
    /**
    * 图标样式
    */
    private String icon;
    /**
    * 跳转方式
    */
    private String target;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date modifyTime;

    // 子菜单  buyong le
    //private List<Menu> childMenus;



}