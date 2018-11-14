package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectDataService extends TopService {

    public List<Map> getAllSubject() {
        return baseDao.getAllSubject();
    }

    public List<Map> getSubjectAssessment(Map<String, Object> map) {
        return baseDao.getSubjectAssessment(map);
    }

    public Object getIndicatorSubjectData(Map<String,Object> paras,String subjectCode){
        Object data = null;
        Map map = getIndicatorDataByTableName(paras);
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
        return backSubjectData(paras, items, "get师资结构统计");
    }
}
