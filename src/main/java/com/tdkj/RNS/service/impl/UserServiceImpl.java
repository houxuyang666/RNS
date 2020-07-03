package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.dao.UserDao;
import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.entity.UserinfoVO;
import com.tdkj.RNS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }


    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    @Override
    public List<UserinfoVO> selectUserUserinfo() {
        return userDao.selectUserUserinfo();
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
