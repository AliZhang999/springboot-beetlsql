package com.ali.bean.subjectanalysis.subjectdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RadarSeries {
    private List<Double> value;
    private String name;
}
