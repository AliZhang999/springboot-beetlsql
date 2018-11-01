package com.ali.controller;

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
@RequestMapping("teacherInfo")
@Api(tags = "TeacherInfo")
public class TeacherInfoController {

    @Autowired
    TeacherInfoService teacherInfoService;

    @GetMapping("getTeacherInfo")
    @ApiOperation(value = "教师画像")
    public Map<String, List<Map>> getTeacherInfo(String year, String teacherCode){
        Map<String,Object> paras = new HashMap<>();
        paras.put("teacherCode", teacherCode);
        paras.put("year",year);
        Map<String, List<Map>> teacherInfo = teacherInfoService.getTeacherInfo(paras);
        return teacherInfo;
    }
}
