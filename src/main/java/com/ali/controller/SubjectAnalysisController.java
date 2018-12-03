package com.ali.controller;

import com.ali.bean.subjectanalysis.subjectcompare.*;
import com.ali.bean.subjectanalysis.subjectdata.*;
import com.ali.bean.subjectanalysis.subjectoverview.*;
import com.ali.bean.subjectanalysis.subjecttrend.Trend;
import com.ali.bean.subjectanalysis.subjecttrend.TrendItem;
import com.ali.bean.subjectanalysis.table.Child;
import com.ali.bean.subjectanalysis.table.Column;
import com.ali.bean.subjectanalysis.table.Columns;
import com.ali.bean.subjectanalysis.table.Table;
import com.ali.service.SubjectAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("subjectAnalysis")
@Api(tags = "SubjectAnalysis")
public class SubjectAnalysisController {

    @Autowired
    SubjectAnalysisService subjectAnalysisService;

    @GetMapping("subjectList")
    @ApiOperation(value = "学科列表")
    public List<Map> getSubjectList(){
        return subjectAnalysisService.getNewSubjectList();
    }

    @GetMapping("fschoolList")
    @ApiOperation(value = "外校列表")
    public List<Map> getFSchoolList(){
        return subjectAnalysisService.getFSchoolList();
    }

    @GetMapping("subjectOverView")
    @ApiOperation(value = "学科全景")
    public SubjectOverView getSubjectOverview(String subjectCode){

        SubjectOverView model = new SubjectOverView();

        SubjectInfo subjectInfo = getSubjectInfo(subjectCode);//学科信息
        model.setSubjectInfo(subjectInfo);

        List<SubjectCompare> subjectCompare = getSubjectCmpData(subjectCode);//学科对比数据
        model.setSubjectCompare(subjectCompare);

        SubjectRankingGroup subjectRankingGroups = getSubjectRankingData();//学科排名数据
        model.setSubjectRankingGroups(subjectRankingGroups);

        TrendAnalysisGroup trendAnalysisGroups = getTrendAnalysisData(subjectCode);//趋势分析
        model.setTrendAnalysisGroup(trendAnalysisGroups);

        return model;
    }

    @GetMapping("subjectData")
    @ApiOperation(value = "学科数据")
    public SubjectData getSubjectData(String subjectCode,String type){

        SubjectData model = new SubjectData();

        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        List<Map> subjectInfo = subjectAnalysisService.getNewSubjectInfo(paras);
        String showName = "";
        if(subjectInfo.size() > 0){
            Map map = subjectInfo.get(0);
            showName = map.get("name").toString();
        }

        model.setSubjectName(showName);

        Echart echart;

        Table table;

        if(type.equals("ttl")){//学科数据/综合
            echart = getRadarData(subjectCode);
            //table = getTotalTable(subjectCode);
        }else{//学科数据/学科
            echart = getRectangleData(subjectCode);
            //table = getSubjectTable(subjectCode);
        }
        //表格数据
        model.setTable(mockTableData(subjectCode));

        model.setEchart(echart);

        return model;
    }

    private Table mockTableData(String subjectCode) {
        Table table = new Table();
        table.setColumns(mockTableColumns());
        table.setDatas(mockTableDatas(subjectCode));
        return table;
    }

