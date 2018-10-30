package com.ali.controller;

import com.ali.repository.master2017.Master2017Repository;
import io.swagger.annotations.Api;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("master2017")
public class Master2017Controller {

    @Autowired
    Master2017Repository master2017Repository;

    @Autowired
    SQLManager master2017SQLManager;

    @GetMapping("sqlmanager/all")
    public List<Map> getSqlManagerAll(){
        List<Map> list = master2017SQLManager.select("master2017.selectAll",Map.class);
        return list;
    }

    @GetMapping("repository/all")
    public List<Map> getRepositoryAll(){
        List<Map> list = master2017Repository.selectAll();
        return list;
    }

}
