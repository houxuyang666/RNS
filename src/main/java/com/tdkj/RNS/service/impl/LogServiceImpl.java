package com.tdkj.RNS.service.impl;

import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.dao.LogDao;
import com.tdkj.RNS.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hxy
 * @version 1.0
 * @date 2020/5/26 16:27
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired(required=false)
    private LogDao logDao;

    @Override
    public int insert(Log log) {
        return logDao.insert(log);
    }
}
