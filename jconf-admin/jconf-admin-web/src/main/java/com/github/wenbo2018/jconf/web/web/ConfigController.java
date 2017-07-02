package com.github.wenbo2018.jconf.web.web;

import com.github.pagehelper.PageInfo;
import com.github.wenbo2018.jconf.admin.remote.api.ConfigService;
import com.github.wenbo2018.jconf.admin.remote.core.PageModel;
import com.github.wenbo2018.jconf.admin.remote.dto.ConfigDTO;
import com.github.wenbo2018.jconf.admin.remote.dto.UserDTO;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.bean.ConfigVo;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return "admin/configIndex";
    }

    @RequestMapping(value = "/configPagses", method = RequestMethod.GET)
    @ResponseBody
    public PageModel<ConfigVo> pageModel(int pageIndex, int pageSize) {
        PageInfo<ConfigDTO> pageInfo = configService.queryByPage(pageIndex, pageSize);
        List<ConfigDTO> configs = pageInfo.getList();
        List<ConfigVo> configVos = new ArrayList<ConfigVo>();
        for (ConfigDTO configDTO:configs) {
            ConfigVo configVo=new ConfigVo();
            configVo.setConfigType(configDTO.getConfigType());
            configVo.setDateTime(configDTO.getDateTime());
            configVo.setId(configDTO.getId());
            configVo.setProjectName(configDTO.getProjectName());
            configVo.setKey(configDTO.getKey());
            configVo.setUserName(configDTO.getUserName());
            configVo.setValue(configDTO.getValue());
            configVo.setEnv(configDTO.getEnv().split(","));
            configVo.setStatus(configDTO.getStatus());
            configVo.setDesc(configDTO.getDesc());
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
                                         String projectName,
                                         @RequestParam(value = "env[]") String[] env,
                                         String desc) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (StringUtils.isEmpty(key)
                || StringUtils.isEmpty(value)
                || configType <= 0
                || StringUtils.isEmpty(projectName)
                ||StringUtils.isEmpty(desc)) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        if (env == null||env.length<=0) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("请输入完整的参数");
            return result;
        }
        UserDTO userDTO = getUser();
        if (userDTO == null) {
            result.setCode(ResultCode.ERROR);
            result.setMessage("服务端异常!");
            return result;
        }

        ConfigDTO configDTO = new ConfigDTO();
        configDTO.setEnv(com.github.wenbo2018.jconf.web.utils.StringUtils.arrayToString(env));
        configDTO.setKey(key);
        configDTO.setProjectName(projectName);
        configDTO.setConfigType(configType);
        configDTO.setValue(value);
        configDTO.setDesc(desc);
        configDTO.setUserName(userDTO.getUserName());
        configDTO.setUserEmail(userDTO.getUserEmail());
        configService.add(configDTO);
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
    public CommonResultJson configUpdate(Integer id,
                                         String value,
                                         Integer configType,
                                         @RequestParam(value = "env[]") String[] env,
                                         String desc) {
        CommonResultJson result = new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (id <= 0 ||configType<=0|| value == null||env==null||env.length<=0||StringUtils.isEmpty(desc)) {
            result.setCode(ResultCode.PARAMETER_ERROR);
            result.setMessage("参数不合法！");
            return result;
        }
        ConfigDTO configDTO=configService.load(id);
        configDTO.setValue(value);
        configDTO.setConfigType(configType);
        configDTO.setDesc(desc);
        configDTO.setEnv(com.github.wenbo2018.jconf.web.utils.StringUtils.arrayToString(env));
        configService.update(configDTO);
        ConfigVo configVo=new ConfigVo();
        configVo.setConfigType(configDTO.getConfigType());
        configVo.setDateTime(configDTO.getDateTime());
        configVo.setId(configDTO.getId());
        configVo.setProjectName(configDTO.getProjectName());
        configVo.setKey(configDTO.getKey());
        configVo.setUserName(configDTO.getUserName());
        configVo.setValue(configDTO.getValue());
        configVo.setEnv(configDTO.getEnv().split(","));
        configVo.setStatus(configDTO.getStatus());
        configVo.setDesc(configDTO.getDesc());
        result.setDataObject("configVo",configVo);
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
        ConfigDTO configDTO=configService.load(id);
        int status=-configDTO.getStatus();
        configDTO.setStatus(status);
        configService.update(configDTO);
        ConfigVo configVo=new ConfigVo();
        configVo.setConfigType(configDTO.getConfigType());
        configVo.setDateTime(configDTO.getDateTime());
        configVo.setId(configDTO.getId());
        configVo.setProjectName(configDTO.getProjectName());
        configVo.setKey(configDTO.getKey());
        configVo.setUserName(configDTO.getUserName());
        configVo.setValue(configDTO.getValue());
        configVo.setEnv(configDTO.getEnv().split(","));
        configVo.setStatus(configDTO.getStatus());
        configVo.setDesc(configDTO.getDesc());
        result.setDataObject("configVo",configVo);
        return result;
    }

}
