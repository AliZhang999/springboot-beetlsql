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

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get留学生统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get留学生统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get学生交流统计(Map<String, Object> paras) {
        String[] items = {"本专业到境外学生数","本专业到境内学生数","境外到本专业学生数","境内到本专业学生数"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get学生交流统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get学生交流统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();

            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get毕业生统计(Map<String, Object> paras) {
        String[] items = {"应届毕业生数","应届未按时毕业生数"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get毕业生统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get毕业生统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();
            //计算学科毕业率
            Map<String, Map<String,Object>> analysisData = DatabaseDataUtil.analysisMajorDataBySubject(result,data,items);
            for (String key : analysisData.keySet()) {
                Map<String,Object> mapval = analysisData.get(key);
                Integer 应届毕业生数 = Integer.valueOf(mapval.get("应届毕业生数").toString());
                Integer 应届未按时毕业生数 = Integer.valueOf(mapval.get("应届未按时毕业生数").toString());
                mapval.put("毕业率", DatabaseDataUtil.roundedUpTo2DecimalPlacesByDiv(应届毕业生数, 应届毕业生数+应届未按时毕业生数));
            }
            endData.put(year,analysisData);
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get获奖统计(Map<String, Object> paras) {
        String[] items = {"国家级获奖","国家级一等奖","国家级二等奖","国家级三等奖","省部级获奖","省部级一等奖","省部级二等奖","省部级三等奖"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get获奖统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get获奖统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();
            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String, Map<String, Map<String, Object>>> get教师专利授权统计(Map<String, Object> paras) {
        String[] items = {"发明专利","实用新型专利","外观专利","著作权"};

        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            List<Map> result = null;
            if (year.equals("2016")){
                result = master2016Dao.get教师专利授权统计();
            }else if (year.equals("2017")){
                result = master2017Dao.get教师专利授权统计();
            }

            Map<String,Map<String,Object>> data = new HashMap<>();
            endData.put(year,DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }
}
