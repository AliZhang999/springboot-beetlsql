package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return master2017Dao.getAllSubject();
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

}
