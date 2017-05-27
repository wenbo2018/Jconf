package com.github.wenbo2018.jconf.client.curator;

import com.github.wenbo2018.jconf.client.JconfConfig;
import com.github.wenbo2018.jconf.client.constants.Constants;
import com.github.wenbo2018.jconf.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorConfigLoader implements JconfConfig {

    private static Logger logger = LoggerFactory.getLogger(CuratorConfigLoader.class);

    @Override
    public String getValue(String key) {
        String value = null;
        try {
            value = CuratorManager.getInstance().getCuratorClient().get(Constants.JCONF_PREFEX + "/" + key);
        } catch (Exception e) {
            logger.error("get value error", e);
        }
        return value;
    }

    @Override
    public ConcurrentMap<String, String> sync() {
        List<String> keyList = null;
        ConcurrentMap<String, String> sysnMap = new ConcurrentHashMap<String, String>();
        try {
            keyList = CuratorManager.getInstance().getCuratorClient().getChild(Constants.JCONF_PREFEX);
        } catch (Exception e) {
            logger.error("config sync failue:{}", e);
        }
        if (CollectionUtils.isEmpty(keyList)) {
            logger.error("not found any config can be sync");
        } else {
            for (String key : keyList) {
                 String value=getValue(key);
                 sysnMap.put(key,value);
            }
        }
        return sysnMap;
    }
}
