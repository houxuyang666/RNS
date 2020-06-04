package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Log;

public interface LogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);


}