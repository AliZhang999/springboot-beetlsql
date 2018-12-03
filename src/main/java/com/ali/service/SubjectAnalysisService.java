package com.ali.service;

import com.ali.bean.subjectanalysis.subjectoverview.JYBSubjectAssetmentResult;
import com.ali.bean.subjectanalysis.subjectoverview.SubjectRanking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectAnalysisService extends TopService {

    public List<Map> getNewSubjectList(){
        return baseDao.getNewSubjectList();
    }

    public List<JYBSubjectAssetmentResult> getNewSubjectAssessment(Map<String, Object> paras){
        return baseDao.getNewSubjectAssessment(paras);
    }

    public List<Map> getNewSubjectInfo(Map<String, Object> paras){
        return baseDao.getNewSubjectInfo(paras);
    }

    public List<Map> getNew高层次人才统计(Map<String, Object> map){
        return baseDao.getNew高层次人才统计(map);
    }

    public List<Map> get外校列表(){
        return baseDao.get外校列表();
    }

    public List<SubjectRanking> getNew各学科第四轮学科排名情况(){
        return baseDao.getNew各学科第四轮学科排名情况();
    }

    public List<Map> getFSchoolList() {
        return baseDao.get外校列表();
    }

    public List<Map> getNew各学科高层次人才类型(Map<String, Object> map){
        return baseDao.getNew各学科高层次人才类型(map);
    }

    public List<Map> getNew各学科各类型高层次人才三年数据(Map<String, Object> map){
        return baseDao.getNew各学科各类型高层次人才三年数据(map);
    }

    public List<Map> getNew单个学科分类型高层次三年数据统计(Map<String, Object> map){
        return baseDao.getNew单个学科分类型高层次三年数据统计(map);
    }

    public List<Map> getNew单个学科高层次人才分类统计(Map<String, Object> map){
        return baseDao.getNew单个学科高层次人才分类统计(map);
    }

    public List<Map> getNew本校学科外校学科高层次人才分类统计(Map<String, Object> map){
        return baseDao.getNew本校学科外校学科高层次人才分类统计(map);
    }
}
