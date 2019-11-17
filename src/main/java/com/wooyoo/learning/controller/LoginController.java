package com.wooyoo.learning.controller;

import com.wooyoo.learning.dao.domain.User;
import com.wooyoo.learning.dao.mapper.ProductMapper;
import com.wooyoo.learning.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{userName}/{pwd}")
    public User login(@PathVariable("userName")
                               String userName, @PathVariable("pwd") String pwd){
        String str = "";
       User user =  userMapper.selectByLogin(userName,pwd);
        return user;
    }

    @GetMapping("checkUser/{userName}")
    public Map checkUser(@PathVariable("userName")String userName){
        Map<String, String> map = new HashMap<String,String>();
        User user =  userMapper.selectByUserName(userName);
        if(user==null){
            map.put("message", "fail");
        }else{
            map.put("message", "success");
            map.put("balance", String.valueOf(user.getBalance()));
        }
        return map;
    }
}
