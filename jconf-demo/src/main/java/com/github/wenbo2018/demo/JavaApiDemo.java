package com.github.wenbo2018.demo;

import com.github.wenbo2018.jconf.client.Jconf;

/**
 * Created by wenbo.shen on 2017/5/27.
 */
public class JavaApiDemo {
    public static void main(String[] args) {
        System.out.println(Jconf.getString("https"));
    }
}
