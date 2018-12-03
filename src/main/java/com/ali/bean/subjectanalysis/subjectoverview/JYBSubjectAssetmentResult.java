package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    教育部学科评估结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JYBSubjectAssetmentResult {
    private String 评估批次;//第几轮
    private String 排名;//分数
    private String 排名百分位;//比例
    private String 评估等级;//结果
    private String 参评高校数;//参加高校数
}
