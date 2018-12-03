package com.ali.dao;

import com.ali.config.MutipleSqlManager;
import com.ali.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Master2017_14schoolDao {

    @Autowired
    MutipleSqlManager mutipleSqlManager;

    public List<Map> getBXTJJYJFZECmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件教育经费总额对标",Map.class,paras);
    }

    public List<Map> getBXTJJXYQSBZCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件教学仪器设备值对标",Map.class,paras);
    }

    public List<Map> getBXTJJXJFZECmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件教学经费总额对标",Map.class,paras);
    }

    public List<Map> getBXTJJXJFZZCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件教学经费支出对标",Map.class,paras);
    }

    public List<Map> getBXTJSJJXJFCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件实践教学经费对标",Map.class,paras);
    }

    public List<Map> getBXTJGDZCZZCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页办学条件固定资产总值对标",Map.class,paras);
    }

    public List<Map> getXKZYBSXWSQYJXKDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业博士学位授权一级学科点对标",Map.class,paras);
    }

    public List<Map> getXKZYBSXWSQEJXKDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业博士学位授权二级学科点对标",Map.class,paras);
    }

    public List<Map> getXKZYSSXWSQYJXKDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业硕士学位授权一级学科点对标",Map.class,paras);
    }

    public List<Map> getXKZYSSXWSQEJXKDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业硕士学位授权二级学科点对标",Map.class,paras);
    }

    public List<Map> getXKZYBSHLDZCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业博士后流动站对标",Map.class,paras);
    }

    public List<Map> getXKZYBKZYZSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业本科专业总数对标",Map.class,paras);
    }

    public List<Map> getXKZYZDXKCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学科专业重点学科对标",Map.class,paras);
    }

    public List<Map> getXSQKBKSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况本科生对标",Map.class,paras);
    }

    public List<Map> getXSQKZKSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况专科生对标",Map.class,paras);
    }

    public List<Map> getXSQKSSYJSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况硕士研究生对标",Map.class,paras);
    }

    public List<Map> getXSQKBSYJSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况博士研究生对标",Map.class,paras);
    }

    public List<Map> getXSQKLXSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况留学生对标",Map.class,paras);
    }

    public List<Map> getXSQKSJLQSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况实际录取数对标",Map.class,paras);
    }

    public List<Map> getXSQKJYSSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况就业生数对标",Map.class,paras);
    }

    public List<Map> getXSQKBYSSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页学生情况毕业生数对标",Map.class,paras);
    }

    public List<Map> getKYSPZLZZQSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平专利著作权数对标",Map.class,paras);
    }

    public List<Map> getKYSPCBZZSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平出版专著数对标",Map.class,paras);
    }

    public List<Map> getKYSPFBLWSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平发表论文数对标",Map.class,paras);
    }

    public List<Map> getKYSPZXXMSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平纵向项目数对标",Map.class,paras);
    }

    public List<Map> getKYSPHXXMSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平横向项目数对标",Map.class,paras);
    }

    public List<Map> getKYSPHXXMJFCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平横向项目经费对标",Map.class,paras);
    }

    public List<Map> getKYSPZXXMJFCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页科研水平纵向项目经费对标",Map.class,paras);
    }

    public List<Map> getJXCGGJJJXCGJCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果国家级教学成果奖对标",Map.class,paras);
    }

    public List<Map> getJXCGGJJJYJXYJYGGXMCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果国家级教育教学研究与改革项目对标",Map.class,paras);
    }

    public List<Map> getJXCGGJJBKJXGCCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果国家级本科教学工程对标",Map.class,paras);
    }

    public List<Map> getJXCGSBJBKJXGCCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果省部级本科教学工程对标",Map.class,paras);
    }

    public List<Map> getJXCGRCPYMSCXSYQCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果人才培养模式创新实验区对标",Map.class,paras);
    }

    public List<Map> getJXCGGJJJXJDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果国家级教学基地对标",Map.class,paras);
    }

    public List<Map> getJXCGGJJSJYRCXCYJDCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页教学成果牵头建设国家级实践育人创新创业基地对标",Map.class,paras);
    }

    public List<Map> getSZQKJZGSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页师资情况教职工数对标",Map.class,paras);
    }

    public List<Map> getSZQKSYSZCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页师资情况实验师资对标",Map.class,paras);
    }

    public List<Map> getSZQKGCCRCCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页师资情况高层次人才对标",Map.class,paras);
    }

    public List<Map> getSZQKJSSTJKBLCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("common.首页师资情况教授上台讲课比例对标",Map.class,paras);
    }

    public List<Map> getSZQKZRJSCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("master2017.首页师资情况专任教师对标",Map.class,paras);
    }

    public List<Map> getSZQKBSFGLCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("master2017.首页师资情况博硕覆盖率对标",Map.class,paras);
    }

    public List<Map> getSZQKSSBCmpData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2017_14schoolSQLManager().select("master2017.首页师资情况生师比对标",Map.class,paras);
    }

}
