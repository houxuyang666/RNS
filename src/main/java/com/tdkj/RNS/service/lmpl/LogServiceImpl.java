package com.tdkj.RNS.service.lmpl;

import com.tdkj.RNS.entity.Log;
import com.tdkj.RNS.mapper.LogMapper;
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
    private LogMapper logMapper;

    @Override
    public int insert(Log log) {
        return logMapper.insert(log);
    }
}
