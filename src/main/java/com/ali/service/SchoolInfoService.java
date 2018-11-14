package com.ali.service;

import com.ali.util.DatabaseDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SchoolInfoService extends TopService {

    public Map<String,Map> getSchoolInfo(Map<String,Object> paras){
        Map<String,Map> data = new HashMap<>();

        String project = "校级BI";
        String namespace = "二全校整体情况";
        String name = "2.1核心数据";
        paras.put("tableName", DatabaseDataUtil.getIndicatorTableName(project,namespace,name));
        Map map = getIndicatorDataByTableName(paras);
        data.put("hxsj",map);

        namespace = "一学校概况";
        name = "1.1.1学校基本概况";
        paras.put("tableName", DatabaseDataUtil.getIndicatorTableName(project,namespace,name));
        map = getIndicatorDataByTableName(paras);
        data.put("xjgk",map);

        return data;
    }

}
