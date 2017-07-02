package com.github.wenbo2018.jconf.dao;


import com.github.wenbo2018.jconf.entity.Config;

import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface ConfigDao {

    void add(Config config);

    List<Config> list();

    void delete(int id);

    Config load(int id);

    void update(Config config);

}
