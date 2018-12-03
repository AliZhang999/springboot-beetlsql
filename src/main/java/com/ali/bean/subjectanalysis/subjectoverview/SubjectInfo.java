package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/*
    学科全景/学科信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInfo {
    private String subjectCode;
    private String subjectName;
    private String 授权级别;//授权级别
    private String 软科排名;//软科排名
    private Map<String,JYBSubjectAssetmentResult> jybSubjectAssetmentResults;//教育部评估结果
}
