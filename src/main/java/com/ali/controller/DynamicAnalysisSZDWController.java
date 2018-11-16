/**
 *
 */
package com.ali.controller;

import com.ali.entity.*;
import com.ali.service.DynamicAnalysisSZDWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 测试动态数据分析
 *
 * @author qqwer
 */
@RestController
@RequestMapping(path = "/dynamicAnalysis/szdw", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DynamicAnalysisSZDWController extends TopController implements DynamicAnalysis {

    @Autowired
    DynamicAnalysisSZDWService dynamicAnalysisSZDWService;

    @Override
    public ResponseEntity<DynamicAnalysisOptionModel> option() {
        DynamicAnalysisOptionModel model = new DynamicAnalysisOptionModel();
        int[] years = new int[]{2016, 2017};
        model.setStartYear(years[0]);
        model.setEndYear(years[years.length - 1]);

        //获取学科数据
        List<Option> subjectList = invokeOtpitonMethod(dynamicAnalysisSZDWService,getOptionServiceMethodNameByParam("SubjectList"), new HashMap<>());
        model.setOptions(subjectList);

        List<IndicationModel> indications = new ArrayList<>();

        List<String> typeNameList = Arrays.asList("教师情况","学历情况","最高学位","专业技术职称","高层次人才","高层次研究团队");

        for (String type : typeNameList) {
            List<Option> typeList = new ArrayList<>();
            for (int year : years) {
                Map<String, Object> paras = new HashMap<>();
                paras.put("year", year);
                paras.put("type", type);
                List<Option> list = invokeOtpitonMethod(dynamicAnalysisSZDWService, getOptionServiceMethodNameByParam(type), paras);
                for (Option option : list) {
                    if (!typeList.contains(option)) {
                        typeList.add(option);
                    }
                }
            }
            IndicationModel im = new IndicationModel();
            im.setIndicationName(type);
            im.setIndexs(typeList);
            indications.add(im);
            model.setIndications(indications);
        }

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<List<IndicationResult>> analysis(@RequestBody DynamicAnalysisOptionModel model) {

        int startYear = model.getStartYear();
        int endYear = model.getEndYear();
        List<Integer> years = getYearsbetweenStartAndEndYear(startYear, endYear);//选择的年份
        List<Option> options = model.getOptions();//选择的学科
        List<IndicationModel> indications = model.getIndications();//选择的指标组
        List<IndicationResult> AnalysisDatalist = new ArrayList<>();

        for (IndicationModel indication : indications) {
            //组装单个指标(专业技术职称{教师+副教授})组数据,一个指标组数据包括：
            IndicationResult indicationResult = new IndicationResult();
            indicationResult.setIndicationName(indication.getIndicationName());
            // 1.趋势分析数据：指标组下各个指标的总和数据[16年法学和化学的指标(教授+副教授)总和值，17年法学和化学的指标(教授+副教授)总和值]
            indicationResult.setTrendAnalysis(getTrendAnalysisModelData(years,options,indication));
            // 2.对比分析数据：指标组下各个指标的单个数据(法学和化学的教授16和17年的总和，副教授16和17年的总和)
            indicationResult.setCompAnalysis(getCompAnalysisModelData(years,options,indication));

            AnalysisDatalist.add(indicationResult);
        }

        return ResponseEntity.ok(AnalysisDatalist);
    }

    private AnalysisData getTrendAnalysisModelData(List<Integer> years, List<Option> options, IndicationModel indication){
        //趋势分析，就是勾选的指标汇总，然后做年度折线图。x轴是年度。数据是指标汇总
        //1.趋势分析数据：指标组下各个指标的总和数据[16年法学和化学的指标(教授+副教授)总和值，17年法学和化学的指标(教授+副教授)总和值]
        AnalysisData trendAnalysis = new AnalysisData();
        trendAnalysis.setTotalHide(false);
        String[] categorys = new String[years.size()];
        for(int i=0;i<years.size();i++){
            String year = years.get(i).toString();
            categorys[i]=year;
        }
        trendAnalysis.setCategory(categorys);
        List<SeriesItem> series = new ArrayList<>();

        for (Option option : options) {
            SeriesItem seriesItem = new SeriesItem();
            seriesItem.setName(option.getName());
            Object[] datas = new Object[years.size()];
            for (int i=0;i<years.size();i++) {
                int year = years.get(i);
                List<Option> indexs = indication.getIndexs();
                Map<String,Object> paras = new HashMap<>();
                paras.put("year",year);
                paras.put("subjectcode",option.getKey());
                paras.put("indications",getIndications(indexs));
                List<Map> list = invokeAnalysisMethod(dynamicAnalysisSZDWService, getTrendAnalysisServiceMethodNameByParam(indication.getIndicationName()), paras);
                if(list != null && list.size()>0){
                    datas[i]=list.get(0).get("总数");
                }else{
                    datas[i]="-";
                }
            }
            seriesItem.setData(datas);
            series.add(seriesItem);
        }
        trendAnalysis.setSeries(series);
        return trendAnalysis;
    }

    private AnalysisData getCompAnalysisModelData(List<Integer> years,List<Option> options,IndicationModel indication){
        //对比分析，就是指标按学科堆叠，x轴是学科，指标是单项数据
        //2.对比分析数据：指标组下各个指标的单个数据(法学和化学的教授16和17年的总和，副教授16和17年的总和)
        AnalysisData compAnalysis = new AnalysisData();
        compAnalysis.setTotalHide(false);

        List<SeriesItem> series = new ArrayList<>();

        String[] categorys = new String[options.size()];

        List<Option> indexs = indication.getIndexs();
        List<String> indications = getIndications(indexs);
        for (String indicator : indications) {
            SeriesItem seriesItem = new SeriesItem();
            seriesItem.setName(indicator);
            Object[] seriesItemData = new Object[options.size()];
            for (int i=0;i<options.size();i++) {
                Option option = options.get(i);
                categorys[i]=option.getName();
                int sum = 0;
                for (Integer year : years) {//16 17
                    Map<String,Object> paras = new HashMap<>();
                    paras.put("year",year);
                    paras.put("subjectcode",option.getKey());
                    List<String> indicators = new ArrayList<>();
                    indicators.add(indicator);
                    paras.put("indications",indicators);
                    List<Map> list = invokeAnalysisMethod(dynamicAnalysisSZDWService, getCompAnalysisServiceMethodNameByParam(indication.getIndicationName()), paras);
                    if(list != null && list.size()>0){
                        sum += Integer.valueOf(list.get(0).get("总数").toString());
                    }else{
                        sum += 0;
                    }
                }
                seriesItemData[i]=sum;
            }
            seriesItem.setData(seriesItemData);
            series.add(seriesItem);
        }
        compAnalysis.setCategory(categorys);
        compAnalysis.setSeries(series);
        return compAnalysis;
    }

    private String getOptionServiceMethodNameByParam(String type){//option方法中分类对应的service方法名
        Map<String,String> serviceMethodNameMap = new HashMap<>();
        serviceMethodNameMap.put("SubjectList","getSubjectList");
        serviceMethodNameMap.put("教师情况","get教师情况指标类型");
        serviceMethodNameMap.put("学历情况","get学历情况指标类型");
        serviceMethodNameMap.put("最高学位","get最高学位指标类型");
        serviceMethodNameMap.put("专业技术职称","get专业技术职称指标类型");
        serviceMethodNameMap.put("高层次人才","get高层次人才指标类型");
        serviceMethodNameMap.put("高层次研究团队","get高层次研究团队指标类型");
        return serviceMethodNameMap.get(type);
    }

    private String getTrendAnalysisServiceMethodNameByParam(String indicatorname){//analysis方法中分类对应的service方法名
        Map<String,String> serviceMethodNameMap = new HashMap<>();
        serviceMethodNameMap.put("教师情况","get教师情况指标趋势统计");
        serviceMethodNameMap.put("学历情况","get学历情况指标趋势统计");
        serviceMethodNameMap.put("最高学位","get最高学位指标趋势统计");
        serviceMethodNameMap.put("专业技术职称","get专业技术职称指标趋势统计");
        serviceMethodNameMap.put("高层次人才","get高层次人才指标趋势统计");
        serviceMethodNameMap.put("高层次研究团队","get高层次研究团队指标趋势统计");
        return serviceMethodNameMap.get(indicatorname);
    }

    private String getCompAnalysisServiceMethodNameByParam(String indicatorname){//analysis方法中分类对应的service方法名
        Map<String,String> serviceMethodNameMap = new HashMap<>();
        serviceMethodNameMap.put("教师情况","get教师情况指标对比统计");
        serviceMethodNameMap.put("学历情况","get学历情况指标对比统计");
        serviceMethodNameMap.put("最高学位","get最高学位指标对比统计");
        serviceMethodNameMap.put("专业技术职称","get专业技术职称指标对比统计");
        serviceMethodNameMap.put("高层次人才","get高层次人才指标对比统计");
        serviceMethodNameMap.put("高层次研究团队","get高层次研究团队指标对比统计");
        return serviceMethodNameMap.get(indicatorname);
    }

    private List<Integer> getYearsbetweenStartAndEndYear(int startYear,int endYear){
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++){
            years.add(i);
        }
        return years;
    }

    private List<String> getIndications(List<Option> indexs){
        List<String> indicationList = new ArrayList<>();
        for (Option option : indexs) {
            String indication = option.getKey();
            indicationList.add(indication);
        }
        return indicationList;
    }

}
