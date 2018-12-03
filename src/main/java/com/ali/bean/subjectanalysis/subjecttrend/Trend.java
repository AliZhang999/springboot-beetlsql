package com.ali.bean.subjectanalysis.subjecttrend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trend {
    private String subjectCode;
    private List<TrendItem> trendItems;
}
