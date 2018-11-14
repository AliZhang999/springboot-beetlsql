package com.ali.controller;

import com.ali.service.PersonnelTrainingService;
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
@RequestMapping("personnelTraining")
@Api(tags = "PersonnelTraining")
public class PersonnelTrainingController {

    @Autowired
    PersonnelTrainingService personnelTrainingService;

    @GetMapping("studentSource")
    @ApiOperation(value = "生源统计")
    public Map<String,Map<String,Map<String,Map<String,Object>>>> 生源统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get生源统计(paras);
    }

    @GetMapping("studentInfo")
    @ApiOperation(value = "学生情况")
    public Map<String, Map<String, Map<String, Object>>> 学生情况(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get学生情况(paras);
    }

    @GetMapping("schoolResults")
    @ApiOperation(value = "在校成果")
    public Map<String, Map<String, Map<String, Object>>> 在校成果(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get在校成果(paras);
    }

    @GetMapping("foreignStudent")
    @ApiOperation(value = "留学生统计")
    public Map<String,Map<String,Map<String,Object>>> 留学生统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get留学生统计(paras);
    }

    @GetMapping("studentExchange")
    @ApiOperation(value = "学生交流统计")
    public Map<String,Map<String,Map<String,Object>>> 学生交流统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get学生交流统计(paras);
    }

    @GetMapping("graduate")
    @ApiOperation(value = "毕业生统计")
    public Map<String,Map<String,Map<String,Object>>> 毕业生统计(String years){
        Map<String,Object> paras = new HashMap<>();
        paras.put("years",years);
        return personnelTrainingService.get毕业生统计(paras);
    }

}
