package com.ali.controller;

import com.ali.service.MajorInfoService;
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
@RequestMapping("majorInfo")
@Api(tags = "MajorInfo")
public class MajorInfoController {

    @Autowired
    MajorInfoService majorInfoService;

    @GetMapping("getMajorList")
    @ApiOperation(value = "专业列表")
    public List<Map> getMajorList(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> majorList = majorInfoService.getMajorList(paras);
        return majorList;
    }

    @GetMapping("getMajorInfo")
    @ApiOperation(value = "专业画像")
    public Map<String, List<Map>> getMajorInfo(String year, String majorCode){
        Map<String,Object> paras = new HashMap<>();
        paras.put("majorCode", majorCode);
        paras.put("year",year);
        Map<String, List<Map>> majorInfo = majorInfoService.getMajorInfo(paras);
        return majorInfo;
    }
}
