package com.tdkj.RNS.service.lmpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private WorktypeMapper worktypeMapper;


    @Override
    public int insertSelective(Worktype worktype) {
        return worktypeMapper.insertSelective(worktype);
    }

    @Override
    public PageInfo<Worktype> selectByLimit(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Worktype> list = worktypeMapper.selectByLimit();
        //用PageInfo对结果进行包装
        PageInfo<Worktype> page = new PageInfo<Worktype>(list);
        return page;
    }


}
