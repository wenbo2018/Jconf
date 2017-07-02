package com.github.wenbo2018.jconf.admin.remote.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1985530917568391446L;
    private int id;
    private int userId;
    private String userName;
    private String passWord;
    private String userEmail;
    private String token;
    private Date addTime;
}
