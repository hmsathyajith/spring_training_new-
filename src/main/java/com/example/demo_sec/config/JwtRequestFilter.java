package com.example.demo_sec.config;

import com.example.demo_sec.service.CustomUserDetailsService;
import com.example.demo_sec.util.JwtUtil;
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

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            userName = jwtUtil.extractUsername(jwt);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request,response);
    }
}