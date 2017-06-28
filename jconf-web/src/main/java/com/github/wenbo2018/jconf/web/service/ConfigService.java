package com.github.wenbo2018.jconf.web.service;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.web.dto.Config;

/**
 * Created by shenwenbo on 2017/4/16.
 */

public interface ConfigService {
    void add(Config config);
    void delete(int id);
    PageInfo<Config> queryByPage(Integer pageIndex, Integer pageSize);
    void update(Config config);
    Config load(int id);

}
