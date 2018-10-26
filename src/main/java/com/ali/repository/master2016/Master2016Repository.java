package com.ali.repository.master2016;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

@SqlResource("master2016")
public interface Master2016Repository extends BaseMapper {
    List<Map> selectAll();
}
