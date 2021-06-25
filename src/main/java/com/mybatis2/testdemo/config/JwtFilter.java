package com.mybatis2.testdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    @Qualifier("userService")
    UserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        final String headerRequest = req.getHeader("Authorization");
        if(headerRequest!=null){

            if(isValid(headerRequest)){
                String username = jwtUtils.getClaimsforToken(headerRequest.substring(7)).getSubject();
                UserDetails user = userService.loadUserByUsername(username);
                if(user!=null){
                    UsernamePasswordAuthenticationToken usertoken = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usertoken);

                }
            }

        }
        filterChain.doFilter(req, res);
    }

    private boolean isValid(String token){
        if(token.startsWith("Bearer")&& !jwtUtils.isExpired(token.substring(7))){
           return true;
        }return false;
    }
}
