package com.github.wenbo2018.jconf.common.util;

import java.util.Collection;

/**
 * Created by wenbo.shen on 2017/5/25.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }
}
