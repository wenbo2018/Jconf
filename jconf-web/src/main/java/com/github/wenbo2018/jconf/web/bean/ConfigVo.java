package com.github.wenbo2018.jconf.web.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by wenbo.shen on 2017/6/26.
 */
@Data
public class ConfigVo {
    private int id;
    private String key;
    private String value;
    private String  projectName;
    private String userName;
    private int configType;
    private Date dateTime;
    private String[] env;
    private int status;
}
