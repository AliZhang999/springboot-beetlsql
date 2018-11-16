package com.ali.dao;

import com.ali.entity.Option;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaseDao{

    @Autowired
    SQLManager baseSQLManager;

    public List<Map> getSubjectAssessment(Map<String, Object> map) {
        return baseSQLManager.select("base.subjectAssessment", Map.class, map);
    }

    public List<Map> getAllSubject() {
        return baseSQLManager.select("base.subjectType",Map.class);
    }

    public List<Option> getSubjectList() {
        return baseSQLManager.select("common.subjectList", Option.class);
    }
}
