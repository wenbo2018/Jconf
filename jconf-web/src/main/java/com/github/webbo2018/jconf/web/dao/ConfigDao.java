package com.github.webbo2018.jconf.web.dao;

import com.github.webbo2018.jconf.web.dto.Config;

import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface ConfigDao {
    void add(Config config);
    List<Config> list();
}
