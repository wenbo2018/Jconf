package com.github.wenbo2018.jconf.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Data
public class Config {
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
