package com.ali.controller;

import com.ali.service.PlatformBuildingService;
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
@RequestMapping("platformBuilding")
@Api(tags = "PlatformBuilding")
public class PlatformBuildingController {

    @Autowired
    PlatformBuildingService platformBuildingService;

    @GetMapping("getTeacherList")
    @ApiOperation(value = "学科平台")
    public Map<String,Map<String, Map<String,Object>>> getTeacherList(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        Map<String,Map<String, Map<String,Object>>> subjectPlatformList = platformBuildingService.getSubjectPlatformList(paras);
        return subjectPlatformList;
    }

}
