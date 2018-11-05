package com.ali.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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

    public static double roundedUpTo2DecimalPlacesByDiv(Object obj1,Object obj2) {// return:str1/str2*100
        double val1 = 0.0;
        double val2 = 0.0;
        if(!obj1.getClass().toString().equals(Double.class.toString())) {
            val1 = Double.valueOf(obj1.toString());
        }else {
            val1 = (double) obj1;
        }
        if(!obj2.getClass().toString().equals(Double.class.toString())) {
            val2 = Double.valueOf(obj2.toString());
        }else {
            val2 = (double) obj2;
        }

        double rate = 0.0;
        if(val1 == 0.0 || val2 == 0.0) {
            rate = 0.0;
        }else {
            rate = val1 / val2 * 100;
        }
        BigDecimal b = new BigDecimal(rate);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