    private List<Map> mockTableDatas(String subjectCode) {
        List<Map> datas = new ArrayList<>();
        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        List<Map> mapList = subjectAnalysisService.getNew单个学科分类型高层次三年数据统计(paras);
        for (Map map : mapList) {
            Double 人才数2018 = Double.valueOf(map.get("人才数2018").toString());
            Double 人才数2017 = Double.valueOf(map.get("人才数2017").toString());
            Double 人才数2016 = Double.valueOf(map.get("人才数2016").toString());

            Map<String,Double> yearVal = new HashMap<>();
            yearVal.put("2018",人才数2018);
            yearVal.put("2017",人才数2017);
            yearVal.put("2016",人才数2016);

            String[] years = new String[]{"2018","2017","2016"};
            for (String year : years) {
                Map<String,Object> data = new HashMap<>();
                data.put("indicator",map.get("type").toString());
                //学科值
                data.put("zlyear",year);
                data.put("zldata",yearVal.get(year));
                data.put("zlscore",getIndicatorScoreInAllSubject(year,yearVal.get(year),subjectCode));
                Double percent;
                if(getIndicatorScoreInAllSubject(year,yearVal.get(year),subjectCode) == 0){
                    percent = 0.0;
                }else if(getSubjectScoreInAllSubject(year,subjectCode) == 0){
                    percent = 0.0;
                }else{
                    percent = getIndicatorScoreInAllSubject(year,yearVal.get(year),subjectCode)/getSubjectScoreInAllSubject(year,subjectCode);
                }
                data.put("zlscorepercent",percent);
                data.put("zlranking",3);
                //增量值
                Integer thisYear = Integer.valueOf(year);
                Integer lastYear = thisYear-1;
                Double aDouble = yearVal.get("" + thisYear) == null ? 0.0 : yearVal.get("" + thisYear);
                Double bDouble = yearVal.get("" + lastYear) == null ? 0.0 : yearVal.get("" + lastYear);
                data.put("zelyear",year);
                data.put("zeldata",(aDouble-bDouble));
                data.put("zelscore",getIndicatorScoreInAllSubject(year,(aDouble-bDouble),subjectCode));
                data.put("zelscorepercent",getIndicatorScoreInAllSubject(year,(aDouble-bDouble),subjectCode)/getSubjectScoreInAllSubject(year,subjectCode));
                data.put("zelranking",4);
                //趋势
                data.put("trend",(人才数2018-人才数2017) > 0 ? "up" : "down");
                datas.add(data);
            }
        }
        if(datas.size()<=0){
            String[] indicators = new String[]{"万人计划","新世纪优秀人才","百千万人才工程","省级高层次人才","文化名家暨“四个一批”人才","省部级突出贡献专家"};
            String[] years = new String[]{"2018","2017","2016"};
            String indicatorName = indicators[(int)(1+Math.random()*5)];
            /*for (String year : years) {
                Map<String,Object> data = new HashMap<>();
                data.put("indicator",indicatorName);
                //学科值
                data.put("zlyear",year);
                data.put("zldata",(int)(1+Math.random()*10));
                data.put("zlscore",(int)(50+Math.random()*50));
                data.put("zlscorepercent",Math.random()*100);
                data.put("zlranking",3);
                //增量值
                data.put("zelyear",year);
                data.put("zeldata",(int)(1+Math.random()*10));
                data.put("zelscore",(int)(50+Math.random()*50));
                data.put("zelscorepercent",Math.random()*100);
                data.put("zelranking",(int)(1+Math.random()*10));
                //趋势
                data.put("trend","up");
                datas.add(data);
            }*/

            for (String indicator : indicators) {

                Map<String,Object> topData = new HashMap<>();
                topData.put("indicator",indicatorName);
                //学科值
                topData.put("zlyear",2018);
                topData.put("zldata",(int)(1+Math.random()*10));
                topData.put("zlscore",(int)(50+Math.random()*50));
                topData.put("zlscorepercent",Math.random()*100);
                topData.put("zlranking",3);
                //增量值
                topData.put("zelyear",2018);
                topData.put("zeldata",(int)(1+Math.random()*10));
                topData.put("zelscore",(int)(50+Math.random()*50));
                topData.put("zelscorepercent",Math.random()*100);
                topData.put("zelranking",(int)(1+Math.random()*10));
                //趋势
                topData.put("trend","up");

                List<Map> childrendatas = new ArrayList<>();
                for (String year : years){
                    Map<String,Object> data = new HashMap<>();
                    data.put("indicator",indicatorName);
                    //学科值
                    data.put("zlyear",year);
                    data.put("zldata",(int)(1+Math.random()*10));
                    data.put("zlscore",(int)(50+Math.random()*50));
                    data.put("zlscorepercent",Math.random()*100);
                    data.put("zlranking",3);
                    //增量值
                    data.put("zelyear",year);
                    data.put("zeldata",(int)(1+Math.random()*10));
                    data.put("zelscore",(int)(50+Math.random()*50));
                    data.put("zelscorepercent",Math.random()*100);
                    data.put("zelranking",(int)(1+Math.random()*10));
                    //趋势
                    data.put("trend","up");
                    childrendatas.add(data);
                }
                topData.put("children",childrendatas);
                datas.add(topData);
            }
        }
        return datas;
    }

    public double getSubjectScoreInAllSubject(String year,String subjectCode){//单个学科在所有学科中的分数
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("schoolcode",10593);
        List<Map> mapList = subjectAnalysisService.getNew高层次人才统计(paras);
        Map maxMap = mapList.get(0);
        Double max = Double.valueOf(maxMap.get("人才数").toString());
        Map minMap = mapList.get(mapList.size()-1);
        Double min = Double.valueOf(minMap.get("人才数").toString());
        Double x = 0.0;
        for (Map map : mapList) {
            if(map.get("subject_code").toString().equals(subjectCode)){
                x = Double.valueOf(map.get("人才数").toString());
            }
        }
        double val = Double.valueOf(60.0+(x-min)*40.0/(max-min));
        return val;
    }

    public double getIndicatorScoreInAllSubject(String year,Double value,String subjectCode){//单个指标在所有学科中的分数
        Map<String,Object> paras = new HashMap<>();
        paras.put("year",year);
        paras.put("schoolcode",10593);
        paras.put("subjectCode",subjectCode);
        paras.put("year",year);
        List<Map> mapList = subjectAnalysisService.getNew单个学科高层次人才分类统计(paras);
        Double max;
        Double min;
        if(mapList.size()>0){
            Map maxMap = mapList.get(0);
            max = Double.valueOf(maxMap.get("人才数").toString());
            Map minMap = mapList.get(mapList.size()-1);
            min = Double.valueOf(minMap.get("人才数").toString());
        }else{
            max=0.0;
            min=0.0;
        }
        double val = Double.valueOf(60.0+(value-min)*40.0/(max-min));
        double result;
        if(Double.isFinite(val)){
            result = 0.0;
        }else if(Double.isInfinite(val)){
            result = 0.0;
        }else if(Double.isNaN(val)){
            result = 0.0;
        }else{
            result = val;
        }
        return result;
    }

    public double getMapMaxOrMinValue(Map map,String type){
        List<Double> list = new ArrayList<>();
        Set set = map.keySet();
        for (Object key : set) {
            Double value = Double.valueOf(map.get(key).toString());
            list.add(value);
        }
        double result = 0.0;
        for (Double aDouble : list) {
            if(type.equals("max")){
                result = (result > aDouble) ? result : aDouble;
            }else{
                result = (result < aDouble) ? result : aDouble;
            }
        }
        return result;
    }

