package com.tdkj.RNS.service.lmpl;

import com.tdkj.RNS.entity.Permission;
import com.tdkj.RNS.entity.User;
import com.tdkj.RNS.mapper.UserMapper;
import com.tdkj.RNS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    private UserMapper userMapper;
    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }


    @Override
    public List<Permission> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int findByidUpdate(User user) {
        return userMapper.findByidUpdate(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
