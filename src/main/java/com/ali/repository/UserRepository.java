package com.ali.repository;

import com.ali.bean.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("user")
public interface UserRepository extends BaseMapper<User> {
    List<User> selectAll();
}
