package com.github.wenbo2018.jconf.client.curator;

import com.github.wenbo2018.jconf.client.JconfConfig;
import com.github.wenbo2018.jconf.client.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorConfigLoader implements JconfConfig {

    private static Logger logger= LoggerFactory.getLogger(CuratorConfigLoader.class);

    @Override
    public String getValue(String key) {
        String value=null;
        try {
            value=CuratorManager.getInstance().getCuratorClient().get(Constants.JCONF_PREFEX+"/"+key);
        } catch (Exception e) {
            logger.error("get value error",e);
        }
        return value;
    }
}
