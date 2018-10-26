package com.ali.controller;

import com.ali.repository.master2016.Master2016Repository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("master2016")
public class Master2016Controller {

    @Autowired
    Master2016Repository master2016Repository;

    @Autowired
    SQLManager master2016SQLManager;

    @GetMapping("sqlmanager/all")
    public List<Map> getSqlManagerAll(){
        List<Map> list = master2016SQLManager.select("master2016.selectAll",Map.class);
        return list;
    }

    @GetMapping("repository/all")
    public List<Map> getRepositoryAll(){
        List<Map> list = master2016Repository.selectAll();
        return list;
    }

}
