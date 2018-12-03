package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectOverView {
    SubjectInfo subjectInfo;//学科信息
    List<SubjectCompare> subjectCompare;//学科数据对比
    SubjectRankingGroup subjectRankingGroups;//学科排名
    TrendAnalysisGroup trendAnalysisGroup;//趋势分析
}
