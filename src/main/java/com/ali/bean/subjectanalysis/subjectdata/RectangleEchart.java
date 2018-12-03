package com.ali.bean.subjectanalysis.subjectdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    矩形图
    echarts示例：
    option = {
        series: [{
            type: 'treemap',
            data: [{
                name: '哲学',            // First tree
                value: 10
            },{
                name: '化学',            // Second tree
                value: 20
            },{
                name: '物理学',            // Second tree
                value: 20
            }
            ]
        }]
    }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RectangleEchart extends Echart{
    private List<RectangleSeries> series;
}
