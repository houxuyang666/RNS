package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.entity.UserinfoVO;

import java.util.List;

public interface UserService {




    User findByName(String username);

    int insert(User user);

    User queryById(Integer id);

    int update(User user);

    List<User> selectUser();

    List<UserinfoVO> selectUserUserinfo();

    List<UserinfoVO> selectUserByCondition(UserinfoVO userinfoVO);
}
