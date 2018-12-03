package com.ali.dao;

import com.ali.config.MutipleSqlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Indicator2016Dao{

    @Autowired
//    SQLManager indicator2016SQLManager;
    MutipleSqlManager mutipleSqlManager;

    public List<Map> getIndicatorDataByTableName(Map<String, Object> paras) {
        return mutipleSqlManager.getIndicator2016SQLManager().select("indicator2016.indicatorData", Map.class, paras);
    }
}
