package com.github.wenbo2018.jconf.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}
