package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubjectResearchService extends TopService {

    public Map<String, Map<String, Map<String, Object>>> get教师专利授权统计(Map<String, Object> paras) {
        String[] items = {"发明专利","实用新型专利","外观专利","著作权"};
        return backSubjectData(paras,items,"get教师专利授权统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get教师主编专业教材统计(Map<String, Object> paras) {
        String[] items = {"国家级规划教材","省部级规划教材","国家级精品教材","省部级精品教材"};
        return backSubjectData(paras,items,"get教师主编专业教材统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get教师主持科研项目统计(Map<String, Object> paras) {
        String[] items = {"纵向项目","横向项目",
                "科技部项目","国家重大科技专项","国家自然基金项目","国家社科基金项目",
                "国家艺术基金","国防军队重要科研项目","境外合作科研项目","国家级全国教育科学规划课题",
                "教育部级全国教育科学规划课题","教育部人文社会科学研究项目","部委级项目","省教育厅科研立项",
                "省科技厅立项","省自然科学基金","省哲学社科基金","省级其它","无"};
        return backSubjectData(paras,items,"get教师主持科研项目统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get教师出版专著统计(Map<String, Object> paras) {
        String[] items = {"专著","译著", "辞书"};
        return backSubjectData(paras,items,"get教师出版专著统计");
    }

    public Map<String, Map<String, Map<String, Object>>> get教师发表论文统计(Map<String, Object> paras) {
        String[] items = {
                "论文总数","他引次数","第一作者","通讯作者","科研论文","教研论文",
                "SCI","SSCI","EI","CPCI","AHCI","CSCD","CSSCI","ISTP","北大中文核心期刊",
                "是行业联合发表","未与行业联合发表",
                "是与行业联合发表","未与行业联合发表",
                "是与国际联合发表","未与国际联合发表",
                "是跨学科论文","非跨学科论文"
        };
        return backSubjectData(paras,items,"get教师发表论文统计");
    }

}