    public double calcMapSubjectValue(Map map,String keyname,Double y){
        double max = getMapMaxOrMinValue(map, "max");
        double min = getMapMaxOrMinValue(map, "min");
        double x;
        if(keyname != null || !keyname.equals("")){
            x = Double.valueOf(map.get(keyname).toString());
        }else{
            x = y;
        }
        double val = Double.valueOf(60.0+(x-min)*40.0/(max-min));
        return val;
    }

    private Columns mockTableColumns() {
        Columns columns = new Columns();
        List<Column> columnList = new ArrayList<>();
        //---------------
        Column column1 = new Column();
        column1.setText("指标维度");
        column1.setValue("indicator");
        columnList.add(column1);
        //---------------
        Column column2 = new Column();
        column2.setText("总量");
        List<Child> children = new ArrayList<>();

        Child child1 = new Child();
        child1.setText("年份");
        child1.setValue("zlyear");
        children.add(child1);

        Child child2 = new Child();
        child2.setText("数据");
        child2.setValue("zldata");
        children.add(child2);

        Child child3 = new Child();
        child3.setText("得分");
        child3.setValue("zlscore");
        children.add(child3);

        Child child4 = new Child();
        child4.setText("百分比得分");
        child4.setValue("zlscorepercent");
        children.add(child4);

        Child child5 = new Child();
        child5.setText("排名");
        child5.setValue("zlranking");
        children.add(child5);

        column2.setChildren(children);
        columnList.add(column2);
        //--------------
        Column column3 = new Column();
        column3.setText("增量");
        List<Child> children3 = new ArrayList<>();

        Child child6 = new Child();
        child6.setText("年份");
        child6.setValue("zelyear");
        children3.add(child6);

        Child child7 = new Child();
        child7.setText("数据");
        child7.setValue("zeldata");
        children3.add(child7);

        Child child8 = new Child();
        child8.setText("得分");
        child8.setValue("zelscore");
        children3.add(child8);

        Child child9 = new Child();
        child9.setText("百分比得分");
        child9.setValue("zelscorepercent");
        children3.add(child9);

        Child child10 = new Child();
        child10.setText("排名");
        child10.setValue("zelranking");
        children3.add(child10);

        column3.setChildren(children3);
        columnList.add(column3);
        //---------------
        Column column4 = new Column();
        column4.setText("趋势");
        column4.setValue("trend");
        columnList.add(column4);

        columns.setColumns(columnList);

        return columns;
    }

    @GetMapping("subjectCmpare")
    @ApiOperation(value = "学科对比")
    public CompareGroup getSubjectCompare(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode){
        return mockCompareGroupData(leftSubjectCode, rightType, rightSchoolCode, rightSubjectCode);
    }

    @GetMapping("subjectHighlevelTalentsTypes")
    @ApiOperation(value = "学科高层次人才类型")
    public Map<String,List<Map>> getSubjectHighlevelTalentsTypes(String subjectCode){
        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);

        Map<String,List<Map>> data = new HashMap<>();
        data.put("高层次人才",subjectAnalysisService.getNew各学科高层次人才类型(paras));
        {//人才培养
            List<Map> list = new ArrayList<>();

            Map<String,String> map = new HashMap<>();
            map.put("type","本科生");
            list.add(map);

            Map<String,String> map2 = new HashMap<>();
            map2.put("type","博士生");
            list.add(map2);

            Map<String,String> map3 = new HashMap<>();
            map3.put("type","硕士生");
            list.add(map3);

            data.put("人才培养",list);
        }

        {//学术论文
            List<Map> list = new ArrayList<>();

            Map<String,String> map = new HashMap<>();
            map.put("type","SCI");
            list.add(map);

            Map<String,String> map2 = new HashMap<>();
            map2.put("type","SSCI");
            list.add(map2);

            Map<String,String> map3 = new HashMap<>();
            map3.put("type","EI");
            list.add(map3);

            data.put("学术论文",list);
        }

        {//成果获奖
            List<Map> list = new ArrayList<>();

            Map<String,String> map = new HashMap<>();
            map.put("type","一等奖");
            list.add(map);

            Map<String,String> map2 = new HashMap<>();
            map2.put("type","二等奖");
            list.add(map2);

            Map<String,String> map3 = new HashMap<>();
            map3.put("type","三等奖");
            list.add(map3);

            data.put("成果获奖",list);
        }

        {//科研项目
            List<Map> list = new ArrayList<>();

            Map<String,String> map = new HashMap<>();
            map.put("type","国家级");
            list.add(map);

            Map<String,String> map2 = new HashMap<>();
            map2.put("type","省级");
            list.add(map2);

            Map<String,String> map3 = new HashMap<>();
            map3.put("type","‘部委级");
            list.add(map3);

            data.put("科研项目",list);
        }

