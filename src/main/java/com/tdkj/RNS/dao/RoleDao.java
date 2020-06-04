package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Role;

public interface RoleDao {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);


}