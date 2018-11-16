package com.ali.controller;

import com.ali.entity.Option;
import com.ali.service.TopService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class TopController {

    public List<Option> invokeOtpitonMethod(TopService service,String methodName,Map<String,Object> paras){
        List<Option> result = null;
        try{
            if(paras.isEmpty()){
                result = (List<Option>) service.getClass().getMethod(methodName,new Class[]{}).invoke(service,new Object[]{});
            }else{
                result = (List<Option>) service.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(service,new Object[]{paras});
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

    public List<Map> invokeAnalysisMethod(TopService service,String methodName,Map<String,Object> paras){
        List<Map> result = null;
        try{
            if(paras.isEmpty()){
                result = (List<Map>) service.getClass().getMethod(methodName,new Class[]{}).invoke(service,new Object[]{});
            }else{
                result = (List<Map>) service.getClass().getMethod(methodName,new Class[]{Map.class}).invoke(service,new Object[]{paras});
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
