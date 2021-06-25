package com.mybatis2.testdemo.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtUtils implements Serializable {

    private final String SECRET= "mysecret";

    public String generarToken(UserDetails user){
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return "Bearer "+Jwts.builder().claim("authorities",authorities).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public boolean isExpired(String token){
         Claims claims = getClaimsforToken(token);
         Date date = claims.getExpiration();
          return date.before(new Date());
    }



    public Claims getClaimsforToken( String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
