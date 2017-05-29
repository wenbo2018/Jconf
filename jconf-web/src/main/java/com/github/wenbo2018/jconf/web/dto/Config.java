package com.github.wenbo2018.jconf.web.dto;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Data
public class Config {
    private int id;
    private String key;
    private String value;
    private int env;
    private int  projectId;
    private int configType;
    private String userName;
    private String userEmail;
    private Date dateTime;
}
