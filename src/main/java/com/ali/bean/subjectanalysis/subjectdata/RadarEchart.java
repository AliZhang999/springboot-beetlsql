package com.ali.bean.subjectanalysis.subjectdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    雷达图
    echarts实例：
    option = {
        title: {
            text: '学科雷达图'
        },
        tooltip: {},
        legend: {
            data: ['0101-哲学', '本校均值']
        },
        radar: {
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
               }
            },
            indicator: [
               { name: '高层次人才', max: 6500},
               { name: '科研项目', max: 16000},
               { name: '成果获奖', max: 30000},
               { name: '学术论文', max: 38000},
               { name: '人才培养', max: 52000},
               { name: '平台建设', max: 25000}
            ]
        },
        series: [{
            name: '学科值 vs 本校均值',
            type: 'radar',
            data : [
                {
                    value : [4300, 10000, 28000, 35000, 50000, 19000],
                    name : '0101-哲学'
                },
                {
                    value : [5000, 14000, 28000, 31000, 42000, 21000],
                    name : '本校均值'
                }
            ]
        }]
    };
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RadarEchart extends Echart{
    private List<String> legend;
    private List<RadarIndicator> indicators;
    private List<RadarSeries> series;
}
