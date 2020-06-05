package com.sh.service.impl;

import com.sh.entity.Logs;
import com.sh.mapper.LogsMapper;
import com.sh.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Sunhu At 2020/6/3 17:30
 *
 * @author Sun
 */
@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    LogsMapper logsMapper;

    @Override
    public void saveLog(Logs logs) {
        logsMapper.insert(logs);
    }
}
