package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherInfoService {

    @Autowired
    BaseDao baseDao;

    @Autowired
    Master2016Dao master2016Dao;

    @Autowired
    Master2017Dao master2017Dao;

    @Autowired
    Indicator2016Dao indicator2016Dao;

    public Map<String, List<Map>> getTeacherInfo(Map<String,Object> paras){
        String year = paras.get("year").toString();
        Map<String,List<Map>> teacherInfo = new HashMap<>();
        if (year.equals("2016")){
            teacherInfo.put("授课",master2016Dao.get教师授课信息(paras));
            teacherInfo.put("论文",master2016Dao.get教师论文信息(paras));
            teacherInfo.put("专利",master2016Dao.get教师专利信息(paras));
            teacherInfo.put("项目",master2016Dao.get教师项目信息(paras));
            teacherInfo.put("奖励",master2016Dao.get教师奖励信息(paras));
            teacherInfo.put("专著",master2016Dao.get教师专著信息(paras));
            teacherInfo.put("教材",master2016Dao.get教师教材信息(paras));
            teacherInfo.put("成果",master2016Dao.get教师成果信息(paras));
            teacherInfo.put("个人",master2016Dao.get教师个人信息(paras));
        }else if (year.equals("2017")){
            teacherInfo.put("授课",master2017Dao.get教师授课信息(paras));
            teacherInfo.put("论文",master2017Dao.get教师论文信息(paras));
            teacherInfo.put("专利",master2017Dao.get教师专利信息(paras));
            teacherInfo.put("项目",master2017Dao.get教师项目信息(paras));
            teacherInfo.put("奖励",master2017Dao.get教师奖励信息(paras));
            teacherInfo.put("专著",master2017Dao.get教师专著信息(paras));
            teacherInfo.put("教材",master2017Dao.get教师教材信息(paras));
            teacherInfo.put("成果",master2017Dao.get教师成果信息(paras));
            teacherInfo.put("个人",master2017Dao.get教师个人信息(paras));
        }
        return teacherInfo;
    }
}
