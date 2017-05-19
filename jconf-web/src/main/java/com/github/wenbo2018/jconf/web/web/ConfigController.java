package com.github.wenbo2018.jconf.web.web;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.dto.PageModel;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenwenbo on 2017/4/17.
 */
@Controller
@RequestMapping("/jconf/admin/config")
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
        PageInfo<Config> pageInfo = configService.queryByPage(pageIndex + 1, pageSize);
        PageModel<Config> pageModel = new PageModel<Config>();
        pageModel.setList(pageInfo.getList());
        pageModel.setPageIndex(pageInfo.getPageNum());
        pageModel.setTotal((int) pageInfo.getTotal());
        pageModel.setPageSize(pageInfo.getPageSize());
        return pageModel;
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public CommonResultJson configUpdate(String key, String value, @Param("false") Integer env, @Param("false") Integer projectId, String userName, String email) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (StringUtils.isEmpty(key)||StringUtils.isEmpty(value)||StringUtils.isEmpty(userName)||StringUtils.isEmpty(email)) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        if (projectId<=0||env<=0) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        Config config=new Config();
        config.setEnv(env);
        config.setKey(key);
        config.setProjectId(projectId);
        config.setUserName(userName);
        config.setUserEmail(email);
        config.setValue(value);
        configService.add(config);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public CommonResultJson configDelete(Integer id) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id<0) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        configService.delete(id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public CommonResultJson configUpdate(int id ,String value) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id<=0||value==null) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        configService.update(id,value);
        return result;
    }

}
