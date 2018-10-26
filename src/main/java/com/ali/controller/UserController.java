package com.ali.controller;

import com.ali.bean.User;
import com.ali.repository.beetltest.UserRepository;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("beetltest")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SQLManager beetltestSQLManager;

    @GetMapping("sqlmanager/{id}")
    public User getSqlManagerUserById(@PathVariable("id") Integer id){
        User user = beetltestSQLManager.single(User.class, id);
        return user;
    }

    @GetMapping("repository/{id}")
    public User getRepositoryUserById(@PathVariable("id") Integer id){
        User user = userRepository.single(id);
        return user;
    }

    @GetMapping("sqlmanager/all")
    public List<User> findAllSqlmanagerUser(){
        List<User> users = beetltestSQLManager.select("user.selectAll", User.class);
        return users;
    }

    @GetMapping("repository/all")
    public List<User> findAllRepositoryUser(){
        List<User> users = userRepository.selectAll();
        return users;
    }

    @GetMapping("gentoconsole")
    public void genToConsole() throws Exception {
        beetltestSQLManager.genPojoCodeToConsole("user");
        System.out.println("==============");
        beetltestSQLManager.genSQLTemplateToConsole("user");
    }

}
