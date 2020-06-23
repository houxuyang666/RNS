package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Userinfo;

public interface UserinfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Userinfo record);

}