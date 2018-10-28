package com.ali.controller;

import com.ali.repository.indicator2016.Indicator2016Repository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("indicator2016")
public class Indicator2016Controller {

    @Autowired
    Indicator2016Repository indicator2016Repository;

    @Autowired
    SQLManager indicator2016SQLManager;

    @GetMapping("sqlmanager/all")
    public List<Map> getSqlManagerAll(){
        List<Map> list = indicator2016SQLManager.select("indicator2016.selectAll",Map.class);
        return list;
    }

    @GetMapping("repository/all")
    public List<Map> getRepositoryAll(){
        List<Map> list = indicator2016Repository.selectAll();
        return list;
    }

}
