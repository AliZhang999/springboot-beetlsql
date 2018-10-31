package com.ali.util;

import org.apache.commons.lang3.StringUtils;

public class IndicatorUtil {

    public static String getIndicatorTableName(String project, String namespace, String name){
        return StringUtils.join(new String[] { project, namespace, name }, '$').replaceAll("\\.", "_");
    }
}
