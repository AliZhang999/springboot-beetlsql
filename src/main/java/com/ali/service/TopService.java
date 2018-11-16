package com.ali.service;

import com.ali.dao.BaseDao;
import com.ali.dao.Indicator2016Dao;
import com.ali.dao.Master2016Dao;
import com.ali.dao.Master2017Dao;
import com.ali.entity.Option;
import com.ali.util.DatabaseDataUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopService {

    @Autowired
    BaseDao baseDao;

    @Autowired
    Master2016Dao master2016Dao;

    @Autowired
    Master2017Dao master2017Dao;

    @Autowired
    Indicator2016Dao indicator2016Dao;

    public Map<String,Map<String, Map<String,Object>>> backSubjectData(Map<String, Object> paras, String[] items, String methodName) {
        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            //如果对应的sql不需要参数时，给paras中增加一个标志位字段：sqlNoParam="true"
            paras.put("sqlNoParam","true");
            paras.put("year",year);
            List<Map> result = getDataByMethodNameWithParams(methodName,paras);

            Map<String,Map<String,Object>> data = new HashMap<>();
            endData.put(year, DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public Map<String,Map<String, Map<String,Object>>> backSubjectDataWithParam(Map<String, Object> paras, String[] items, String methodName) {
        String years = paras.get("years").toString();
        String[] yearArr = years.split(",");

        Map<String,Map<String,Map<String,Object>>> endData = new HashMap<>();

        for (String year : yearArr) {
            paras.put("year",year);
            List<Map> result = getDataByMethodNameWithParams(methodName,paras);

            Map<String,Map<String,Object>> data = new HashMap<>();
            endData.put(year, DatabaseDataUtil.analysisMajorDataBySubject(result,data,items));
        }

        return endData;
    }

    public List<Map> getDataByMethodNameWithParams(String methodName,Map<String,Object> paras){
        String year = paras.get("year").toString();
        List<Map> result = null;
        try {
            if (year.equals("2016")){
                if(paras.get("sqlNoParam") != null){
                    result = (List<Map>) master2016Dao.getClass().getMethod(methodName,new Class[]{}).invoke(master2016Dao,new Object[]{});
                }else{
                    result = (List<Map>) master2016Dao.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(master2016Dao,new Object[]{paras});
                }
            }else if (year.equals("2017")){
                if(paras.get("sqlNoParam") != null){
                    result = (List<Map>) master2017Dao.getClass().getMethod(methodName,new Class[]{}).invoke(master2017Dao,new Object[]{});
                }else{
                    result = (List<Map>) master2017Dao.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(master2017Dao,new Object[]{paras});
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
        根据tablename获取indicator2016数据库中的数据
     */
    public Map getIndicatorDataByTableName(Map<String,Object> paras){
        List<Map> maps = indicator2016Dao.getIndicatorDataByTableName(paras);
        String script = maps.get(0).get("data").toString();
        Map map = new Gson().fromJson(script, Map.class);
        return map;
    }

    public List<Option> getGroupTypeListByMethodName(String methodName, Map<String,Object> paras){
        List<Option> result = null;
        int year = Integer.parseInt(paras.get("year").toString());
        try{
            if(year == 2016){
                result = (List<Option>) master2016Dao.getClass().getMethod(methodName,new Class[]{}).invoke(master2016Dao,new Object[]{});
            }else if(year == 2017){
                result = (List<Option>) master2017Dao.getClass().getMethod(methodName,new Class[]{}).invoke(master2017Dao,new Object[]{});
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Map> getAnalysisIndicationsListByMethodName(String methodName, Map<String,Object> paras){
        List<Map> result = null;
        int year = Integer.parseInt(paras.get("year").toString());
        try{
            if(year == 2016){
                result = (List<Map>) master2016Dao.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(master2016Dao,new Object[]{paras});
            }else if(year == 2017){
                result = (List<Map>) master2017Dao.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(master2017Dao,new Object[]{paras});
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
