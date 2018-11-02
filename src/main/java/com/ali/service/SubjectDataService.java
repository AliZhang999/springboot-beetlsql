package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import com.ali.util.DatabaseDataUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectDataService {

    @Autowired
    BaseDao baseDao;

    @Autowired
    Master2016Dao master2016Dao;

    @Autowired
    Master2017Dao master2017Dao;

    @Autowired
    Indicator2016Dao indicator2016Dao;

    public List<Map> getAllSubject() {
        return baseDao.getAllSubject();
    }

    public List<Map> getSubjectAssessment(Map<String, Object> map) {
        return baseDao.getSubjectAssessment(map);
    }

    public Object getIndicatorDataByTableName(Map<String,Object> paras,String subjectCode){
        Object data = null;
        List<Map> maps = indicator2016Dao.getIndicatorDataByTableName(paras);
        String script = maps.get(0).get("data").toString();
        Map map = new Gson().fromJson(script, Map.class);
        for (Object key : map.keySet()) {
            if(key.equals(subjectCode)){
                data = map.get(key);
                break;
            }
        }
        return data;
    }

    public Map<String,Map<String,Map<String,Object>>> get师资结构数据(Map<String, Object> paras) {
        String[] items = {"正高级职称","副高级职称","高级职称","博士学位"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get师资结构统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get师资结构统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }
}
