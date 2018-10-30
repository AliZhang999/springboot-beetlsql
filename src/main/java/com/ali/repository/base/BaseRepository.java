package com.ali.repository.base;

import com.ali.bean.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("base")
public interface BaseRepository extends BaseMapper<User> {
    List<User> selectAll();
}
