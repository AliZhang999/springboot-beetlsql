package com.ali.service;

import com.ali.util.DatabaseDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonnelTrainingService extends TopService {

    public Map<String,Map<String,Map<String,Map<String,Object>>>> get生源统计(Map<String, Object> paras){
        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");
        String[] items = {"招生计划数","实际录取数","第一志愿专业录取数","实际报到人数"};

        Map<String,Map<String,Map<String,Map<String,Object>>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get生源统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get生源统计();
            }

            Map<String,Map<String,Map<String,Object>>> data = new HashMap<>();

            for (Map map : result) {
                String 专业代码 = map.get("专业代码").toString();
                String 学科代码 = 专业代码.substring(0, 4);
                if(data.containsKey(学科代码)){
                    Map<String, Map<String, Object>> 学科代码Map = data.get(学科代码);
                    if(学科代码Map.containsKey(map.get("省份").toString())){
                        Map<String, Object> 省份Map = 学科代码Map.get(map.get("省份").toString());
                        for (String item : items) {
                            Integer oldval =Integer.valueOf(省份Map.get(item).toString());
                            Integer mapval = Integer.valueOf(map.get(item).toString());
                            省份Map.put(item,oldval+mapval);
                        }
                    }else{
                        Map<String,Object> c = new HashMap<>();
                        for (String item : items) {
                            Integer mapval = Integer.valueOf(map.get(item).toString());
                            c.put(item,mapval);
                        }
                        学科代码Map.put(map.get("省份").toString(),c);
                    }
                }else{
                    Map<String,Map<String,Object>> b = new HashMap<>();
                    Map<String,Object> c = new HashMap<>();
                    for (String item : items) {
                        Integer mapval = Integer.valueOf(map.get(item).toString());
                        c.put(item,mapval);
                    }
                    b.put(map.get("省份").toString(),c);
                    data.put(学科代码,b);
                }
            }
            endData.put(year,data);
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get留学生统计(Map<String, Object> paras) {
        String[] items = {"留学生数"};
        return backSubjectData(paras,items,"get留学生统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get学生交流统计(Map<String, Object> paras) {
        String[] items = {"本专业到境外学生数","本专业到境内学生数","境外到本专业学生数","境内到本专业学生数"};
        return backSubjectData(paras,items,"get学生交流统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get学生情况(Map<String, Object> paras) {
        String[] items = {"本科生数","留学生","港澳台学生","一年级","二年级","三年级","四年级","其他年级"};
        return backSubjectDataWithParam(paras,items,"get学生情况");
    }

    public Map<String, Map<String, Map<String, Object>>> get在校成果(Map<String, Object> paras) {
        String[] items = {
                "国家级创新","一年级国家级创新","二年级国家级创新","三年级国家级创新","四年级国家级创新",
                "国家级创业","一年级国家级创业","二年级国家级创业","三年级国家级创业","四年级国家级创业",
                "省部级创新","一年级省部级创新","二年级省部级创新","三年级省部级创新","四年级省部级创新",
                "省部级创业","一年级省部级创业","二年级省部级创业","三年级省部级创业","四年级省部级创业"
        };
        return backSubjectDataWithParam(paras,items,"get在校成果");
    }

    public Map<String, Map<String, Map<String, Object>>> get毕业生统计(Map<String, Object> paras) {
        String[] items = {"应届毕业生数","应届未按时毕业生数"};
        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");
        Map<String,Map<String,Map<String,Object>>> endData = backSubjectData(paras,items,"get毕业生统计");
        //计算学科毕业率
        for (String year : yearArr) {
            Map<String, Map<String, Object>> analysisData = endData.get(year);
            for (String key : analysisData.keySet()) {
                Map<String,Object> mapval = analysisData.get(key);
                Integer 应届毕业生数 = Integer.valueOf(mapval.get("应届毕业生数").toString());
                Integer 应届未按时毕业生数 = Integer.valueOf(mapval.get("应届未按时毕业生数").toString());
                mapval.put("毕业率", DatabaseDataUtil.roundedUpTo2DecimalPlacesByDiv(应届毕业生数, 应届毕业生数+应届未按时毕业生数));
            }
        }
        return endData;
    }

}
