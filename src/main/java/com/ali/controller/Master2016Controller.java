package com.ali.controller;

import com.ali.repository.master2016.Master2016Repository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("master2016")
public class Master2016Controller {

    @Autowired
    Master2016Repository master2016Repository;

    @Autowired
    SQLManager master2016SQLManager;

    @GetMapping("sqlmanager/all")
    public long getSqlManagerAll(){
        long i = master2016SQLManager.allCount(Object.class);
        return i;
    }

    @GetMapping("repository/all")
    public int getRepositoryAll(){
        int i = master2016Repository.selectAll();
        return i;
    }

}
