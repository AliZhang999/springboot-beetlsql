package com.ali.controller;

import com.ali.service.SubjectResearchService;
import com.ali.service.TeachingStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("subjectResearch")
@Api(tags = "SubjectResearch")
public class SubjectResearchController {

    @Autowired
    SubjectResearchService subjectResearchService;

    @GetMapping("teacherPatent")
    @ApiOperation(value = "教师专利授权统计")
    public Map<String,Map<String,Map<String,Object>>> 教师专利授权统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectResearchService.get教师专利授权统计(paras);
    }

    @GetMapping("teachingMaterial")
    @ApiOperation(value = "教师主编专业教材统计")
    public Map<String,Map<String,Map<String,Object>>> 教师主编专业教材统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectResearchService.get教师主编专业教材统计(paras);
    }

    @GetMapping("teachingResearchProject")
    @ApiOperation(value = "教师主持科研项目统计")
    public Map<String,Map<String,Map<String,Object>>> 教师主持科研项目统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectResearchService.get教师主持科研项目统计(paras);
    }

    @GetMapping("teachingPublishingMonographs")
    @ApiOperation(value = "教师出版专著统计")
    public Map<String,Map<String,Map<String,Object>>> 教师出版专著统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectResearchService.get教师出版专著统计(paras);
    }

    @GetMapping("teachingPublishingPapers")
    @ApiOperation(value = "教师发表论文统计")
    public Map<String,Map<String,Map<String,Object>>> 教师发表论文统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return subjectResearchService.get教师发表论文统计(paras);
    }
}
