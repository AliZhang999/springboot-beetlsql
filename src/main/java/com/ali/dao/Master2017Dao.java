package com.ali.dao;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Master2017Dao {

    @Autowired
    SQLManager master2017SQLManager;

    public List<Map> getAllSubject() {
        return master2017SQLManager.select("master2017.allSubject", Map.class);
    }

    public List<Map> get教师授课信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师授课信息", Map.class, paras);
    }

    public List<Map> get教师论文信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师论文信息", Map.class, paras);
    }

    public List<Map> get教师专利信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师专利信息", Map.class, paras);
    }

    public List<Map> get教师项目信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师项目信息", Map.class, paras);
    }

    public List<Map> get教师奖励信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师奖励信息", Map.class, paras);
    }

    public List<Map> get教师专著信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师专著信息", Map.class, paras);
    }

    public List<Map> get教师教材信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师专著信息", Map.class, paras);
    }

    public List<Map> get教师成果信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师成果信息", Map.class, paras);
    }

    public List<Map> get教师个人信息(Map<String, Object> paras) {
        return master2017SQLManager.select("common.获取教师个人信息", Map.class, paras);
    }

    public List<Map> get高层次人才统计() {
        return master2017SQLManager.select("common.高层次人才统计",Map.class);
    }
}
