package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeachingStaffService extends TopService {

    public Map<String,Map<String,Map<String,Object>>> get高层次人才统计(Map<String,Object> paras) {
        String[] items = {"高层次人才总数","中国科学院院士","中国工程院院士","引进海外高层次人才“千人计划”入选者","长江学者特聘教授","国家杰出青年科学基金资助者","国家优秀青年科学基金资助者","新世纪优秀人才","教育部高校青年教师获奖者","青年“千人计划”","百千万人才工程","万人计划","国家级教学名师","省级高层次人才","省部级突出贡献专家","省级教学名师入选者","外国科学院院士","中国社会科学院学部委员"};
        return backSubjectData(paras,items,"get高层次人才统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get专职教师统计(Map<String, Object> paras) {
        String[] items = {"专职教师总数","离职专职教师","在职专职教师"};
        return backSubjectData(paras,items,"get专职教师统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get教学团队统计(Map<String, Object> paras) {
        String[] items = {"研究人员数","正高级研究人员","副高级研究人员","讲师研究人员","助教研究人员","中级研究人员","初级研究人员","国家级教学团队","省部级教学团队","教育部创新团队","国家自然科学基金委创新研究群体","省级高层次研究团队"};
        return backSubjectData(paras,items,"get教学团队统计");
    }

}
