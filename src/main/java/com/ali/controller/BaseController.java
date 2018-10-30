package com.ali.controller;

import com.ali.bean.User;
import com.ali.repository.base.BaseRepository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("base")
public class BaseController {

    @Autowired
    BaseRepository baseRepository;

    @Autowired
    SQLManager baseSQLManager;

    @GetMapping("sqlmanager/{id}")
    public User getSqlManagerUserById(@PathVariable("id") Integer id){
        User user = baseSQLManager.single(User.class, id);
        return user;
    }

    @GetMapping("repository/{id}")
    public User getRepositoryUserById(@PathVariable("id") Integer id){
        User user = baseRepository.single(id);
        return user;
    }

    @GetMapping("sqlmanager/all")
    public List<User> findAllSqlmanagerUser(){
        List<User> users = baseSQLManager.select("base.selectAll", User.class);
        return users;
    }

    @GetMapping("allSubject")//科学列表
    public List<Map> getAllSubject(){
        List<Map> subjects = baseSQLManager.select("base.allSubject", Map.class);
        return subjects;
    }

}
