package com.github.wenbo2018.jconf.admin.remote.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
@Data
public class ConfigDTO implements Serializable{
    private static final long serialVersionUID = 9118951020172366058L;
    private int id;
    private String key;
    private String value;
    private String env;
    private String  projectName;
    private int configType;
    private String userName;
    private String userEmail;
    private int status;
    private String desc;
    private Date dateTime;
}
