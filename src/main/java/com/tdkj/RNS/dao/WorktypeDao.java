package com.tdkj.RNS.dao;

import com.tdkj.RNS.entity.Worktype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorktypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Worktype record);

    int insertSelective(Worktype record);

    Worktype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Worktype record);

    int updateByPrimaryKey(Worktype record);

    List<Worktype> selectByLimit();

    //List<Worktype> selectLimit(@Param("page")int page,@Param("rows")int rows);

}