package com.github.wenbo2018.jconf.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.admin.remote.api.ConfigService;
import com.github.wenbo2018.jconf.admin.remote.dto.ConfigDTO;
import com.github.wenbo2018.jconf.common.util.CollectionUtils;
import com.github.wenbo2018.jconf.core.RegistryManager;
import com.github.wenbo2018.jconf.dao.ConfigDao;
import com.github.wenbo2018.jconf.entity.Config;
import com.github.wenbo2018.jconf.utils.BeanListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigDao configDao;

    @Override
    @Transactional
    public void add(ConfigDTO configDTO) {
        if (configDTO == null) {
            return;
        }
        Config config = new Config();
        BeanUtils.copyProperties(configDTO, config);
        configDao.add(config);
        RegistryManager.registerService(config.getKey(), config.getValue());
    }

    @Override
    public void delete(int id) {
        Config config = configDao.load(id);
        if (config != null) {
            RegistryManager.unregisterService(config.getKey());
        }
        configDao.delete(id);
    }

    @Override
    public PageInfo<ConfigDTO> queryByPage(Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 0 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageIndex, pageSize);
        List<Config> list = configDao.list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        PageInfo page = new PageInfo(list);
        if (page.getSize()<=0) {
            return null;
        }
        List<ConfigDTO> configDTOlist = BeanListUtil.copyPropertiesArrayList(list, ConfigDTO.class);
        page.setList(configDTOlist);
        return page;
    }

    @Override
    public void update(ConfigDTO configDTO) {
        if (configDTO == null) {
            return;
        }
        Config config = new Config();
        BeanUtils.copyProperties(configDTO, config);
        configDao.update(config);
    }

    @Override
    public ConfigDTO load(int id) {
        if (id <= 0) {
            return null;
        }
        Config config = configDao.load(id);
        if (config == null) {
            return null;
        }
        ConfigDTO configDTO = new ConfigDTO();
        BeanUtils.copyProperties(config, configDTO);
        return configDTO;
    }
}
