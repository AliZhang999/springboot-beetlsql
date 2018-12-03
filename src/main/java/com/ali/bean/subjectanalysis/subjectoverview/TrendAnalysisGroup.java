package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/*
    学科全景/趋势分析组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendAnalysisGroup {
    private Map<String,List<TrendAnalysis>> datas;
}
