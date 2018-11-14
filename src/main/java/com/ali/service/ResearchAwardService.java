package com.ali.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResearchAwardService extends TopService {

    public Map<String, Map<String, Map<String, Object>>> get获奖统计(Map<String, Object> paras) {
        String[] items = {"特等奖","一等奖","二等奖","三等奖","国家自然科学奖","国家技术发明奖","国家科技进步奖","国家级人文社科奖",
                "国家最高科学技术奖","教育部高校科研成果奖（科学技术、人文社科）","省（市、自治区）政府自然科学奖","省（市、自治区）政府技术发明奖",
                "省（市、自治区）政府科技进步奖","省（市、自治区）政府哲学社科奖","省（市、自治区）政府国际和国外奖励","国际科学技术合作奖","国家何梁何利科技奖",
                "国际和国外奖励"
        };
        return backSubjectData(paras,items,"get获奖统计");
    }
}
