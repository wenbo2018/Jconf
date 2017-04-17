package com.github.wenbo2018.jconf.web.web;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.dto.PageModel;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenwenbo on 2017/4/17.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping("/index")
    public String index() {
        return "admin/datas";
    }

    @RequestMapping(value = "/configPagses", method = RequestMethod.GET)
    @ResponseBody
    public PageModel<Config> pageModel(int pageIndex, int pageSize) {
        PageInfo<Config> pageInfo=configService.queryByPage(pageIndex+1,pageSize);
        PageModel<Config> pageModel=new PageModel<Config>();
        pageModel.setList(pageInfo.getList());
        pageModel.setPageIndex(pageInfo.getPageNum());
        pageModel.setTotal((int) pageInfo.getTotal());
        pageModel.setPageSize(pageInfo.getPageSize());
        return pageModel;
    }


}
