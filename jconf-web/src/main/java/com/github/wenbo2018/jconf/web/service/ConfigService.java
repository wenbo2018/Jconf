package com.github.wenbo2018.jconf.web.service;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.web.dto.Config;

import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */

public interface ConfigService {

    void add(Config config);

    PageInfo<Config> queryByPage(Integer pageIndex, Integer pageSize);

    void update(Config config);

    void delete(int id);

    Config load(int id);
}
