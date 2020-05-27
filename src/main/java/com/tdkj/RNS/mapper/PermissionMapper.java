package com.tdkj.RNS.mapper;

import com.tdkj.RNS.entity.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer pid);


}