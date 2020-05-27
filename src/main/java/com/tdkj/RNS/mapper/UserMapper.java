package com.tdkj.RNS.mapper;

import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);


    User findByName(String username);

    List<Permission> findByUsername(String username);

    int findByidUpdate(User user);
}