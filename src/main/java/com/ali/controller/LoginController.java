package com.ali.controller;

import com.ali.bean.LoginModel;
import com.ali.bean.LoginSuccessModel;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("login")
    public ResponseEntity<LoginSuccessModel> login(/*@RequestParam(value = "username",required=false) String username, @RequestParam(value = "password",required=false) String password*/@RequestBody @ApiParam(value = "用户登录信息", required = true) LoginModel model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
        Map<String,Object> result = new HashMap<>();
        try {
            subject.login(token);
            //result.put("token",subject.getSession().getId());
            //result.put("msg","登录成功");
            //logger.info("登录成功");
        } catch (IncorrectCredentialsException e) {
            result.put("msg", "密码错误");
            return ResponseEntity.badRequest().build();
        }   catch (LockedAccountException e) {
            result.put("msg", "登录失败，该用户已被冻结");
            return ResponseEntity.badRequest().build();
        } catch (AuthenticationException e) {
            result.put("msg", "该用户不存在");
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        LoginSuccessModel success = new LoginSuccessModel();
        success.setUuid(subject.getPrincipal().toString());
        success.setToken(request.getSession().getId());
        success.setName(model.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @GetMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> result = new HashMap<>();
        if (subject.isAuthenticated()){
            result.put("token",subject.getSession().getId());
            subject.logout();
            result.put("msg", "退出登录成功");
            logger.info("退出登录");
        }else{
            result.put("msg", "未登录，请先登录");
        }
        return result.toString();
    }
}
