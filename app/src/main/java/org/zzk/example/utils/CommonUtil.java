package org.zzk.example.utils;

import java.util.Collection;

/**
 * Created by zwl on 16/9/30.
 */

public class CommonUtil {
    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
