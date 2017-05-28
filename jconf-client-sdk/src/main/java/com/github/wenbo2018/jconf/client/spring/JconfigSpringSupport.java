package com.github.wenbo2018.jconf.client.spring;

import com.github.wenbo2018.jconf.client.Jconf;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by wenbo.shen on 2017/5/24.
 */
public class JconfigSpringSupport extends PropertyPlaceholderConfigurer  {

    private static Logger logger = LoggerFactory.getLogger(JconfigSpringSupport.class);

    JconfigSpringSupport() {
        super();
    }

    public String resolvePlaceholder(String placeholder, Properties props) {
        String value = Jconf.getString(placeholder);
        if (StringUtils.isEmpty(value)) {
            logger.error("Unable to read configuration property parameters from Jconf#parameter:{}",placeholder);
        }
        return value;
    }

//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        try {
//            Properties mergedProps = mergeProperties();
//            getPropertiesFromJconf(mergedProps);
//            convertProperties(mergedProps);
//            processProperties(beanFactory, mergedProps);
//        } catch (IOException ex) {
//            logger.error("BeanInitializationException:{}",ex);
//            throw new BeanInitializationException("Jconf Could not load properties", ex);
//        }
//    }
//
//
//    private void getPropertiesFromJconf(Properties properties) {
//        ConcurrentMap<String, String> cache = JconfCache.getInstance().getJconfCache();
//        if (cache == null || cache.isEmpty()) {
//            return;
//        }
//        Iterator<Map.Entry<String, String>> it = cache.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            properties.setProperty(entry.getKey(), entry.getValue());
//        }
//    }
}
