package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import com.ali.util.DatabaseDataUtil;
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

    public Map<String,Map<String,Map<String,Object>>> get高层次人才统计(Map<String,Object> paras) {
        String[] items = {"高层次人才总数","中国科学院院士","中国工程院院士","引进海外高层次人才“千人计划”入选者","长江学者特聘教授","国家杰出青年科学基金资助者","国家优秀青年科学基金资助者","新世纪优秀人才","教育部高校青年教师获奖者","青年“千人计划”","百千万人才工程","万人计划","国家级教学名师","省级高层次人才","省部级突出贡献专家","省级教学名师入选者","外国科学院院士","中国社会科学院学部委员"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get高层次人才统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get高层次人才统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get专职教师统计(Map<String, Object> paras) {
        String[] items = {"专职教师总数","离职专职教师","在职专职教师"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get专职教师统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get专职教师统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get教学团队统计(Map<String, Object> paras) {
        String[] items = {"国家级教学团队","省部级教学团队","教育部创新团队","国家自然科学基金委创新研究群体","省级高层次研究团队"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get教学团队统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get教学团队统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }
}
