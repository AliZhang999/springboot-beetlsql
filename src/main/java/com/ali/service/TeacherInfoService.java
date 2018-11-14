package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherInfoService extends TopService {

    public Map<String, List<Map>> getTeacherInfo(Map<String,Object> paras){
        Map<String,List<Map>> teacherInfo = new HashMap<>();
        teacherInfo.put("授课",getDataByMethodNameWithParams("get教师授课信息",paras));
        teacherInfo.put("论文",getDataByMethodNameWithParams("get教师论文信息",paras));
        teacherInfo.put("专利",getDataByMethodNameWithParams("get教师专利信息",paras));
        teacherInfo.put("项目",getDataByMethodNameWithParams("get教师项目信息",paras));
        teacherInfo.put("奖励",getDataByMethodNameWithParams("get教师奖励信息",paras));
        teacherInfo.put("专著",getDataByMethodNameWithParams("get教师专著信息",paras));
        teacherInfo.put("教材",getDataByMethodNameWithParams("get教师教材信息",paras));
        teacherInfo.put("成果",getDataByMethodNameWithParams("get教师成果信息",paras));
        teacherInfo.put("个人",getDataByMethodNameWithParams("get教师个人信息",paras));
        return teacherInfo;
    }

    public List<Map> getTeacherList(Map<String, Object> paras) {
        paras.put("sqlNoParam","true");
        return getDataByMethodNameWithParams("get教师列表",paras);
    }
}
