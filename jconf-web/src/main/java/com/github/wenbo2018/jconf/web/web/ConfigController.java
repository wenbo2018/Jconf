package com.github.wenbo2018.jconf.web.web;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.bean.ConfigVo;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.dto.PageModel;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenwenbo on 2017/4/17.
 */
@Controller
@RequestMapping("/jconf/admin/config")
public class ConfigController extends AbstractController {

    @Autowired
    private ConfigService configService;

    @RequestMapping("/index")
    public String index() {
        return "admin/app";
    }

    @RequestMapping(value = "/configPagses", method = RequestMethod.GET)
    @ResponseBody
    public PageModel<ConfigVo> pageModel(int pageIndex, int pageSize) {
        PageInfo<Config> pageInfo = configService.queryByPage(pageIndex, pageSize);
        List<Config> configs = pageInfo.getList();
        List<ConfigVo> configVos = new ArrayList<ConfigVo>();
        for (Config config:configs) {
            ConfigVo configVo=new ConfigVo();
            configVo.setConfigType(config.getConfigType());
            configVo.setDateTime(config.getDateTime());
            configVo.setId(config.getId());
            config.setProjectName(config.getProjectName());
            configVo.setKey(config.getKey());
            configVo.setUserName(config.getUserName());
            configVo.setValue(config.getValue());
            configVo.setEnv(config.getEnv().split(","));
            configVo.setStatus(config.getStatus());
            configVos.add(configVo);
        }
        PageModel<ConfigVo> pageModel = new PageModel<ConfigVo>();
        pageModel.setList(configVos);
        pageModel.setPageIndex(pageInfo.getPageNum());
        pageModel.setTotal((int) pageInfo.getTotal());
        pageModel.setPageSize(pageInfo.getPageSize());
        return pageModel;
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public CommonResultJson configUpdate(String key,
                                         String value,
                                         Integer configType,
                                         String projectName, @RequestParam(value = "env[]") String[] env) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || configType <= 0 || StringUtils.isEmpty(projectName)) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        if (env == null||env.length<=0) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        User user = getUser();
        if (user == null) {
            result.setCode(ResultCode.ERROR);
            result.setMessage("服务端异常!");
            return result;
        }

        Config config = new Config();
        config.setEnv(com.github.wenbo2018.jconf.web.utils.StringUtils.arrayToString(env));
        config.setKey(key);
        config.setProjectName(projectName);
        config.setConfigType(configType);
        config.setValue(value);
        config.setUserName(user.getUserName());
        config.setUserEmail(user.getUserEmail());
        configService.add(config);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public CommonResultJson configDelete(Integer id) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id < 0) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        configService.delete(id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public CommonResultJson configUpdate(int id, String value) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id <= 0 || value == null) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        Config config=configService.load(id);
        config.setValue(value);
        configService.update(config);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus")
    public CommonResultJson configUpdate(int id) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id <= 0 ) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        Config config=configService.load(id);
        config.setStatus(-config.getStatus());
        configService.update(config);
        return result;
    }

}
