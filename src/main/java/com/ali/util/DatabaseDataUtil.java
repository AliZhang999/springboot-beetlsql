package com.ali.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseDataUtil {

    public static String getIndicatorTableName(String project, String namespace, String name){
        return StringUtils.join(new String[] { project, namespace, name }, '$').replaceAll("\\.", "_");
    }

    public static Map<String, Map<String,Object>> analysisMajorDataBySubject(List<Map> source, Map<String, Map<String,Object>> target,String[] items){
        for (Map map : source) {
            String 专业代码 = map.get("专业代码").toString();
            String 学科代码 = 专业代码.substring(0, 4);
            if(target.containsKey(学科代码)){
                for (String item : items) {
                    Integer oldval =Integer.valueOf(target.get(学科代码).get(item).toString());
                    Integer mapval = Integer.valueOf(map.get(item).toString());
                    target.get(学科代码).put(item,oldval+mapval);
                }
            }else{
                Map<String,Object> a = new HashMap<>();
                for (String item : items) {
                    Integer mapval = Integer.valueOf(map.get(item).toString());
                    a.put(item,mapval);
                }
                target.put(学科代码,a);
            }
        }
        return target;
    }
}
