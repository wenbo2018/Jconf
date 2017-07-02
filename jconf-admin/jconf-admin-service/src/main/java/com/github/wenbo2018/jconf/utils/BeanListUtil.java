package com.github.wenbo2018.jconf.utils;

import com.github.wenbo2018.jconf.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
public class BeanListUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanListUtil.class);

    public static <T, E> List<E> copyPropertiesArrayList(List<T> source, Class<E> target) {
        List<E> es = new ArrayList<E>();
        try {
            if (CollectionUtils.isNotEmpty(source)) {
                for (T t : source) {
                    E e = target.newInstance();
                    BeanUtils.copyProperties(t, e);
                    es.add(e);
                }
            }
        } catch (Exception e) {
            logger.error("copy bean properties error:{}", e);
        }
        return es;
    }
}
