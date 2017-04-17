package com.github.wenbo2018.jconf.web.service.impl;

import com.github.wenbo2018.jconf.web.RegistryManager;
import com.github.wenbo2018.jconf.web.dao.ConfigDao;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by shenwenbo on 2017/4/16.
 */
//@Service
public class ConfigServiceImpl implements ConfigService {

//    @Resource
    private ConfigDao configDao;

    @Override
    public void add(Config config) {
        configDao.add(config);
        RegistryManager.registerService(config.getKey(),config.getValue());
    }

}
