package com.ali.controller;

import com.ali.service.HomePageService;
import com.ali.service.MajorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("homePage")
@Api(tags = "HomePage")
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @GetMapping("schoolTotalView")
    @ApiOperation(value = "首页")
    public Map<String, List<Map>> getSchoolViewData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        Map<String, List<Map>> data = homePageService.getSchoolTotalViewData(paras);
        return data;
    }

    @GetMapping("GXQK/DZDWDrill")
    @ApiOperation(value = "首页/高校情况/党政单位钻取")
    public List<Map> getDZDWDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getDZDWDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/JXDWDrill")
    @ApiOperation(value = "首页/高校情况/教学单位钻取")
    public List<Map> getJXDWDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getJXDWDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/ZDXKDrill")
    @ApiOperation(value = "首页/高校情况/重点学科钻取")
    public List<Map> getZDXKDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getZDXKDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/ZXSSDrill")
    @ApiOperation(value = "首页/高校情况/在校生数钻取")
    public List<Map> getZXSSDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getZXSSDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/JZGZGXWDrill")
    @ApiOperation(value = "首页/高校情况/教职工总数最高学位钻取")
    public List<Map> getJZGZGXWDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getJZGZGXWDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/JZGZYJSZCDrill")
    @ApiOperation(value = "首页/高校情况/职工总数专业技术职称钻取")
    public List<Map> getJZGZTJSZCDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getJZGZYJSZCDrillData(paras);
        return data;
    }

    @GetMapping("GXQK/ZZDMJDrill")
    @ApiOperation(value = "首页/高校情况/总占地面积钻取")
    public List<Map> getZZDMJDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getZZDMJDrillData(paras);
        return data;
    }

    /*@GetMapping("JXCG/GJJJYJXYJYGGXMDrill")
    @ApiOperation(value = "首页/教学成果/国家级教育教学研究与改革项目钻取")
    public List<Map> getGJJJYJXYJYGGXMDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getGJJJYJXYJYGGXMDrillData(paras);
        return data;
    }

    @GetMapping("JXCG/SJBKJXGCDrill")
    @ApiOperation(value = "首页/教学成果/省级本科教学工程钻取")
    public List<Map> getSJBKJXGCDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getSJBKJXGCDrillData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJBKJXGCDrill")
    @ApiOperation(value = "首页/教学成果/国家级本科教学工程钻取")
    public List<Map> getGJJBKJXGCDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getGJJBKJXGCDrillData(paras);
        return data;
    }

    @GetMapping("JXCG/QTJSGJJSJYRCXCYJDDrill")
    @ApiOperation(value = "首页/教学成果/牵头建设国家级实践育人创新创业基地钻取")
    public List<Map> getQTJSGJJSJYRCXCYJDDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getQTJSGJJSJYRCXCYJDDrillData(paras);
        return data;
    }

    @GetMapping("JXCG/RCPYMSCXSYQDrill")
    @ApiOperation(value = "首页/教学成果/人才培养模式创新实验区钻取")
    public List<Map> getRCPYMSCXSYQDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getRCPYMSCXSYQDrillData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJJXJDDrill")
    @ApiOperation(value = "首页/教学成果/国家级教学基地钻取")
    public List<Map> getGJJJXJDDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("sqlNoParam","true");
        List<Map> data = homePageService.getGJJJXJDDrillData(paras);
        return data;
    }

    @GetMapping("KYSP/ZLZZQDrill")
    @ApiOperation(value = "首页/科研水平/专利著作权钻取")
    public List<Map> getZLZZQDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getZLZZQDrillData(paras);
        return data;
    }

    @GetMapping("KYSP/CBZZDrill")
    @ApiOperation(value = "首页/科研水平/出版专著钻取")
    public List<Map> getCBZZDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getCBZZDrillData(paras);
        return data;
    }

    @GetMapping("KYSP/FBLWDrill")
    @ApiOperation(value = "首页/科研水平/发表论文钻取")
    public List<Map> getFBLWDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getFBLWDrillData(paras);
        return data;
    }

    @GetMapping("KYSP/ZXXMDrill")
    @ApiOperation(value = "首页/科研水平/纵向项目钻取")
    public List<Map> getZXXMDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getZXXMDrillData(paras);
        return data;
    }

    @GetMapping("KYSP/HXXMDrill")
    @ApiOperation(value = "首页/科研水平/横向项目钻取")
    public List<Map> getHXXMDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getHXXMDrillData(paras);
        return data;
    }

    @GetMapping("SZQK/GCCRCDrill")
    @ApiOperation(value = "首页/师资情况/高层次人才钻取")
    public List<Map> getGCCRCDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getGCCRCDrillData(paras);
        return data;
    }

    @GetMapping("XKZY/KSZYDrill")
    @ApiOperation(value = "首页/学科专业/开设专业钻取")
    public List<Map> getKSZYDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getKSZYDrillData(paras);
        return data;
    }

    @GetMapping("XKZY/FGXKMLDrill")
    @ApiOperation(value = "首页/学科专业/覆盖学科门类钻取")
    public List<Map> getFGXKMLDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getFGXKMLDrillData(paras);
        return data;
    }*/

    @GetMapping("XKZY/BSXWSQYJXKDCmp")
    @ApiOperation(value = "首页/学科专业/博士学位授权一级学科点对标")
    public List<Map> getBSXWSQYJXKDDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYBSXWSQYJXKDCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/BSXWSQEJXKDCmp")
    @ApiOperation(value = "首页/学科专业/博士学位授权二级学科点对标")
    public List<Map> getXKZYBSXWSQEJXKDCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYBSXWSQEJXKDCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/SSXWSQYJXKDCmp")
    @ApiOperation(value = "首页/学科专业/硕士学位授权一级学科点对标")
    public List<Map> getXKZYSSXWSQYJXKDCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYSSXWSQYJXKDCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/SSXWSQEJXKDCmp")
    @ApiOperation(value = "首页/学科专业/硕士学位授权二级学科点对标")
    public List<Map> getXKZYSSXWSQEJXKDCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYSSXWSQEJXKDCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/BSHLDZCmp")
    @ApiOperation(value = "首页/学科专业/博士后流动站对标")
    public List<Map> getXKZYBSHLDZCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYBSHLDZCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/BKZYZSCmp")
    @ApiOperation(value = "首页/学科专业/本科专业总数对标")
    public List<Map> getXKZYBKZYZSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYBKZYZSCmpData(paras);
        return data;
    }

    @GetMapping("XKZY/ZDXKCmp")
    @ApiOperation(value = "首页/学科专业/重点学科对标")
    public List<Map> getXKZYZDXKCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXKZYZDXKCmpData(paras);
        return data;
    }

    /*@GetMapping("XKZY/SSXWSQYJXKDDrill")
    @ApiOperation(value = "首页/学科专业/硕士学位授权一级学科点钻取")
    public List<Map> getSSXWSQYJXKDDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getSSXWSQYJXKDDrillData(paras);
        return data;
    }

    @GetMapping("XKZY/SSXWSQEJXKDDrill")
    @ApiOperation(value = "首页/学科专业/硕士学位授权二级学科点钻取")
    public List<Map> getSSXWSQEJXKDDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getSSXWSQEJXKDDrillData(paras);
        return data;
    }

    @GetMapping("XKZY/BSHLDZDrill")
    @ApiOperation(value = "首页/学科专业/博士后流动站钻取")
    public List<Map> getBSHLDZDrillData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        List<Map> data = homePageService.getBSHLDZDrillData(paras);
        return data;
    }*/

    @GetMapping("BXTJ/JXYQSBZCmp")
    @ApiOperation(value = "首页/办学条件/教学仪器设备值对标")
    public List<Map> getBXTJJXYQSBZCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJJXYQSBZCmpData(paras);
        return data;
    }

    @GetMapping("BXTJ/JYJFZECmp")
    @ApiOperation(value = "首页/办学条件/教育经费总额对标")
    public List<Map> getBXTJJYJFZECmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJJYJFZECmpData(paras);
        return data;
    }

    @GetMapping("BXTJ/JXJFZECmp")
    @ApiOperation(value = "首页/办学条件/教学经费总额对标")
    public List<Map> getBXTJJXJFZECmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJJXJFZECmpData(paras);
        return data;
    }

    @GetMapping("BXTJ/JXJFZZCmp")
    @ApiOperation(value = "首页/办学条件/教学经费支出对标")
    public List<Map> getBXTJJXJFZZCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJJXJFZZCmpData(paras);
        return data;
    }

    @GetMapping("BXTJ/SJJXJFCmp")
    @ApiOperation(value = "首页/办学条件/实践教学经费对标")
    public List<Map> getBXTJSJJXJFCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJSJJXJFCmpData(paras);
        return data;
    }

    @GetMapping("BXTJ/GDZCZZCmp")
    @ApiOperation(value = "首页/办学条件/固定资产总值对标")
    public List<Map> getBXTJGDZCZZCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getBXTJGDZCZZCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/BKSCmp")
    @ApiOperation(value = "首页/学生情况/本科生对标")
    public List<Map> getXSQKBKSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKBKSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/ZKSCmp")
    @ApiOperation(value = "首页/学生情况/专科生对标")
    public List<Map> getXSQKZKSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKZKSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/SSYJSCmp")
    @ApiOperation(value = "首页/学生情况/硕士研究生对标")
    public List<Map> getXSQKSSYJSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKSSYJSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/BSYJSCmp")
    @ApiOperation(value = "首页/学生情况/博士研究生对标")
    public List<Map> getXSQKBSYJSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKBSYJSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/LXSCmp")
    @ApiOperation(value = "首页/学生情况/留学生对标")
    public List<Map> getXSQKLXSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKLXSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/SJLQSCmp")
    @ApiOperation(value = "首页/学生情况/实际录取数对标")
    public List<Map> getXSQKSJLQSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKSJLQSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/JYSSCmp")
    @ApiOperation(value = "首页/学生情况/就业生数对标")
    public List<Map> getXSQKJYSSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKJYSSCmpData(paras);
        return data;
    }

    @GetMapping("XSQK/BYSSCmp")
    @ApiOperation(value = "首页/学生情况/毕业生数对标")
    public List<Map> getXSQKBYSSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getXSQKBYSSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/ZLZZQSCmp")
    @ApiOperation(value = "首页/科研水平/专利著作权数对标")
    public List<Map> getKYSPZLZZQSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPZLZZQSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/CBZZSCmp")
    @ApiOperation(value = "首页/科研水平/出版专著数对标")
    public List<Map> getKYSPCBZZSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPCBZZSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/FBLWSCmp")
    @ApiOperation(value = "首页/科研水平/发表论文数对标")
    public List<Map> getKYSPFBLWSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPFBLWSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/ZXXMSCmp")
    @ApiOperation(value = "首页/科研水平/纵向项目数对标")
    public List<Map> getKYSPZXXMSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPZXXMSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/HXXMSCmp")
    @ApiOperation(value = "首页/科研水平/横向项目数对标")
    public List<Map> getKYSPHXXMSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPHXXMSCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/HXXMJFCmp")
    @ApiOperation(value = "首页/科研水平/横向项目经费对标")
    public List<Map> getKYSPHXXMJFCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPHXXMJFCmpData(paras);
        return data;
    }

    @GetMapping("KYSP/ZXXMJFCmp")
    @ApiOperation(value = "首页/科研水平/纵向项目经费对标")
    public List<Map> getKYSPZXXMJFCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getKYSPZXXMJFCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJJXCGJCmp")
    @ApiOperation(value = "首页/教学成果/国家级教学成果奖对标")
    public List<Map> getJXCGGJJJXCGJCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGGJJJXCGJCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJJYJXYJYGGXMCmp")
    @ApiOperation(value = "首页/教学成果/国家级教育教学研究与改革项目对标")
    public List<Map> getJXCGGJJJYJXYJYGGXMCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGGJJJYJXYJYGGXMCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJBKJXGCCmp")
    @ApiOperation(value = "首页/教学成果/国家级本科教学工程对标")
    public List<Map> getJXCGGJJBKJXGCCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGGJJBKJXGCCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/SBJBKJXGCCmp")
    @ApiOperation(value = "首页/教学成果/省部级本科教学工程对标")
    public List<Map> getJXCGSBJBKJXGCCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGSBJBKJXGCCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/RCPYMSCXSYQCmp")
    @ApiOperation(value = "首页/教学成果/人才培养模式创新实验区对标")
    public List<Map> getJXCGRCPYMSCXSYQCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGRCPYMSCXSYQCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJJXJDCmp")
    @ApiOperation(value = "首页/教学成果/国家级教学基地对标")
    public List<Map> getJXCGGJJJXJDCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGGJJJXJDCmpData(paras);
        return data;
    }

    @GetMapping("JXCG/GJJSJYRCXCYJDCmp")
    @ApiOperation(value = "首页/教学成果/牵头建设国家级实践育人创新创业基地对标")
    public List<Map> getJXCGGJJSJYRCXCYJDCmpData(String year){
        if(year.equals("2016")){
            Map map = new HashMap();
            map.put("error","2016年无此数据");
            List<Map> list = new ArrayList<>();
            list.add(map);
            return list;
        }
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getJXCGGJJSJYRCXCYJDCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/JZGSCmp")
    @ApiOperation(value = "首页/师资情况/教职工数对标")
    public List<Map> getSZQKJZGSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKJZGSCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/SYSZCmp")
    @ApiOperation(value = "首页/师资情况/实验师资对标")
    public List<Map> getSZQKSYSZCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKSYSZCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/GCCRCCmp")
    @ApiOperation(value = "首页/师资情况/高层次人才对标")
    public List<Map> getSZQKGCCRCCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKGCCRCCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/JSSTJKBLCmp")
    @ApiOperation(value = "首页/师资情况/教授上台讲课比例对标")
    public List<Map> getSZQKJSSTJKBLCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKJSSTJKBLCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/ZRJSCmp")
    @ApiOperation(value = "首页/师资情况/专任教师对标")
    public List<Map> getSZQKZRJSCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKZRJSCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/BSFGLCmp")
    @ApiOperation(value = "首页/师资情况/博硕覆盖率对标")
    public List<Map> getSZQKBSFGLCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKBSFGLCmpData(paras);
        return data;
    }

    @GetMapping("SZKQ/SSBCmp")
    @ApiOperation(value = "首页/师资情况/生师比对标")
    public List<Map> getSZQKSSBCmpData(String year){
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("14school","true");
        List<Map> data = homePageService.getSZQKSSBCmpData(paras);
        return data;
    }

}
