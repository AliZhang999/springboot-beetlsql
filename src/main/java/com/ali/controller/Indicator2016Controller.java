package com.ali.controller;

import com.ali.repository.indicator2016.Indicator2016Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @GetMapping("sqlmanager/专业评估七学生基本情况data")
    public String[] getSqlManager专业评估$七学生发展$7_1学生基本情况data(){
        List<Map> data = indicator2016Repository.selectAll();
        String[] strings = new String[data.size()];
        for (int i=0;i<data.size();i++) {
            String a = (String) data.get(i).get("data");
            strings[i]=a;
        }
        return strings;
    }

    /*@GetMapping("repository/专业评估七学生基本情况data")
    public List<Map> getRepository专业评估$七学生发展$7_1学生基本情况data(){
        List<Map> list = indicator2016Repository.select专业评估$七学生发展$7_1学生基本情况data();
        return list;
    }*/

}
