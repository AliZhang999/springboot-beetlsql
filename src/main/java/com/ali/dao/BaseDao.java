package com.ali.dao;

import com.ali.bean.subjectanalysis.subjectoverview.JYBSubjectAssetmentResult;
import com.ali.bean.subjectanalysis.subjectoverview.SubjectRanking;
import com.ali.config.MutipleSqlManager;
import com.ali.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaseDao{

    @Autowired
//    SQLManager baseSQLManager;
    MutipleSqlManager mutipleSqlManager;

    public List<Map> getSubjectAssessment(Map<String, Object> map) {
        return mutipleSqlManager.getBaseSQLManager().select("base.subjectAssessment", Map.class, map);
    }

    public List<Map> getAllSubject() {
        return mutipleSqlManager.getBaseSQLManager().select("base.subjectType",Map.class);
    }

    public List<Option> getSubjectList() {
        return mutipleSqlManager.getBaseSQLManager().select("common.subjectList", Option.class);
    }

    public List<Map> getNewSubjectList() {
        return mutipleSqlManager.getBaseSQLManager().select("base.subjectList",Map.class);
    }

    public List<JYBSubjectAssetmentResult> getNewSubjectAssessment(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.newSubjectAssessment", JYBSubjectAssetmentResult.class, map);
    }

    public List<Map> getNewSubjectInfo(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.newSubjectInfo", Map.class, map);
    }

    public List<Map> getNew高层次人才统计(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new高层次人才统计", Map.class, map);
    }

    public List<Map> get外校列表(){
        return mutipleSqlManager.getBaseSQLManager().select("base.new外校列表", Map.class);
    }

    public List<SubjectRanking> getNew各学科第四轮学科排名情况(){
        return mutipleSqlManager.getBaseSQLManager().select("base.new各学科第四轮学科排名情况", SubjectRanking.class);
    }

    public List<Map> getNew各学科高层次人才类型(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new各学科高层次人才类型", Map.class, map);
    }

    public List<Map> getNew各学科各类型高层次人才三年数据(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new各学科各类型高层次人才三年数据", Map.class, map);
    }

    public List<Map> getNew单个学科分类型高层次三年数据统计(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new单个学科分类型高层次三年数据统计", Map.class, map);
    }

    public List<Map> getNew单个学科高层次人才分类统计(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new单个学科高层次人才分类统计", Map.class, map);
    }

    public List<Map> getNew本校学科外校学科高层次人才分类统计(Map<String, Object> map){
        return mutipleSqlManager.getBaseSQLManager().select("base.new本校学科外校学科高层次人才分类统计", Map.class, map);
    }
}
