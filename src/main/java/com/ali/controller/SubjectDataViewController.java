package com.ali.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("subjectDataView")
@Api(tags = "SubjectData")
public class SubjectDataViewController {

    @Autowired
    SQLManager baseSQLManager;

    @Autowired
    SQLManager master2016SQLManager;

    @Autowired
    SQLManager master2017SQLManager;

    @Autowired
    SQLManager indicator2016SQLManager;

    @GetMapping("allSubject")
    @ApiOperation(value = "获取学科列表")
    public List<Map> getAllSubject(){
        List<Map> subjects = master2017SQLManager.select("base.allSubject", Map.class);
        return subjects;
    }

    @GetMapping("subjectAssessment/{subjectCode}")
    @ApiOperation(value = "学科评估结果")
    public Map getSubjectAssessment(@PathVariable("subjectCode") String subjectCode){
        String code = subjectCode.substring(0,4);
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        List<Map> subjects = baseSQLManager.select("base.subjectAssessment", Map.class, map);
        return subjects.get(0);
    }

    @GetMapping("subjectSZJG/{subjectCode}")
    @ApiOperation(value = "学科师资结构数据")
    public Object getsubjectSZJG(@PathVariable("subjectCode") String subjectCode){
        String code = subjectCode.substring(0,4);

        String project = "专业评估";
        String namespace = "四教师队伍";
        String name = "4.2.2专业课校内授课教师结构分析";

        String tableName = StringUtils.join(new String[] { project, namespace, name }, '$').replaceAll("\\.", "_");

        Map<String,Object> paras = new HashMap<>();
        paras.put("tableName",tableName);
        paras.put("year",2017);
        paras.put("schoolCode",10593);

        Map data = new HashMap();

        List<Map> maps = indicator2016SQLManager.select("base.indicatorData", Map.class, paras);
        String script = maps.get(0).get("data").toString();
        Map map = new Gson().fromJson(script, Map.class);
        for (Object key : map.keySet()) {
            Object o = map.get(key);
            System.out.println(key+":=====>"+o);
        }

        /*if(obj instanceof List){
            List<Map> list = (List<Map>) obj;
            for(Map o : list){
                if(o.get("专业代码").toString().equals(subjectCode)){
                    data.put("schoolData", o);
                    break;
                }
            }
        }*/
        
        return data;
    }


    @GetMapping("allUser")
    public List<Map> getAllUser(){
        Map<String, Object> paras = new HashMap<>();
        paras.put("name","aaa");
        List<Map> select = baseSQLManager.select("base.selectAll", Map.class, paras);
        return select;
    }

}
