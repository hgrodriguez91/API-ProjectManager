package com.mybatis2.testdemo.service;

import com.mybatis2.testdemo.entity.Roles;
import com.mybatis2.testdemo.entity.User;
import com.mybatis2.testdemo.mappers.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userService")
public class userService  implements UserDetailsService {

   @Autowired
   com.mybatis2.testdemo.mappers.userMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userMapper.findByUsername(username);
            if(user!= null){
                ArrayList<GrantedAuthority> auths = new ArrayList<>();
                for(Roles r : user.getRoles()){
                    auths.add(new SimpleGrantedAuthority(r.getRole()));
                }
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(),true,true,true, true, auths);
                return userDetails;

    }else{
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
    }
}
