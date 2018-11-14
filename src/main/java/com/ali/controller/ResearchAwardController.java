package com.ali.controller;

import com.ali.service.ResearchAwardService;
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
@RequestMapping("researchAward")
@Api(tags = "ResearchAward")
public class ResearchAwardController {

    @Autowired
    ResearchAwardService researchAwardService;

    @GetMapping("prize")
    @ApiOperation(value = "获奖统计")
    public Map<String,Map<String,Map<String,Object>>> 获奖统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return researchAwardService.get获奖统计(paras);
    }
}
