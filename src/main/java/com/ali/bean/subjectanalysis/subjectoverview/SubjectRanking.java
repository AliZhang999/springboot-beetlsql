package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    学科全景/学科排名
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRanking {
    private String 学科代码;
    private String 学科名称;
    private String 评估等级;
    private String 参评高校数;
    private String 排名百分位;
    private String 排名;
}
