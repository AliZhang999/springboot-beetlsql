package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeachingStaffService {

    @Autowired
    BaseDao baseDao;

    @Autowired
    Master2016Dao master2016Dao;

    @Autowired
    Master2017Dao master2017Dao;

    @Autowired
    Indicator2016Dao indicator2016Dao;


    public List<Map> get高层次人才统计(Map<String,Object> paras) {
        String year = paras.get("year").toString();
        List<Map> result = null;
        if (year.equals("2016")){
            result = master2016Dao.get高层次人才统计();
        }else if (year.equals("2017")){
            result = master2017Dao.get高层次人才统计();
        }
        for (Map map : result) {
            String 专业代码 = map.get("专业代码").toString();

        }
        return result;
    }
}
