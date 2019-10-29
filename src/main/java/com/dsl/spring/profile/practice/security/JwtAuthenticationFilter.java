/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved
 */

package com.dsl.spring.profile.practice.security;

import com.dsl.spring.profile.practice.service.UserService;
import com.dsl.spring.profile.practice.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dsl.spring.profile.practice.constant.SecurityConstant.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String username = null;
        String jwtToken = null;

        String header = request.getHeader(AUTHORIZATION_HEADER);
        if(header != null && header.startsWith(BEARER))
        {
            jwtToken = header.substring(7);
            try
            {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (ExpiredJwtException e)
            {
                System.out.println("Token is expired");
            }
        }
        else
        {
            System.out.println("Bearer not found");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if(jwtTokenUtil.isTokenValid(jwtToken, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
