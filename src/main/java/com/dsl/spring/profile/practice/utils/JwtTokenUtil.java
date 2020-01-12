/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.spring.profile.practice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.dsl.spring.profile.practice.constant.SecurityConstant.*;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtTokenUtil
{
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(Authentication authentication)
    {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim(SCOPE, authorities)
                .signWith(HS512, SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        String username = getUsernameFromToken(token);
        return Objects.equals(userDetails.getUsername(), username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpirationDateFromToken(token).before(new Date());
    }
}
