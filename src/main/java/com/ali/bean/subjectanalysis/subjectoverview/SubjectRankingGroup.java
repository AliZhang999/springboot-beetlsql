package com.ali.bean.subjectanalysis.subjectoverview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/*
    学科全景/学科排名
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRankingGroup {
    private Map<String,List<SubjectRanking>> subjectRankings;
}
