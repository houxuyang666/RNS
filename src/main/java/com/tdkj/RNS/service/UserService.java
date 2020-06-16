package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.entity.User;

import java.util.List;

public interface UserService {




    User findByName(String username);

    int insert(User user);

    User queryById(Integer id);

    int update(User user);

    List<User> selectUser();
}
