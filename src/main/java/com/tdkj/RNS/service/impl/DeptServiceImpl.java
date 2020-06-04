package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.entity.Dept;
import com.tdkj.RNS.dao.DeptDao;
import com.tdkj.RNS.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:57
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public int insertSelective(Dept dept) {
        return deptDao.insertSelective(dept);
    }
}
