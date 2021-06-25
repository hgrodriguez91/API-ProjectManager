package com.mybatis2.testdemo.controllers;

import com.mybatis2.testdemo.config.JwtUtils;
import com.mybatis2.testdemo.entity.User;
import com.mybatis2.testdemo.mappers.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    JwtUtils utils;

    @Autowired
    @Qualifier("userService")
    UserDetailsService userService;

    @Autowired
    userMapper userMapper;

    @PostMapping("/login")


    public String createAuthenticationToken(@RequestBody User user) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User usuario = userMapper.findByUsername(user.getUsername());
       if(usuario!=null && encoder.matches(user.getPassword(),usuario.getPassword())){
           UserDetails userDetails = userService.loadUserByUsername(usuario.getUsername());
           return utils.generarToken(userDetails);
       }
        return "Credenciales Invalidas";
    }

    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "User Logout successfully";
    }


}
