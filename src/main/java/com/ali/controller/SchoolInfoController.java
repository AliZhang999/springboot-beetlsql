package com.ali.controller;

import com.ali.service.SchoolInfoService;
import com.ali.service.TeacherInfoService;
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
@RequestMapping("schoolInfo")
@Api(tags = "SchoolInfo")
public class SchoolInfoController {

    @Autowired
    SchoolInfoService schoolInfoService;

    @GetMapping("getSchoolInfo")
    @ApiOperation(value = "学校画像")
    public Map<String,Map> getTeacherInfo(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        Map<String,Map> teacherInfo = schoolInfoService.getSchoolInfo(paras);
        return teacherInfo;
    }
}
