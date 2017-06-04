package com.github.wenbo2018.jconf.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wenbo.shen on 2017/6/4.
 */
@Controller
@RequestMapping("/jconf")
public class IndexController extends AbstractController {

    @RequestMapping("/wel")
    public String wel() {
        return "admin/wel";
    }

}
