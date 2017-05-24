package com.github.wenbo2018.jconf.client.spring;

import com.github.wenbo2018.jconf.client.Jconf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by wenbo.shen on 2017/5/24.
 */
public class JconfigPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Logger logger = LoggerFactory.getLogger(JconfigPropertyPlaceholderConfigurer.class);

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        String defaultPropertyValue= Jconf.getString(propertyName);
        if (defaultPropertyValue==null) {
            logger.error("not find value from the value {}",propertyName);
        }
        return super.convertProperty(propertyName, defaultPropertyValue);  //将处理过的值传给父类继续处理
    }
}
