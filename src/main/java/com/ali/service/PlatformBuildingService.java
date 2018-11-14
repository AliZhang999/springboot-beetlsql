package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlatformBuildingService extends TopService{

    public Map<String,Map<String, Map<String,Object>>> getSubjectPlatformList(Map<String, Object> paras) {
        Map<String,Map<String, Map<String,Object>>> endData = new HashMap<>();
        String years = paras.get("years").toString();
        String[] strings = years.split(",");
        for (String year : strings) {
            Map<String,Object> para = new HashMap<>();
            para.put("sqlNoParam","true");
            para.put("year",year);
            List<Map> data = getDataByMethodNameWithParams("get学科平台列表", para);
            endData.put(year,integrateData(data));
        }

        return endData;
    }

    private Map<String, Map<String,Object>> integrateData(List<Map> data){
        Map<String,Map<String,Object>> result = new HashMap<>();
        for (Map map : data) {
            Map<String,Object> tmp = new HashMap<>();
            String keyVal = "";
            for (Object key : map.keySet()) {
                String val = map.get(key).toString();
                if(!key.toString().equals("学科代码")){
                    tmp.put(key.toString(),val);
                }else{
                    keyVal = val;
                }
            }
            result.put(keyVal,tmp);
        }
        return result;
    }
}