        return data;
    }

    @GetMapping("subjectHighlevelTalentsTypeYearData")
    @ApiOperation(value = "学科高层次人才类型三年数据")
    public Map<String,List<Map>> getSubjectHighlevelTalentsTypeYearData(Trend trend){
        Map<String,List<Map>> data = new HashMap<>();

        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",trend.getSubjectCode());
        List<TrendItem> trendItems = trend.getTrendItems();
        for (TrendItem trendItem : trendItems) {
            if(trendItem.getType().equals("高层次人才")){
                String selects = trendItem.getSelects();
                String[] strings = selects.split(",");
                for (String string : strings) {
                    paras.put("type",string);
                    List<Map> mapList = subjectAnalysisService.getNew各学科各类型高层次人才三年数据(paras);
                    data.put(string,mapList);
                }
            }else{
                String selects = trendItem.getSelects();
                String[] strings = selects.split(",");
                for (String string : strings) {
                    paras.put("type",string);
                    Map<String,Object> map = new HashMap<>();
                    map.put("subject_code",trend.getSubjectCode());
                    List<Map> info = subjectAnalysisService.getNewSubjectInfo(paras);
                    Map map1 = info.get(0);
                    map.put("subject_name",map1.get("subject_name"));
                    map.put("type",string);
                    map.put("人才数2018",5);
                    map.put("人才数2017",13);
                    map.put("人才数2016",8);
                    List<Map> result = new ArrayList<>();
                    result.add(map);
                    //List<Map> mapList = subjectAnalysisService.getNew各学科各类型高层次人才三年数据(paras);
                    data.put(string,result);
                }
            }
        }
        //return subjectAnalysisService.getNew各学科各类型高层次人才三年数据(paras);
        return data;
    }

    private CompareGroup mockCompareGroupData(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode){
        CompareGroup compareGroup = new CompareGroup();
        Map<String,Object> oMap = new HashMap<>();
        oMap.put("leftSubjectCode",leftSubjectCode);
        oMap.put("rightType",rightType);
        oMap.put("rightSchoolCode",rightSchoolCode);
        oMap.put("rightSubjectCode",rightSubjectCode);

        Map<String,Object> paras1 = new HashMap<>();
        paras1.put("subjectCode",rightSubjectCode);
        List<Map> subjectInfo1 = subjectAnalysisService.getNewSubjectInfo(paras1);
        String showName1;
        if(subjectInfo1.size() > 0){
            Map map1 = subjectInfo1.get(0);
            showName1 = map1.get("code").toString()+"-"+map1.get("name").toString();
            oMap.put("rightSelectItem",showName1);
        }

        compareGroup.setOparam(oMap);

        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",leftSubjectCode);
        List<Map> subjectInfo = subjectAnalysisService.getNewSubjectInfo(paras);
        String showName = "";
        if(subjectInfo.size() > 0){
            Map map = subjectInfo.get(0);
            showName = map.get("code").toString()+"-"+map.get("name").toString();
        }
        compareGroup.setLeftName(showName);
        compareGroup.setVsData(mockVsData(leftSubjectCode, rightType, rightSchoolCode, rightSubjectCode));
        compareGroup.setRightName(rightType);
        compareGroup.setIncrementData(mockIncrementData(leftSubjectCode, rightType, rightSchoolCode, rightSubjectCode));
        //表格数据
        compareGroup.setTable(getCompareTable(leftSubjectCode, rightType, rightSchoolCode, rightSubjectCode));
        return compareGroup;
    }

    private Table getCompareTable(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode) {
        Table table = new Table();
        table.setColumns(mockCompareTableColumns());
        table.setDatas(mockCompareTableDatas(leftSubjectCode, rightType, rightSchoolCode, rightSubjectCode));
        return table;
    }

    private List<Map> mockCompareTableDatas(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode) {
        Map<String,Object> paras = new HashMap<>();
        paras.put("leftSubjectCode",leftSubjectCode);
        if(!rightType.equals("本校均值")){
            paras.put("rightSchoolCode",rightSchoolCode);
            paras.put("rightSubjectCode",rightSubjectCode);
        }
        List<Map> mapList = subjectAnalysisService.getNew本校学科外校学科高层次人才分类统计(paras);
        List<Map> datas = new ArrayList<>();
        String lastIndicator="";
        for (Map map : mapList) {
            String nowIndicator = map.get("type").toString();
            Map<String,Object> topData = new HashMap<>();
            if(!map.get("type").toString().equals(lastIndicator)){
                lastIndicator = nowIndicator;

                topData.put("indicator",nowIndicator);
                topData.put("year",map.get("year"));
                topData.put("schoolval",map.get("本校数据"));
                topData.put("fschoolval",map.get("标杆数据"));
                topData.put("middlethree",5);
                topData.put("middle5percent",3);
                topData.put("middle10percent",2);
                topData.put("middle25percent",2);
                datas.add(topData);

            }else{
                for (Map data1 : datas) {
                    if(data1.get("indicator").toString().equals(nowIndicator)){
                        Map<String,Object> data2 = new HashMap<>();
                        data2.put("indicator",data1.get("indicator"));
                        data2.put("year",map.get("year"));
                        data2.put("schoolval",(int)(1+Math.random()*10));
                        data2.put("fschoolval",(int)(1+Math.random()*10));
                        data2.put("middlethree",5);
                        data2.put("middle5percent",3);
                        data2.put("middle10percent",2);
                        data2.put("middle25percent",2);
                        if(data1.get("children") != null){
                            List<Map> childrendatas = (List<Map>) data1.get("children");
                            childrendatas.add(data2);
                            topData.put("children",childrendatas);
                        }else{
                            List<Map> childrendatas = new ArrayList<>();
                            childrendatas.add(data2);
                            topData.put("children",childrendatas);
                        }
                    }
                }
                datas.add(topData);
            }

            /*data.put("indicator",map.get("type"));
            data.put("year",map.get("year"));
            data.put("schoolval",map.get("本校数据"));
            data.put("fschoolval",map.get("标杆数据"));
            data.put("middlethree",5);
            data.put("middle5percent",3);
            data.put("middle10percent",2);
            data.put("middle25percent",2);
            datas.add(data);*/

        }
        if(datas.size()<=0){
            String[] indicators = new String[]{"万人计划","新世纪优秀人才","百千万人才工程","省级高层次人才","省部级突出贡献专家"};

            String[] years = new String[]{"2018","2017","2016"};
            for (String indicator : indicators) {

                Map<String,Object> topData = new HashMap<>();
                topData.put("indicator",indicator);
                topData.put("year",2018);
                topData.put("schoolval",(int)(1+Math.random()*10));
                topData.put("fschoolval",(int)(1+Math.random()*10));
                topData.put("middlethree",5);
                topData.put("middle5percent",3);
                topData.put("middle10percent",2);
                topData.put("middle25percent",2);

                List<Map> childrendatas = new ArrayList<>();
                for (String year : years){
                    Map<String,Object> data = new HashMap<>();
                    data.put("indicator",indicator);
                    data.put("year",year);
                    data.put("schoolval",(int)(1+Math.random()*10));
                    data.put("fschoolval",(int)(1+Math.random()*10));
                    data.put("middlethree",5);
                    data.put("middle5percent",3);
                    data.put("middle10percent",2);
                    data.put("middle25percent",2);
                    childrendatas.add(data);
                }
                topData.put("children",childrendatas);
                datas.add(topData);
            }
        }
        return datas;
    }

    private Columns mockCompareTableColumns() {
        Columns columns = new Columns();
        List<Column> columnList = new ArrayList<>();
        //---------------
        Column column1 = new Column();
        column1.setText("指标维度");
        column1.setValue("indicator");
        columnList.add(column1);

        //---------------
        Column column2 = new Column();
        column2.setText("年份");
        column2.setValue("year");
        columnList.add(column2);

        //---------------
        Column column3 = new Column();
        column3.setText("本校数据");
        column3.setValue("schoolval");
        columnList.add(column3);

        //---------------
        Column column4 = new Column();
        column4.setText("标杆数据");
        column4.setValue("fschoolval");
        columnList.add(column4);

        //---------------
        Column column5 = new Column();
        column5.setText("前三中值");
        column5.setValue("middlethree");
        columnList.add(column5);

        //---------------
        Column column6 = new Column();
        column6.setText("前5%值");
        column6.setValue("middle5percent");
        columnList.add(column6);

        //---------------
        Column column7 = new Column();
        column7.setText("前10%值");
        column7.setValue("middle10percent");
        columnList.add(column7);

        //---------------
        Column column8 = new Column();
        column8.setText("前25%值");
        column8.setValue("middle25percent");
        columnList.add(column8);

        columns.setColumns(columnList);

        return columns;
    }

    private VSData mockVsData(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode){
        VSData vsData = new VSData();
        LeftItem leftItem = new LeftItem();
        List<Item> leftItemList = new ArrayList<>();
        {
            //本校高层次人才18年的值
            Map<String,Object> paras1 = new HashMap<>();
            paras1.put("year",2018);
            paras1.put("schoolcode",10593);
            List<Map> list1 = subjectAnalysisService.getNew高层次人才统计(paras1);
            Map<String, Double> map1 = calulateAvgAndSelfValue(leftSubjectCode, list1);
            Item item1 = new Item();
            item1.setItemName("高层次人才");
            item1.setItemValue(map1.get("val"));
            leftItemList.add(item1);

            Item item2 = new Item();
            item2.setItemName("科研项目");
            item2.setItemValue(65.0);
            leftItemList.add(item2);

            Item item3 = new Item();
            item3.setItemName("成果获奖");
            item3.setItemValue(76.0);
            leftItemList.add(item3);

            Item item4 = new Item();
            item4.setItemName("学术论文");
            item4.setItemValue(87.0);
            leftItemList.add(item4);

            Item item5 = new Item();
            item5.setItemName("人才培养");
            item5.setItemValue(74.0);
            leftItemList.add(item5);

            Item item6 = new Item();
            item6.setItemName("平台建设");
            item6.setItemValue(79.0);
            leftItemList.add(item6);
        }
        leftItem.setItemList(leftItemList);
        vsData.setLeftItem(leftItem);

        RightItem rightItem = new RightItem();
        List<Item> rightItemList = new ArrayList<>();
        {
            Map<String,Object> paras2 = new HashMap<>();
            List<Map> list2;
            Map<String, Double> map2;
            double val2;
            if(rightType.equals("本校均值")){
                paras2.put("year",2018);
                paras2.put("schoolcode",10593);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(leftSubjectCode, list2);
                val2 = map2.get("avg");
            }else if(rightType.equals("本校学科")){
                paras2.put("year",2018);
                paras2.put("schoolcode",10593);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(rightSubjectCode, list2);
                val2 = map2.get("val");
            }else{
                paras2.put("year",2018);
                paras2.put("schoolcode",rightSchoolCode);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(rightSubjectCode, list2);
                val2 = map2.get("val");
            }

            Item item1 = new Item();
            item1.setItemName("高层次人才");
            item1.setItemValue(val2);
            rightItemList.add(item1);

            Item item2 = new Item();
            item2.setItemName("科研项目");
            item2.setItemValue(65.0);
            rightItemList.add(item2);

            Item item3 = new Item();
            item3.setItemName("成果获奖");
            item3.setItemValue(88.0);
            rightItemList.add(item3);

            Item item4 = new Item();
            item4.setItemName("学术论文");
            item4.setItemValue(72.0);
            rightItemList.add(item4);

            Item item5 = new Item();
            item5.setItemName("人才培养");
            item5.setItemValue(80.0);
            rightItemList.add(item5);

            Item item6 = new Item();
            item6.setItemName("平台建设");
            item6.setItemValue(69.0);
            rightItemList.add(item6);
        }
        rightItem.setItemList(rightItemList);
        vsData.setRightItem(rightItem);

        return vsData;
    }

    private IncrementData mockIncrementData(String leftSubjectCode, String rightType, String rightSchoolCode, String rightSubjectCode){
        IncrementData incrementData = new IncrementData();
        LeftItem leftItem = new LeftItem();
        List<Item> leftItemList = new ArrayList<>();
        {
            //本校高层次人才18年同比17年结果
            Map<String,Object> paras1 = new HashMap<>();
            paras1.put("year",2018);
            paras1.put("schoolcode",10593);
            List<Map> list1 = subjectAnalysisService.getNew高层次人才统计(paras1);
            Map<String, Double> map1 = calulateAvgAndSelfValue(leftSubjectCode, list1);
            Double val18 = map1.get("val");

            paras1.put("year",2017);
            List<Map> list2 = subjectAnalysisService.getNew高层次人才统计(paras1);
            Map<String, Double> map2 = calulateAvgAndSelfValue(leftSubjectCode, list2);
            Double val17 = map2.get("val");

            double rate = val17 == 0 ? 0 : val18/val17*100;
            Item item1 = new Item();
            item1.setItemName("高层次人才");
            item1.setItemValue(rate);
            leftItemList.add(item1);

            Item item2 = new Item();
            item2.setItemName("科研项目");
            item2.setItemValue(85.0);
            leftItemList.add(item2);

            Item item3 = new Item();
            item3.setItemName("成果获奖");
            item3.setItemValue(25.9);
            leftItemList.add(item3);

            Item item4 = new Item();
            item4.setItemName("学术论文");
            item4.setItemValue(73.5);
            leftItemList.add(item4);

            Item item5 = new Item();
            item5.setItemName("人才培养");
            item5.setItemValue(35.4);
            leftItemList.add(item5);

            Item item6 = new Item();
            item6.setItemName("平台建设");
            item6.setItemValue(66.8);
            leftItemList.add(item6);
        }
        leftItem.setItemList(leftItemList);
        incrementData.setLeftItem(leftItem);

        RightItem rightItem = new RightItem();
        List<Item> rightItemList = new ArrayList<>();
        {
            Map<String,Object> paras2 = new HashMap<>();
            List<Map> list2;
            Map<String, Double> map2;
            List<Map> list3;
            Map<String, Double> map3;
            double val2;

            if(rightType.equals("本校均值")){
                paras2.put("schoolcode",10593);

                paras2.put("year",2018);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(leftSubjectCode, list2);
                double val18 = map2.get("avg");

                paras2.put("year",2017);
                list3 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map3 = calulateAvgAndSelfValue(leftSubjectCode, list3);
                double val17 = map3.get("avg");

                val2 = val17 == 0 ? 0 : val18/val17*100;

            }else if(rightType.equals("本校学科")){
                paras2.put("schoolcode",10593);

                paras2.put("year",2018);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(rightSubjectCode, list2);
                double val18 = map2.get("val");

                paras2.put("year",2017);
                list3 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map3 = calulateAvgAndSelfValue(rightSubjectCode, list3);
                double val17 = map3.get("val");

                val2 = val17 == 0 ? 0 : val18/val17*100;

            }else{
                paras2.put("schoolcode",rightSchoolCode);

                paras2.put("year",2018);
                list2 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map2 = calulateAvgAndSelfValue(rightSubjectCode, list2);
                double val18 = map2.get("val");

                paras2.put("year",2017);
                list3 = subjectAnalysisService.getNew高层次人才统计(paras2);
                map3 = calulateAvgAndSelfValue(rightSubjectCode, list3);
                double val17 = map3.get("val");

                val2 = val17 == 0 ? 0 : val18/val17*100;
            }

            Item item1 = new Item();
            item1.setItemName("高层次人才");
            item1.setItemValue(val2);
            rightItemList.add(item1);

            Item item2 = new Item();
            item2.setItemName("科研项目");
            item2.setItemValue(56.0);
            rightItemList.add(item2);

            Item item3 = new Item();
            item3.setItemName("成果获奖");
            item3.setItemValue(88.0);
            rightItemList.add(item3);

            Item item4 = new Item();
            item4.setItemName("学术论文");
            item4.setItemValue(56.0);
            rightItemList.add(item4);

            Item item5 = new Item();
            item5.setItemName("人才培养");
            item5.setItemValue(56.0);
            rightItemList.add(item5);

            Item item6 = new Item();
            item6.setItemName("平台建设");
            item6.setItemValue(56.0);
            rightItemList.add(item6);
        }
        rightItem.setItemList(rightItemList);
        incrementData.setRightItem(rightItem);

        return incrementData;
    }

    private SubjectInfo getSubjectInfo(String subjectCode){
        SubjectInfo subjectInfo = new SubjectInfo();
        subjectInfo.set软科排名("");
        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        //基本信息
        List<Map> info = subjectAnalysisService.getNewSubjectInfo(paras);
        if(info.size()>0){
            Map map = info.get(0);
            subjectInfo.setSubjectCode(map.get("code").toString());
            subjectInfo.setSubjectName(map.get("name").toString());
            subjectInfo.set授权级别(map.get("level").toString());
        }
        //教育部评估结果
        List<JYBSubjectAssetmentResult> list = subjectAnalysisService.getNewSubjectAssessment(paras);
        Map<String,JYBSubjectAssetmentResult> data = new HashMap<>();
        for (JYBSubjectAssetmentResult jybSubjectAssetmentResult : list) {
            data.put(jybSubjectAssetmentResult.get评估批次(),jybSubjectAssetmentResult);
        }
        subjectInfo.setJybSubjectAssetmentResults(data);
        return subjectInfo;
    }

    private List<SubjectCompare> getSubjectCmpData(String subjectCode){
        return mockSubjectCompareData(subjectCode);
    }

    private SubjectRankingGroup getSubjectRankingData(){
        return mockSubjectRankingData();
    }

    private TrendAnalysisGroup getTrendAnalysisData(String subjectCode){
        return mockTrendAnalysisData(subjectCode);
    }

    private Map<String,Double> calulateAvgAndSelfValue(String subjectCode,List<Map> list){
        Map<String,Double> data = new HashMap<>();
        double x = 0;
        double avgTotal = 0.0;

        double max = 0;
        double min = 0;
        if(list.size()>0){
            Map maxMap = list.get(0);
            max = Double.valueOf(maxMap.get("人才数").toString());
            Map minMap = list.get(list.size()-1);
            min = Double.valueOf(minMap.get("人才数").toString());
        }
        for (Map map : list) {
            //total += Integer.valueOf(map.get("人才数").toString());
            int y = Integer.valueOf(map.get("人才数").toString());
            avgTotal += Double.valueOf(60.0 + (y - min) * 40.0 / (max - min));
            if(map.get("subject_code").toString().equals(subjectCode)){
                x = Double.valueOf(map.get("人才数").toString());
            }
        }
        double avg = Double.valueOf(avgTotal/list.size());
        double val = Double.valueOf(60.0+(x-min)*40.0/(max-min));
        data.put("avg",avg);
        data.put("val",val);
        return data;
    }

    public List<Map> calculateAllSubjectValue(List<Map> list){
        double max = 0;
        double min = 0;
        if(list.size()>0){
            Map maxMap = list.get(0);
            max = Double.valueOf(maxMap.get("人才数").toString());
            Map minMap = list.get(list.size()-1);
            min = Double.valueOf(minMap.get("人才数").toString());
        }
        for (Map map : list) {
            double x = Double.valueOf(map.get("人才数").toString());
            double val = Double.valueOf(60.0+(x-min)*40.0/(max-min));
            map.put("val",val);
        }
        return list;
    }

    private List<SubjectCompare> mockSubjectCompareData(String subjectCode){
        List<SubjectCompare> list = new ArrayList<>();
        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        paras.put("year","2018");
        paras.put("schoolcode",10593);
        List<Map> mapList = subjectAnalysisService.getNew高层次人才统计(paras);
        SubjectCompare compare1 = new SubjectCompare();
        Map<String, Double> valueMap = calulateAvgAndSelfValue(subjectCode, mapList);
        compare1.setType("高层次人才");
        compare1.setSubjectValue(valueMap.get("val"));
        compare1.setAverageValue(valueMap.get("avg"));

        SubjectCompare compare2 = new SubjectCompare();
        compare2.setType("科研项目");
        compare2.setSubjectValue(50*Math.random()+50);
        compare2.setAverageValue(50*Math.random()+50);

        SubjectCompare compare3 = new SubjectCompare();
        compare3.setType("成果获奖");
        compare3.setSubjectValue(50*Math.random()+50);
        compare3.setAverageValue(50*Math.random()+50);

        SubjectCompare compare4 = new SubjectCompare();
        compare4.setType("学术论文");
        compare4.setSubjectValue(50*Math.random()+50);
        compare4.setAverageValue(50*Math.random()+50);

        SubjectCompare compare5 = new SubjectCompare();
        compare5.setType("人才培养");
        compare5.setSubjectValue(50*Math.random()+50);
        compare5.setAverageValue(50*Math.random()+50);

        SubjectCompare compare6 = new SubjectCompare();
        compare6.setType("平台建设");
        compare6.setSubjectValue(50*Math.random()+50);
        compare6.setAverageValue(50*Math.random()+50);

        list.add(compare1);
        list.add(compare2);
        list.add(compare3);
        list.add(compare4);
        list.add(compare5);
        list.add(compare6);

        return list;
    }

    public List<SubjectRanking> mockSoftRanking(List<SubjectRanking> rankingList){
        String[] level = new String[]{"A","A+","A-","B","B+","B-","C","C+","C-","D","D+","D-"};
        Iterator<SubjectRanking> iterator = rankingList.iterator();
        while (iterator.hasNext()){
            SubjectRanking subjectRanking = iterator.next();
            subjectRanking.set参评高校数(""+(int)(Math.random()*20));
            subjectRanking.set排名(""+(int)(Math.random()*20));
            subjectRanking.set排名百分位(""+Math.random()*100);
            subjectRanking.set评估等级(level[(int)(1+Math.random()*10)]);
        }
        return rankingList;
    }

    private SubjectRankingGroup mockSubjectRankingData(){
        SubjectRankingGroup subjectRankingGroup = new SubjectRankingGroup();
        Map<String,List<SubjectRanking>> subjectRankingListMap = new HashMap<>();
        subjectRankingListMap.put("第四轮学科评估",subjectAnalysisService.getNew各学科第四轮学科排名情况());
        List<SubjectRanking> rankingList = subjectAnalysisService.getNew各学科第四轮学科排名情况();
        subjectRankingListMap.put("软科排名",mockSoftRanking(rankingList));
        subjectRankingGroup.setSubjectRankings(subjectRankingListMap);
        return subjectRankingGroup;
    }

    private TrendAnalysisGroup mockTrendAnalysisData(String subjectCode){
        //高层次人才
        String[] years = new String[]{"2016","2017","2018"};
        TrendAnalysisGroup group = new TrendAnalysisGroup();
        Map<String,List<TrendAnalysis>> trendAnalysisListMap = new HashMap<>();
        List<TrendAnalysis> trendAnalysisList = new ArrayList<>();
        for (String year : years) {
            TrendAnalysis analysis = new TrendAnalysis();
            analysis.setYear(year);
            Map<String,Object> paras = new HashMap<>();
            paras.put("year",year);
            paras.put("schoolcode",10593);
            List<Map> list = subjectAnalysisService.getNew高层次人才统计(paras);
            Map<String, Double> valueMap = calulateAvgAndSelfValue(subjectCode,list);
            analysis.setValue(valueMap.get("val"));
            trendAnalysisList.add(analysis);
        }
        trendAnalysisListMap.put("高层次人才",trendAnalysisList);

        String[] items = new String[]{"科研项目","成果获奖","学术论文","人才培养","平台建设"};
        for (String item : items) {
            List<TrendAnalysis> dataList = new ArrayList<>();
            for (String year : years) {
                TrendAnalysis trendAnalysis = new TrendAnalysis();
                trendAnalysis.setYear(year);
                trendAnalysis.setValue(50*Math.random()+50);
                dataList.add(trendAnalysis);
            }
            trendAnalysisListMap.put(item,dataList);
        }
        group.setDatas(trendAnalysisListMap);
        return group;
    }

    private Echart getRadarData(String subjectCode){

        RadarEchart echart = new RadarEchart();

        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        List<Map> subjectInfo = subjectAnalysisService.getNewSubjectInfo(paras);
        String showName = "";
        if(subjectInfo.size() > 0){
            Map map = subjectInfo.get(0);
            showName = map.get("code").toString()+"-"+map.get("name").toString();
            echart.setLegend(Arrays.asList(showName, "本校均值"));
        }

        //获取高层次人才2018的数据
        paras.put("year",2018);
        paras.put("schoolcode",10593);
        List<Map> list = subjectAnalysisService.getNew高层次人才统计(paras);
        Map<String, Double> valueMap = calulateAvgAndSelfValue(subjectCode,list);
        Double val = valueMap.get("val");
        Double avg = valueMap.get("avg");

        List<RadarIndicator> radarIndicators = new ArrayList<>();

        RadarIndicator radarIndicator1 = new RadarIndicator();
        radarIndicator1.setName("高层次人才");
        radarIndicator1.setMax(val > avg ? val+50 : avg+50);
        radarIndicators.add(radarIndicator1);

        RadarIndicator radarIndicator2 = new RadarIndicator();
        radarIndicator2.setName("科研项目");
        radarIndicator2.setMax(110.0);
        radarIndicators.add(radarIndicator2);

        RadarIndicator radarIndicator3 = new RadarIndicator();
        radarIndicator3.setName("成果获奖");
        radarIndicator3.setMax(110.0);
        radarIndicators.add(radarIndicator3);

        RadarIndicator radarIndicator4 = new RadarIndicator();
        radarIndicator4.setName("学术论文");
        radarIndicator4.setMax(110.0);
        radarIndicators.add(radarIndicator4);

        RadarIndicator radarIndicator5 = new RadarIndicator();
        radarIndicator5.setName("人才培养");
        radarIndicator5.setMax(110.0);
        radarIndicators.add(radarIndicator5);

        RadarIndicator radarIndicator6 = new RadarIndicator();
        radarIndicator6.setName("平台建设");
        radarIndicator6.setMax(110.0);
        radarIndicators.add(radarIndicator6);

        echart.setIndicators(radarIndicators);

        List<RadarSeries> radarSeries = new ArrayList<>();

        RadarSeries series1 = new RadarSeries();
        series1.setName(showName);
        List<Double> values1 = new ArrayList<>();
        values1.add(val);
        values1.add(65.0);
        values1.add(75.0);
        values1.add(85.0);
        values1.add(70.0);
        values1.add(60.0);
        series1.setValue(values1);
        radarSeries.add(series1);

        RadarSeries series2 = new RadarSeries();
        series2.setName("本校均值");
        List<Double> values2 = new ArrayList<>();
        values2.add(avg);
        values2.add(60.0);
        values2.add(70.0);
        values2.add(80.0);
        values2.add(75.0);
        values2.add(65.0);
        series2.setValue(values2);
        radarSeries.add(series2);

        echart.setSeries(radarSeries);

        return echart;
    }

    private Echart getRectangleData(String subjectCode){
        RectangleEchart echart = new RectangleEchart();
        List<RectangleSeries> seriess = new ArrayList<>();

        Map<String,Object> paras = new HashMap<>();
        paras.put("subjectCode",subjectCode);
        paras.put("year","2018");
        paras.put("schoolcode",10593);
        List<Map> mapList = subjectAnalysisService.getNew高层次人才统计(paras);
        List<Map> list = calculateAllSubjectValue(mapList);
        for (Map map : list) {
            RectangleSeries series = new RectangleSeries();
            series.setName(map.get("subject_name").toString());
            series.setValue(Double.valueOf(map.get("val").toString()));
            seriess.add(series);
        }
        echart.setSeries(seriess);

        return echart;
    }

}
