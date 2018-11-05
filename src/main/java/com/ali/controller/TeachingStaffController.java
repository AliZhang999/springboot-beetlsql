package com.ali.controller;

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
@RequestMapping("teachingStaff")
@Api(tags = "TeachingStaff")
public class TeachingStaffController {

    @Autowired
    TeachingStaffService teachingStaffService;

    @GetMapping("higherLevelTalentsCount")
    @ApiOperation(value = "高层次人才统计")
    public Map<String,Map<String,Map<String,Object>>> 高层次人才统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get高层次人才统计(paras);
    }

    @GetMapping("fullTimeTeacher")
    @ApiOperation(value = "专职教师统计")
    public Map<String,Map<String,Map<String,Object>>> 专职教师统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get专职教师统计(paras);
    }

    @GetMapping("teachingTeam")
    @ApiOperation(value = "教学团队统计")
    public Map<String,Map<String,Map<String,Object>>> 教学团队统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get教学团队统计(paras);
    }

    @GetMapping("studentSource")
    @ApiOperation(value = "生源统计")
    public Map<String,Map<String,Map<String,Map<String,Object>>>> 生源统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get生源统计(paras);
    }

    @GetMapping("foreignStudent")
    @ApiOperation(value = "留学生统计")
    public Map<String,Map<String,Map<String,Object>>> 留学生统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get留学生统计(paras);
    }

    @GetMapping("studentExchange")
    @ApiOperation(value = "学生交流统计")
    public Map<String,Map<String,Map<String,Object>>> 学生交流统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get学生交流统计(paras);
    }

    @GetMapping("graduate")
    @ApiOperation(value = "毕业生统计")
    public Map<String,Map<String,Map<String,Object>>> 毕业生统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get毕业生统计(paras);
    }

    @GetMapping("prize")
    @ApiOperation(value = "获奖统计")
    public Map<String,Map<String,Map<String,Object>>> 获奖统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get获奖统计(paras);
    }

    @GetMapping("teacherPatent")
    @ApiOperation(value = "教师专利授权统计")
    public Map<String,Map<String,Map<String,Object>>> 教师专利授权统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return teachingStaffService.get教师专利授权统计(paras);
    }
}
