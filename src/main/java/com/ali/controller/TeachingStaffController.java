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
}
