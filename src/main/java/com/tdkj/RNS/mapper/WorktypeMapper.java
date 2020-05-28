package com.tdkj.RNS.mapper;

import com.tdkj.RNS.entity.Worktype;

import java.util.List;

public interface WorktypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Worktype record);

    int insertSelective(Worktype record);

    Worktype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Worktype record);

    int updateByPrimaryKey(Worktype record);

    List<Worktype> select();

    List<Worktype> selectByLimit(int page, int rows);
}