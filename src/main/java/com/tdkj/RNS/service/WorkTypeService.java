package com.tdkj.RNS.service;

import com.github.pagehelper.PageInfo;
import com.tdkj.RNS.entity.Worktype;

import java.util.List;


/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:56
 */
public interface WorkTypeService {

    int insertSelective(Worktype worktype);

    PageInfo<Worktype> selectByLimit(int pageNo, int pageSize);

    List<Worktype> selectLimit(int page, int rows);

    int count();

}
