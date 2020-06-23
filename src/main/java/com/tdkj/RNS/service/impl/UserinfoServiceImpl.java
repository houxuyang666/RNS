package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.dao.UserinfoDao;
import com.tdkj.RNS.entity.Userinfo;
import com.tdkj.RNS.service.UserinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(UUserinfo)表服务实现类
 *
 * @author makejava
 * @since 2020-06-22 15:04:43
 */
@Service("UserinfoService")
public class UserinfoServiceImpl implements UserinfoService {
    @Resource
    private UserinfoDao UserinfoDao;

    @Override
    public List<Userinfo> queryAllByLimit(int offset, int limit) {
        return null;
    }



    /**
     * 新增数据
     *
     * @param Userinfo 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(Userinfo Userinfo){
        UserinfoDao.insert(Userinfo);
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Userinfo selectByPrimaryKey(Integer id) {
        return UserinfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Userinfo userinfo) {
        return UserinfoDao.updateByPrimaryKey(userinfo);
    }

}