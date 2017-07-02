package com.github.wenbo2018.jconf.admin.remote.api;


import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.admin.remote.dto.ConfigDTO;

/**
 * Created by shenwenbo on 2017/4/16.
 */

public interface ConfigService {
    void add(ConfigDTO configDTO);
    void delete(int id);
    PageInfo queryByPage(Integer pageIndex, Integer pageSize);
    void update(ConfigDTO configDTO);
    ConfigDTO load(int id);
}
