package com.tdkj.RNS.service.lmpl;

import com.tdkj.RNS.entity.Worktype;
import com.tdkj.RNS.mapper.WorktypeMapper;
import com.tdkj.RNS.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/27 16:57
 */
@Service
public class WorkTypeServiceImpl implements WorkTypeService {


    @Autowired
    private WorktypeMapper WorktypeMapper;
    @Override
    public List<Worktype> select() {
        return WorktypeMapper.select();
    }
}
