package com.ali.controller;

import com.ali.service.TeachingStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("teachingStaff")
@Api(tags = "TeachingStaff")
public class TeachingStaffController {

    @Autowired
    TeachingStaffService teachingStaffService;

    @GetMapping("higherLevelTalentsCount")
    @ApiOperation(value = "高层次人才统计")
    public List<Map> 高层次人才统计(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        return teachingStaffService.get高层次人才统计(paras);
    }
}
