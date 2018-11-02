package com.ali.controller;

import com.ali.service.SubjectDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("subjectDataView")
@Api(tags = "SubjectData")
public class SubjectDataController {

    @Autowired
    SubjectDataService subjectDataService;

    @GetMapping("allSubject")
    @ApiOperation(value = "获取学科列表")
    public List<Map> getAllSubject(){
        List<Map> subjects = subjectDataService.getAllSubject();
        return subjects;
    }

    @GetMapping("subjectAssessment/{subjectCode}")
    @ApiOperation(value = "学科评估结果")
    public Map getSubjectAssessment(@PathVariable("subjectCode") String subjectCode){
        String code = subjectCode.substring(0,4);
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        List<Map> subjects = subjectDataService.getSubjectAssessment(map);
        return subjects.get(0);
    }

    @GetMapping("subjectSZJG")
    @ApiOperation(value = "学科师资结构数据")
    public Map<String,Map<String,Map<String,Object>>> get师资结构数据(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectDataService.get师资结构数据(paras);
    }

}
