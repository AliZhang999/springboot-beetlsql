package com.ali.repository.master2016;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("master2016")
public interface Master2016Repository extends BaseMapper {
    int selectAll();
}
