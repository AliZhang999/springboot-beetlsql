package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
    学科全景/趋势分析
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendAnalysis {
    private String year;
    private Double value;
}
