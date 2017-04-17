package com.github.wenbo2018.jconf.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.web.RegistryManager;
import com.github.wenbo2018.jconf.web.dao.ConfigDao;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private static Logger logger= LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Resource
    private ConfigDao configDao;

    @Override
    public void add(Config config) {
        configDao.add(config);
        RegistryManager.registerService(config.getKey(),config.getValue());
    }

    @Override
    public PageInfo<Config> queryByPage(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null?0:pageIndex;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageIndex, pageSize);
        List<Config> list = configDao.list();
        //用PageInfo对结果进行包装
        PageInfo<Config> page = new PageInfo<Config>(list);
        return page;
    }

    @Override
    public void update(Config config) {
        RegistryManager.registerService(config.getKey(),config.getValue());
        configDao.update(config);
    }

    @Override
    public void delete(int id) {
        Config config=new Config();
        config=configDao.load(id);
        if (config!=null) {
            configDao.delete(id);
            RegistryManager.registerService(config.getKey(),config.getValue());
        } else {
            logger.error("not found any config in mysql ");
        }
    }

    @Override
    public Config load(int id) {
        return configDao.load(id);
    }

}
