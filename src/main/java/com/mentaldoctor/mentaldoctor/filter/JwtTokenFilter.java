package com.mentaldoctor.mentaldoctor.filter;

import com.mentaldoctor.mentaldoctor.service.impl.UserServiceImpl;
import com.mentaldoctor.mentaldoctor.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取JWT Token
        String tokenHeader=request.getHeader(JwtTokenUtil.HEADER_STRING);
        //验证JWT 前缀
        if(tokenHeader!=null&&tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)){
            //获取JWT token
            String authToken=tokenHeader.substring(JwtTokenUtil.TOKEN_PREFIX.length());
            String username=JwtTokenUtil.getUsernameFromToken(authToken);
            if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=userService.loadUserByUsername(username);
                //验证Token是否有效
                if(JwtTokenUtil.validateToken(authToken,userDetails)){
                    log.debug(username+" token valid");
                    //进行无状态登陆
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
