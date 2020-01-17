package com.fly.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.demo.entity.User;
import com.fly.demo.service.UserService;

/**
 * UserController
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("user")
public class UserController
{
    @Autowired
    UserService userService;
    
    @ResponseBody
    @RequestMapping(value = "/view/{id}")
    public User view(@PathVariable Long id)
    {
        return userService.queryById(id);
    }
    
    @ResponseBody
    @RequestMapping(value = "/list/{pageNo}")
    public List<User> list(@PathVariable Integer pageNo, @RequestParam(defaultValue = "5") int pageSize)
    {
        pageSize = Math.max(pageSize, 2);
        return userService.queryForPagination(new User(), pageNo, pageSize).getItems();
    }
    
    @ResponseBody
    @RequestMapping(value = "/all")
    public List<User> all()
    {
        return userService.queryAll();
    }
}