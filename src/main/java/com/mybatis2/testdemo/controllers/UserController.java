package com.mybatis2.testdemo.controllers;
import com.mybatis2.testdemo.entity.User;
import com.mybatis2.testdemo.mappers.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private userMapper userMapper;

      @GetMapping
         @PreAuthorize("hasRole('ROLE_ADMIN')")
        public List<User> getAllUsers(){
       Object principal = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
          System.out.println(principal);
        return userMapper.findAll();
        }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User getUserById(@PathVariable Integer id){
        return userMapper.findUserByUserId(id);
     }


}
