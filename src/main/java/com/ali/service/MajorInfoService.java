package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MajorInfoService extends TopService {

    public List<Map> getMajorList(Map<String, Object> paras) {
        paras.put("sqlNoParam","true");
        return getDataByMethodNameWithParams("get校内专业列表",paras);
    }

    public Map<String, List<Map>> getMajorInfo(Map<String,Object> paras){
        Map<String,List<Map>> getMajorInfo = new HashMap<>();

        getMajorInfo.put("专业信息",专业基本信息(paras));
        getMajorInfo.put("教职工",getDataByMethodNameWithParams("get专业内教职工列表",paras));
        getMajorInfo.put("教学成果",getDataByMethodNameWithParams("get专业内教学成果列表",paras));
        getMajorInfo.put("开设课程",getDataByMethodNameWithParams("get专业内开设课程列表",paras));
        getMajorInfo.put("实习基地",getDataByMethodNameWithParams("get专业内实习基地列表",paras));
        getMajorInfo.put("本科生",getDataByMethodNameWithParams("get专业内本科生列表",paras));
        getMajorInfo.put("各类竞赛",getDataByMethodNameWithParams("get专业内各类竞赛列表",paras));
        getMajorInfo.put("专业比赛",getDataByMethodNameWithParams("get专业内专业比赛列表",paras));
        getMajorInfo.put("学术论文",getDataByMethodNameWithParams("get专业内学术论文列表",paras));

        return getMajorInfo;
    }

    private List<Map> 专业基本信息(Map<String,Object> paras){
        List<Map> 专业基本信息 = getDataByMethodNameWithParams("get专业基本信息",paras);
        if(专业基本信息.size()>0){
            Map 专业信息 = 专业基本信息.get(0);
            String 标准代码 = (String)专业信息.get("专业代码");
            try{
                String 专业类代码 = 标准代码.substring(0,2);
                String 专业学科类别代码 = 标准代码.substring(0, 4);
                专业信息.put("专业类", get专业学科类别(专业类代码,1));
                专业信息.put("学科门类", get专业学科类别(专业学科类别代码,2));
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println(标准代码);
            }
        }
        return 专业基本信息;
    }

    private String get专业学科类别(String 校内代码,int type){
        if(type==1){
            Map<String,String> 专业类 = new HashMap<>();
            专业类.put("01", "哲学");
            专业类.put("02", "经济学");
            专业类.put("03", "法学");
            专业类.put("04", "教育学");
            专业类.put("05", "文学");
            专业类.put("06", "历史学");
            专业类.put("07", "理学");
            专业类.put("08", "工学");
            专业类.put("09", "农学");
            专业类.put("10", "医学");
            专业类.put("12", "管理学");
            专业类.put("13", "艺术学");
            return 专业类.get(校内代码);
        }else if(type==2){
            Map<String,String> 学科门类 = new HashMap<>();
            学科门类.put("0101", "哲学类");

            学科门类.put("0201", "经济学类");
            学科门类.put("0202", "财政学类");
            学科门类.put("0203", "金融学类");
            学科门类.put("0204", "经济与贸易类");

            学科门类.put("0301", "法学类");
            学科门类.put("0302", "政治学类");
            学科门类.put("0303", "社会学类");
            学科门类.put("0304", "民族学类");
            学科门类.put("0305", "马克思主义理论类");
            学科门类.put("0306", "公安学类");

            学科门类.put("0401", "教育学类");
            学科门类.put("0402", "体育学类");

            学科门类.put("0501", "中国语言文学类");
            学科门类.put("0502", "外国语言文学类");
            学科门类.put("0503", "新闻传播学类");

            学科门类.put("0601", "历史学类");

            学科门类.put("0701", "数学类");
            学科门类.put("0702", "物理学类");
            学科门类.put("0703", "化学类");
            学科门类.put("0704", "天文学类");
            学科门类.put("0705", "地理科学类");
            学科门类.put("0706", "大气科学类");
            学科门类.put("0707", "海洋科学类");
            学科门类.put("0708", "地球物理学类");
            学科门类.put("0709", "地质学类");
            学科门类.put("0710", "生物科学类");
            学科门类.put("0711", "心理学类");
            学科门类.put("0712", "统计学类");

            学科门类.put("0801", "力学类");
            学科门类.put("0802", "机械类");
            学科门类.put("0803", "仪器类");
            学科门类.put("0804", "材料类");
            学科门类.put("0805", "能源动力类");
            学科门类.put("0806", "电气类");
            学科门类.put("0807", "电子信息类");
            学科门类.put("0808", "自动化类");
            学科门类.put("0809", "计算机类");
            学科门类.put("0810", "土木类");
            学科门类.put("0811", "水利类");
            学科门类.put("0812", "测绘类");
            学科门类.put("0813", "化工与制药类");
            学科门类.put("0814", "地质类");
            学科门类.put("0815", "矿业类");
            学科门类.put("0816", "纺织类");
            学科门类.put("0817", "轻工类");
            学科门类.put("0818", "交通运输类");
            学科门类.put("0819", "海洋工程类");
            学科门类.put("0820", "航空航天类");
            学科门类.put("0821", "兵器类");
            学科门类.put("0822", "核工程类");
            学科门类.put("0823", "农业工程类");
            学科门类.put("0824", "林业工程类");
            学科门类.put("0825", "环境科学与工程类");
            学科门类.put("0826", "生物医学工程类");
            学科门类.put("0827", "食品科学与工程类");
            学科门类.put("0828", "建筑类");
            学科门类.put("0829", "安全科学与工程类");
            学科门类.put("0830", "生物工程类");
            学科门类.put("0831", "公安技术类");

            学科门类.put("0901", "植物生产类");
            学科门类.put("0902", "自然保护与环境生态类");
            学科门类.put("0903", "动物生产类");
            学科门类.put("0904", "动物医学类");
            学科门类.put("0905", "林学类");
            学科门类.put("0906", "水产类");
            学科门类.put("0907", "草学类");

            学科门类.put("1001", "基础医学类");
            学科门类.put("1002", "临床医学类");
            学科门类.put("1003", "口腔医学类");
            学科门类.put("1004", "公共卫生与预防医学类");
            学科门类.put("1005", "中医学类");
            学科门类.put("1006", "中西医结合类");
            学科门类.put("1007", "药学类");
            学科门类.put("1008", "中药学类");
            学科门类.put("1009", "法医学类");
            学科门类.put("1010", "医学技术类");
            学科门类.put("1011", "护理学类");

            学科门类.put("1201", "管理科学与工程类");
            学科门类.put("1202", "工商管理类");
            学科门类.put("1203", "农业经济管理类");
            学科门类.put("1204", "公共管理类");
            学科门类.put("1205", "图书情报与档案管理类");
            学科门类.put("1206", "物流管理与工程类");
            学科门类.put("1207", "工业工程类");
            学科门类.put("1208", "电子商务类");
            学科门类.put("1209", "旅游管理类");

            学科门类.put("1301", "艺术学理论类");
            学科门类.put("1302", "音乐与舞蹈学类");
            学科门类.put("1303", "戏剧与影视学类");
            学科门类.put("1304", "美术学类");
            学科门类.put("1305", "设计学类");
            return 学科门类.get(校内代码);
        }
        return "";
    }

}
