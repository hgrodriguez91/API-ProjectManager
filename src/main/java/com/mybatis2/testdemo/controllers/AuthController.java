package com.mybatis2.testdemo.controllers;

import com.mybatis2.testdemo.config.JwtUtils;
import com.mybatis2.testdemo.entity.User;
import com.mybatis2.testdemo.mappers.userMapper;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin(origins = "*")
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
    public HashMap<String, String> createAuthenticationToken(@RequestBody User user) throws Exception {

        HashMap<String, String> map = new HashMap<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User usuario = userMapper.findByUsername(user.getUsername());
       if(usuario!=null && encoder.matches(user.getPassword(),usuario.getPassword())){
           UserDetails userDetails = userService.loadUserByUsername(usuario.getUsername());
           map.put("token", utils.generarToken(userDetails));
           return map;
       }
       map.put("error","CREDENCIALES INVALIDAS");
       return map;
    }


    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "User Logout successfully";
    }


}
