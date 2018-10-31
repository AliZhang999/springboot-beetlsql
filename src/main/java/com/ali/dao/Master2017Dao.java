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
}
