package com.github.wenbo2018.jconf.web.web;

import com.github.wenbo2018.jconf.admin.remote.api.ConfigService;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
public class SearchController extends AbstractController {

    @Autowired
    private ConfigService configService;

    @ResponseBody
    @RequestMapping("/jconf/search/key/")
    public CommonResultJson getKeyByProjectName(String projectName) {
        CommonResultJson commonResultJson = new CommonResultJson();
        commonResultJson.setCode(ResultCode.SUCCESS);
        return commonResultJson;
    }

}
