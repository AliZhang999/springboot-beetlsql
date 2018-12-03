package com.ali.service;

import com.ali.bean.subjectanalysis.subjectoverview.JYBSubjectAssetmentResult;
import com.ali.bean.subjectanalysis.subjectoverview.SubjectRanking;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePageService extends TopService {

    public Map<String, List<Map>> getSchoolTotalViewData(Map<String,Object> paras){
        Map<String,List<Map>> getMajorInfo = new HashMap<>();

        getMajorInfo.put("高校情况",getDataByMethodNameWithParams("get高校情况",paras));
        getMajorInfo.put("教学成果",getDataByMethodNameWithParams("get教学成果",paras));
        getMajorInfo.put("办学条件",getDataByMethodNameWithParams("get办学条件",paras));
        getMajorInfo.put("学生情况",getDataByMethodNameWithParams("get学生情况",paras));
        getMajorInfo.put("师资情况",getDataByMethodNameWithParams("get师资情况",paras));
        getMajorInfo.put("学科专业",getDataByMethodNameWithParams("get学科专业",paras));
        getMajorInfo.put("科研水平",getDataByMethodNameWithParams("get科研水平",paras));

        return getMajorInfo;
    }

    public List<Map> getDZDWDrillData(Map<String,Object> paras){
        return getDataByMethodNameWithParams("getDZDWDrillData",paras);
    }

    public List<Map> getJXDWDrillData(Map<String,Object> paras){
        return getDataByMethodNameWithParams("getJXDWDrillData",paras);
    }

    public List<Map> getZDXKDrillData(Map<String,Object> paras){
        return getDataByMethodNameWithParams("getZDXKDrillData",paras);
    }

    public List<Map> getGJJJYJXYJYGGXMDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getGJJJYJXYJYGGXMDrillData",paras);
    }

    public List<Map> getSJBKJXGCDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSJBKJXGCDrillData",paras);
    }

    public List<Map> getGJJBKJXGCDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getGJJBKJXGCDrillData",paras);
    }

    public List<Map> getQTJSGJJSJYRCXCYJDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getQTJSGJJSJYRCXCYJDDrillData",paras);
    }

    public List<Map> getRCPYMSCXSYQDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getRCPYMSCXSYQDrillData",paras);
    }

    public List<Map> getGJJJXJDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getGJJJXJDDrillData",paras);
    }

    public List<Map> getZLZZQDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getZLZZQDrillData",paras);
    }

    public List<Map> getCBZZDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getCBZZDrillData",paras);
    }

    public List<Map> getFBLWDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getFBLWDrillData",paras);
    }

    public List<Map> getZXXMDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getZXXMDrillData",paras);
    }

    public List<Map> getHXXMDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getHXXMDrillData",paras);
    }

    public List<Map> getGCCRCDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getGCCRCDrillData",paras);
    }

    public List<Map> getKSZYDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKSZYDrillData",paras);
    }

    public List<Map> getFGXKMLDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getFGXKMLDrillData",paras);
    }

    public List<Map> getBSXWSQYJXKDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBSXWSQYJXKDDrillData",paras);
    }

    public List<Map> getBSXWSQEJXKDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBSXWSQEJXKDDrillData",paras);
    }

    public List<Map> getSSXWSQYJXKDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSSXWSQYJXKDDrillData",paras);
    }

    public List<Map> getSSXWSQEJXKDDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSSXWSQEJXKDDrillData",paras);
    }

    public List<Map> getBSHLDZDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBSHLDZDrillData",paras);
    }

    public List<Map> getZXSSDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getZXSSDrillData",paras);
    }

    public List<Map> getJZGZGXWDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJZGZGXWDrillData",paras);
    }

    public List<Map> getJZGZYJSZCDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJZGZYJSZCDrillData",paras);
    }

    public List<Map> getZZDMJDrillData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getZZDMJDrillData",paras);
    }

    public List<Map> getBXTJJYJFZECmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJJYJFZECmpData",paras);
    }

    public List<Map> getBXTJJXYQSBZCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJJXYQSBZCmpData",paras);
    }

    public List<Map> getBXTJJXJFZECmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJJXJFZECmpData",paras);
    }

    public List<Map> getBXTJJXJFZZCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJJXJFZZCmpData",paras);
    }

    public List<Map> getBXTJSJJXJFCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJSJJXJFCmpData",paras);
    }

    public List<Map> getBXTJGDZCZZCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getBXTJGDZCZZCmpData",paras);
    }

    public List<Map> getXKZYBSXWSQYJXKDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYBSXWSQYJXKDCmpData",paras);
    }

    public List<Map> getXKZYBSXWSQEJXKDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYBSXWSQEJXKDCmpData",paras);
    }

    public List<Map> getXKZYSSXWSQYJXKDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYSSXWSQYJXKDCmpData",paras);
    }

    public List<Map> getXKZYSSXWSQEJXKDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYSSXWSQEJXKDCmpData",paras);
    }

    public List<Map> getXKZYBSHLDZCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYBSHLDZCmpData",paras);
    }

    public List<Map> getXSQKBKSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKBKSCmpData",paras);
    }

    public List<Map> getXSQKZKSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKZKSCmpData",paras);
    }

    public List<Map> getXSQKSSYJSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKSSYJSCmpData",paras);
    }

    public List<Map> getXSQKBSYJSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKBSYJSCmpData",paras);
    }

    public List<Map> getXSQKLXSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKLXSCmpData",paras);
    }

    public List<Map> getXSQKSJLQSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKSJLQSCmpData",paras);
    }

    public List<Map> getXSQKJYSSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKJYSSCmpData",paras);
    }

    public List<Map> getXSQKBYSSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXSQKBYSSCmpData",paras);
    }

    public List<Map> getKYSPZLZZQSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPZLZZQSCmpData",paras);
    }

    public List<Map> getKYSPCBZZSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPCBZZSCmpData",paras);
    }

    public List<Map> getKYSPFBLWSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPFBLWSCmpData",paras);
    }

    public List<Map> getKYSPZXXMSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPZXXMSCmpData",paras);
    }

    public List<Map> getKYSPHXXMSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPHXXMSCmpData",paras);
    }

    public List<Map> getKYSPHXXMJFCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPHXXMJFCmpData",paras);
    }

    public List<Map> getKYSPZXXMJFCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getKYSPZXXMJFCmpData",paras);
    }

    public List<Map> getJXCGGJJJXCGJCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGGJJJXCGJCmpData",paras);
    }

    public List<Map> getJXCGGJJJYJXYJYGGXMCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGGJJJYJXYJYGGXMCmpData",paras);
    }

    public List<Map> getJXCGGJJBKJXGCCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGGJJBKJXGCCmpData",paras);
    }

    public List<Map> getJXCGSBJBKJXGCCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGSBJBKJXGCCmpData",paras);
    }

    public List<Map> getJXCGRCPYMSCXSYQCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGRCPYMSCXSYQCmpData",paras);
    }

    public List<Map> getJXCGGJJJXJDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGGJJJXJDCmpData",paras);
    }

    public List<Map> getJXCGGJJSJYRCXCYJDCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getJXCGGJJSJYRCXCYJDCmpData",paras);
    }

    public List<Map> getSZQKJZGSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKJZGSCmpData",paras);
    }

    public List<Map> getSZQKSYSZCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKSYSZCmpData",paras);
    }

    public List<Map> getSZQKGCCRCCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKGCCRCCmpData",paras);
    }

    public List<Map> getSZQKJSSTJKBLCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKJSSTJKBLCmpData",paras);
    }

    public List<Map> getSZQKZRJSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKZRJSCmpData",paras);
    }

    public List<Map> getSZQKBSFGLCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKBSFGLCmpData",paras);
    }

    public List<Map> getSZQKSSBCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getSZQKSSBCmpData",paras);
    }

    public List<Map> getXKZYBKZYZSCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYBKZYZSCmpData",paras);
    }

    public List<Map> getXKZYZDXKCmpData(Map<String, Object> paras) {
        return getDataByMethodNameWithParams("getXKZYZDXKCmpData",paras);
    }

}
