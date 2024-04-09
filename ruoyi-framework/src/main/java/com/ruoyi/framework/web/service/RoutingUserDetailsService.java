package com.ruoyi.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class RoutingUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService webUserDetailsService;

    @Autowired
    @Qualifier("appUserDetailsServiceImpl")
    private UserDetailsService appUserDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 根据请求来判断使用哪个UserDetailsService
        if (isAppRequest(request)) {
            System.out.println("App request");
            return appUserDetailsService.loadUserByUsername(username);
        } else {
            System.out.println("Web request");
            return webUserDetailsService.loadUserByUsername(username);
        }
    }

    private boolean isAppRequest(HttpServletRequest request) {
        // 在这里定义如何判断请求是否来自APP
        // 例如，可以检查请求头、参数等
        return "APP".equals(request.getHeader("Client-Type"));
//        return false; // 示例逻辑
    }
}
