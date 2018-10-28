package com.ali.repository.indicator2016;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

@SqlResource("indicator2016")
public interface Indicator2016Repository extends BaseMapper {
    List<Map> selectAll();

    List<Map> select专业评估$七学生发展$7_1学生基本情况data();
}
