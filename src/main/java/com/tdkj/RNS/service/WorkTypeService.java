package com.tdkj.RNS.service;

import com.tdkj.RNS.entity.Worktype;

import java.util.List;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:56
 */
public interface WorkTypeService {

    List<Worktype> select();

    int insertSelective(Worktype worktype);

    List<Worktype> selectByLimit(int page, int rows);
}
