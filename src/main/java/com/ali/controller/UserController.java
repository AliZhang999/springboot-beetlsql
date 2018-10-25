package com.ali.controller;

import com.ali.bean.User;
import com.ali.repository.UserRepository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    SQLManager sqlManager;

    @Autowired
    UserRepository userRepository;

    @GetMapping("sqlmanager/{id}")
    public User getSqlManagerUserById(@PathVariable("id") Integer id){
        User user = sqlManager.single(User.class, id);
        return user;
    }

    @GetMapping("repository/{id}")
    public User getRepositoryUserById(@PathVariable("id") Integer id){
        User user = userRepository.single(id);
        return user;
    }

    @GetMapping("sqlmanager/md")
    public List<User> findAllSqlmanagerUser(){
        List<User> users = sqlManager.select("user.selectAll", User.class);
        return users;
    }

    @GetMapping("repository/md")
    public List<User> findAllRepositoryUser(){
        List<User> users = userRepository.selectAll();
        return users;
    }

    @GetMapping("gentoconsole")
    public void genToConsole() throws Exception {
        sqlManager.genPojoCodeToConsole("user");
        System.out.println("==============");
        sqlManager.genSQLTemplateToConsole("user");
    }

}
